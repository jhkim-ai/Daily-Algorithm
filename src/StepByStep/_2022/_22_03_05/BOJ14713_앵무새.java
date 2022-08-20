package StepByStep._2022._22_03_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ14713_앵무새 {

    private static int N, size;
    private static String[] result;
    private static List<String>[] words;
    private static Map<String, Integer> mapWordToIdx;
    private static Map<Integer, Integer> mapNextIdx;

    public static void main(String[] args) throws IOException {
        init();
        if(!isOk()){
            System.out.println("Impossible");
        } else {
            System.out.println("Possible");
        }
    }

    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mapWordToIdx = new HashMap<>(); // 단어들이 몇 번째 List에 있는지 저장
        mapNextIdx = new HashMap<>();   // 문장 List에서 다음 차례가 몇 번째인지 저장

        N = Integer.parseInt(br.readLine());
        words = new List[N];

        for(int idx = 0; idx < N; ++idx){
            words[idx] = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            while(st.hasMoreTokens()) {
                String word = st.nextToken();
                words[idx].add(word);
                mapWordToIdx.put(word, idx);
            }
            mapNextIdx.put(idx, 0);
            size += words[idx].size();
        }

        result = br.readLine().split(" ");
    }

    public static boolean isOk(){
        if(size != result.length) return false;

        for(String word : result){
            if(!mapWordToIdx.containsKey(word)) return false; // 앵무새가 말한 word가 아니라면 false

            int listIdx = mapWordToIdx.get(word);
            int locIdx = mapNextIdx.get(listIdx);

            String savedStr = words[listIdx].get(locIdx); // 다음 차례의 단어
            if(!savedStr.equals(word)) return false; // 현재 word가 다음 차례가 아니라면 false

            mapNextIdx.put(listIdx, locIdx+1);
        }

        return true;
    }
}
