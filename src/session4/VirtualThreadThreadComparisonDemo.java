package session4;
//Java 19 - stable in 21
public class VirtualThreadThreadComparisonDemo {
    public static void main(String[] args) throws InterruptedException {
        int taskCount = 10000;

        // Platform threads demo
        long platformStart = System.currentTimeMillis();
        Thread[] platformThreads = new Thread[taskCount];
        try {
            for (int i = 0; i < taskCount; i++) {
                platformThreads[i] = Thread.ofPlatform().unstarted(() -> {
                    try {
                        Thread.sleep(1000); // Simulate blocking I/O
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                platformThreads[i].start();
            }
            for (Thread t : platformThreads) t.join();
            long platformEnd = System.currentTimeMillis();
            System.out.println("Platform threads: All tasks completed in " + (platformEnd - platformStart) + " ms");
        } catch (OutOfMemoryError | Exception e) {
            System.out.println("Platform threads: Failed to create all threads - " + e);
        }

        // Virtual threads demo
        long virtualStart = System.currentTimeMillis();
        Thread[] virtualThreads = new Thread[taskCount];
        try {
            for (int i = 0; i < taskCount; i++) {
                virtualThreads[i] = Thread.ofVirtual().unstarted(() -> {
                    try {
                        Thread.sleep(1000); // Simulate blocking I/O
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                virtualThreads[i].start();
            }
            for (Thread t : virtualThreads) t.join();
            long virtualEnd = System.currentTimeMillis();
            System.out.println("Virtual threads: All tasks completed in " + (virtualEnd - virtualStart) + " ms");
        } catch (Exception e) {
            System.out.println("Virtual threads: Failed to create all threads - " + e);
        }
    }
}
//
//Platform Thread Demo: The program creates 10,000 platform threads, each simulating a blocking I/O task (using Thread.sleep(1000)), and measures the total time taken for all tasks to complete.
//
//Virtual Thread Demo: It creates the same number of virtual threads (10,000), each performing the same blocking I/O task, and measures the time taken for these tasks to complete.
//
//Performance Comparison: The program compares the time taken for platform threads and virtual threads to complete all tasks.
//
//Resource Efficiency: It highlights the difference in performance and resource usage, showing how virtual threads handle large numbers of concurrent tasks more efficiently than platform threads.
