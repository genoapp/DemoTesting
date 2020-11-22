package ge.demo.util;

import java.util.Objects;

public class Size {
    private float width;
    private float height;

    public Size(float width, float height) {
        this.width = width;
        this.height = height;
    }


    public Size setWidth(float width) {
        this.width = width;
        return this;
    }

    public Size setHeight(float height) {
        this.height = height;
        return this;
    }

    public float getHeight() {
        return height;
    }


    public float getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size)) return false;
        Size size = (Size) o;
        return Float.compare(size.width, width) == 0 &&
                Float.compare(size.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "Size{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
