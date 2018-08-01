package com.pos.service.impl;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.pos.manage.model.SyncPsaiceCreditDataModel;
import com.pos.manage.service.QuartzPsaiceCreditSyncService;
import com.rongdu.cashloan.manage.utils.FileUtil;
import com.rongdu.cashloan.manage.utils.GZIPUtil;
import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.common.exception.BussinessException;
import com.wz.cashloan.core.common.util.DateUtil;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hui on 08/10/2017.
 */
@Service("quartzPsaiceCreditSyncService")
public class QuartzPsaiceCreditSyncServiceImpl implements QuartzPsaiceCreditSyncService{

    private static final Logger logger = LoggerFactory.getLogger(QuartzPsaiceCreditSyncServiceImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    // endpoint以杭州为例，其它region请按实际情况填写
    @Value("#{conf['repayment.plan.endpoint']}")
    String endpoint;
    String accessKeyId;
    String accessKeySecret;
    @Value("#{conf['repayment.plan.bucketName']}")
    String bucketName;
    @Value("#{conf['repayment.plan.basepath']}")
    String basepath;
    String filepath;

    String idcoop;

    String idcoop_data;

    @Value("#{conf['repayment.plan.idpre']}")
    String idpre;

    int selectCount = 1000;

    @Value("#{conf['repayment.plan.select.count']}")
    public void setSelectCount(String selectCount) {
        try {
            this.selectCount = Integer.parseInt(selectCount);
        }catch (NumberFormatException e){
            logger.error("配置文件 select count is error");
        }
    }

    private String LOG_PATH;

    private File LOG_FILE;

    private File LOG_CK_FILE;

    private File LOG_OK_FILE;

    private File LOG_TARGET_FILE;

    private String timeStr;

    private String uploadPath;

    public boolean init() throws IOException {
        try {
            filepath = Global.getValue("psaice_upload_data_dir");
            accessKeyId = Global.getValue("psaice_upload_data_keyid");
            accessKeySecret = Global.getValue("psaice_upload_data_keysecret");
            idcoop = Global.getValue("psaice_upload_data_idcoop");

            idcoop_data = Global.getValue("psaice_upload_data_idcoop_data");


            uploadPath = basepath + idcoop + "/";


            logger.info("== 诚安 配置 {},{}", filepath, idcoop);
            logger.info("== 诚安 配置 {},{}", accessKeyId, accessKeySecret);
            logger.info("== 诚安 配置 {}", uploadPath);
            logger.info("== 诚安 配置  idcoop_data {}", idcoop_data);

            if (StringUtils.isEmpty(filepath) || StringUtils.isEmpty(accessKeyId) || StringUtils.isEmpty(accessKeySecret) || StringUtils.isEmpty(idcoop)){
                throw new BussinessException("未做数据库配置或不完整");
            }

            timeStr = DateUtil.dateStr7(new Date());
            this.LOG_PATH = filepath + "/" + timeStr + "/";
            //清除旧数据
            File logpath = new File(this.LOG_PATH);
            if(logpath.exists()){
                FileUtil.deleteDir(logpath);   //清空目录文件
            }
            if(!logpath.exists()){
                logpath.mkdirs();  //创建目录
            }
            logger.info("=== LOG_PATH {}", this.LOG_PATH);

            this.LOG_FILE = new File(this.LOG_PATH + "REPAYMENT_PLAN_"+ timeStr +".txt");
            this.LOG_CK_FILE = new File(this.LOG_PATH + "REPAYMENT_PLAN_"+ timeStr +"_CK.txt");
            this.LOG_OK_FILE = new File(this.LOG_PATH + timeStr +"_OK.txt");
            this.LOG_TARGET_FILE = new File(this.LOG_PATH + timeStr +".tar.gz");

            //创建文件
            this.LOG_FILE.createNewFile();
            this.LOG_CK_FILE.createNewFile();
            this.LOG_OK_FILE.createNewFile();

            return true;
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public PutObjectResult uploadPlanTask() throws Exception {

        init();

        int total = this.countRepayPlan();//总查询量
        int size = selectCount;//每次查询的数据量
        int index = 0;//查询中间量
        List<SyncPsaiceCreditDataModel> list = null;
        while (total > index){
            int start = Math.min(total,index);

            list = this.loadRepayPlan(start, size);

            //生成文件
            this.generateFile(list, this.LOG_FILE);

            index += size;
        }

        FileUtil.createLog(total+"", this.LOG_CK_FILE, false);

        if(list ==  null || list.size() < 1){
            logger.warn("====== upload plan task , list is null");
            return null;
        }

        //生成压缩包
        //打包
        GZIPUtil.compress(this.LOG_TARGET_FILE, false, this.LOG_FILE, this.LOG_CK_FILE);

        //上传文件
        PutObjectResult ret = this.uploadOSS(this.LOG_TARGET_FILE);

        //upload 标识文件
        this.uploadOSS(this.LOG_OK_FILE);


        //清理文件  保留压缩文件
        this.LOG_OK_FILE.delete();
        this.LOG_FILE.delete();
        this.LOG_CK_FILE.delete();

        return ret;
    }

    public PutObjectResult uploadOSS(File file){

        String key = this.getOSSKey(file);
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        PutObjectResult result;
        try {
            // 上传文件
            result = ossClient.putObject(bucketName, key, file);
        }catch (Exception e){
            logger.error("========upload oss is error ", e);
            throw e;
        }finally {
            // 关闭client
            ossClient.shutdown();
        }

        return result;
    }

//    private final String qSql2 = "SELECT br.id as `id`, " +
//            " b.create_time as `start_time`, " +
//            " b.id as `no_busb`,  " +
//            " br.id as `bill_id`,  " +
//            " br.state as `status`,  " +
//            " b.state as `bstatus`, " +
//            " br.repay_time as `due_date`,  " +
//            " br.amount as `amount`,  " +
//            " b.amount as `capital`,  " +
//            " b.interest as `interest`,  " +
//            " bde.interest_ratio AS `rate`,  " +
//            " blog.amount AS `real_amount`,  " +
//            " br.penalty_amout AS `real_penalty`,  " +
//            " br.penalty_day as `overdue_day`,  " +
//            " blog.repay_time as `repay_time` " +
//            " FROM cl_borrow_repay br  " +
//            "     LEFT JOIN cl_borrow b ON br.borrow_id = b.id " +
//            "     LEFT JOIN cl_borrow_detail bde ON br.borrow_id = bde.borrow_id " +
//            "     LEFT JOIN cl_borrow_repay_log blog ON br.id = blog.repay_id LIMIT ?, ?";


    private final String countSql = "SELECT count(1) " +
            "     FROM cl_borrow_repay br " +
            "     LEFT JOIN cl_borrow b ON br.borrow_id = b.id" +
            "     LEFT JOIN cl_borrow_detail bde ON br.borrow_id = bde.borrow_id" +
            "     LEFT JOIN cl_borrow_repay_log blog ON br.id = blog.repay_id";

    private final String qSql = "SELECT CONCAT('CZWX', br.id) as `id`, " +
            "            '' as `idcoop`,            " +
            "            b.create_time as `start_time`, " +
            "            b.ORDER_NO as `no_busb`, " +
            "            b.ORDER_NO as `bill_id`,  " +
            "            1 as `totalPeriod`," +
            "            1 as `period`," +
            "            (case b.state when '30' then '0' when '40' then '1' when '50' then '2' end) as `status` ,            " +
            "            br.repay_time as `due_date`,  " +
            "            br.amount as `amount`,  " +
            "            b.amount as `capital`,  " +
            "            b.interest as `interest`,  " +
            "            bde.interest_ratio AS `rate`,  " +
            "            blog.amount AS `real_amount`, " +
            "            b.amount as `real_repay_amt`," +
            "            b.interest as `real_repay_interest`, " +
            "            br.penalty_amout AS `real_penalty`," +
            "            blog.repay_time as `repay_time`," +
            "            br.penalty_day as `overdue_day`," +
            "            bde.overdue_penalty as `demurrage`," +
            "            now() as `extract_time`" +    //抽取时间
            "            FROM cl_borrow_repay br  " +
            "                LEFT JOIN cl_borrow b ON br.borrow_id = b.id " +
            "                LEFT JOIN cl_borrow_detail bde ON br.borrow_id = bde.borrow_id " +
            "                LEFT JOIN cl_borrow_repay_log blog ON br.id = blog.repay_id LIMIT ?, ?";

    private List<SyncPsaiceCreditDataModel> loadRepayPlan(final int start, int size){
        List list = jdbcTemplate.query(qSql, new Object[]{start, size}, new RowMapper<SyncPsaiceCreditDataModel>() {
            @Override
            public SyncPsaiceCreditDataModel mapRow(ResultSet rs, int i) throws SQLException {
                SyncPsaiceCreditDataModel plan = new SyncPsaiceCreditDataModel();
                plan.setId( rs.getString("id"));
                plan.setIdCoop(idcoop_data);
                plan.setStartTime(rs.getDate("start_time"));
                plan.setNoBusb(rs.getString("no_busb"));
                plan.setBillId(rs.getString("bill_id"));
                plan.setTotalPeriod(rs.getInt("totalPeriod"));   //总期数  1  不会分多期
                plan.setPeriod(rs.getInt("period"));

                plan.setStatus(rs.getInt("status"));

                plan.setDueDate(rs.getDate("due_date"));
                plan.setAmount(BigDecimal.valueOf(rs.getDouble("amount")));
                plan.setCapital(BigDecimal.valueOf(rs.getDouble("capital")));
                plan.setInterest(BigDecimal.valueOf(rs.getDouble("interest")));
                plan.setRate(BigDecimal.valueOf(rs.getDouble("rate")));   // 取总利率  包含 服务费利率 与 利息
                plan.setRealAmount(BigDecimal.valueOf(rs.getDouble("real_amount")));

                plan.setRealCapital(BigDecimal.valueOf(rs.getDouble("real_repay_amt")));   // 还款必要一次性还清  所以 实际还本金 利息  取 应还本金利息
                plan.setRealInterest(BigDecimal.valueOf(rs.getDouble("real_repay_interest")));
                plan.setRealPenalty(BigDecimal.valueOf(rs.getDouble("real_penalty")));
                plan.setRepayTime(rs.getDate("repay_time"));
                plan.setOverdueDay(rs.getInt("overdue_day"));
                plan.setDemurrage(BigDecimal.valueOf(rs.getDouble("real_penalty")));  // 逾期罚金 就是逾期费
                return plan;
            }
        });
        return list;
    }

    private int countRepayPlan(){
        return jdbcTemplate.queryForObject(countSql, Integer.class);
    }


    /**
     * 创建文件
     * @param list
     * @return
     * @throws Exception
     */
    public File generateFile(List<SyncPsaiceCreditDataModel> list, File file) throws Exception{
        Map<String, Object> logMap = new HashedMap();

        for (SyncPsaiceCreditDataModel plan : list){

            logMap.clear();

            logMap.put("id", plan.getId());
            logMap.put("id_coop", plan.getIdCoop());
            logMap.put("start_time", DateUtil.dateStr4(plan.getStartTime()));
            logMap.put("no_busb", plan.getNoBusb());
            logMap.put("bill_id", plan.getBillId());
            logMap.put("total_period", plan.getTotalPeriod());
            logMap.put("period", plan.getPeriod());
            logMap.put("status", plan.getStatus());
            logMap.put("due_date", DateUtil.dateStr4(plan.getDueDate()));
            logMap.put("amount", plan.getAmount().doubleValue());
            logMap.put("capital", plan.getCapital().doubleValue());
            logMap.put("interest", plan.getInterest().doubleValue());
            logMap.put("rate", plan.getRate().doubleValue());
            logMap.put("real_amount", plan.getRealAmount().doubleValue());
            logMap.put("real_capital", plan.getRealCapital().doubleValue());
            logMap.put("real_interest", plan.getRealInterest().doubleValue());
            logMap.put("real_penalty", plan.getRealPenalty().doubleValue());
            logMap.put("repay_time", DateUtil.dateStr4(plan.getRepayTime()));
            logMap.put("overdue_day", plan.getOverdueDay());
            logMap.put("demurrage", plan.getDemurrage().doubleValue());


            //logger.info("====json === {}", JSONObject.valueToString(logMap));

            FileUtil.createLog(JSONObject.valueToString(logMap), file, true);

        }

        return file;
    }



    private String getOSSKey(File file){
        return uploadPath + DateUtil.dateStr7(new Date()) + "/" + file.getName();
    }

    public class PutResult{
        public int code;
        public String msg;

        public PutResult(int code, String msg){
            this.code = code;
            this.msg = msg;
        }
    }

}
