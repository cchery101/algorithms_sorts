
import java.util.Arrays;
import java.lang.Math;

public class MergeSort
{
    // method invocation counters
    public int exchangecount = 0;
    public int comparecount = 0;
    public int copycount = 0;

    // implementation
    private boolean lessthan(Comparable a, Comparable b) {
        comparecount++;
        return a.compareTo(b) < 0;
    }
    
    private void exchange(Comparable[] data, int i, int j) {
        exchangecount++;
        Comparable temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private void copyfrom(Comparable[] from, int i, Comparable[] to, int j) {
        copycount++;
        to[j] = from[i];
    }

    public boolean issorted(Comparable[] data) {
        return issorted(data, 0, data.length - 1);
    }

    public boolean issorted(Comparable[] data, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (lessthan(data[i], data[i - 1])) return false;
        }
        return true;
    }
    
    public void insertionsort(Comparable[] data) {
        insertionsort(data, 0, data.length - 1);
    }

    // insertion sort used for small subarrays
    public void insertionsort(Comparable[] data, int lo, int hi) {
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
    public void merge(Comparable[] data, Comparable[] workarray,
                            int lo, int mid, int hi) {
        // debug checks
        assert issorted(data, lo, mid - 1);
        assert issorted(data, mid, hi);
        // temporary copy for the required range
        for (int i = lo; i <= hi; i++) {
            //workarray[i] = data[i];
            copyfrom(data, i, workarray, i);
        }
        int ptlo = lo;
        int pthi = mid;
        for (int i = lo; i <= hi; i++) {
            if ((pthi > hi) || (lessthan(workarray[ptlo], workarray[pthi]) && ptlo < mid)) {
                //data[i] = workarray[ptlo];
                copyfrom(workarray, ptlo, data, i);
                ptlo++;
            }
            else {
                //data[i] = workarray[pthi];
                copyfrom(workarray, pthi, data, i);
                pthi++;
            }
        }
    }

    // bottom up merging from insertion sorted subarrays
    public void bottomup(Comparable[] data) {
        int N = data.length;
        Comparable[] workarray = new Comparable[N];
        int endpoint;
        // iterate upwards by powers
        for (int elemsize = 2; elemsize < 2 * N; elemsize *= 2) {
            for (int i = 0; i < N; i += elemsize) {
                // merge within each element
                endpoint = Math.min(i + elemsize, N) - 1;
                if (i + elemsize / 2 <= endpoint) {
                    merge(data, workarray, i, i + elemsize / 2, endpoint); }
            }
        }
    }

    // merge sort by halving (wrapper for lo-hi recursive sort)
    public void topdown(Comparable[] data, int insertionsize) {
        Comparable[] workarray = new Comparable[data.length];
        topdown(data, workarray, insertionsize, 0, data.length - 1);
    }

    // recursive component of merge
    public void topdown(Comparable[] data, Comparable[] workarray,
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
        MergeSort testobj;
        Integer[] workarray;
        // test merge with integers
        /*testobj = new MergeSort();
        System.out.println("Partial Merge test: Last > mid");
        Integer[] myintarray1 = new Integer[] {1, 3, 4, 5, 7, 2, 6, 8, 9, 10};
        workarray = new Integer[myintarray1.length];
        System.out.println(Arrays.toString(myintarray1));
        testobj.merge(myintarray1, workarray, 1, 5, 8);
        System.out.println(Arrays.toString(myintarray1));
        System.out.println(testobj.issorted(myintarray1, 1, 8));
        // test merge with integers
        System.out.println("Partial Merge test: Last < mid");
        Integer[] myintarray2 = new Integer[] {2, 4, 6, 9, 10, 1, 3, 5, 8, 7};
        workarray = new Integer[myintarray2.length];
        System.out.println(Arrays.toString(myintarray2));
        testobj.merge(myintarray2, workarray, 1, 5, 8);
        System.out.println(Arrays.toString(myintarray2));
        System.out.println(testobj.issorted(myintarray2, 1, 8));
        // test insertion sort by section
        System.out.println("Partial insertion sort test");
        Integer[] myintarray3 = new Integer[] {3, 4, 1, 2, 7, 5, 9, 10, 6, 8};
        System.out.println(Arrays.toString(myintarray3));
        testobj.insertionsort(myintarray3, 1, 8);
        System.out.println(Arrays.toString(myintarray3));
        System.out.println(testobj.issorted(myintarray3, 1, 8));
        */// test bottom up merge sort
        testobj = new MergeSort();
        System.out.println("Bottom up merge");
        Integer[] myintarray4 = new Integer[] {3, 10, 5, 11, 2, 7, 8, 4};
        System.out.println(Arrays.toString(myintarray4));
        testobj.bottomup(myintarray4);
        System.out.println(Arrays.toString(myintarray4));
        System.out.println(String.format("Compares: %d   Exchanges: %d  Copies: %d",
            testobj.comparecount, testobj.exchangecount, testobj.copycount));
        System.out.println(testobj.issorted(myintarray4));
        // test top down merge sort
        testobj = new MergeSort();
        System.out.println("Top down merge");
        Integer[] myintarray5 = new Integer[] {3, 10, 5, 11, 2, 7, 8, 4};
        System.out.println(Arrays.toString(myintarray5));
        testobj.topdown(myintarray5, 4);
        System.out.println(Arrays.toString(myintarray5));
        System.out.println(String.format("Compares: %d   Exchanges: %d  Copies: %d",
            testobj.comparecount, testobj.exchangecount, testobj.copycount));
        System.out.println(testobj.issorted(myintarray5));
    }
}
