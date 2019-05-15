public class ArrayOperations {
    /**
     * Delete the value at the given position in the argument array, shifting
     * all the subsequent elements down, and storing a 0 as the last element of
     * the array.
     */
    public static void delete(int[] values, int pos) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        // TODO: YOUR CODE HERE
        int curr = pos;
        while(curr != values.length-1){
            values[curr] = values[curr + 1];
            curr += 1;
        }
        values[values.length - 1] = 0;
    }

    /**
     * Insert newInt at the given position in the argument array, shifting all
     * the subsequent elements up to make room for it. The last element in the
     * argument array is lost.
     */
    public static void insert(int[] values, int pos, int newInt) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        // TODO: YOUR CODE HERE
        int curr = values.length-1;
        while(curr != pos) {
            values[curr] = values[curr - 1];
            curr -= 1;
        }
        values[pos] = newInt;
    }
}
