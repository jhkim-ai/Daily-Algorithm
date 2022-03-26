package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ2804_크로스워드만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        char[] a = st.nextToken().toCharArray();
        char[] b = st.nextToken().toCharArray();
        int row = 0;
        int col = 0;
        char word = '\0';
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < b.length; ++i){
            set.add(b[i]);
        }

        for(int i = 0; i < a.length; ++i){
            if(!set.contains(a[i])) continue;
            word = a[i];
            col = i;
            break;
        }

        for(int i = 0; i < b.length; ++i){
            if(b[i] != word) continue;
            row = i;
            break;
        }

        for(int y = 0; y < b.length; ++y){
            for(int x = 0; x < a.length; ++x){
                if(y == row) {
                    System.out.print(a[x]);
                } else if(x == col){
                    System.out.print(b[y]);
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
