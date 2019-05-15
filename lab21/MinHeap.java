import java.util.ArrayList;

/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> {

    /* An ArrayList that stores the elements in this MinHeap. */
    private ArrayList<E> contents;
    private int size;

    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>(50);
        contents.add(null);
        size = 0;
    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        return index * 2;
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        return index * 2 + 1;
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        return index / 2;
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. */
    private int min(int index1, int index2) {
        E left = getElement(index1);
        E right = getElement(index2);
        if (left == null && right != null) {
            return index2;
        } else if (right == null && left != null) {
            return index1;
        }
        if (left.compareTo(right) < 0) {
            return index1;
        } else {
            return index2;
        }
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E peek() {
        return getElement(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        int parIndex = getParentOf(index);
        if (parIndex != 0) {
            if (contents.get(index).compareTo(contents.get(parIndex)) < 0) {
                swap(index, parIndex);
                bubbleUp(parIndex);
            }
        }
    }

    /* Bubbles down the element currently at index INDEX. */
    private void bubbleDown(int index) {
        int leftIndex = getLeftOf(index);
        int rightIndex = getRightOf(index);
        if (leftIndex >= contents.size()) {
            return;
        } else {
            int minIndex = min(leftIndex, rightIndex);
            if (contents.get(index).compareTo(getElement(minIndex)) > 0) {
                swap(index, minIndex);
                bubbleDown(minIndex);
            }
        }
    }

    /* Inserts element into the MinHeap. */
    public void insert(E element) {

        int currIndex = size + 1;
        setElement(currIndex, element);
        bubbleUp(currIndex);
        size += 1;
    }

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        return size;
    }

    /* Returns the smallest element. */
    public E removeMin() {
        //Swap the element at the root with the element in the bottom rightmost position of the tree
        E toReturn = getElement(1);
        swap(1, size);
        contents.remove(size);
        bubbleDown(1);
        size -= 1;
        return toReturn;
    }

    /* Updates the position of ELEMENT inside the MinHeap, which may have been
       mutated since the inital insert. If a copy of ELEMENT does not exist in
       the MinHeap, do nothing.*/
    public void update(E element) {

        int currIndex = contents.indexOf(element);
        if (currIndex == -1) {
            return;
        }
        setElement(currIndex, element);
        bubbleUp(currIndex);
        bubbleDown(currIndex);
    }
}
