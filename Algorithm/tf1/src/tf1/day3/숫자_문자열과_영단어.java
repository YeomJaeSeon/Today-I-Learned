package tf1.day3;

import java.util.Arrays;

public class 숫자_문자열과_영단어 {
    public static void main(String[] args) {
        숫자_문자열과_영단어 instance = new 숫자_문자열과_영단어();
        System.out.println(instance.solution("one4seveneight"));

    }
    public int solution(String s) {
        String[] infoArr = {
                "zero",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine"
        };

        StringBuilder resultSb = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                resultSb.append(s.charAt(i));
                continue;
            }

            sb.append(s.charAt(i));
            int idx = -1;
            for(int j = 0; j <= 9; j++){
                if(infoArr[j].equals(sb.toString())){
                    idx = j;
                    sb.setLength(0);
                    break;
                }
            }

            if(idx != -1){
                resultSb.append(idx);
            }

        }

        return Integer.parseInt(resultSb.toString());
    }
}
