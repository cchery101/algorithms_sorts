
import java.util.Arrays;

public class ShellSort
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
        for (int i = 1; i < data.length; i++) {
            if (lessthan(data[i], data[i - 1])) return false;
        }
        return true;
    }

    public static boolean ishsorted(Comparable[] data, int h) {
        for (int start = 0; start < h; start++) {
            for (int j = start + h; j < data.length; j += h) {
                if (lessthan(data[j], data[j - h]))   return false;
            }
        }
        return true;
    }

    public static void hsort(Comparable[] data, int h) {
        int N = data.length;
        // insertion sort
        for (int start = 0; start < h; start++) {
            for (int i = h + start; i < N; i += h) {
                for (int j = i; j >= h; j -= h) {
                    if (lessthan(data[j], data[j - h])) exchange(data, j, j - h);
                    else                                break;
                }
            }
        }
    }

    public static void shellsort(Comparable[] data, int divisor) {
        for (int h = data.length / divisor; h > 1; h = h / divisor)
            { hsort(data, h); }
        hsort(data, 1);
    }

    public static void main(String[] args)
    {
        // test with integers
        int divisor = Integer.parseInt(args[0]);
        Integer[] myintarray = new Integer[] {3, 4, 5, 1, 2, 7, 8, 6, 10, 9, 11};
        System.out.println(Arrays.toString(myintarray));
        shellsort(myintarray, divisor);
        System.out.println(Arrays.toString(myintarray));
        System.out.println(issorted(myintarray));
    }
}
