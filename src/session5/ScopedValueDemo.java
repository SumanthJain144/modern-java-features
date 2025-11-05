package session5;

import java.lang.ScopedValue;

public class ScopedValueDemo {

    // Define the scoped value (immutable, thread-safe)
    private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

    public static void main(String[] args) {

        // Bind a value to this scope for the duration of the run block
        ScopedValue.where(REQUEST_ID, "REQ-12345").run(() -> {
            controllerMethod();
        });

        // Outside scope → accessing it is illegal
        try {
            System.out.println(REQUEST_ID.get());
        } catch (Exception e) {
            System.out.println("Outside scope access → " + e.getMessage());
        }
    }

    private static void controllerMethod() {
        System.out.println("[Controller] requestId = " + REQUEST_ID.get());
        serviceMethod();
    }

    private static void serviceMethod() {
        System.out.println("[Service] requestId = " + REQUEST_ID.get());
        repositoryMethod();
    }

    private static void repositoryMethod() {
        System.out.println("[Repository] requestId = " + REQUEST_ID.get());
    }
}