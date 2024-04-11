import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainClass {
    WebDriver driver;
    public MainClass(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mrpro\\Downloads\\web_9\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void Habr(){
        //Переходим на Habr.com
        driver.get("https://habr.com/ru/articles/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Нажимаем на "Поиск"
        driver.findElement(
                By.xpath("//*[@class=\"tm-svg-img tm-header-user-menu__icon tm-header-user-menu__icon_search\"]")
        ).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Проверяем, является ли поле ввода запроса активным элементом
        Assert.assertEquals(
                driver.findElement(
                        By.xpath("//*[@class=\"tm-input-text-decorated__input\"]")
                ),
                driver.switchTo().activeElement()
        );

        //Отправляем поисковой запрос
        driver.findElement(By.xpath("//*[@class=\"tm-input-text-decorated__input\"]"))
                .sendKeys("Selenium WebDriver");
        driver.findElement(By.xpath("//*[@class=\"tm-svg-img tm-svg-icon\"]"))
                .click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Открываем статью из результатов поиска
        driver.findElement(By.linkText("Что такое Selenium?"))
                .click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Проверяем дату на соответствие
        Assert.assertEquals(
                "28 сен 2012 в 17:14",
                driver.findElement(
                        By.xpath("//*[@title=\"2012-09-28, 17:14\"]")
                ).getText()
        );

        //Пролистываем вниз страницы
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Меняем размер окна и нажимаем на раздел "Статьи"
        driver.manage().window().setSize(new Dimension(1280, 1025));
        driver.findElement(
                By.xpath("//*[@href=\"/ru/articles/\" and @class=\"footer-menu__item-link router-link-active\"]")
        ).click();
    }

    @Test
    public void MailRu() {
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
                By.xpath("//*[@class=\"resplash-btn resplash-btn_outlined-themed resplash-btn_mailbox-big ejf-iekc__1jdtl7f\"]")
        ).isDisplayed();

        driver.quit();
    }
}
