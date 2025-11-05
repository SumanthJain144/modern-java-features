package session6;

//Java 21 (preview)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SequencedCollection;

public class SequencedCollectionComparison {
    public static void main(String[] args) {
        System.out.println("=== Before Java 21 (No SequencedCollection) ===");
        beforeSequencedCollection();

        System.out.println("\n=== With SequencedCollection (Java 21+) ===");
        withSequencedCollection();
    }

    // Before Java 21: Manual operations
    static void beforeSequencedCollection() {
        List<String> list = new ArrayList<>();
        list.add("Banana");              // [Banana]
        list.add(0, "Apple");            // addFirst: [Apple, Banana]
        list.add(list.size(), "Cherry"); // addLast: [Apple, Banana, Cherry]

        System.out.println("First: " + list.get(0));                  // Apple
        System.out.println("Last: " + list.get(list.size() - 1));     // Cherry

        // Remove first and last
        list.remove(0);                          // removeFirst
        list.remove(list.size() - 1);            // removeLast
        System.out.println("After removals: " + list); // [Banana]

        // Reversed view (must create new list)
        List<String> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        System.out.println("Reversed: " + reversed); // [Banana]
    }

    // Java 21+: SequencedCollection API
    static void withSequencedCollection() {
        SequencedCollection<String> sc = new ArrayList<>();
        sc.add("Banana");           // [Banana]
        sc.addFirst("Apple");       // [Apple, Banana]
        sc.addLast("Cherry");       // [Apple, Banana, Cherry]

        System.out.println("First: " + sc.getFirst()); // Apple
        System.out.println("Last: " + sc.getLast());   // Cherry

        sc.removeFirst();           // [Banana, Cherry]
        sc.removeLast();            // [Banana]
        System.out.println("After removals: " + sc);

        // Live reversed view
        SequencedCollection<String> reversed = sc.reversed();
        System.out.println("Reversed: " + reversed);   // [Banana]

        // Modifying reversed view reflects in original
        reversed.addFirst("Date");
        System.out.println("Original after modifying reversed: " + sc); // [Banana, Date]
    }
}
