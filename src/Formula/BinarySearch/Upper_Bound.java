package Formula.BinarySearch;

public class Upper_Bound {

    private static int[] arr = {13, 13, 15, 17, 17, 17, 17, 20, 20, 30};

    public static void main(String[] args) throws Exception {

        // Upper Bound
        // K 값 초과인 값의 처음 위치를 구할 때
        int k = 17;
        int left = 0;
        int right = arr.length;

        while(left < right){
            int mid = (left + right) / 2;

            if(arr[mid] <= k) left = mid + 1;
            else right = mid;
        }

        System.out.println(right);
    }
}
