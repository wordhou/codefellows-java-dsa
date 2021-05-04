package challenges.utilities;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordFrequencyCounter {
    static final String delimiterRegex = "([^a-zA-Z']+)|( ')|(' )";

    public String firstDuplicateWord(String s) {
        Set<String> hashSet = new HashSet<>();
        Iterator<String> iterator = Pattern.compile(delimiterRegex).splitAsStream(s).iterator();

        while (iterator.hasNext()) {
            String word = iterator.next().toLowerCase(Locale.ROOT);
            if (hashSet.contains(word)) return word;
            else hashSet.add(word);
        }

        return null;
    }

    public Map<String, Integer> countFrequencies(String str) {
        Map<String, Integer> count = new HashMap<>();
        Pattern.compile(delimiterRegex).splitAsStream(str)
                .forEach(s -> count.merge(s.toLowerCase(Locale.ROOT), 1, Integer::sum));
        return count;
    }

    public List<String> mostCommonWords(String str, int k) {
        Map<String, Integer> count = new HashMap<>();
        Pattern.compile(delimiterRegex).splitAsStream(str)
                .forEach(s -> count.merge(s.toLowerCase(Locale.ROOT), 1, Integer::sum));
        return count.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}