package ru.my_shop.autotest.steps;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import ru.my_shop.autotest.helpers.ConfigContainer;

import static com.codeborne.selenide.Selenide.open;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

/**
 * Класс описывающий Hooks (код выполняющийся до и после каждого теста)
 */
public class CucumberHooks extends AbstractSteps {
    private WebDriver driver;

    @Before
    public void setupTest() {

        // Загружаем настройки тестовой среды из файла "config.properties"
        ConfigContainer.getInstance().loadConfig();

        logger.info("Выполняется открытие браузера");
        ChromeDriverManager.getInstance(CHROME).setup();
        Configuration.startMaximized = true;
        open(config.getProperties("url.homePage"));
    }

    @After
    public void teardown() {
        logger.info("Выполняется закрытие браузера");
        if (driver != null) {
            driver.quit();
        }
    }

}
