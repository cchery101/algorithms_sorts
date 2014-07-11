
public class TestDraw {
    public static void main(String[] args) {
        StdDraw.clear();
        StdDraw.point(1,1);
        StdDraw.point(2,2);
        StdDraw.setXscale(0, 3);
        StdDraw.setYscale(0, 3);
        StdDraw.line(1,1,2,2);
    }
}