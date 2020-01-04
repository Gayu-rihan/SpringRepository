package com.iprimed.serivces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements ICoach {
	
//	Constructor dependencies injection
//	public IFortuneService fortuneService;
//	public CricketCoach(IFortuneService fortuneService) {
//		this.fortuneService = fortuneService;
//	}
	
	private IFootballCoach footballCoach;
	private long contactNo;
	private String email;

	@Autowired
	public CricketCoach(IFootballCoach footballCoach) {
		this.footballCoach = footballCoach;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	private IFortuneService fortuneService;
	
	public IFortuneService getFortuneService() {
		return fortuneService;
	}

	public void setFortuneService(IFortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	public String getDailyWorkout() {
		return "Do net practice for 30 mins";
	}

	public String getFortune() {
		return fortuneService.getFortune();
	}
	
	public String getData() {
		return footballCoach.getData();
	}

}
