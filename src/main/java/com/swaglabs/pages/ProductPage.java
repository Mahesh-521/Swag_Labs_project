package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    private WebDriver driver;

    private By productHeading=By.xpath("//span[@data-test='title']");
    private By backpack = By.id("add-to-cart-sauce-labs-backpack");
    private By bikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By tshirtRed = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private By cartIcon = By.id("shopping_cart_container");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageDisplayed() {
    	WebElement heading=driver.findElement(productHeading);
    	if(heading.isDisplayed()) {
    		return true;
    	}
        return false;
    }

    public void addBackpackToCart() {
        driver.findElement(backpack).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(bikeLight).click();
    }

    public void addTShirtRedToCart() {
        driver.findElement(tshirtRed).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    public void logout() throws InterruptedException {
        driver.findElement(menuButton).click();
        Thread.sleep(1000);
        driver.findElement(logoutLink).click();
    }
    
    public void sortByPriceHighToLow() {
        Select sort = new Select(driver.findElement(By.className("product_sort_container")));
        sort.selectByVisibleText("Price (high to low)");
    }
    
    public String getProductNameByIndex(int index) {
        return driver.findElements(By.className("inventory_item_name")).get(index).getText();
    }

    public void addProductToCart(String productName) {
        WebElement product = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button"));
        product.click();
    }
    public void goToSidebar() {
        WebElement sidebar = driver.findElement(By.className("bm-menu"));
        if (!sidebar.isDisplayed()) {
            driver.findElement(By.id("react-burger-menu-btn")).click();
        }
    }

    
    public void clickResetAppState() {
        driver.findElement(By.id("reset_sidebar_link")).click();
    }
}
