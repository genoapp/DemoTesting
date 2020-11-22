package ge.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HerokuappPage extends Page<HerokuappPage> {

    @FindBy(xpath = "//*[@id=\"content\"]/ul/li[18]/a")
    private WebElement fileUpload;

    @FindBy(xpath = "//*[@id=\"content\"]/ul/li[39]/a")
    private WebElement shiftingContent;

    @FindBy(xpath = "//*[@id=\"content\"]/ul/li[5]/a")
    private WebElement challengingDom;


    public FileUploadPage clickFileUpload() {
        fileUpload.click();
        return dispatch(FileUploadPage.class);
    }


    public ShiftingContentPage clickShiftingContent() {
        shiftingContent.click();
        return dispatch(ShiftingContentPage.class);
    }


    public ChallengingDomPage clickChallengingDom() {
        challengingDom.click();
        return dispatch(ChallengingDomPage.class);
    }


}
