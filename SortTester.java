
import java.lang.Math;
import java.util.Arrays;

public class SortTester {
    public static void main(String[] args) {
        int problemsize = Integer.parseInt(args[0]);
        Double[][] testarrays = new Double[5][problemsize];
        // create random arrays for each method to sort
        System.out.println("Creating Test Arrays");
        for (int i = 0; i < problemsize; i++) {
            for (int j = 0; j < 5; j++) {
                testarrays[j][i] = Math.random();
            }
        }
        // run each sorting method
        //System.out.println("Selection Sort");
        //SelectionSort.sort(testarrays[0]);
        System.out.println("Insertion Sort");
        InsertionSort.sort(testarrays[1]);
        System.out.println("Shell Sort");
        ShellSort.shellsort(testarrays[2], 3);
        System.out.println("Bottom Up Merge Sort");
        MergeSort.bottomup(testarrays[3], 2);
        System.out.println("Top Down Merge Sort");
        MergeSort.topdown(testarrays[4], 7);
        System.out.println("Done");
        // confirm that results are sorted
        for (int i = 0; i < 5; i++) {
            System.out.println(MergeSort.issorted(testarrays[i]));
        }

    }
}