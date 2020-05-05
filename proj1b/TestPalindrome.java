import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String a = "aba";
        String b = "a";
        String c = "";
        String d = "abc";
        String e = "abba";

        assertTrue(palindrome.isPalindrome(a) && palindrome.isPalindrome(b));
        assertTrue( palindrome.isPalindrome(c) && palindrome.isPalindrome(e));
        assertFalse(palindrome.isPalindrome(d));

        // Test for the off-by-1 palindrome.
        String offByOne = "flake";
        assertTrue(palindrome.isPalindrome(offByOne, new OffByOne()));

        String offBy5 = "mouth";
        assertTrue(palindrome.isPalindrome(offBy5, new OffByN(5)));
    }
}
