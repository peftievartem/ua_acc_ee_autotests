package com.self.utils;

import com.self.utils.helpers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class World {
    public static WebDriver driver = (new WebDriverManager()).setupDriver();


    public World(){

    }
}
