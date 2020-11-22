package ge.demo.pages;

import ge.demo.util.Tag;
import ge.demo.util.annotations.Tagged;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShiftingContentMenuPage extends Page<ShiftingContentMenuPage> {

    @Tagged(Tag.HOME)
    @FindBy(xpath = "//*[@id=\"content\"]/div/ul/li[1]/a")
    private WebElement homeButton;




}
