package ge.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ChallengingDomPage extends Page<ChallengingDomPage> {


    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div/div[2]/table/tbody/tr[*]/td[1]")
    private List<WebElement> loremElements;


    public List<String> getLoremItems() {
        return loremElements
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


}
