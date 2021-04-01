import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int number;                 // number of bins.
    private int size;                   // number of key-values pairs.
    private double loadFactor;
    private LinkedList<Pair>[] bins;
    private HashSet<K> keys;

    private class Pair {
        K key;
        V value;
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % number;
    }

    private void resize() {
        number *= 2;
        loadFactor = (double) size / number;
        LinkedList<Pair>[] tmp = bins;
        bins = new LinkedList[number];
        for (LinkedList<Pair> bin : tmp) {
            if (bin != null) {
                for (Pair t : bin) {
                    put(t);
                }
            }
        }
    }

    public MyHashMap() {
        this(16, 0.75);
    }


    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        if (loadFactor > 0.75) {
            initialSize *= 2;
        }
        this.loadFactor = loadFactor;
        this.number = initialSize;
        this.bins = new LinkedList[number];
        this.keys = new HashSet<>();
    }

    @Override
    public void clear() {
        size = 0;
        updateLoadFactor();
        for (int i = 0; i < number; i++) {
            bins[i] = null;
        }
        keys.clear();
    }

    private void updateLoadFactor() {
        loadFactor = (double) size / number;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null.");
        }
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            return getPair(key).value;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            getPair(key).value = value;
        } else {
            put(new Pair(key, value));
            size += 1;
            loadFactor = (double) size / number;
            if (loadFactor > 0.75) {
                resize();
            }
            keys.add(key);
        }
    }


    private void put(Pair kvPair) {
        int binNum = hash(kvPair.key);
        if (bins[binNum] == null) {
            bins[binNum] = new LinkedList<>();
            bins[binNum].add(kvPair);
        } else {
            bins[binNum].add(kvPair);
        }
    }

    private Pair getPair(K key) {
        int binNum = hash(key);
        int binSize = bins[binNum].size();
        for (int i = 0; i < binSize; i++) {
            if (key.equals(bins[binNum].get(i).key)) {
                return bins[binNum].get(i);
            }
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            V val = get(key);
            bins[hash(key)].remove(getPair(key));
            keys.remove(key);
            size -= 1;
            return val;
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        if (containsKey(key)) {
            if (get(key).equals(value)) {
                return remove(key);
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
