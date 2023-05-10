package com.selferp.utils;

import com.selferp.utils.helpers.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class World {
    public static WebDriver driver = (new WebDriverManager()).setupDriver();


    public World(){

    }
}
