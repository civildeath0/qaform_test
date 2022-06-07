import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    @BeforeEach
    public void config() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "2100x1080";
    }

    @Test
    void successfulRegistrationTest() {
        open("https://demoqa.com/automation-practice-form");

        $x("//input[@id='firstName']").sendKeys("Sam");
        $x("//input[@id='lastName']").sendKeys("Serzhantov");
        $x("//input[@id='userEmail']").sendKeys("civildeath0@gmail.com");
        $x("//input[@id='userNumber']").sendKeys("+79132033901");
        $x("//input[@id='dateOfBirthInput']").click();

        ElementsCollection dateSelectors = $$x("//select");
        for (SelenideElement selector : dateSelectors)
            selector.selectOption(1);

        $x("//div[@role='option']").click();

        $x("//input[@id='subjectsInput']").setValue("English").pressEnter();
        $x("//label[text()='Male']").click();

        ElementsCollection checkboxes = $$x("//input[@type='checkbox']/following-sibling::label");
        for (SelenideElement checkbox : checkboxes)
            checkbox.click();

        $x("//input[@id='uploadPicture']").uploadFile(new File("src/test/resources/1.png"));
        $x("//textarea[@id='currentAddress']").sendKeys("Kutuzovskiy prospekt, 1");
        $x("//div[contains(text(), 'State')]").scrollTo().click();
        $x("//*[contains(text(), 'Haryana')]").click();
        $x("//div[contains(text(), 'City')]").click();
        $x("//*[contains(text(), 'Karnal')]").click();
    }
}
