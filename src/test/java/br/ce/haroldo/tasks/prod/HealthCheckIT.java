package br.ce.haroldo.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {

    @Test
    public void healthCheck() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "src/test/resource/chromedriver-win64/chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		// WebDriver driver = new RemoteWebDriver(new URL("http://10.85.11.29:4444/wd/hub"), options);
		// driver.navigate().to("http://10.85.11.29:8001/tasks");
		WebDriver driver = new RemoteWebDriver(new URL("http://172.31.240.1:4444/wd/hub"), options);
        try{
            driver.navigate().to("http://172.31.240.1:9999/tasks");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            String version = driver.findElement(By.id("version")).getText();
            Assert.assertTrue(version.startsWith("build"));
        } finally {
            driver.quit();
        }
	
    }
}