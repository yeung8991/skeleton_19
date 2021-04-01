import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private static final int R = 128;

    private Node root;
    private int size;

    private class Node {
        boolean isKey;
        Node[] next = new Node[R];
    }

    public MyTrieSet() {
        root = new Node();
    }

    @Override
    public void clear() {
        root = new Node();
    }

    @Override
    public boolean contains(String key) {
        if (root == null) {
            return false;
        }
        return contains(key, root, 0);
    }

    private boolean contains(String key, Node n, int i) {
        if (n == null) {
            return false;
        }
        if (i == key.length()) {
            return n.isKey;
        }
        return contains(key, n.next[key.charAt(i)], i + 1);
    }

    @Override
    public void add(String key) {
        if (!contains(key)) {
            add(key, root, 0);
        }
    }

    private void add(String key, Node n, int i) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        if (i == key.length()) {
            n.isKey = true;
        } else {
            char c = key.charAt(i);
            if (n.next[c] == null){
                n.next[c] = new Node();
            }
            add(key, n.next[c], i + 1);
        }
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> keyList = new ArrayList<>();
        collect(new StringBuilder(prefix), getNode(prefix, root, 0), keyList);
        return keyList;
    }

    private Node getNode(String prefix, Node n, int i) {
        if (i == prefix.length()) {
            return n;
        }
        if (n.next[prefix.charAt(i)] == null) {
            return null;
        }
        return getNode(prefix, n.next[prefix.charAt(i)], i + 1);
    }

    private void collect(StringBuilder prefix, Node n, List<String> keyList) {
        if (n == null) {
            return;
        }
        if (n.isKey) {
            keyList.add(prefix.toString());
        }
        for (int i = 0; i < R; i++) {
            collect(prefix.append((char) i), n.next[i], keyList);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        int length = longestPrefixOf(key, root, 0, -1);
        if (length == -1) {
            return null;
        }
        return key.substring(0, length);
    }

    private int longestPrefixOf(String key, Node n, int i, int length) {
        if (n == null) {
            return length;
        }
        if (n.isKey) {
            length = i;
        }
        if (i == key.length()) {
            return length;
        }
        return longestPrefixOf(key, n.next[key.charAt(i)], i + 1, length);
    }
}
