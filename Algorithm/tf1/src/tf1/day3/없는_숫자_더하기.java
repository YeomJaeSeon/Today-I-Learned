package tf1.day3;

public class 없는_숫자_더하기 {
    public static void main(String[] args) {
        없는_숫자_더하기 instance = new 없는_숫자_더하기();
        System.out.println(instance.solution(new int[]{1,2,3,4,6,7,8,0}));

    }
    public int solution(int[] numbers) {
        boolean[] visited = new boolean[10];

        for (int number : numbers) {
            visited[number] = true;
        }

        int sum = 0;
        for(int i = 0; i < 10; i++){
            if(!visited[i]){
                sum += i;
            }
        }

        return sum;
    }
}
