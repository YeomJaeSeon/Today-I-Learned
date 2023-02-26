package problems1;

public class 부족한_금액_계산하기 {
    public static void main(String[] args) {
        부족한_금액_계산하기 instance = new 부족한_금액_계산하기();
        System.out.println(instance.solution(
                3, 20, 4
        ));

    }
    public long solution(int price, int money, int count) {
        long needTotal = 0;

        for(int i = 1; i <= count; i++){
            needTotal += (price * i);
        }

        return money > needTotal ? 0 : needTotal - money;
    }
}
