import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A DLList is a list of integers. Like SLList, it also hides the terrible
 * truth of the nakedness within, but with a few additional optimizations.
 */
public class DLList<Item> implements Iterable<Item> {
    private class Node {
        private Node prev;
        private Item item;
        private Node next;

        private Node(Item i, Node p, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public Iterator<Item> iterator() {
        return new DLListIterator();
    }


    private class DLListIterator implements Iterator<Item> {
        private Node bookmark = sentinel;

        public Item next() {
            Item toReturn = bookmark.next.item;
            bookmark = bookmark.next;
            return toReturn;
        }

        public boolean hasNext() {
            if (bookmark.next.item != null) {
                return true;
            }
            return false;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;
    private int size;

    /**
     * Creates an empty DLList.
     */
    public DLList() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Returns a DLList consisting of the given values.
     */
    public static <Item> DLList<Item> of(Item... values) {
        DLList<Item> list = new DLList<>();
        for (Item value : values) {
            list.addLast(value);
        }
        return list;
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Adds item to the back of the list.
     */
    public void addLast(Item item) {
        Node n = new Node(item, sentinel.prev, sentinel);
        n.next.prev = n;
        n.prev.next = n;
        size += 1;
    }

    @Override
    public String toString() {
        String result = "";
        Node p = sentinel.next;
        boolean first = true;
        while (p != sentinel) {
            if (first) {
                result += p.item.toString();
                first = false;
            } else {
                result += " " + p.item.toString();
            }
            p = p.next;
        }
        return result;
    }

    /**
     * Returns whether this and the given list or object are equal.
     */
    public boolean equals(Object o) {
        DLList other = (DLList) o;
        if (size() != other.size()) {
            return false;
        }
        Node op = other.sentinel.next;
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            if (!(p.item.equals(op.item))) {
                return false;
            }
            op = op.next;
        }
        return true;
    }


    public static void main(String[] args) {
        List<String> friends = new ArrayList<>();
        friends.add("Nyan");
        friends.add("Meow");
        friends.add("Nyaahh");
        for (String friend : friends) {
            friends.remove(1);
            System.out.println("friend=" + friend);
        }

//        DLList<Integer> d = DLList.of(1,2,3,4);
//        Iterator<Integer> i = d.iterator();
//        boolean b;
//        b = i.hasNext();
//        b = i.hasNext();
    }
}
