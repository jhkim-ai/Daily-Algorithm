package 오답_및_복습;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ5427_불 {

    static int T, w, h;
    static char[][] map;
    static Point man;
    static List<Point> list;
    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            list = new ArrayList<>();
            for (int y = 0; y < h; y++) {
                map[y] = br.readLine().toCharArray();
                for (int x = 0; x < w; x++) {
                    if(map[y][x] == '@')
                        man = new Point(y, x);
                    else if(map[y][x] == '*')
                        list.add(new Point(y, x));
                }

            }

            
        }
    }

    static class Point{
        int y;
        int x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static String input = "5\n" +
            "4 3\n" +
            "####\n" +
            "#*@.\n" +
            "####\n" +
            "7 6\n" +
            "###.###\n" +
            "#*#.#*#\n" +
            "#.....#\n" +
            "#.....#\n" +
            "#..@..#\n" +
            "#######\n" +
            "7 4\n" +
            "###.###\n" +
            "#....*#\n" +
            "#@....#\n" +
            ".######\n" +
            "5 5\n" +
            ".....\n" +
            ".***.\n" +
            ".*@*.\n" +
            ".***.\n" +
            ".....\n" +
            "3 3\n" +
            "###\n" +
            "#@#\n" +
            "###";
}
