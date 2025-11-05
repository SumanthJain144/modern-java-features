package session4;
//Java 19 - stable in 21
public class VirtualThreadBasicExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.ofVirtual().start(() -> System.out.println("Hello from virtual thread!"));
        thread.join();  // Wait for the virtual thread to finish before exiting
    }
}
