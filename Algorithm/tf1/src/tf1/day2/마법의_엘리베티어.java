package tf1.day2;

public class 마법의_엘리베티어 {
    public static void main(String[] args) {
        마법의_엘리베티어 instance = new 마법의_엘리베티어();
        System.out.println(instance.solution(155));
//        System.out.println(instance.solution(154));
    }
    public int solution(int storey) {
        int answer = 0;
        while(storey > 0){
            int n = storey % 10;
            if(n >= 6){
                answer += (10 - n);
                storey += 10;
            }else if(n == 5){
                answer += 5;
                storey += (storey / 10) % 10 >= 5 ? 10 : 0;
            } else{
                answer += n;
            }

            storey /= 10;
        }
        return answer;
    }
}
/**
 * 155 -> 11
 * 154 -> 10
 */

/**
 * 995
 *
 * <1>
 *     990 (5)
 *     100 (1)
 * </1>
 *
 * <2>
 *     990 (5)
 *     10 (1)
 *
 * </2>
 */

/**
 * 5일 경우 십의자리도 확인해야한다.. 잘 이해안댐;
 */