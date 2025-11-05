package session2;

// pattern matching evolves further in Java 19+

public class PatternMatchingUnnamedRecords {

    // Define the record classes (Java 16+ feature)
    record Point(int x, int y) {} 
    record Line(Point start, Point end) {} 
    record Triangle(Point a, Point b, Point c) {} 
    record ColoredPoint(Point point, String color) {}  // Records with nested objects

    // Before Java 21: Classic extraction without pattern matching
    public static int processClassic(Object obj) {
        System.out.println("Classic (pre-Java 21) extraction:");
        // Manually extract values based on type using casting
        if (obj instanceof Point) {
            Point p = (Point) obj;
            return p.x() + p.y();  // Sum of x and y
        } else if (obj instanceof Line) {
            Line l = (Line) obj;
            // Recursively process points in the line
            return processClassic(l.start()) + processClassic(l.end());
        } else if (obj instanceof Triangle) {
            Triangle t = (Triangle) obj;
            // Recursively process points in the triangle
            return processClassic(t.a()) + processClassic(t.b()) + processClassic(t.c());
        } else {
            return 0;
        }
    }

    // After Java 21: Pattern matching for records and other objects
    public static int processWithPatternMatching(Object obj) {
        System.out.println("Pattern matching extraction:");
        // With pattern matching, we can directly extract values in the instanceof check
        if (obj instanceof Point p) {
            return p.x() + p.y();  // Directly bind to variables p.x and p.y
        } else if (obj instanceof Line l) {
            // Recursively process points in the line using pattern matching
            return processWithPatternMatching(l.start()) + processWithPatternMatching(l.end());
        } else if (obj instanceof Triangle t) {
            // Recursively process points in the triangle using pattern matching
            return processWithPatternMatching(t.a()) + processWithPatternMatching(t.b()) + processWithPatternMatching(t.c());
        } else {
            return 0;
        }
    }

    // Demonstrating unnamed patterns (Java 21+ / 23+ / 24+)
    public static void unnamedPatternExamples() {
        System.out.println("Unnamed patterns (Java 21+/23+/24+):");

        // Create a ColoredPoint object (contains a Point and a color)
        ColoredPoint cp = new ColoredPoint(new Point(5, 10), "blue");

        // Classic pattern matching (with named components)
        if (cp instanceof ColoredPoint(Point p, String c)) {
            // Extract both the Point and the color
            System.out.println("Classic: x=" + p.x() + ", y=" + p.y() + ", color=" + c);
        }

        // Unnamed pattern: only care about the Point, ignore the color
        if (cp instanceof ColoredPoint(Point p, _)) {
            System.out.println("Unnamed pattern: x=" + p.x() + ", y=" + p.y());
        }

        // Nested unnamed pattern: only care about x of the Point, ignore y and color
        if (cp instanceof ColoredPoint(Point(int x, _), _)) {
            System.out.println("Nested unnamed pattern: x=" + x);
        }

        System.out.println();  // Empty line to separate examples
    }

    public static void main(String[] args) {
        // Create some points and a triangle
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);
        Triangle triangle = new Triangle(p1, p2, p3);

        // Classic extraction (pre-Java 21) using casting
        System.out.println("Triangle sum (classic): " + processClassic(triangle));
        System.out.println();

        // Pattern matching extraction (Java 21+)
        System.out.println("Triangle sum (pattern matching): " + processWithPatternMatching(triangle));
        System.out.println();

        // Unnamed patterns (Java 21+/23+/24+)
        unnamedPatternExamples();
    }
}

//
//Conciseness: Unnamed patterns allow you to ignore irrelevant fields, reducing unnecessary code.
//
//Focused Matching: You can match only the fields you care about, making the code more efficient.
//
//Improved Readability: The code becomes clearer by explicitly showing which parts of the object are being used.
//
//Expressiveness: Unnamed patterns convey intent more effectively, highlighting what is relevant in the object.
//
//Cleaner Code: Reduces boilerplate and eliminates the need for extra variables to store ignored fields.