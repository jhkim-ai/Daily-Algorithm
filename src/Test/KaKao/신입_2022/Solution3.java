package Test.KaKao.신입_2022;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution3 {

    private static int[] fees;
    private static String[] records;

    public static void main(String[] args) {
        fees = new int[]{180, 5000, 10, 600};
        records = new String[]{"05:34 5961 IN", "06:00 0000 IN",
            "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
            "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN",
            "23:00 5961 OUT"};

        int countCar = 0;
        int[] resultFees;

        List<List<String>> carsInfo = new ArrayList<>();
        List<String> carIdxToNumber = new ArrayList<>(); // idx -> 차번호
        Map<String, Integer> mapCarNumberToIdx = new HashMap<>(); // 차번호 -> idx
        Map<String, Integer> mapOrderByCarIdx = new HashMap<>(); // 차번호 -> idx

        // input 값 parsing
        for(String record : records){
            StringTokenizer st = new StringTokenizer(record, " ");
            String time = st.nextToken();
            String carNumber = st.nextToken();
            String info = st.nextToken(); // dummy

            // 번호판을 Map 등록
            if(!mapCarNumberToIdx.containsKey(carNumber)){
                mapCarNumberToIdx.put(carNumber, countCar++);
                carIdxToNumber.add(carNumber);
            }

            // carsInfo 에 등록이 안되어 있다면, 추가
            if(carsInfo.size() < countCar){
                carsInfo.add(new ArrayList<>(Arrays.asList(time)));
            } else { // 등록이 되어 있다면, 입/출차 정보 등록
                int carIdx = mapCarNumberToIdx.get(carNumber);
                carsInfo.get(carIdx).add(time);
            }
        }

        List<String> orderByCarNumber = new ArrayList<>();
        for(String key : mapCarNumberToIdx.keySet()){
            orderByCarNumber.add(key);
        }
        Collections.sort(orderByCarNumber);
        for (int idx = 0; idx < orderByCarNumber.size(); ++idx) {
            mapOrderByCarIdx.put(orderByCarNumber.get(idx), idx);
        }

        resultFees = new int[countCar]; // 각 차번호별 요금 총계
        for(int idx = 0; idx < carsInfo.size(); ++idx){
            List<String> carInfo = carsInfo.get(idx);
            int totalMinute = getParkingTimeByCarNumber(carInfo);
            int feesByCar = getFeesByParkingTime(totalMinute, fees);

            String carNumber = carIdxToNumber.get(idx);
            resultFees[mapOrderByCarIdx.get(carNumber)] = feesByCar;
        }

        System.out.println(Arrays.toString(resultFees));
    }

    public static int getParkingTimeByCarNumber(List<String> record){
        int totalMinute = 0;
        int size = record.size();

        for (int idx = 0; idx < size; idx+=2) {
            String in = record.get(idx);
            String out = null;
            if(idx == size - 1){
                out = "23:59";
            } else {
                out = record.get(idx+1);
            }
            totalMinute += getMinuteDifferentTimeToTime(in, out);
        }

        return totalMinute;
    }

    // 누적 주차 시간(분)으로부터 주차 요금 구하기
    public static int getFeesByParkingTime(int totalMinute, int[] fees){
        int sum = 0;
        int defaultTime = fees[0]; // 기본 시간
        int defaultMoney = fees[1]; // 기본 요금
        int perMinute = fees[2]; // 단위 시간
        int feesPerMinute = fees[3]; // 단위 요금

        sum += defaultMoney;
        if(totalMinute <= defaultTime){
            return sum;
        }

        totalMinute -= defaultTime;
        if(totalMinute % perMinute != 0){
            totalMinute /= perMinute;
            totalMinute++;
        } else {
            totalMinute /= perMinute;
        }

        sum += totalMinute * feesPerMinute;

        return sum;
    }

    // 시간 차를 분으로 구하기
    public static int getMinuteDifferentTimeToTime(String in, String out){
        int sumMinute = 0;

        StringTokenizer st = new StringTokenizer(in, ":");
        int inHour = parseInt(st.nextToken());
        int inMinute = parseInt(st.nextToken());

        st = new StringTokenizer(out, ":");
        int outHour = parseInt(st.nextToken());
        int outMinute = parseInt(st.nextToken());

        if(outMinute < inMinute){
            sumMinute += 60 - inMinute + outMinute;
            sumMinute += (outHour - inHour - 1) * 60;
        } else {
            sumMinute += outMinute - inMinute;
            sumMinute += (outHour - inHour) * 60;
        }

        return sumMinute;
    }
}
