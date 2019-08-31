package com.example.demo.primary.entity;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="show_a")
public class ShowA {
	@Id
	@Column(name="this_key")
	private int thisKey;
	@Column(name="best_show")
	private String bestShow;
	@Column(name="worst_show")
	private String worstShow;
	
	public ShowA() {
		
	}
	
	public ShowA(String bestShow, String worstShow) {
		Random r = new Random();
		this.thisKey = r.nextInt();
		this.bestShow = bestShow;
		this.worstShow = worstShow;
	}
	
	public int getThisKey() {
		return thisKey;
	}
	public void setThisKey(int thisKey) {
		this.thisKey = thisKey;
	}
	public String getBestShow() {
		return bestShow;
	}
	public void setBestShow(String bestShow) {
		this.bestShow = bestShow;
	}
	public String getWorstShow() {
		return worstShow;
	}
	public void setWorstShow(String worstShow) {
		this.worstShow = worstShow;
	}
	
}
