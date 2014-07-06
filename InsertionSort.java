
import java.util.Arrays;

public class InsertionSort
{
    private static boolean lessthan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void exchange(Comparable[] data, int i, int j) {
        Comparable temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    
    public static void sort(Comparable[] data)
    {
        int N = data.length;
        // insertion sort
        for (int i = 1; i < N; i++) {
            // 
            for (int j = i; j > 0; j--) {
                if (lessthan(data[j], data[j - 1])) exchange(data, j, j - 1);
                else                                break;
            }
        }
    }

    public static void main(String[] args)
    {
        // test with integers
        Integer[] myintarray = new Integer[] {3, 4, 5, 1, 2, 7, 8, 6, 10, 9, 11};
        System.out.println(Arrays.toString(myintarray));
        InsertionSort.sort(myintarray);
        System.out.println(Arrays.toString(myintarray));
        // test with strings
        String[] mystrarray = new String[] {"C", "B", "F", "A", "D", "E"};
        System.out.println(Arrays.toString(mystrarray));
        InsertionSort.sort(mystrarray);
        System.out.println(Arrays.toString(mystrarray));
    }
}
