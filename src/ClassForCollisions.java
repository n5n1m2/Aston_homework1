import java.util.Objects;

public class ClassForCollisions {
    private final String field;

    public ClassForCollisions(String field) {
        this.field = field;
    }

    @Override
    public int hashCode() {
        return 100;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ClassForCollisions that)) return false;
        return Objects.equals(field, that.field);
    }

    public String getField() {
        return field;
    }
}
