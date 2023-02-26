package problems1;

public class 신규_아이디_추천{
    public static void main(String[] args) {
        신규_아이디_추천 instance = new 신규_아이디_추천();
        System.out.println(instance.solution("...!@BaT#*..y.abcdefghijklm"));
        System.out.println(instance.solution("z-+.^."));
        System.out.println(instance.solution("=.="));
        System.out.println(instance.solution("123_.def"));
        System.out.println(instance.solution("abcdefghijklmn.p"));

    }
    public String solution(String new_id) {
        StepSolution stepSolution = new StepSolution(new_id);

        return stepSolution
                .firstStep()
                .secondStep()
                .thirdStep()
                .fourthStep()
                .fifthStep()
                .sixthStep()
                .lastStep();
    }
}

class StepSolution{
    private String new_id;

    public StepSolution(String new_id) {
        this.new_id = new_id;
    }

    public StepSolution firstStep(){
        this.new_id = this.new_id.toLowerCase();

        return this;
    }

    public StepSolution secondStep(){
        StringBuilder second = new StringBuilder();
        // 2
        for(int i = 0; i < this.new_id.length(); i++){
            char ch = this.new_id.charAt(i);
            if((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || ch == '-' || ch == '_' || ch =='.'){
                second.append(ch);
            }
        }

        this.new_id = second.toString();
        return this;
    }

    public StepSolution thirdStep(){
        StringBuilder third = new StringBuilder();
        for(int i = 0; i < this.new_id.length(); i++){
            char ch = this.new_id.charAt(i);
            if(third.length() == 0){
                third.append(ch);
            }else{
                char beforeCh = third.charAt(third.length() - 1);

                if(!(beforeCh == '.' && ch == '.')){
                    third.append(ch);
                }
            }
        }

        this.new_id = third.toString();

        return this;
    }

    public StepSolution fourthStep(){
        StringBuilder sb = new StringBuilder(this.new_id);
        while (sb.length() > 0 && (sb.charAt(0) == '.' || sb.charAt(sb.length() - 1) == '.')){
            if(sb.length() > 0 && sb.charAt(0) == '.'){
                sb.deleteCharAt(0);
            }
            if(sb.length() > 0 && sb.charAt(sb.length() - 1) == '.'){
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        this.new_id = sb.toString();
        return this;
    }
    public StepSolution fifthStep(){
        if(this.new_id.length() == 0){
            this.new_id = "a";
        }

        return this;
    }
    public StepSolution sixthStep(){
        StringBuilder sb = new StringBuilder();
        if(this.new_id.length() >= 16){
            for(int i = 0; i < 15; i++){
                sb.append(this.new_id.charAt(i));
            }

            while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.'){
                sb.deleteCharAt(sb.length() - 1);
            }

            this.new_id = sb.toString();
        }

        return this;
    }
    public String lastStep(){
        StringBuilder sb = new StringBuilder(this.new_id);
        if(sb.length() <= 2){
            char lastCh = sb.charAt(sb.length() - 1);
            while (sb.length() != 3){
                sb.append(lastCh);
            }
        }
        this.new_id = sb.toString();

        return this.new_id;
    }
}

/**
 * 메서드 체인으로 스텝을 가독성있게 순서대로 로직 구성
 */