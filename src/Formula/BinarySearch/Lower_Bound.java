package Formula.BinarySearch;

public class Lower_Bound {

    private static int[] arr = {13, 13, 15, 17, 17, 17, 17, 20, 20, 30};

    public static void main(String[] args) throws Exception {

        // K 값 이상인 값의 처음 위치를 찾을 때
        int k = 17;
        int left = 0;
        int right = arr.length;

        while(left < right){
            int mid = (left + right) / 2;

            if(arr[mid] < k) left = mid + 1;
            else right = mid;
        }

        System.out.println(right);
    }
}
