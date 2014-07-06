
import java.util.Arrays;

public class SelectionSort
{
    Integer[] data;

    private boolean lessthan(int i, int j) {
        return data[i] < data[j];
    }
    
    private void exchange(int i, int j) {
        Integer temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    
    public void sort(Integer[] inputdata)
    {
        // create internal reference to the input array
        data = inputdata;
        int N = data.length;
        int minval;
        // selection loop
        for (int i = 0; i < N; i++)
        {
            minval = i;
            for (int j = i + 1; j < N; j++)
                { if (lessthan(j, minval)) minval = j; }
            exchange(i, minval);
        }
    }

    public static void main(String[] args)
    {
        Integer[] myarray = new Integer[] {3, 4, 5, 1, 2, 7, 8, 6, 10, 9, 11};
        SelectionSort sorter = new SelectionSort();
        System.out.println(Arrays.toString(myarray));
        sorter.sort(myarray);
        System.out.println(Arrays.toString(myarray));
    }
}
