package session4;
//java 23 preview .stable in java 25
import java.util.Arrays;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import module java.base;

public class ModuleImport {
    public static Map<Character, List<String>> groupByFirstLetter(String... values) {
        return Arrays.stream(values)
            .collect(Collectors.groupingBy(
                s -> s.charAt(0)
            ));
    }

    public static void main(String[] args) {
        System.out.println(groupByFirstLetter("apple", "banana", "apricot", "blueberry"));
    }
}