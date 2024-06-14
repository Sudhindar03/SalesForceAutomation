package week1.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DashboardSubscription {

	public static void main(String[] args) {
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
		WebElement searchRecentDashboards = driver
				.findElement(By.xpath("//input[@placeholder='Search recent dashboards...']"));
		searchRecentDashboards.sendKeys("Sudhindar");
		// 6. Click on the Dropdown icon and Select Subscribe
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();
		driver.findElement(By.xpath("(//div[@class='slds-dropdown__item']//span)[3]")).click();
		// 7. Select frequency as 'Daily' and Click on Save in the Edit Subscription
		// popup window.
		driver.findElement(By.xpath("(//label[@for='daily']//span)[2]")).click();
		driver.findElement(By.xpath("//button[@title='Save']//span[1]")).click();
		// 8.Verify Whether the dashboard is subscribed.Expected Result:Dashboards
		// should be subscribed Successfully
		String dashboardSubscriptionStartedText = driver
				.findElement(By.xpath("//div[@class='slds-align-middle slds-hyphenate']//span")).getText();
		//System.out.println(dashboardSubscriptionStartedText);

		if (dashboardSubscriptionStartedText.contains("started a dashboard subscription")) {
			System.out.println("Dashboard subscription is successful");
		} else {
			System.out.println("Dashboard is not subscribed");
		}
		driver.quit();
	}

}
