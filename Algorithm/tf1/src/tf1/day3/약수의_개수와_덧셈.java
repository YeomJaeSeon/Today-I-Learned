package tf1.day3;

public class 약수의_개수와_덧셈 {
    public static void main(String[] args) {
        약수의_개수와_덧셈 instance = new 약수의_개수와_덧셈();
        System.out.println(instance.solution(13, 17));
    }
    public int solution(int left, int right) {
        int answer = 0;
        for(int i = left; i <= right; i++){
            answer = isCountEven(i) ? answer + i : answer - i;
        }

        return answer;
    }
    private boolean isCountEven(int number){
        int count = 0;

        for(int i = 1; i * i <= number; i++){
            if(i * i == number){
                count++;
            }else if(number % i == 0){
                count += 2;
            }
        }

        return count % 2 == 0;
    }
}
