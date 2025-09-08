package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {
    private WebDriver driver;

    private By finishButton = By.id("finish");
    private By cancelButton = By.id("cancel");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductDisplayed(String productName) {
        return driver.getPageSource().contains(productName);
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }
    public double getProductPrice(String productName) {
        String priceText = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']")).getText();
        return Double.parseDouble(priceText.replace("$", ""));
    }

    public double getSubtotalPrice() {
        String subtotalText = driver.findElement(By.className("summary_subtotal_label")).getText();
        return Double.parseDouble(subtotalText.replace("Item total: $", ""));
    }

    public double getTaxPrice() {
        String taxText = driver.findElement(By.className("summary_tax_label")).getText();
        return Double.parseDouble(taxText.replace("Tax: $", ""));
    }

    public double getTotalPrice() {
        String totalText = driver.findElement(By.className("summary_total_label")).getText();
        return Double.parseDouble(totalText.replace("Total: $", ""));
    }


}
