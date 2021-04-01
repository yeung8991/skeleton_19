import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node node;

    private class Node {
        K key;
        V value;
        Node left, right;
        int size;

        Node(K k, V v) {
            key = k;
            value = v;
            size = 1;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        node.size = 0;
        node = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        }
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(key, node);
    }

    private V get(K key, Node tree) {
        if (key == null) {
            throw new IllegalArgumentException("call get() with a null key.");
        }
        if (tree == null) {
            return null;
        }
        int cmp = key.compareTo(tree.key);
        if (cmp == 0) {
            return tree.value;
        } else if (cmp < 0) {
            return get(key, tree.left);
        } else {
            return get(key, tree.right);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(node);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        node = put(key, value, node);
    }

    private Node put(K key, V value, Node tree) {
        if (tree == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(tree.key);
        if (cmp == 0) {
            tree.value = value;
        } else if (cmp < 0) {
            tree.left = put(key, value, tree.left);
        } else {
            tree.right = put(key, value, tree.right);
        }
        tree.size = 1 + size(tree.left) + size(tree.right);
        return tree;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet(node, new HashSet<>());
    }

    private Set<K> keySet(Node node, Set<K> keys) {
        if (node == null) {
            return keys;
        }
        keys = keySet(node.left, keys);
        keys.add(node.key);
        keys = keySet(node.right, keys);
        return keys;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null");
        }
        V val = get(key);
        if (val == null) {
            return null;
        }
        node = remove(key, node);
        return val;
    }

    private Node remove(K key, Node tree) {
        if (tree == null) {
            return null;
        }
        int cmp = key.compareTo(tree.key);
        if (cmp < 0) {
            tree.left = remove(key, tree.left);
        } else if (cmp > 0) {
            tree.right = remove(key, tree.right);
        } else {
            if (tree.right == null) {
                return tree.left;
            }
            if (tree.left == null) {
                return tree.right;
            }
            Node tmp = tree;
            tree = max(tmp.left);
            tree.left = removeMax(tmp.left);
            tree.right = tmp.right;
        }
        tree.size = size(tree.left) + size(tree.right) + 1;
        return tree;
    }

    private Node max(Node n) {
        if (n == null) {
            return null;
        }
        if (n.right == null) {
            return n;
        } else {
            return max(n.right);
        }
    }

    private Node removeMax(Node n) {
        if (n == null) {
            return null;
        }
        if (n.right == null) {
            return n.left;
        } else {
            n.right = removeMax(n.right);
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (get(key) == value) {
            node = remove(key, node);
            return get(key);
        }
        return null;
    }
}
