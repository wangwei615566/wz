package com.rongdu.cashloan.core.model;

import java.io.Serializable;

public class UserCardPicsForm implements Serializable{
	private String cardHandUrl;
	private String cardFaceUrl;
	private String cardOppUrl;
	
	public String getCardHandUrl() {
		return cardHandUrl;
	}
	public void setCardHandUrl(
			String cardHandUrl) {
		this.cardHandUrl = cardHandUrl;
	}
	public String getCardFaceUrl() {
		return cardFaceUrl;
	}
	public void setCardFaceUrl(String cardFaceUrl) {
		this.cardFaceUrl = cardFaceUrl;
	}
	public String getCardOppUrl() {
		return cardOppUrl;
	}
	public void setCardOppUrl(String cardOppUrl) {
		this.cardOppUrl = cardOppUrl;
	}

	
}
