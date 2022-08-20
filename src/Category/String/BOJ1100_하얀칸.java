package Category.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1100_하얀칸 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] map = new char[8][8];
        int ans = 0;
        for (int y = 0; y < 8; ++y) {
            map[y] = br.readLine().toCharArray();
            for (int x = 0; x < 8; ++x) {
                char c = map[y][x];

                if (c != 'F') {
                    continue;
                }

                if (y % 2 == 0 && x % 2 == 0) {
                    ans++;
                } else if (y % 2 == 1 && x % 2 == 1) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
