package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebReader {

    public static String getLine(String cidr) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String result = "";
        try {
            // Navigate to the URL
            driver.get("https://www.ipaddressguide.com/ipv6-cidr");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-control")));

            // Find the input field, clear placeholder, and enter value
            WebElement inputField = driver.findElement(By.className("form-control"));
            inputField.clear();
            inputField.sendKeys(cidr);

            // Click the submit button
            WebElement submitButton = driver.findElement(By.cssSelector(".btn.btn-success"));
            submitButton.click();

            Thread.sleep(2000);

            // Wait for the table to load and fetch data
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".table.table-striped")));

            WebElement table = driver.findElement(By.cssSelector(".table.table-striped"));

            WebElement tbody = table.findElement(By.tagName("tbody"));
            List<WebElement> rows = tbody != null ?
                    tbody.findElements(By.tagName("tr")) :
                    table.findElements(By.tagName("tr"));

            String firstIp = "";
            String lastIp = "";

            Integer rowNumber = 0;
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                for (WebElement cell : cells) {
                    if (rowNumber == 1) {
                        firstIp = cell.getText();
                    }
                    if (rowNumber == 3) {
                        lastIp = cell.getText();
                    }
                    rowNumber++;
                }
            }


            result = "CIDR: " + cidr + "\n" + "First IP: " + firstIp + "\n" + "Last IP: " + lastIp + "\n ";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit(); // Close the browser
        }

        return result;
    }
}
