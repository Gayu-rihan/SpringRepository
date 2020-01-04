package com.iprimed.ui;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iprimed.serivces.ICoach;
import com.iprimed.serivces.IFortuneService;

public class MainApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ICoach coach = context.getBean("cricketCoach", ICoach.class);
		IFortuneService fortune = context.getBean("fortuneService", IFortuneService.class);
		
		System.out.println(coach.getDailyWorkout());
		System.out.println(fortune.getFortune());
		System.out.println(coach.getData());
		context.close();

	}
 
}
