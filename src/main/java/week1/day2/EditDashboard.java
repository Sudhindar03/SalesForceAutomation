package week1.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class EditDashboard {

	public static void main(String[] args) {
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifcations");
			ChromeDriver driver = new ChromeDriver(options);

			// 1. Login to https://login.salesforce.com
			driver.get("https://login.salesforce.com");

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
			driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
			driver.findElement(By.id("Login")).click();
			// 2. Click on the toggle menu button from the left corner
			driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
			// 3. Click View All and click Dashboards from App Launcher
			driver.findElement(By.xpath("//button[contains(text(),'View All')]")).click();
			driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("Dashboards");

			// 4. Click on the Dashboards tab
			driver.findElement(By.xpath("//p[@class='slds-truncate']//mark[contains(text(),'Dashboards')]")).click();
			// 5. Search the Dashboard 'Salesforce Automation by Your Name'
			driver.findElement(By.xpath("//input[@placeholder='Search recent dashboards...']")).sendKeys("Sudhindar");
			// 6. Click on the Dropdown icon and Select Edit
			// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			Thread.sleep(5000);

			driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();
			driver.findElement(By.xpath("//span[text()='Edit']")).click();
			// 7.Click on the Edit Dashboard Properties icon
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));

			Thread.sleep(3000);

			WebElement editDashboardProperties = driver
					.findElement(By.xpath("//button[@title='Edit Dashboard Properties']"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", editDashboardProperties);
			// 8. Enter Description as 'SalesForce' and click on save.
			// WebElement dashboardName =
			// driver.findElement(By.xpath("//input[@id='dashboardNameInput']"));
			// dashboardName.clear();
			// dashboardName.sendKeys("Salesforce Automation by Sudhindar K M");
			driver.findElement(By.xpath("//input[@id='dashboardDescriptionInput']")).sendKeys("Salesforce");
			driver.findElement(By.xpath("(//button[contains(@class,'slds-button slds-button_brand')])[2]")).click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// WebElement privateDashboardFolder =
			// driver.findElement(By.xpath("//button[text()='Private Dashboards']"));
			// js.executeScript("arguments[0].click();", privateDashboardFolder);
			// driver.findElement(By.xpath("//button[@title='Cancel']/following-sibling::button[1]")).click();
			// driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
			driver.switchTo().defaultContent();
			// 9. Click on Done and Click on save in the popup window displayed.
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
			driver.findElement(By.xpath("//button[@class='slds-button doneEditing']")).click();
			driver.findElement(By.xpath("(//button[contains(@class,'slds-button slds-button_brand')])[2]")).click();
			// 10. Verify the Description.Expected Result:The Dashboard is Edited
			// SuccessfullyStep
			String dashboardDescriptionText = driver
					.findElement(By.xpath("//div[@id='main']//p[@class='slds-page-header__info']")).getText();

			if (dashboardDescriptionText.equalsIgnoreCase("salesforce")) {
				System.out.println("The dashboard is edited successfully");
			} else {
				System.out.println("The dashboard is not edited");
			}

			driver.quit();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
