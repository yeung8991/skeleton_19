import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.
    @Test
    public void testEqualChars() {
        char a = 'a';
        char b = 'f';
        assertTrue(offByN.equalChars(a, b) && offByN.equalChars(b, a));

        char c = 'c';
        char e = 'e';
        assertFalse(offByN.equalChars(c, e));
    }
}
