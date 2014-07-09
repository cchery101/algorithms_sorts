
public class Brute {
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
            points[idx].draw();
            System.out.println(points[idx].toString());
            idx++;
        }
        StdDraw.show();
    }
}
