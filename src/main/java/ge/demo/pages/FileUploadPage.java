package ge.demo.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class FileUploadPage extends Page<FileUploadPage> {


    @FindBy(id = "file-upload")
    WebElement inputFile;

    @FindBy(id = "file-submit")
    WebElement uploadButton;


    public FileUploadPage chooseFile(@NotNull File file) {
        inputFile.sendKeys(file.getAbsolutePath());
        return this;
    }


    public UploadedFilePage clickUpload() {
        uploadButton.click();
        return dispatch(UploadedFilePage.class);
    }


}
