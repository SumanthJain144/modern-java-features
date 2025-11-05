package session4;
//Java 22

import java.lang.classfile.ClassFile;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.nio.file.Path;

//class api
public class ClassFileApi {
    public static void main(String[] args) throws Exception {
        // Generate a class named HelloWorld with a main method
        ClassFile.of()
            .buildTo(
                Path.of("HelloWorld.class"),
                ClassDesc.of("HelloWorld"),
                classBuilder -> classBuilder.withMethodBody(
                    "main",
                    MethodTypeDesc.ofDescriptor("([Ljava/lang/String;)V"),
                    0x0009, // ACC_PUBLIC | ACC_STATIC
                    codeBuilder -> codeBuilder
                        .getstatic(
                            ClassDesc.of("java.lang.System"),
                            "out",
                            ClassDesc.of("java.io.PrintStream")
                        )
                        .ldc("class api")
                        .invokevirtual(
                            ClassDesc.of("java.io.PrintStream"),
                            "println",
                            MethodTypeDesc.ofDescriptor("(Ljava/lang/Object;)V")
                        )
                        .return_()
                )
            );
    }
}

