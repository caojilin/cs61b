import java.util.LinkedList;

public class SimpleNameMap {

    /* Instance variables here? */
    LinkedList<Entry>[] arr;

    SimpleNameMap(String key, String value) {
        arr = (LinkedList<Entry>[]) new Object[10];
    }

    int hashCode(String key) {
        return key.hashCode();
    }

    int size() {
        int total = 0;
        for (LinkedList e : arr) {
            total += e.size();
        }
        return total;
    }

    void resize() {
        float loadFactor = size() / arr.length;
        if (loadFactor > 0.75) {
            LinkedList<Entry>[] newarr = (LinkedList<Entry>[]) new Object[arr.length * 2];
            System.arraycopy(arr, 0, newarr, 0, arr.length);
        }
    }

    /* Returns true if the given KEY is a valid name that starts with A - Z. */
    private static boolean isValidName(String key) {
        return 'A' <= key.charAt(0) && key.charAt(0) <= 'Z';
    }

    /* Returns true if the map contains the KEY. */
    boolean containsKey(String key) {
        int index = hashCode(key);
        Entry temp = new Entry(key, "");
        for (Entry e : arr[index]) {
            if (temp.keyEquals(e)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the value for the specified KEY. If KEY is not found, return
       null. */
    String get(String key) {
        int index = hashCode(key);
        Entry temp = new Entry(key, "");
        for (Entry e : arr[index]) {
            if (temp.keyEquals(e)) {
                return e.value;
            }
        }
        return null;
    }

    /* Puts a (KEY, VALUE) pair into this map. If the KEY already exists in the
       SimpleNameMap, replace the current corresponding value with VALUE. */
    void put(String key, String value) {
        int index = Math.floorMod(key.hashCode(), arr.length);
        resize();
        arr[index].add(new Entry(key, value));
    }

    /* Removes a single entry, KEY, from this table and return the VALUE if
       successful or NULL otherwise. */
    String remove(String key) {
        int index = hashCode(key);
        Entry temp = new Entry(key, "");
        for (Entry e : arr[index]) {
            if (temp.keyEquals(e)) {
                arr[index].remove(e);
                return e.value;
            }
        }
        return null;
    }


    private static class Entry {

        private String key;
        private String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
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
}
