package com.iprimed.serivces;

public class CricketCoach implements ICoach {
	
//	Constructor dependencies injection
//	public IFortuneService fortuneService;
//	public CricketCoach(IFortuneService fortuneService) {
//		this.fortuneService = fortuneService;
//	}

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

}
