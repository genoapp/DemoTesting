package ge.demo.pages;

import ge.demo.util.Tag;
import ge.demo.util.annotations.Tagged;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShiftingContentPage extends Page<ShiftingContentPage> {

    @Tagged(Tag.MENU)
    @FindBy(xpath = "//*[@id=\"content\"]/div/a[1]")
    private WebElement menuButton;

    @Tagged(Tag.IMAGE)
    @FindBy(xpath = "//*[@id=\"content\"]/div/a[2]")
    private WebElement imageButton;


    public ShiftingContentImagePage clickImageExample() {
        imageButton.click();
        return dispatch(new ShiftingContentImagePage());
    }


    public ShiftingContentMenuPage clickMenuExample() {
        menuButton.click();
        return  dispatch(ShiftingContentMenuPage.class);
    }


}
