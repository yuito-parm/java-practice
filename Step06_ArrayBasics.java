public class Step06_ArrayBasics {
    public static void main(String[] args) {
        int[] scores =  {72, 85, 90, 68, 77};

        for (int i = 0; i < scores.length; i++) {
            System.out.println((i + 1) + "人目の点数: " + scores[i] + "点");
        }
    }
}
