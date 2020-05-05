public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> chars = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            chars.addLast(word.charAt(i));
        }
        return chars;
    }

    /** To verify if the word is palindrome. */
    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word), word.length());
    }

    /** A helper method of isPalindrome. */
    private boolean isPalindromeHelper(Deque word, int length) {
        if (length == 0 || length == 1) {
            return true;
        } else if (word.removeFirst() != word.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(word, length - 2);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(wordToDeque(word), word.length(), cc);
    }

    private boolean isPalindromeHelper(Deque word, int length, CharacterComparator cc) {
        if (length == 0 || length == 1) {
            return true;
        } else if (!cc.equalChars((char) word.removeFirst(), (char) word.removeLast())) {
            return false;
        } else {
            return isPalindromeHelper(word, length - 2, cc);
        }
    }

}
