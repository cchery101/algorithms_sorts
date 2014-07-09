
import java.util.Arrays;

public class Brute {
    private static void getlines(Point[] points) {
        // search for lines of four points
        int N = points.length;
        Arrays.sort(points);
        int i, j, k, l;
        Point a, b, c, d;
        for (i = 0; i < N; i++) { a = points[i];
        for (j = i + 1; j < N; j++) { b = points[j];
        for (k = j + 1; k < N; k++) { c = points[k];
        for (l = k + 1; l < N; l++) { d = points[l];
            if (a.slopeTo(b) == a.slopeTo(c) &&
                a.slopeTo(c) == a.slopeTo(d))
                { System.out.println(String.format(
                    "%s -> %s -> %s -> %s", a.toString(), b.toString(),
                    c.toString(), d.toString())); }
        }}}}
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
