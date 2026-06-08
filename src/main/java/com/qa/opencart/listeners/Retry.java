package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count =0;
	private static int maxTry =3;
	
	@Override
	public boolean retry(ITestResult iTestResult){
		if(!iTestResult.isSuccess()) {//check if maxtry count is reached
			if(count < maxTry) {
				count++; //increase count
				iTestResult.setStatus(ITestResult.FAILURE);//mark test as failed
				return true;//tells testng to re-run the test
			}else {
				iTestResult.setStatus(ITestResult.FAILURE);//if maxcount reached, test marked as failed
			}
			
		}
		return false;
	}

}
