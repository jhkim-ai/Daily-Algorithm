package Category.Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ2204_도비의난독증테스트 {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = new HashMap<>();

        N = Integer.parseInt(br.readLine());
        while(N != 0){
            map.clear();
            String[]arr = new String[N];
            for(int i = 0; i < N; ++i){
                String str = br.readLine();
                String lowerStr = str.toLowerCase();
                arr[i] = lowerStr;
                map.put(lowerStr, str);
            }

            quickSort(arr, 0, N-1);

            sb.append(map.get(arr[0])).append("\n");
            N = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);
    }

    public static void quickSort(String[] arr, int start, int end){
        if(start >= end) return;

        int pivot = (start + end) / 2;
        int left = start;
        int right = end;
        String tmp = null;

        while(left <= right){
            while(arr[left].compareTo(arr[pivot]) < 0){
                left++;
            }
            while(arr[right].compareTo(arr[pivot]) > 0){
                right--;
            }
            if(left <= right){
                tmp = arr[right];
                arr[right] = arr[left];
                arr[left] = tmp;
                left++;
                right--;
            }
        }

        quickSort(arr, start, right);
        quickSort(arr, left, end);
    }
}
