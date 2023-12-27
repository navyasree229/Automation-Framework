package com.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private final WebDriver driver;
    private final JavascriptExecutor js;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) this.driver;
    }

    public void flash(WebElement element) {
        String bgcolor = element.getCssValue("backgroundColor");//purple
        for (int i = 0; i < 10; i++) {
            changeColor("rgb(0,200,0)", element);// 1
            changeColor(bgcolor, element);// 2
        }
    }

    private void changeColor(String color, WebElement element) {
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);//green->purple--g--p--g--p

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
    }
}
