package com.swaglabs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	private WebDriver driver;

	private By checkoutButton = By.id("checkout");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickCheckout() {
		driver.findElement(checkoutButton).click();
	}

	public boolean isProductInCart(String productName) {
		List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
		for (WebElement item : items) {
			if (item.getText().equals(productName)) {
				return true;
			}
		}
		return false;
	}

	public void removeProduct(String productName) {
		WebElement removeButton = driver
				.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button"));
		removeButton.click();
	}
	
	public int getCartItemCount() {
	    try {
	        return driver.findElements(By.className("cart_item")).size();
	    } catch (Exception e) {
	        return 0;
	    }
	}


}
