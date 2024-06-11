package week1.day1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewDashboard {

	public static void main(String[] args) {

		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		// Launch the Chrome browser
		ChromeDriver driver = new ChromeDriver(options);

		// 1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com");

		// Maximize the browser window
		driver.manage().window().maximize();

		// Adding implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Enter the Username
		driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");

		// Enter the Password
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");

		// Click Login button
		driver.findElement(By.id("Login")).click();

		// 2. Click on the toggle menu button from the left corner

		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();

		// 3. Click View All and click Dashboards from App Launcher

		driver.findElement(By.xpath("//button[contains(text(),'View All')]")).click();
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("Dashboards");
		driver.findElement(By.xpath("//p[@class='slds-truncate']//mark[contains(text(),'Dashboards')]")).click();

		// 4. Click on the New Dashboard option

		driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();

		// 5. Enter Name as 'Salesforce Automation by Your Name ' and Click on Create

		WebElement frameElement = driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(frameElement);
		String dbName = "Salesforce Automation by Sudhindar K M";
		driver.findElement(By.id("dashboardNameInput")).sendKeys(dbName);
		driver.findElement(By.id("submitBtn")).click();
		driver.switchTo().defaultContent();
		// 6.Click on Save and Verify Dashboard name.
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {

		// e.printStackTrace();
		// }
		driver.switchTo().frame(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		// WebElement saveButtonDashboard = wait.until(ExpectedConditions
		// .elementToBeClickable(By.xpath("//div[@class='slds-button-group']//button[contains(text(),'Save')]")));
		// saveButtonDashboard.click();
		driver.findElement(By.xpath("//div[@class='slds-button-group']//button[contains(text(),'Save')]")).click();
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		WebElement dashboardsTab = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Dashboards']//span[1]")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", dashboardsTab);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// WebElement element = wait.until(ExpectedConditions
		// .presenceOfElementLocated(By.xpath("//a[@title='Dashboards']//span[contains
		// (text(),'Dashboards')]")));

		// element.click();

		// WebElement dashboardTable =
		// driver.findElement(By.xpath("//div[@class='slds-scrollable_y']/table//tbody"));

		// List<WebElement> dashboardTableRows =
		// dashboardTable.findElements(By.tagName("tr"));
		// System.out.println(dashboardTableRows.size());

		// List<WebElement> dashboardTableColumns =
		// dashboardTableRows.get(0).findElements(By.tagName("td"));
		// System.out.println(dashboardTableColumns.size());

		// String newDashboardName = dashboardTableColumns.get(0).getText();
		// System.out.println(newDashboardName);

		String newDashboardName = driver.findElement(By.xpath("//div[@class='slds-scrollable_y']/table//tbody//th//a"))
				.getText();
		System.out.println(newDashboardName);

		if (newDashboardName.equals(dbName)) {
			System.out.println("The New Dashboard is created Successfully");
		} else {
			System.out.println("New Dashboard is not created");
		}
		driver.quit();
	}

}
