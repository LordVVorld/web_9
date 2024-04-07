import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainClass {

    @Test
    public void FirstTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mrpro\\Downloads\\web_9\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //Переходим на Mail.ru
        driver.get("https://account.mail.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Вводим почту
        driver.findElement(By.xpath("//*[@class=\"input-0-2-77\"]")).clear();
        driver.findElement(By.xpath("//*[@class=\"input-0-2-77\"]")).sendKeys("trashmailforlab");
        driver.findElement(By.xpath("//*[@data-test-id=\"next-button\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Вводим пароль
        driver.findElement(By.xpath("//*[@class=\"input-0-2-77 withIcon-0-2-78\"]")).clear();
        driver.findElement(By.xpath("//*[@class=\"input-0-2-77 withIcon-0-2-78\"]")).sendKeys("toptester123");
        driver.findElement(
                By.xpath("//*[@data-test-id=\"submit-button\"]")
        ).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Нажимаем на аватар
        driver.findElement(
                By.xpath("//*[@class='ph-avatar-img svelte-dfhuqc']")
        ).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Сверяем результаты с ожиданием
        driver.findElement(
                By.xpath("//*[@class=\"ph-name svelte-1popff4\"]")
        ).isDisplayed();
        Assert.assertEquals(
                "Тестовый Тестич",
                driver.findElement(
                        By.xpath("//*[@class=\"ph-name svelte-1popff4\"]")
                ).getText()
        );

        //Выходим из аккаунта
        driver.findElement(
                By.xpath("//*[@data-testid=\"whiteline-account-exit\"]")
        ).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Проверяем наличие кнопки регистрации
        driver.findElement(
                By.xpath("//*[@class=\"resplash-btn resplash-btn_outlined-themed resplash-btn_mailbox-big cea-fefe-ehxscg\"]")
        ).isDisplayed();

        driver.quit();
    }
}
