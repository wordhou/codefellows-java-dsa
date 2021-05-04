package challenges.utilities;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class WordFrequencyCounterTest {

    WordFrequencyCounter counter = new WordFrequencyCounter();


    @Test
    public void firstDuplicateWord() {
        assertNull("Empty string", counter.firstDuplicateWord(""));
        assertNull("String with no duplicate", counter.firstDuplicateWord("The quick red fox jumps over a lazy brown dog."));
        assertEquals("String with same case duplicate",
                "the", counter.firstDuplicateWord("the quick red fox jumps over the lazy brown dog."));
        assertEquals("String with same case duplicate with multiple sets of duplicates",
                "the", counter.firstDuplicateWord("the quick red fox jumps over the lazy brown fox."));
        assertEquals("String with different case duplicate",
                "the", counter.firstDuplicateWord("The quick red fox jumps over the lazy brown fox."));
        assertEquals("String with different case duplicate",
                "the", counter.firstDuplicateWord("The quick red fox jumps over the lazy brown fox."));
        assertEquals("String with different case duplicate",
                "quick", counter.firstDuplicateWord("Quick red fox QuIcK jumps over the lazy brown fox."));
        assertEquals("String with non-duplicate words that are substrings",
                "the", counter.firstDuplicateWord("The quick red fox quickly jumps over the lazy brown fox."));
        assertEquals("String with similar words",
                "fox", counter.firstDuplicateWord("The quick reddish fox lazily but quickly jumps over a red lazy brown fox."));
        assertEquals("String with punctuation",
                "the", counter.firstDuplicateWord("Hark!!~ The herald angels sing; Glory --- to the king of Kings!"));
    }

    @Test
    public void countFrequencies() {
    }
}