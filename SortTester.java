
import java.lang.Math;
import java.util.Arrays;

public class SortTester {
    public static void main(String[] args) {
        int excost = 2;
        int problemsize;
        Double[][] testarrays;
        SelectionSort selectobj;
        InsertionSort insertobj;
        ShellSort shellobj;
        MergeSort bmergeobj;
        MergeSort tmergeobj;
        for (String s: args) {
            problemsize = Integer.parseInt(s);
            testarrays = new Double[5][problemsize];
            // create random arrays for each method to sort
            //System.out.println(String.format(
            //    "Creating Test Arrays of Size: %d", problemsize));
            for (int i = 0; i < problemsize; i++) {
                for (int j = 0; j < 5; j++) {
                    testarrays[j][i] = Math.random();
                }
            }
            // run each sorting method
            selectobj = new SelectionSort();
            selectobj.sort(testarrays[0]);
            System.out.println(String.format("Selection,%d,%d,%d",
                problemsize, selectobj.comparecount, selectobj.exchangecount * excost));
            insertobj = new InsertionSort();
            insertobj.sort(testarrays[1]);
            System.out.println(String.format("Insertion,%d,%d,%d",
                problemsize, insertobj.comparecount, insertobj.exchangecount * excost));
            shellobj = new ShellSort();
            shellobj.sort(testarrays[2], 3);
            System.out.println(String.format("Shell,%d,%d,%d",
                problemsize, shellobj.comparecount, shellobj.exchangecount * excost));
            bmergeobj = new MergeSort();
            bmergeobj.bottomup(testarrays[3]);
            System.out.println(String.format("Bottom Up Merge,%d,%d,%d",
                problemsize, bmergeobj.comparecount, bmergeobj.exchangecount * excost +
                bmergeobj.copycount));
            tmergeobj = new MergeSort();
            tmergeobj.topdown(testarrays[4], 8);
            System.out.println(String.format("Top Down Merge,%d,%d,%d",
                problemsize, tmergeobj.comparecount, tmergeobj.exchangecount * excost +
                tmergeobj.copycount));
            // confirm that results are sorted
            for (int i = 0; i < 5; i++) {
                if (!shellobj.issorted(testarrays[i])) {
                    System.out.println("Sorting Error");
                }
            }
        }
    }
}
