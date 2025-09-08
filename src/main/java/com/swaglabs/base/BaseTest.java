package com.swaglabs.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.swaglabs.config.ConfigReader;
import com.swaglabs.utilites.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	protected WebDriver driver;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	protected ConfigReader config;

	@BeforeSuite
	public void setupreport() {
		extent = ExtentManager.getInstance();
	}

	@BeforeMethod
	public void setup() {
		System.out.println("Before method");

		// Setup WebDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();

		// Configure Chrome options
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--incognito");

		// Initialize ChromeDriver with options
		driver = new ChromeDriver(chromeOptions);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Initialize configuration reader and open base URL
		config = new ConfigReader();
		driver.get(config.getBaseURL());
	}

	@AfterMethod
	public void teardown() {
		System.out.println("After method");
		driver.quit();
	}

	@AfterSuite
	public void flushreport() {
		extent.flush();
	}
}