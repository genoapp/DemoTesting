package ge.demo;

import ge.demo.pages.HerokuappPage;
import ge.demo.pages.ShiftingContentPage;
import ge.demo.util.AssertLater;
import ge.demo.util.Size;
import ge.demo.util.Tag;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;


@Epic("Demo Test")
@Feature("there are three test cases in this section, each of them is quite important...")
@DisplayName("Demo Test")
public class DemoTest {

    private static DemoContext mContext;

    private HerokuappPage mMainPage;

    @BeforeClass
    public static void initContext() {
        WebDriverManager.firefoxdriver().setup();
        mContext = DemoContext
                .getBuilder()
                .setDriver(new FirefoxDriver())
                .build();

        //or else
        //mContext = DemoContext.getDefault(); // default driver is chrome driver
    }

    @Before
    public void startSession() {
        mMainPage = mContext.startWebSite();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("File Upload")
    @Description("checks if Image is uploaded")
    public void checkFileUpload() throws IOException {

        //Expected text
        final String FILE_UPLOAD_SUCCESS_TEXT = "File Uploaded!";

        final String MESSAGE_SUCCESS_NOT_PRESENT = "File was uploaded but there is not present a success message on the page";
        final String MESSAGE_FILE_NAME_IS_DIFFERENT = "File name is different than it was expected";

        File mTempFile = new File(String.valueOf(Files.createTempFile("demo_", ".tmp")));

        mMainPage.clickFileUpload()
                .chooseFile(mTempFile)
                .clickUpload()
                .job((page) -> {

                    assertTrue(MESSAGE_SUCCESS_NOT_PRESENT,
                            page.isPresentText(FILE_UPLOAD_SUCCESS_TEXT));

                    assertEquals(MESSAGE_FILE_NAME_IS_DIFFERENT,
                            mTempFile.getName(), page.getUploadedFileName());

                });
    }


    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Shifting Content")
    @Description("<br>1. checks design of menu button when mouse is on it<br>" +
            "2. checks if Image is moved left when user clicks 'Pixel Shift'<br>" +
            "3. checks design of home button when mouse is on it")
    public void checkShiftingContent() {

        final String MESSAGE_MENU_DOES_NOT_CHANGE = "Mouse was moved on menu button but it has not changed";
        final String MESSAGE_IMAGE_DOES_NOT_MOVE = "Image has not Moved left after click the button 'Pixel Shift'";
        final String MESSAGE_HOME_DOES_NOT_CHANGE = "Mouse was moved on home button but it has not changed";

        mMainPage.clickShiftingContent()
                .job((page) -> {

                    String mBeforeColor = page.getTextColor(Tag.MENU);
                    page.mouseMove(Tag.MENU);
                    String mAfterColor = page.getTextColor(Tag.MENU);

                    assertNotEquals(MESSAGE_MENU_DOES_NOT_CHANGE,
                            mBeforeColor, mAfterColor);

                })
                .clickImageExample()
                .job((page) -> {

                    Point mBeforePoint = page.getImageLocation();
                    page.clickPixelShift();
                    Point mAfterPoint = page.getImageLocation();

                    assertNotEquals(MESSAGE_IMAGE_DOES_NOT_MOVE,
                            mBeforePoint, mAfterPoint);

                })
                .back()
                .back(ShiftingContentPage.class)
                .clickMenuExample()
                .job((page) -> {

                    Size mBeforeSize = page.getElementSize(Tag.HOME);
                    page.mouseMove(Tag.HOME);
                    Size mAfterSize = page.getElementSize(Tag.HOME);

                    assertNotEquals(MESSAGE_HOME_DOES_NOT_CHANGE,
                            mBeforeSize, mAfterSize);

                })
                .click(Tag.HOME);

    }


    @Test
    @Flaky
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Challenging DOM")
    @Description("checks one column in table")
    public void checkChallengingDom() {

        //message with string format
        final String MESSAGE_NOT_ZERO = "Column 1 Row %d does not contain expected value,";

        mMainPage.clickChallengingDom()
                .job((page) -> {

                    AssertLater mAssertLater = new AssertLater();
                    AtomicInteger mRowNumber = new AtomicInteger(1);

                    for (String item : page.getLoremItems()) {
                        final String mValue = item.substring(item.length() - 1);

                        mAssertLater.test(() -> assertEquals(String.format(MESSAGE_NOT_ZERO, mRowNumber.getAndIncrement())
                                , "0", mValue));
                    }

                    mAssertLater.assertAll();
                });

    }


    @AfterClass
    public static void quitSession() {
        mContext.getDriver().quit();
    }
}
