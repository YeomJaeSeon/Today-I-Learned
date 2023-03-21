package tf1.day2;

public class 코카콜라 {
    public static void main(String[] args) {
        코카콜라 instance = new 코카콜라();
        System.out.println(instance.solution(3, 1, 20));
    }
    public int solution(int a, int b, int n) {
        int answer = 0;
        while (n >= a){
            answer += (n / a) * b;
            int remain = n % a;

            n /= a;
            n *= b;
            n += remain;
        }

        return answer;
    }
}
/**
 * 쉬운 구현문제
 *
 * 남은 n에 b만큼 곱해줘야한다.
 */
