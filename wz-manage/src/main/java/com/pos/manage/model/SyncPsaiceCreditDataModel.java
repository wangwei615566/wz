package com.pos.manage.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hui on 08/10/2017.
 */
public class SyncPsaiceCreditDataModel {

    String id;
    String idCoop;
    Date startTime;
    String noBusb;
    String billId;
    int totalPeriod;
    int period;
    int status;
    Date dueDate;
    BigDecimal amount;
    BigDecimal capital;
    BigDecimal interest;
    BigDecimal rate;
    BigDecimal realAmount;
    BigDecimal realCapital;
    BigDecimal realInterest;
    BigDecimal realPenalty;
    Date repayTime;
    int overdueDay;
    BigDecimal demurrage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCoop() {
        return idCoop;
    }

    public void setIdCoop(String idCoop) {
        this.idCoop = idCoop;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getNoBusb() {
        return noBusb;
    }

    public void setNoBusb(String noBusb) {
        this.noBusb = noBusb;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public int getTotalPeriod() {
        return totalPeriod;
    }

    public void setTotalPeriod(int totalPeriod) {
        this.totalPeriod = totalPeriod;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getRealCapital() {
        return realCapital;
    }

    public void setRealCapital(BigDecimal realCapital) {
        this.realCapital = realCapital;
    }

    public BigDecimal getRealInterest() {
        return realInterest;
    }

    public void setRealInterest(BigDecimal realInterest) {
        this.realInterest = realInterest;
    }

    public BigDecimal getRealPenalty() {
        return realPenalty;
    }

    public void setRealPenalty(BigDecimal realPenalty) {
        this.realPenalty = realPenalty;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public int getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(int overdueDay) {
        this.overdueDay = overdueDay;
    }

    public BigDecimal getDemurrage() {
        return demurrage;
    }

    public void setDemurrage(BigDecimal demurrage) {
        this.demurrage = demurrage;
    }

}
