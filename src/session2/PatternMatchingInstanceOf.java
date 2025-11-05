package session2;




//Java 16

public class PatternMatchingInstanceOf {

    // Java 13: Manual instanceof and casting
	public static void processShapeEarier(Shape shape) {
	    if (shape instanceof Circle) {
	        Circle circle = (Circle) shape;
	        System.out.println("Circle area: " + circle.area());
	        System.out.println("Circle circumference: " + circle.perimeter());
	    } else if (shape instanceof Rectangle) {
	        Rectangle rectangle = (Rectangle) shape;
	        System.out.println("Rectangle area: " + rectangle.area());
	        System.out.println("Rectangle perimeter: " + rectangle.perimeter());
	    } else if (shape instanceof Triangle) {
	        Triangle triangle = (Triangle) shape;
	        System.out.println("Triangle area: " + triangle.area());
	        System.out.println("Triangle perimeter: " + triangle.perimeter());
	    } else {
	        System.out.println("Unknown shape");
	    }
	}

    // Java 14 to 16: Pattern matching for instanceof (preview in 14/15, standard in 16+)
	public static void processShapeNow(Shape shape) {
	    if (shape instanceof Circle c) {
	        System.out.println("Circle area: " + c.area());
	        System.out.println("Circle circumference: " + c.perimeter());
	    } else if (shape instanceof Rectangle r) {
	        System.out.println("Rectangle area: " + r.area());
	        System.out.println("Rectangle perimeter: " + r.perimeter());
	    } else if (shape instanceof Triangle t) {
	        System.out.println("Triangle area: " + t.area());
	        System.out.println("Triangle perimeter: " + t.perimeter());
	    } else {
	        System.out.println("Unknown shape");
	    }
	}
    // Java 24: Pattern matching for primitives (preview)
    @SuppressWarnings("preview")
	public static void java24PrimitiveExample(Object obj) {
        System.out.println("Java 24 Primitive Pattern Example:");
        if (obj instanceof int i) {
            System.out.println("Primitive int: " + i);
        } else if (obj instanceof double d) {
            System.out.println("Primitive double: " + d);
        } else {
            System.out.println("Other type");
        }
        System.out.println();
    }

    public static void main(String[] args) {


        processShapeEarier(new Circle(5.0));
        processShapeNow(new Triangle(6.0,5.0,6.0));

    }
}



abstract class Shape {
    abstract double area();
    abstract double perimeter();
}

class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double perimeter() {
        return 2 * (length + width);
    }
}

class Triangle extends Shape {
    private final double a, b, c;  // Lengths of sides

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        // Using Heron's formula
        double s = perimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }
}
