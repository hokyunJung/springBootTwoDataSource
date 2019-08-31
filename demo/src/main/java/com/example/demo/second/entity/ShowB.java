package com.example.demo.second.entity;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="show_b")
public class ShowB {
	
	@Id
	private int thisKey;
	
	private String bestCase;
	
	private String worstCase;
	
	public ShowB() {
		
	}
	
	public ShowB(String a, String b) {
		Random r = new Random();
		this.thisKey = r.nextInt();
		this.bestCase=a;
		this.worstCase=b;
	}
	
	public int getThisKey() {
		return thisKey;
	}
	public void setThisKey(int thisKey) {
		this.thisKey = thisKey;
	}
	public String getBestCase() {
		return bestCase;
	}
	public void setBestCase(String bestCase) {
		this.bestCase = bestCase;
	}
	public String getWorstCase() {
		return worstCase;
	}
	public void setWorstCase(String worstCase) {
		this.worstCase = worstCase;
	}
	
	
	
}
