package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private WebDriver driver;

    private By backHomeButton = By.id("back-to-products");
    private By completeHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderComplete() {
        return driver.findElement(completeHeader).isDisplayed();
    }

    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }
}
