package ge.demo.pages;

import ge.demo.util.annotations.Tagged;
import ge.demo.util.Tag;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShiftingContentImagePage extends Page<ShiftingContentImagePage> {

    @FindBy(xpath = "//*[@id=\"content\"]/div/p[3]/a")
    private WebElement pixelShiftLink;

    @Tagged(Tag.IMAGE)
    @FindBy(xpath = "//*[@id=\"content\"]/div/img")
    private WebElement image;


    public ShiftingContentImagePage clickPixelShift() {
        pixelShiftLink.click();
        return this;
    }


    public Point getImageLocation() {
        return image.getLocation();
    }

}
