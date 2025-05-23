public class Step05_ForIfCombo {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i + "は偶数です！");
            } else {
                System.out.println(i + "は奇数です！");
            }
        }

        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
                System.out.println("👏");
            } else {
                System.out.println("😑");
            }
        }
    }
}
