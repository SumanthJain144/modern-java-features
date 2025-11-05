package session3;

public class PatternMatchingSwitchDemo {

    public static void main(String[] args) {
        Object[] inputs = {
                new Circle(4.0),
                new Rectangle(5.0, 3.0),
                new Rectangle(7.0, 7.0),
                "Just a string",
                null
        };

        System.out.println("Before Pattern Matching for switch:");
        for (Object input : inputs) {
            beforeScenario(input);
        }

        System.out.println("\nAfter Pattern Matching for switch:");
        for (Object input : inputs) {
            afterScenario(input);
        }
    }

    private static void beforeScenario(Object obj) {
        if (obj == null) {
            System.out.println("null value");
        } else if (obj instanceof Circle) {
            Circle c = (Circle) obj;
            System.out.println("Circle with area: " + (Math.PI * c.radius() * c.radius()));
        } else if (obj instanceof Rectangle) {
            Rectangle r = (Rectangle) obj;
            if (r.width() == r.height()) {
                System.out.println("Square area: " + r.width() * r.height());
            } else {
                System.out.println("Rectangle area: " + (r.width() * r.height()));
            }
        } else {
            System.out.println("Unknown type: " + obj);
        }
    }

    private static void afterScenario(Object obj) {
        var result = switch (obj) {
            case null -> "null value";
            case Circle c -> "Circle area: " + (Math.PI * c.radius() * c.radius());
            case Rectangle r when r.width() == r.height() -> "Square area: " + r.width() * r.height();
            case Rectangle r -> "Rectangle area: " + (r.width() * r.height());
            default -> "Unknown shape";
        };

        System.out.println(result);
    }


    sealed interface Shape permits Circle, Rectangle {
    }

    record Circle(double radius) implements Shape {
    }

    record Rectangle(double width, double height) implements Shape {
    }
}
