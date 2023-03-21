package problems1;

public class 점찍기 {
    public static void main(String[] args) {
        점찍기 instance = new 점찍기();
        System.out.println(instance.solution(1, 5));
    }

    public long solution(int k, int d) {
        long answer = 0;
        long d2 = (long)Math.pow(d, 2);

        for(int i = 0; i * k <= d; i++){
            long x2 = (long)Math.pow(i * k, 2);
            long y2 = d2 - x2;
            long count = ((long)Math.sqrt(y2) / k) + 1;

            answer += count;
        }

        return answer;
    }

}

/**
 * 너무 간단한문젠지알았는데 시간초과
 * -> for loop 1번으로 구할수있음 이중 for문 하지않아도됨 O(n^2) -> O(n)으로 가능
 *
 * x^2 + y^2 = d^2 공식을 통해서 풀수있음
 *
 * 한번의 for loop이면 나머지 좌표의 최대값을 구할수 있음
 *
 * 또, long타입에 주의하자.
 * 그리고 0좌표도 포함됨에 주의
 */
