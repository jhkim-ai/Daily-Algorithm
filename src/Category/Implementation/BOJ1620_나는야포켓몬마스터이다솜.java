package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1620_나는야포켓몬마스터이다솜 {

    private static int N, M;
    private static Map<String, Integer> mapMonsterToIdx;
    private static Map<Integer, String> mapIdxToMonster;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mapIdxToMonster = new HashMap<>();
        mapMonsterToIdx = new HashMap<>();

        int idx = 0;
        while(N-- > 0){
            String monster = br.readLine();
            mapIdxToMonster.put(++idx, monster);
            mapMonsterToIdx.put(monster, idx);
        }

        StringBuilder sb = new StringBuilder();
        while(M-- > 0){
            String str = br.readLine();
            char c = str.charAt(0);
            c -= '0';

            if(c >= 0 && c <= 9) {
                idx = Integer.parseInt(str);
                sb.append(mapIdxToMonster.get(idx));
            } else {
                sb.append(mapMonsterToIdx.get(str));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
