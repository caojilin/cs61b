import java.util.Iterator;
import java.util.LinkedList;

public class HashMap<K, V> implements Map61BL<K, V>, Iterable<K> {

    /* Instance variables here? */
    LinkedList<Entry>[] arr;
    float loadFactor = 0.75f;
    int size;

    /* Creates a new hash map with a default array of size 16 and load factor of 0.75. */
    HashMap() {
        arr = new LinkedList[16];
        size = 0;
    }

    /* Creates a new hash map with an array of size INITIALCAPACITY
    and default load factor of 0.75. */
    HashMap(int initialCapacity) {
        arr = new LinkedList[initialCapacity];
        size = 0;
    }

    /* Creates a new hash map with INITIALCAPACITY and LOADFACTOR. */
    HashMap(int initialCapacity, float loadFactor) {
        arr = new LinkedList[initialCapacity];
        this.loadFactor = loadFactor;
        size = 0;
    }

    /* Returns the length of this HashMap's internal array. */
    int capacity() {
        return arr.length;
    }

    float getLoadFactor() {
        return (float) size() / arr.length;
    }

    void resize() {
        float currLoadFactor = getLoadFactor();
        if (currLoadFactor > loadFactor) {
            LinkedList<Entry>[] newArr = new LinkedList[arr.length * 2];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != null) {
                    for (Entry entry : arr[i]) {
                        int index = Math.floorMod(entry.getKey().hashCode(), newArr.length);
                        newArr[index] = new LinkedList<>();
                        newArr[index].add(entry);
                    }
                }
            }
            arr = newArr;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        arr = new LinkedList[capacity()];
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        //TODO Maybe need to deal with same key problems.
        int index = Math.floorMod(key.hashCode(), arr.length);
        if (containsKey(key)) {
            LinkedList<Entry> linkedList = arr[index];
            Entry<K, V> entry = getSameKeyEntry(key);
            linkedList.remove(entry);
            linkedList.add(new Entry(key, value));
        } else {
            arr[index] = new LinkedList<Entry>();
            arr[index].add(new Entry(key, value));
            size += 1;
            resize();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {
        int index = 0;
        Iterator<Entry> currIter;

        @Override
        public K next() {
            return (K) currIter.next().key;
        }

        @Override
        public boolean hasNext() {
            if (arr[index] == null || !currIter.hasNext()) {
                index++;
                while (arr[index] == null) {
                    if (index == capacity() - 1) {
                        return false;
                    }
                    index++;
                }
                currIter = arr[index].iterator();
            }
            if (index == capacity() - 1 && !currIter.hasNext()) {
                return false;
            }
            return true;
        }
    }

    private Entry<K, V> getSameKeyEntry(K key) {
        Entry<K, V> temp = new Entry<>(key, null);
        int index = Math.floorMod(key.hashCode(), arr.length);
        LinkedList<Entry> linkedList = arr[index];
        for (Entry entry : linkedList) {
            if (temp.keyEquals(entry)) {
                return entry;
            }
        }
        return null;
    }


    @Override
    public boolean containsKey(K key) {
        int index = Math.floorMod(key.hashCode(), arr.length);
        Entry temp = new Entry(key, "");
        LinkedList linkedList = arr[index];
        if (linkedList == null) {
            return false;
        } else {
            for (Entry e : arr[index]) {
                if (temp.keyEquals(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(K key) {
        int index = Math.floorMod(key.hashCode(), arr.length);
        LinkedList<Entry> linkedList = arr[index];
        Entry<K, V> entry = getSameKeyEntry(key);
        linkedList.remove(entry);
        size -= 1;
        return entry.getValue();
    }

    @Override
    public boolean remove(K key, V value) {
        if (containsKey(key)) {
            remove(key);
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        Entry entry = getSameKeyEntry(key);
        if (entry != null) {
            return getSameKeyEntry(key).getValue();
        }
        return null;
    }


    /* Returns true if the given KEY is a valid name that starts with A - Z. */
    private static boolean isValidName(String key) {
        return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
    }

    private static class Entry<K, V> {

        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    public static void main(String[] args) {
//        HashMap<String, Integer> studentIDs = new HashMap<String, Integer>();
//        studentIDs.put("christine", 12345);
//        studentIDs.put("kevin", 345);
//        studentIDs.put("alex", 612);
//        studentIDs.put("carlo", 12345);
//        Iterator<String> iter = studentIDs.iterator();
//        System.out.println(iter.next());
//        HashMap h = new HashMap(5);
    }
}



