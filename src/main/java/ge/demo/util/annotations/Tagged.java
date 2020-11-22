package ge.demo.util.annotations;


import ge.demo.util.Tag;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Tagged {
    Tag value();
}
