package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1026 {
    private static int N;
    private static int[] A;
    private static int[] B;
    public static void main(String[] args) throws IOException {
        initInput();
        int[] sortedA = Arrays.stream(A).boxed().sorted().mapToInt(Integer::intValue).toArray();
        int[] sortedB = Arrays.stream(B).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        int sum = calculateEachElementBySameLenArray(sortedA, sortedB);
        printResult(sum);
    }
    private static void printResult(int answer){
        System.out.println(answer);
    }

    private static int calculateEachElementBySameLenArray(int[] sortedA, int[] sortedB){
        int sum = 0;
        for(int i = 0; i < N; i++){
            sum += (sortedA[i] * sortedB[i]);
        }
        return sum;
    }
    private static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];

        StringTokenizer stA = new StringTokenizer(br.readLine(), " ");
        StringTokenizer stB = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(stA.nextToken());
            B[i] = Integer.parseInt(stB.nextToken());
        }
    }
}

/**
 * B의 큰 원소엔 A의 작은 원소가
 * B의 작은 원소엔 A의 큰 원소가 와야 S가 최솟값
 *
 * A를 오름차순으로 정렬 B를 내림차순으로 정렬한 뒤, 1-1 매핑한뒤 S계산하면됨
 * 결과는 S만 출력하면 되니, B를 재배열한다는 것은 무시해도된다.
 */