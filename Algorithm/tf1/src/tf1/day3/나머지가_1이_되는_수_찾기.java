package tf1.day3;

public class 나머지가_1이_되는_수_찾기 {
    public static void main(String[] args) {
        나머지가_1이_되는_수_찾기 instance = new 나머지가_1이_되는_수_찾기();
        System.out.println(instance.solution(12));

    }
    public int solution(int n) {
        int answer = 0;
        for(int x = 1; x < n; x++){
            if(n % x == 1){
                answer = x;
                break;
            }
        }
        return answer;
    }
}
