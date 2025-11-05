package session4;
//Java 22
import java.io.IOException;
import java.nio.file.Path;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.MethodModel;

public class ClassFileParse{ 
    public static void main(String[] args) throws IOException {
        // Path to your HelloWorld.class file
        Path classFilePath = Path.of("HelloWorld.class");

        // Parse the class file
        ClassModel classModel = ClassFile.of().parse(classFilePath);

        // Get and print the class name
        String className = classModel.thisClass().name().stringValue();
        System.out.println("Class: " + className);

        // Iterate over the class elements and print method names
        for (var element : classModel) {
            if (element instanceof MethodModel method) {
                System.out.println("Method: " + method.methodName().stringValue());
            }
        }
    }
}