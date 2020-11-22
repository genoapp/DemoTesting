package ge.demo.pages;

import ge.demo.DemoContext;
import ge.demo.util.Size;
import ge.demo.util.Tag;
import ge.demo.util.annotations.Tagged;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Page<T extends Page<T>> {

    private DemoContext context;

    public DemoContext getContext() {
        return context;
    }


    protected void prepare(DemoContext context) {
        this.context = context;
    }


    public boolean isPresentText(String text) {
        return context.getDriver()
                .findElement(By.tagName("body"))
                .getText()
                .contains(text);
    }


    public T job(@NotNull Consumer<T> page) {
        page.accept(self());
        return self();
    }


    protected <P extends Page<P>> P dispatch(Class<P> pClass) {
        return dispatch(pClass, context);
    }


    protected <P extends Page<P>> P dispatch(P page) {
        return dispatch(page, context);
    }


    @NotNull
    public static <P extends Page<P>> P dispatch(Class<P> pClass, DemoContext context) {
        Objects.requireNonNull(context);
        P page = PageFactory.initElements(context.getDriver(), pClass);
        page.prepare(context);

        return page;
    }


    @NotNull
    @Contract("_, _ -> param1")
    public static <P extends Page<P>> P dispatch(@NotNull P page, DemoContext context) {
        Objects.requireNonNull(context);
        page.prepare(context);
        PageFactory.initElements(context.getDriver(), page);

        return page;
    }


    public T back() {
        getContext().getDriver().navigate().back();
        return self();
    }


    public <B extends Page<B>> B back(Class<B> bClass) {
        back();
        return dispatch(bClass);
    }


    public <B extends Page<B>> B back(B page) {
        back();
        return dispatch(page);
    }

    public T click(Tag tag){
        findWebElementByTag(tag).click();
        return self();
    }


    public T mouseMove(Tag tag) {
        WebElement mElement = findWebElementByTag(tag);
        Actions actions = new Actions(getContext().getDriver());
        actions.moveToElement(mElement).perform();

        return self();
    }


    public Point getLocation(Tag tag) {
        return findWebElementByTag(tag).getLocation();
    }

    public String getTextColor(Tag tag) {
        WebElement mElement = findWebElementByTag(tag);
        return mElement.getCssValue("color");
    }


    public Size getElementSize(Tag tag) {
        WebElement mElement = findWebElementByTag(tag);
        float width = Float.parseFloat(mElement.getCssValue("width").replaceAll("[^\\d.]", ""));
        float height = Float.parseFloat(mElement.getCssValue("height").replaceAll("[^\\d.]", ""));

        return new Size(width, height);
    }


    private Object findObjectByTag(Tag tag) {
        Field mField = Stream.of(getClass().getDeclaredFields())
                .filter((f) -> {
                    Tagged mTagged = f.getDeclaredAnnotation(Tagged.class);
                    return mTagged != null && mTagged.value() == tag;
                })
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Page does not contain tagged Filed with name: " + tag.name()));

        boolean accessible = mField.isAccessible();
        mField.setAccessible(true);
        try {
            return mField.get(this); //return
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            mField.setAccessible(accessible);
        }
    }

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }


    @NotNull
    private WebElement findWebElementByTag(Tag tag) {
        Object mObject = findObjectByTag(tag);
        Objects.requireNonNull(mObject);
        if (mObject instanceof WebElement) {
            return (WebElement) mObject;
        } else
            throw new ClassCastException("can't cast " + mObject.getClass().getTypeName() + " to WebElement");
    }

}
