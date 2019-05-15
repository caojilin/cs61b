/**
 * A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 */
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /**
     * Returns an IntList consisting of the given values.
     */
    public static IntList of(int... values) {
        if (values.length == 0) {
            return null;
        }
        IntList p = new IntList(values[0], null);
        IntList front = p;
        for (int i = 1; i < values.length; i++) {
            p.rest = new IntList(values[i], null);
            p = p.rest;
        }
        return front;
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + rest.size();
    }

    public void add(int value) {
        IntList temp = this;
        while (temp.rest != null) {
            temp = temp.rest;
        }
        temp.rest = new IntList(value, null);
    }

    public int smallest() {
        int small = first;
        IntList temp = this;
        while (temp.rest != null) {
            if (temp.rest.first < small) {
                small = temp.rest.first;
            }
            temp = temp.rest;
        }
        return small;
    }

    public int squaredSum() {
        int sum = first * first;
        IntList temp = this;
        while (temp.rest != null) {
            sum = sum + temp.rest.first * temp.rest.first;
            temp = temp.rest;
        }
        return sum;
    }


    /**
     * Returns the string representation of the list.
     */
    public String toString() {
        if (rest == null) {
            return "" + first;
        }
        return first + " " + rest.toString();
    }

    public static void dSquareList(IntList L) {

        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }


    public static IntList dcatenate(IntList A, IntList B) {
        IntList L = A;

        if (A == null) {
            return B;
        }
        while (A.rest != null) {
            A = A.rest;
        }
        A.rest = B;
        return L;
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     */
    public static IntList catenate(IntList A, IntList B) {
        if (B == null) {
            return A;
        }
        if (A == null) {
            return new IntList(B.first, B.rest);
        }
        return new IntList(A.first, catenate(A.rest, B));
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot/**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

}

