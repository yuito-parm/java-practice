public class Step03_IfStatement {
    public static void main(String[] args) {
        int score = 95;

        if (score >= 90) {
            System.out.println("とてもよくできました！");
        } else if (score >= 70) {
            System.out.println("よくできました！");
        } else if (score >= 50) {
            System.out.println("がんばりました！");
        } else {
            System.out.println("もっとがんばろう！");
        }
    }
}
