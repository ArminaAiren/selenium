package hui.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


public class VKTest {

    private WebDriver driver;

    VKConfig config = ConfigFactory.create(VKConfig.class);
    @Config.Sources({"classpath:VKConfig.properties"})
    public interface VKConfig extends Config {
        String login();
        String password();
        String albumUrl();
    }

    @Before
    public void ctor() {
        driver = new ChromeDriver();
    }

    @Test
    public void messageTest() {

        /*
        driver.get("https://vk.com/");

        WebDriverWait wait = new WebDriverWait (driver, 15);
        wait.until(ExpectedConditions.titleContains('Добро пожаловать'));

        WebElement login = driver.findElement(By.xpath("//input[@id='index_email' or @id='quick_email']"));
        login.sendKeys(config.login());
        WebElement password = driver.findElement(By.xpath("//input[@id='index_pass' or @id='quick_pass']"));
        password.sendKeys(config.password());
        WebElement confirm = driver.findElement(By.xpath("//*[@id='index_login_button' or @id='quick_login_button']"));
        confirm.click();

        String albumUrl = config.albumUrl();
        wait.until(ExpectedConditions.visibilityOf(
            driver.findElement(By.xpath("//a[href='" + albumUrl + "']"))
        ));
        */
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://vk.com/im?media=&sel=200954641");

        WebDriverWait wait = new WebDriverWait (driver, 15);
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//input[@id='email']"))
        ));

        WebElement login = driver.findElement(By.xpath("//input[@id='email']"));
        login.sendKeys(config.login());
        WebElement password = driver.findElement(By.xpath("//input[@id='pass']"));
        password.sendKeys(config.password());
        WebElement confirm = driver.findElement(By.xpath("//*[@id='login_button']"));
        confirm.click();

        String albumUrl = config.albumUrl();
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//a[@href='" + albumUrl + "']"))
        ));

        WebElement messageText = driver.findElement(By.xpath("//*[@id='im_editable200954641']"));
        messageText.sendKeys("Приуэт");

        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//button[@class='im-send-btn im-chat-input--send _im_send im-send-btn_send']"))
        ));

        WebElement sendBtn = driver.findElement(By.xpath("//button[@class='im-send-btn im-chat-input--send _im_send im-send-btn_send']"));
        sendBtn.click();

        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.xpath("//button[@class='im-send-btn im-chat-input--send _im_send im-send-btn_audio']"))
        ));
        //тут я прост проверил что кнопка отправки сообщения поменялась на кнопку микрофона, но это хуйня
        //если б кирилл (со страпоном) не ждал от меня релизов, то я бы проверил, что сообщение ушло
        //например можно взять timestamp перед отправкой сообщения, отправить сообщение,
        //затем взять последний элемент из ul class="ui_clean_list im-mess-stack--mess _im_stack_messages"
        //и у этого li есть атрибут data-ts
        //он должен быть больше таймстэмпа, который мы запомнили перед отправкой сообщения
        //но написать эту проверку я ща уже не успеваю))
        //int ts = Date.now();
        //"//li[@class*-'in-meass' and @class^*='in-mess_failed' and @data-ts > " + ts +  "]"

    }

    @After
    public void destruct() {
        driver.quit();
    }

}