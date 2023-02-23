package tf1.day1;

public class 기사단원의_무기 {
    public static void main(String[] args) {
        기사단원의_무기 instance = new 기사단원의_무기();
        System.out.println(instance.solution(
                10, 3, 2
        ));

    }
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for(int i = 1; i <= number; i++){
            int count = countDivisor(i);

            if(count > limit){
                answer += power;
            }else{
                answer += count;
            }
        }

        return answer;
    }
    private int countDivisor(int number){
        int count = 0;

        for(int i = 1; i * i <= number; i++){
            if(i * i == number){
                count++;
            }else if(number % i == 0){
                count += 2;
            }
        }

        return count;
    }
}

/**
 * 10만 * 10만 -> 시간초과
 *
 *
 * 약수의 개수 구하는 시간줄이는법
 * 제곱근까지 약수를 구하기.
 * O(n * n)을
 * O(sqrt(n) * n)으로 줄임
 */