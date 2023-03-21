package tf1.day2;

public class 삼총사 {
    static int N;
    static int[] result;
    static int answer = 0;
    public static void main(String[] args) {
        삼총사 instance = new 삼총사();
        System.out.println(instance.solution(new int[]{-2, 3, 0, 2, -5}));

    }
    public int solution(int[] number) {
        N = number.length;

        result = new int[3];

        recursive(0, 0, number);
        return answer;
    }
    private void recursive(int m, int start, int[] number){
        if(m == 3){
            int sum = 0;
            for(int i = 0; i < 3; i++){
                sum += number[result[i]];
            }
            if(sum == 0){
                answer++;
            }

            return;
        }
        for(int i = start; i < N; i++){
            result[m] = i;
            recursive(m + 1, i + 1, number);
        }
    }
}

/**
 * 재귀함수 이용
 */