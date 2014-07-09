
import java.util.Arrays;

public class Fast {
    private static void getlines(Point[] points) {
        int N = points.length;
        Point[] aux;
        Point start;
        double currentslope;
        double slope;
        boolean online;
        // sort points by increasing y coordinate
        Arrays.sort(points);
        for (int i = 0; i < N - 2; i++) {
            start = points[i];
            aux = new Point[N - i - 1];
            for (int j = i + 1; j < N; j++) {
                aux[j - (i + 1)] = points[j];
            }
            // sort remaining points by slope to the current start
            Arrays.sort(aux, start.SLOPE_ORDER);
            currentslope = start.slopeTo(aux[0]);
            online = false;
            for (int j = 1; j < N - i - 1; j++) {
                slope = start.slopeTo(aux[j]);
                if (slope == currentslope) {
                    if (online)
                        // already on a line, found an extra point
                        { System.out.print(String.format(" --> %s", aux[j].toString())); }
                    else {
                    // found 3 collinear points to start a line
                        System.out.print(String.format("%s --> %s --> %s",
                            start.toString(), aux[j-1].toString(), aux[j].toString()));
                        online = true;
                    }
                }
                else {
                    if (online) System.out.println();
                    online = false;
                }
                currentslope = slope;
            }
            if (online) System.out.println();
        }
    }

    public static void main(String[] args) {
        // read in the input file
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // N points in the set
        Point[] points = new Point[N];
        int idx = 0;
        // read in data to create Point objects
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            points[idx] = new Point(i, j);
            idx++;
        }
        getlines(points);
    }
}
