package ge.demo.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class AssertLater {

    List<Error> errors = Lists.newArrayList();

    boolean showStackTrace;

    public AssertLater(boolean showStackTrace) {
        this.showStackTrace = showStackTrace;
    }

    public AssertLater() {
        this(true);
    }

    public void test(Runnable runnable) {
        try {
            runnable.run();
        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    public void assertAll() {
        if (!errors.isEmpty()) {
            StringBuffer builder = new StringBuffer();
            builder.append("\n");
            AtomicInteger index = new AtomicInteger(1);
            errors.stream()
                    .map(Error::getMessage)
                    .forEach((s) -> builder.append("Failed ")
                            .append(index.getAndIncrement())
                            .append(": ")
                            .append(s)
                            .append("\n"));
            Error mError = new AssertionError(builder.toString());
            if (showStackTrace) {
                errors.forEach(mError::addSuppressed);
            }
            throw mError;
        }
    }
}
