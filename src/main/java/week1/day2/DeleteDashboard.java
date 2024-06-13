package week1.day2;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DeleteDashboard {

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
		// 6. Click on the Dropdown icon and Select Delete
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//table/tbody/tr[1]/td[6]//button")).click();
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		// 7.Click on the Delete option in the displayed popup window.
		driver.findElement(By.xpath("//button[@title='Delete']//span[1]")).click();
		// 8. Verify Whether Dashboard is Deleted using Dashboard Name Expected
		// Result:The Dashboard is deleted Successfully
		WebElement dashboardsTab = driver.findElement(By.xpath("//a[@title='Dashboards']//span[1]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", dashboardsTab);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// searchRecentDashboards.sendKeys("Sudhindar");

		// String dashboardSearchResults = driver
		// .findElement(By.xpath("(//div[@class='emptyMessageContainer
		// folderListView']//span)[2]")).getText();

		// if (dashboardSearchResults.equalsIgnoreCase("No results found")) {
		// System.out.println("Dashboard is deleted successfully");
		// } else {
		// System.out.println("Dashboard is not deleted");
		// }

		// WebElement dashboardTable =
		// driver.findElement(By.xpath("//div[@class='slds-scrollable_y']/table//tbody"));

		// List<WebElement> dashboardTableRows =
		// dashboardTable.findElements(By.tagName("tr"));
		// System.out.println(dashboardTableRows.size());

		// List<WebElement> dashboardTableColumns =
		// dashboardTableRows.get(0).findElements(By.tagName("td"));
		// System.out.println(dashboardTableColumns.size());

		String existingDashboardName = driver
				.findElement(By.xpath("(//div[@class='slds-scrollable_y']/table//tbody//a)[1]")).getText();
		// System.out.println(existingDashboardName);

		String dashboardName = "Salesforce Automation by Sudhindar K M";

		if (existingDashboardName.equalsIgnoreCase(dashboardName)) {
			System.out.println("Dashboard is not deleted");
		} else {
			System.out.println("Dashboard is deleted successfully");
		}
		// logout of the salesforce application
		driver.findElement(By.xpath("//div[contains(@class,'profileTrigger branding-user-profile')]//span[1]")).click();
		driver.findElement(By.xpath("//a[contains(@class,'profile-link-label logout')]")).click();

		driver.quit();

	}
}
