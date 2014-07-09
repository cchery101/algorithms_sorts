
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class Fast {
    private LinkedQueue<Line> lines = new LinkedQueue<Line>();

    private class Line {
        private LinkedQueue<Point> points = new LinkedQueue<Point>();
        private Point startpoint;
        private Point endpoint;
        public Line(Point pt1, Point pt2, Point pt3) {
            startpoint = pt1;
            points.enqueue(pt1);
            points.enqueue(pt2);
            points.enqueue(pt3);
            endpoint = pt3;
        }
        public void addpoint(Point pt) {
            points.enqueue(pt);
            endpoint = pt;
        }
        public void printline() {
            System.out.print(points.dequeue().toString());
            for (Point pt: points) {
                System.out.print(" -> ");
                System.out.print(pt.toString());
            }
            System.out.println();
        }
        public boolean hassubset(Line that) {
            Iterator<Point> thisiter = points.iterator();
            thisiter.next();
            Iterator<Point> thatiter = that.points.iterator();
            try {
                while (true) {
                    if (thisiter.next().compareTo(thatiter.next()) != 0) {
                        // mismatch, so not a subset
                        return false;
                    }
                }
            }
            catch (NoSuchElementException e) {
                // reached end of list without a mismatch
                return true;
            }
        }
    }

    private void getlines(Point[] points) {
        int N = points.length;
        Point[] aux;
        Point start;
        double currentslope;
        double slope;
        boolean online;
        int linestart;
        Line currentline = new Line(points[0], points[1], points[2]);
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
                    if (online) {
                        // already on a line, found an extra point
                        System.out.print(String.format(" --> %s", aux[j].toString()));
                        currentline.addpoint(aux[j]);
                        }
                    else {
                    // found 3 collinear points to start a line
                        System.out.print(String.format("%s --> %s --> %s",
                            start.toString(), aux[j-1].toString(), aux[j].toString()));
                        online = true;
                        currentline = new Line(start, aux[j-1], aux[j]);
                    }
                }
                else {
                    if (online) {
                        // finish the current line
                        for (Line l: lines) {
                            if (l.hassubset(currentline)) {
                                System.out.println("(duplicate)");
                            }
                        }
                        lines.enqueue(currentline);
                        System.out.println();
                    }
                    online = false;
                }
                currentslope = slope;
            }
            if (online) {
                for (Line l: lines) {
                    if (l.hassubset(currentline)) {
                        System.out.println("(duplicate)");
                    }
                }
                lines.enqueue(currentline);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Fast testobj = new Fast();
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
        // get lines
        System.out.println("Getting Lines");
        testobj.getlines(points);
        // print lines
        System.out.println("Printing Lines");
        for (Line l: testobj.lines) {
            l.printline();
        }
    }
}
