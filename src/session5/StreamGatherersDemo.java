package session5;

import java.util.List;
import java.util.stream.Gatherers;

public class StreamGatherersDemo {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(1, 2, 3, 4, 5);

		// 1) windowFixed(n) - Non-overlapping windows
		System.out.println("\nwindowFixed(2):");
		var fixedWindows = numbers.stream()
				.gather(Gatherers.windowFixed(2))
				.toList();
		System.out.println(fixedWindows); // [[1,2], [3,4], [5]]

		// 2) windowSliding(n) - Sliding movable window (overlapping)
		System.out.println("\nwindowSliding(3):");
		var slidingWindows = numbers.stream()
				.gather(Gatherers.windowSliding(3))
				.toList();
		System.out.println(slidingWindows); // [[1,2,3], [2,3,4], [3,4,5]]

		// 3) scan → cumulative concatenation
		System.out.println("\nscan (cumulative concat string):");
		var cumulative = numbers.stream()
				.gather(Gatherers.scan(() -> "", (acc, n) -> acc + n))
				.toList();
		System.out.println(cumulative); // ["", "1", "12", "123", "1234", "12345"]

		// 4) fold - final concatenated result (single output element stream)
		System.out.println("\nfold (final concat string):");
		var foldedResult = numbers.stream()
				.gather(Gatherers.fold(() -> "", (acc, n) -> acc + n))
				.findFirst()
				.orElse("");
		System.out.println(foldedResult); // "12345"

		// 5) mapConcurrent → parallel-safe mapping
		System.out.println("\nmapConcurrent (Double the values concurrently):");
		var doubled = numbers.stream()
				.gather(Gatherers.mapConcurrent(4, n -> {
			sleep();
			return n * 2;
		})).toList();
		System.out.println(doubled); // output order is preserved but computation is parallel
	}

	private static void sleep() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException ignored) {
		}
	}
}