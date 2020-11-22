package ge.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UploadedFilePage extends Page<UploadedFilePage> {

    @FindBy(id = "uploaded-files")
    WebElement fileName;


    public String getUploadedFileName() {
        return fileName.getText();
    }


}
