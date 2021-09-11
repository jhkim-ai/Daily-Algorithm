package Test.KaKao.신입_2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution1 {

    private static String[] id_list;
    private static String[] report;
    private static int k;

    public static void main(String[] args) {
        id_list = new String[]{"muzi", "frodo", "apeach", "neo"};
        report = new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        k = 2;

        // 각 아이디를 index 로 바꾸어 관리
        int idIdx = 0;
        Map<String, Integer> idToIdx = new HashMap<>();
        for(String str : id_list){
            idToIdx.put(str, idIdx++);
        }

        // init
        Set<String>[][] setReportByUser = new Set[idIdx][2]; // User 별 신고 내역 저장
        for(Set<String>[] userReport : setReportByUser){
            userReport[0] = new HashSet<>(); // 신고한 ID set
            userReport[1] = new HashSet<>(); // 정지된 ID set
        }

        int[] reportedCount = new int[idIdx]; // 각 Id 별로 신고된 횟수
        // report 를 돌며 각 User 별로 신고한 내용 저장하기
        for (String elem : report) {
            StringTokenizer st = new StringTokenizer(elem, " ");
            String userId = st.nextToken();
            String reportedUserId = st.nextToken();

            int userIdx = idToIdx.get(userId);
            Set<String>[] userIdxSet = setReportByUser[userIdx];

            if(userIdxSet[0].contains(reportedUserId)){ // 이미 신고 했다면 pass
                continue;
            }

            // 신고가 안되어 있다면
            int reportedUserIdx = idToIdx.get(reportedUserId);

            reportedCount[reportedUserIdx]++; // 신고 횟수 증가
            setReportByUser[userIdx][0].add(reportedUserId); // 신고한 ID 추가
        }

        // User 별로 신고 완료된 정지 ID를 저장
        for(Set<String>[] userReport : setReportByUser){
            Set<String> reportedUserIdSet = userReport[0];
            Set<String> suspendedUserIdSet = userReport[1];
            for(String userId : id_list){
                int userIdIdx = idToIdx.get(userId);
                if(reportedCount[userIdIdx] < k){ // k 번 이하된 Id 면 pass
                    continue;
                }
                if(reportedUserIdSet.contains(userId)){
                    suspendedUserIdSet.add(userId);
                }
            }
        }

        // 정답 출력
        List<Integer> list = new ArrayList<>();
        for(Set<String>[] userReport : setReportByUser){
            list.add(userReport[1].size());
        }
        System.out.println(list);
    }
}
