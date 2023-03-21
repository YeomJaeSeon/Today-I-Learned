package tf1.day1;

public class 문자열_나누기 {
    public static void main(String[] args) {
        문자열_나누기 instance = new 문자열_나누기();
        System.out.println(instance.solution("banana"));


    }
    public int solution(String s) {
        int answer = 0;
        while(s.length() != 0){
            answer++;

            char x = s.charAt(0);
            int xCount = 1;
            int notXCount = 0;

            for(int j = 1; j < s.length(); j++){
                if(s.charAt(j) == x){
                    xCount++;
                }else{
                    notXCount++;
                }
                if(xCount == notXCount){
                    break;
                }
            }

            s = s.substring(xCount + notXCount);
        }

        return answer;
    }
}
