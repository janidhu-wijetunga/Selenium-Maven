package seleniumPackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        long globalStartTime = System.currentTimeMillis();

        // visit website.
        driver.get("https://www.qa-practice.com/");

        // click drop down.
        driver.findElement(By.linkText("Single UI Elements")).click();

        // TEST CASE 1 - DATA INPUT.
        System.out.println("Test Case 1 - Data Input.");

        long startTime = System.currentTimeMillis();

        driver.findElement(By.linkText("Inputs")).click();

        driver.findElement(By.name("text_string")).sendKeys("hello" + Keys.ENTER);

        String result = driver.findElement(By.className("result-text")).getText();
        assertEquals(result, "hello");

        driver.findElement(By.linkText("Email field")).click();

        driver.findElement(By.name("email")).sendKeys("janidhuw@gmail.com" + Keys.ENTER);

        String emailResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(emailResult, "janidhuw@gmail.com");

        driver.findElement(By.linkText("Password field")).click();

        driver.findElement(By.name("password")).sendKeys("ABcd@1234" + Keys.ENTER);

        String passwordResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(passwordResult, "ABcd@1234");

        long endTime = System.currentTimeMillis();

        System.out.println("Test Case 1 Completed.");

        long duration = endTime - startTime;
        System.out.println("Test Case 1 took : " + duration + " milliseconds (" + (duration / 1000.0) + " seconds).");

        // TEST CASE 2 - BUTTONS.
        System.out.println("\nTest Case 2 - Buttons");

        long startTime2 = System.currentTimeMillis();

        driver.findElement(By.linkText("Buttons")).click();

        driver.findElement(By.id("submit-id-submit")).click();

        String simpleClickResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(simpleClickResult, "Submitted");

        driver.findElement(By.linkText("Looks like a button")).click();

        driver.findElement(By.className("a-button")).click();

        String buttonLookResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(buttonLookResult, "Submitted");

        driver.findElement(By.linkText("Disabled")).click();

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/form/div/select/option[2]")).click();

        driver.findElement(By.id("submit-id-submit")).click();

        String disabledButtonResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(disabledButtonResult, "Submitted");

        long endTime2 = System.currentTimeMillis();

        System.out.println("Test Case 2 Completed.");

        long duration2 = endTime2 - startTime2;
        System.out.println("Test Case 2 took : " + duration2 + " milliseconds (" + (duration2 / 1000.0) + " seconds).");

        // TEST CASE 3 - CHECKBOX.
        System.out.println("\nTest Case 3 - Checkbox");

        long startTime3 = System.currentTimeMillis();

        driver.findElement(By.linkText("Checkbox")).click();

        driver.findElement(By.className("form-check-label")).click();

        driver.findElement(By.id("submit-id-submit")).click();

        String singleCheckboxResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(singleCheckboxResult, "select me or not");

        driver.findElement(By.linkText("Checkboxes")).click();

        driver.findElement(By.id("id_checkboxes_0")).click();
        driver.findElement(By.id("id_checkboxes_1")).click();
        driver.findElement(By.id("id_checkboxes_2")).click();

        driver.findElement(By.id("submit-id-submit")).click();

        String checkboxesResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(checkboxesResult, "one, two, three");

        long endTime3 = System.currentTimeMillis();

        System.out.println("Test Case 3 Completed.");

        long duration3 = endTime3 - startTime3;
        System.out.println("Test Case 3 took : " + duration3 + " milliseconds (" + (duration3 / 1000.0) + " seconds).");

        // TEST CASE 4 - NEW TAB.
        System.out.println("\nTest Case 4 - New Tab");

        long startTime4 = System.currentTimeMillis();

        driver.findElement(By.linkText("New tab")).click();

        driver.findElement(By.id("new-page-link")).click();

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        String newTabHandle = null;

        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                newTabHandle = windowHandle;
                break;
            }
        }

        driver.switchTo().window(newTabHandle);

        String tabLinkResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(tabLinkResult, "I am a new page in a new tab");

        driver.close();

        driver.switchTo().window(originalWindow);

        driver.findElement(By.linkText("New tab button")).click();

        driver.findElement(By.id("new-page-button")).click();

        String originalWindow2 = driver.getWindowHandle();
        Set<String> allWindows2 = driver.getWindowHandles();
        String newTabHandle2 = null;

        for (String windowHandle2 : allWindows2) {
            if (!windowHandle2.equals(originalWindow2)) {
                newTabHandle2 = windowHandle2;
                break;
            }
        }

        driver.switchTo().window(newTabHandle2);

        String newTabLinkResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(newTabLinkResult, "I am a new page in a new tab");

        driver.close();

        driver.switchTo().window(originalWindow2);

        long endTime4 = System.currentTimeMillis();

        System.out.println("Test Case 4 Completed.");

        long duration4 = endTime4 - startTime4;
        System.out.println("Test Case 4 took : " + duration4 + " milliseconds (" + (duration4 / 1000.0) + " seconds).");

        // TEST CASE 5 - ALERTS.
        System.out.println("\nTest Case 5 - Alerts");

        long startTime5 = System.currentTimeMillis();

        driver.findElement(By.linkText("Alerts")).click();

        driver.findElement(By.linkText("Click")).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.findElement(By.linkText("Confirmation box")).click();

        driver.findElement(By.linkText("Click")).click();

        Alert alert2 = driver.switchTo().alert();
        alert2.accept();

        String confirmResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(confirmResult, "Ok");

        driver.findElement(By.linkText("Prompt box")).click();

        driver.findElement(By.linkText("Click")).click();

        Alert alert3 = driver.switchTo().alert();
        alert3.sendKeys("selenium");
        alert3.accept();

        String promptResult = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/p[2]")).getText();
        assertEquals(promptResult, "selenium");

        long endTime5 = System.currentTimeMillis();

        System.out.println("Test Case 5 Completed.");

        long duration5 = endTime5 - startTime5;
        System.out.println("Test Case 5 took : " + duration5 + " milliseconds (" + (duration5 / 1000.0) + " seconds).");

        // TEST CASE 6 - IFRAMES.
        System.out.println("\nTest Case 6 - Iframes");

        long startTime6 = System.currentTimeMillis();

        driver.findElement(By.linkText("Iframes")).click();

        WebElement iframe = driver.findElement(By.cssSelector("#content > iframe"));

        driver.switchTo().frame(iframe);

        driver.findElement(By.cssSelector("body > div > header > div.navbar.navbar-dark.bg-dark.shadow-sm > div > button")).click();

        driver.switchTo().defaultContent();

        long endTime6 = System.currentTimeMillis();

        System.out.println("Test Case 6 Completed.");

        long duration6 = endTime6 - startTime6;
        System.out.println("Test Case 6 took : " + duration6 + " milliseconds (" + (duration6 / 1000.0) + " seconds).");

        // TEST CASE 7 - POP UPS.
        System.out.println("\nTest Case 7 - Pop ups");

        long startTime7 = System.currentTimeMillis();

        driver.findElement(By.linkText("Pop-Up")).click();

        driver.findElement(By.cssSelector("#content > button")).click();

        driver.findElement(By.className("form-check-label")).click();

        driver.findElement(By.cssSelector("#exampleModal > div > div > div.modal-footer > button.btn.btn-primary")).click();

        String modalResult = driver.findElement(By.className("result-text")).getText();
        assertEquals(modalResult, "select me or not");

        driver.findElement(By.linkText("Iframe Pop-Up")).click();

        driver.findElement(By.cssSelector("#content > button")).click();

        driver.findElement(By.cssSelector("#exampleModal > div > div > div > div.modal-footer > button.btn.btn-primary")).click();

        long endTime7 = System.currentTimeMillis();

        System.out.println("Test Case 7 Completed.");

        long duration7 = endTime7 - startTime7;
        System.out.println("Test Case 7 took : " + duration7 + " milliseconds (" + (duration7 / 1000.0) + " seconds).");

        long globalEndTime = System.currentTimeMillis();
        long globalDuration = globalEndTime - globalStartTime;
        System.out.println("\nTotal time for all test cases: " + globalDuration + " milliseconds (" + (globalDuration / 1000.0) + " seconds).");

        driver.quit();
    }
}
