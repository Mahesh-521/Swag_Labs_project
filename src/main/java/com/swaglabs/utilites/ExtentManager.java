package com.swaglabs.utilites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		String projectpath = System.getProperty("user.dir");
		if (extent == null) {
			String reportpath = projectpath + "\\src\\test\\resources\\Reports\\swaglabsreport.html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportpath);
			extent = new ExtentReports();
			extent.attachReporter(spark);
		}
		return extent;
	}

}
