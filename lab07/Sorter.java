import java.util.Arrays;

public class Sorter {

    /* Uses insertion sort to sort the elements of VALUES by inserting item k
       into its correct position. Maintains the following invariant:
       values[0] <= values[1] <= ... <= values[k-1] */
    public static void sort(double[] values) {
        for (int k = 1; k < values.length; k += 1) {
            double temp = values[k];
            int j;
            for (j = k - 1; j >= 0 && values[j] > temp; j -= 1) {
                values[j + 1] = values[j];
            }
            values[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        /*int N = 300;
        double[] values = new double[N];
        for (int k = 0; k < N; k += 1) {
            values[k] = Math.random();
        }

        long a = System.currentTimeMillis();
        sort(values);
        long b = System.currentTimeMillis();
        long c = b - a;
        System.out.printf(a+" "+b+" " +c);*/
        /*double[] arr = new double[]{2,3,1,5,4};

        System.out.printf(Arrays.toString(arr));
        sort(arr);
        System.out.printf(Arrays.toString(arr));*/

       /* if (args.length != 1) {
            System.out.println("Usage: java Sorter N");
            System.out.println("  N - the number of elements to be sorted");
            System.exit(1);
        }
        int N = Integer.parseInt(args[0]);
        double[] values = new double[N];
        for (int k = 0; k < N; k += 1) {
            values[k] = Math.random();
        }
        Timer t = new Timer();
        t.start();
        sort(values);
        long elapsedMs = t.stop();
        System.out.println(elapsedMs + " milliseconds elapsed");
        if (N < 20) {
            for (int k = 0; k < N; k += 1) {
                System.out.println(values[k]);
            }
        }*/
        /*double[] values1 = new double[N];
        for (int k = 0; k < N; k += 1) {
            values1[k] = Math.random();
        }

        t.start();
        sort(values1);
        long e2 = t.stop();
        System.out.println("second call"+ e2 +"\n" +"total time"+ t.tAccum +"\n" +"elapsed time"+t.elapsed());*/

        System.out.printf("" + Integer.MAX_VALUE);
    }
}
