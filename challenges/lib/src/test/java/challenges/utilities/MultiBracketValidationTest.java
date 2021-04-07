package challenges.utilities;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static challenges.utilities.MultiBracketValidation.multiBracketValidation;

public class MultiBracketValidationTest {

    @Test
    public void emptyString() {
        String empty = "";
        assertTrue("empty strings are valid",
                multiBracketValidation(empty));
    }

    @Test
    public void noBrackets() {
        String noBrackets = "word";
        assertTrue("strings without brackets are valid",
                multiBracketValidation(noBrackets));
    }

    @Test
    public void oneSetOfBracketsGood() {
        List<String> cases = Arrays.asList(
                "(Hello World!)", "Hello(World)!", "Hello()World!", "Hello[ ]World!", "{Hello World!}"
        );

        cases.stream().forEach(c ->
            assertTrue("valid strings with brackets one level deep are valid",
                    multiBracketValidation(c)));
    }

    @Test
    public void nestedBracketsGood() {
        List<String> cases = Arrays.asList(
                "(Hello [Wo]rld!)",
                "[{Hello(Wo()rld)!}]",
                "H[e()l{}]lo[(){}]World[]!",
                "{H{ell{o{ W}or}ld}!}",
                "Hello[{()}]Wor[ld{!()}()]"
        );

        cases.stream().forEach(c ->
                assertTrue("complex nested sets of valid brackets are valid",
                        multiBracketValidation(c)));
    }

    @Test
    public void simpleBad() {
        List<String> cases = Arrays.asList(
                "[", "(", "{", ")", "}", "]", "[}", "{]", "(]", "(}", "[)", "{)"
        );

        cases.stream().forEach(c ->
                assertFalse("unclosed brackets, dangling closing brackets, and mismatched brackets are invalid",
                        multiBracketValidation(c)));
    }

    @Test
    public void simpleBadWithExtraCharacters() {
        List<String> cases = Arrays.asList(
                "[Hello world}", "]Hello world", "{Hello world", "Hello [ world", "Hello(} world"
        );

        cases.stream().forEach(c ->
                assertFalse("unclosed brackets, dangling closing brackets, and mismatched brackets are invalid",
                        multiBracketValidation(c)));
    }

    @Test
    public void nestedBadCases() {
        List<String> cases = Arrays.asList(
                "[{]}", "{({", "H[e]l(l){o [W}orld", "[{[{[{}(){]}]}]", "[({[{[{}()){}]", "(Hello [World]"
        );

        cases.stream().forEach(c ->
                assertFalse("unclosed brackets, dangling closing brackets, and mismatched brackets are invalid",
                        multiBracketValidation(c)));
    }
}