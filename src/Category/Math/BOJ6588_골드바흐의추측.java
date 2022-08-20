package Category.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

public class BOJ6588_골드바흐의추측 {

    private static boolean[] isNotPrime;
    private static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        initPrime();
        while(true){
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                break;
            }
            boolean isOk = false;
            for(int i = 3; i <= num/2; ++i){
                if(!isNotPrime[i] && !isNotPrime[num-i]){
                    sb.append(num).append(" = ").append(i).append(" + ").append(num-i);
                    isOk = true;
                    break;
                }
            }

            if(!isOk) {
                sb.append("Goldbach's conjecture is wrong.");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void initPrime(){
        isNotPrime = new boolean[1000001];
        int size = isNotPrime.length;

        for(int i = 2; i * i < size; ++i){
            for(int j = i * i; j < size; j += i){
                if(!isNotPrime[j]){
                    isNotPrime[j] = true;
                }
            }
        }
    }
}
