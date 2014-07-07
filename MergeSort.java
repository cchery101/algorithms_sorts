
import java.util.Arrays;
import java.lang.Math;

public class MergeSort
{
    private static boolean lessthan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void exchange(Comparable[] data, int i, int j) {
        Comparable temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static boolean issorted(Comparable[] data) {
        return issorted(data, 0, data.length - 1);
    }

    public static boolean issorted(Comparable[] data, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (lessthan(data[i], data[i - 1])) return false;
        }
        return true;
    }
    
    public static void insertionsort(Comparable[] data) {
        insertionsort(data, 0, data.length - 1);
    }

    // insertion sort used for small subarrays
    public static void insertionsort(Comparable[] data, int lo, int hi) {
        int N = data.length;
        // insertion sort
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo; j--) {
                if (lessthan(data[j], data[j - 1])) exchange(data, j, j - 1);
                else                                break;
            }
        }
    }

    // merge two sorted subarrays
    public static void merge(Comparable[] data, Comparable[] workarray,
                            int lo, int mid, int hi) {
        // debug checks
        assert issorted(data, lo, mid - 1);
        assert issorted(data, mid, hi);
        // temporary copy for the required range
        for (int i = lo; i <= hi; i++)
            { workarray[i] = data[i]; }
        int ptlo = lo;
        int pthi = mid;
        for (int i = lo; i <= hi; i++) {
            if ((pthi > hi) || (lessthan(workarray[ptlo], workarray[pthi]) && ptlo < mid)) {
                data[i] = workarray[ptlo];
                ptlo++;
            }
            else {
                data[i] = workarray[pthi];
                pthi++;
            }
        }
    }

    // bottom up merging from insertion sorted subarrays
    public static void bottomup(Comparable[] data) {
        int N = data.length;
        Comparable[] workarray = new Comparable[N];
        int endpoint;
        // iterate upwards by powers
        for (int elemsize = 2; elemsize < 2 * N; elemsize *= 2) {
            for (int i = 0; i < N; i += elemsize) {
                // merge within each element
                endpoint = Math.min(i + elemsize, N) - 1;
                merge(data, workarray, i, i + elemsize / 2, endpoint);
            }
        }
    }

    // merge sort by halving (wrapper for lo-hi recursive sort)
    public static void topdown(Comparable[] data, int insertionsize) {
        Comparable[] workarray = new Comparable[data.length];
        topdown(data, workarray, insertionsize, 0, data.length - 1);
    }

    // recursive component of merge
    public static void topdown(Comparable[] data, Comparable[] workarray,
                            int insertionsize, int lo, int hi) {
        // sort by insertion below size limit
        if (hi - lo + 1 <= insertionsize) {
            insertionsort(data, lo, hi);
            return;
        }
        // sort two halves
        int mid = lo + (hi - lo + 1) / 2;
        topdown(data, workarray, insertionsize, lo, mid - 1);
        topdown(data, workarray, insertionsize, mid, hi);
        // merge sorted halves
        merge(data, workarray, lo, mid, hi);
    }

    public static void main(String[] args)
    {
        Integer[] workarray;
        // test merge with integers
        System.out.println("Last > mid");
        Integer[] myintarray1 = new Integer[] {1, 3, 4, 5, 7, 2, 6, 8, 9, 10};
        workarray = new Integer[myintarray1.length];
        System.out.println(Arrays.toString(myintarray1));
        merge(myintarray1, workarray, 1, 5, 8);
        System.out.println(Arrays.toString(myintarray1));
        System.out.println(issorted(myintarray1, 1, 8));
        // test merge with integers
        System.out.println("Last < mid");
        Integer[] myintarray2 = new Integer[] {2, 4, 6, 9, 10, 1, 3, 5, 8, 7};
        workarray = new Integer[myintarray2.length];
        System.out.println(Arrays.toString(myintarray2));
        merge(myintarray2, workarray, 1, 5, 8);
        System.out.println(Arrays.toString(myintarray2));
        System.out.println(issorted(myintarray2, 1, 8));
        // test insertion sort by section
        System.out.println("Partial insertion sort test");
        Integer[] myintarray3 = new Integer[] {3, 4, 1, 2, 7, 5, 9, 10, 6, 8};
        System.out.println(Arrays.toString(myintarray3));
        insertionsort(myintarray3, 1, 8);
        System.out.println(Arrays.toString(myintarray3));
        System.out.println(issorted(myintarray3, 1, 8));
        // test bottom up merge sort
        System.out.println("Bottom up merge");
        Integer[] myintarray4 = new Integer[] {3, 10, 5, 11, 2, 7, 8, 6, 1};
        System.out.println(Arrays.toString(myintarray4));
        bottomup(myintarray4);
        System.out.println(Arrays.toString(myintarray4));
        System.out.println(issorted(myintarray4));
        // test top down merge sort
        System.out.println("Top down merge");
        Integer[] myintarray5 = new Integer[] {3, 10, 5, 11, 2, 7, 8, 6, 4, 9, 1};
        System.out.println(Arrays.toString(myintarray5));
        topdown(myintarray5, 7);
        System.out.println(Arrays.toString(myintarray5));
        System.out.println(issorted(myintarray5));
    }
}
