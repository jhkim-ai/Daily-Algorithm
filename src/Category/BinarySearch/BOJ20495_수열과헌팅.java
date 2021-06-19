package Category.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20495_수열과헌팅 {
    static int[] lowNumbers, maxNumbers, sortedLowNumbers, sortedMaxNumbers;
    static int n;

    public static void main(String[] args) throws IOException {
        input();
        findBoundLocation();
    }

    // 전처리
    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lowNumbers = new int[n];
        maxNumbers = new int[n];
        sortedLowNumbers = new int[n];
        sortedMaxNumbers = new int[n];

        for(int idx = 0; idx <n; ++idx) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int maxValue = Integer.parseInt(st.nextToken());
            int minValue = Integer.parseInt(st.nextToken());
            lowNumbers[idx] = maxValue - minValue;
            sortedLowNumbers[idx] = maxValue - minValue;
            maxNumbers[idx] = maxValue + minValue;
            sortedMaxNumbers[idx] = maxValue + minValue;
        }
        Arrays.sort(sortedLowNumbers);
        Arrays.sort(sortedMaxNumbers);
    }

    //기존 자리의 값이 가질 수 있는 최소, 최대 위치 구함
    static void findBoundLocation() {
        StringBuilder sb = new StringBuilder();
        for(int idx=0;idx < n; ++idx) {
            int minIdx = lowerBound(lowNumbers[idx]);
            int maxIdx = upperBound(maxNumbers[idx]);
            sb.append(minIdx + 1).append(" ").append(maxIdx).append("\n");
        }
        System.out.println(sb);
    }


    static int lowerBound(int boundValue) {
        int low = 0;
        int high = n;

        while(low < high){
            int mid = (low + high) / 2;
            if(sortedMaxNumbers[mid] >= boundValue) high = mid;
            else low = mid + 1;
        }
        return high;
    }

    static int upperBound(int boundValue) {
        int low = 0;
        int high = n;

        while(low < high){
            int mid = (low + high) / 2;
            if(sortedLowNumbers[mid] > boundValue) high = mid;
            else low = mid + 1;
        }
        return high;
    }
}