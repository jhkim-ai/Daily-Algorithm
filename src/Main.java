import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Point a = new Point("김재현", "123");
        Point b = new Point("김재현", "123");
        Point c = new Point();
        Set<Point> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);
        System.out.println(set.size());

        System.out.println("김재현".hashCode());
    }

    public static class Point {
        String y;
        String x;

        public Point(){

        }

        public Point(String y, String x){
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return Objects.equals(y, point.y) && Objects.equals(x, point.x);
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}