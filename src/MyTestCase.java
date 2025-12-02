import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MyTestCase {

	WebDriver driver = new ChromeDriver();
	String website = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();

	@BeforeTest
	public void MySetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();

		driver.get(website);
	}

	@Test(priority = 1, enabled = false)
	public void RadioButtons() {
		// select one random radio button
		WebElement divForRadio = driver.findElement(By.id("radio-btn-example"));
		divForRadio.findElements(By.tagName("input"));
		int RandomIndex = rand.nextInt(divForRadio.findElements(By.tagName("input")).size());
		WebElement SelectedInput = divForRadio.findElements(By.tagName("input")).get(RandomIndex);
		SelectedInput.click();
		boolean ActualResult = SelectedInput.isSelected();
		boolean ExpectedResult = true;
		AssertJUnit.assertEquals(ActualResult, ExpectedResult);

	}

	@Test(priority = 2, enabled = false)

	public void DynamicDropDown() throws InterruptedException {
		String[] myRandomTowCharacter = { "AB", "EA", "GH", "IJ", "KL", "MO", "OP", "IL" };
		int randomIndex = rand.nextInt(myRandomTowCharacter.length);
		String myInputdata = myRandomTowCharacter[randomIndex];
		WebElement autocompleteInput = driver.findElement(By.id("autocomplete"));
		autocompleteInput.sendKeys(myInputdata);
		Thread.sleep(1000);
		autocompleteInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String DataInsideMyInput = (String) js.executeScript("return arguments[0].value", autocompleteInput);
		String updatedData = DataInsideMyInput.toLowerCase();
		boolean AcutualResult = updatedData.contains(myInputdata.toLowerCase());
		System.out.println(updatedData);
		System.out.println(myInputdata.toLowerCase());
		AssertJUnit.assertEquals(AcutualResult, true);
	}

	@Test(priority = 3)

	public void SelectTag() {
		WebElement mySelectelement = driver.findElement(By.id("dropdown-class-example"));
		Select selctor = new Select(mySelectelement);

		int randomIndex = rand.nextInt(1, 4);
		selctor.selectByIndex(randomIndex);
	}
}
