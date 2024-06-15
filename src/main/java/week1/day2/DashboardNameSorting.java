package week1.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DashboardNameSorting {

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
		// 5. Click the sort arrow in the Dashboard Name.
		driver.findElement(By.xpath("//span[@title='Dashboard Name']")).click();
		// 6. Verify the Dashboard displayed in ascending order by Dashboard name.
		// Expected Result:Dashboards should be displayed in ascending order by
		// Dashboard nameStep
		List<WebElement> dashboardNamesElements = driver
				.findElements(By.xpath("//th[@data-label='Dashboard Name']//a"));

		List<String> dashboardNames = new ArrayList<String>();

		for (WebElement element : dashboardNamesElements) {
			dashboardNames.add(element.getText());
		}

		// System.out.println(dashboardNames);

		List<String> sortedDashboardNames = new ArrayList<String>(dashboardNames);
		Collections.sort(sortedDashboardNames);

		if (dashboardNames.equals(sortedDashboardNames)) {
			System.out.println("Dashboard names are sorted in ascending order");
		} else {
			System.out.println("Dashboard names are not sorted in ascending order");
		}
		driver.quit();
	}

}
