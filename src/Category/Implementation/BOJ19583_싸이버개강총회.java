package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ19583_싸이버개강총회 {

    private static int[][] times;
    private static Set<String>[] sets;
    private static Queue<String> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();
        String record = null;
        int cnt = 0;

        sets = new Set[3];
        for(int i = 0; i < sets.length; ++i){
            sets[i] = new HashSet<>();
        }

        while((record = br.readLine()) != null){
            st = new StringTokenizer(record, " ");
            String time = st.nextToken();
            String userId = st.nextToken();

            sets[2].add(userId);
            if(S.compareTo(time) >= 0){
                sets[0].add(userId);
            } else if(E.compareTo(time) <= 0 && Q.compareTo(time) >= 0){
                sets[1].add(userId);
            }
        }

        for(String userId : sets[2]){
            if(sets[0].contains(userId) && sets[1].contains(userId)){
                ++cnt;
            }
        }

        System.out.println(cnt);
    }
}
