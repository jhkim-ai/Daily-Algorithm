package Test.Line.신입채용_하반기_2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {

    private static int N, K;
    private static String[] research;

    public static void main(String[] args) {

        N = 2;
        K = 2;
        research = new String[]{"abaaaa","aaa","abaaaaaa","fzfffffffa"};

        // 1. N일 동안 검색된 모든 단어의 수 구하기
        char[] wordIdx = new char[26];
        int[] arr = new int[26];
        for(String str : research){
            for(int idx = 0; idx < str.length(); ++idx){
                char c = str.charAt(idx);
                arr[c - 'a']++;
            }
        }

        // 2. 2차원 ArrayList 에 저장하기 위한 작업
        // (2-1) 검색된 모든 단어들을 Map 에 저장하여 index 화하기
        // 'a' : 0,
        // 'b' : 1, ...
        Map<Character, Integer> mapWordIndex = new HashMap<>();
        int mapNumberIndex = 0;
        for(int idx = 0; idx < arr.length; ++idx){
            if(arr[idx] == 0) {
                continue;
            }
            char key = (char)(idx+'a');
            wordIdx[mapNumberIndex] = key;
            mapWordIndex.put(key, mapNumberIndex++);
        }

        // (2-2) 2차원 ArrayList 생성
        List<Integer>[] keyWordCountByDate = new ArrayList[mapNumberIndex];
        for(int idx = 0; idx < mapNumberIndex; ++idx){
            keyWordCountByDate[idx] = new ArrayList<>();
        }

        // (2-3) 배열 Idx: 검색 keyWord
        //       배열의 원소: 날짜당 검색 수
        for(int day = 0; day < research.length; ++day){
            char[] keyWordForADay = research[day].toCharArray();
            int[] countingSort = new int[26];
            for(char c : keyWordForADay){
                countingSort[c - 'a']++;
            }

            for(int idx = 0; idx < countingSort.length; ++idx){
                if(countingSort[idx] != 0){
                    char c = (char)(idx+'a'); // 검색어
                    keyWordCountByDate[mapWordIndex.get(c)].add(countingSort[idx]);
                }
            }
            // 해당 day 에 검색어가 없다면, 0 으로 채운다.
            for(List<Integer> keyWord : keyWordCountByDate){
                if(keyWord.size() != day+1){
                    keyWord.add(0);
                }
            }
        }

        // 알고리즘 시작 (Sliding Window)
        int[] issueKeywordCountingSort = new int[26]; // keyWord 당 이슈 검색어가 된 수 저장
        for(int idx = 0; idx < keyWordCountByDate.length; ++idx){
            int sum = 0;
            int startIdx = 0;
            int lengthOfDay = 0;

            for(int day = 0; day < keyWordCountByDate[idx].size(); ++day){
                int count = keyWordCountByDate[idx].get(day);
                if(count < K){ // K 번을 만족 못할 시
                    lengthOfDay = 0;
                    sum = 0;
                    startIdx = day+1;
                    continue;
                }
                sum += count;
                lengthOfDay++;
                if(lengthOfDay >= N){ // N일 연속 시
                    if(sum >= 2 * N * K) { // N일간 총 2*N*K의 검색어를 달성 시 issue 검색 등록
                        issueKeywordCountingSort[wordIdx[idx]-'a']++;
                    }
                    sum -= keyWordCountByDate[idx].get(startIdx++);
                }
            }
        }

        // 정답 출력
        String ans = "None";
        int ansIdx = Integer.MAX_VALUE;
        int maxVal = 0;
        for(int idx = 0; idx < issueKeywordCountingSort.length; ++idx){
            if(maxVal < issueKeywordCountingSort[idx]){
                maxVal = issueKeywordCountingSort[idx];
                ansIdx = idx;
            }
        }
        if(maxVal != 0){
            ans = String.valueOf((char)(ansIdx+'a'));
        }
        System.out.println(ans);
    }
}
