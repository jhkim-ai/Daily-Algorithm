package Category.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1072_게임 {

    public static final int MAX = 1000000000;

    public static int X, Y, Z;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        Z = getPercentage(Y, X);
        int ans = -1;

        if(Z < 99){
            ans = upperBound(1, MAX);
        }

        System.out.println(ans);
    }

    public static int upperBound(int left, int right){

        while(left < right){
            int mid = (left + right) / 2;
            int score = getPercentage(Y + mid, X + mid);

            if(score <= Z) left = mid + 1;
            else right = mid;
        }

        return right;
    }

    public static int getPercentage(double y, double x){
        return (int)((y * 100) / x);
    }
}
