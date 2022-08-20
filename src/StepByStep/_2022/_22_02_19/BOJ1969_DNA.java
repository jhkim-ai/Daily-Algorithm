package StepByStep._2022._22_02_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1969_DNA {

    private static int N, M, ans;
    private static String[] DNAs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = 0;
        DNAs = new String[N];

        for (int i = 0; i < N; ++i) {
            DNAs[i] = br.readLine();
        }

        for (int i = 0; i < M; ++i) {
            sb.append(cntDiffByIndex(i));
        }
        System.out.println(sb);
        System.out.println(ans);
    }

    public static char cntDiffByIndex(int idx) {
        int size = 4;
        int[] cntNucleotide = new int[size]; // A=0, C=1, G=2, T=3

        for (String dna : DNAs) {
            char c = dna.charAt(idx);
            if (c == 'A') {
                cntNucleotide[0]++;
            } else if (c == 'C') {
                cntNucleotide[1]++;
            } else if (c == 'G') {
                cntNucleotide[2]++;
            } else {
                cntNucleotide[3]++;
            }
        }

        int maxCnt = 0;
        int maxCntIdx = 0;
        for(int i = 0; i < size; ++i){
            if(cntNucleotide[i] > maxCnt){
                maxCnt = cntNucleotide[i];
                maxCntIdx = i;
            }
        }

        char c = '\0';
        if(maxCntIdx == 0){
            c = 'A';
        } else if (maxCntIdx == 1){
            c = 'C';
        } else if (maxCntIdx == 2){
            c = 'G';
        } else {
            c = 'T';
        }

        ans += N-cntNucleotide[maxCntIdx];

        return c;
    }
}
