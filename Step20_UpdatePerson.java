public class Step20_UpdatePerson {
    public static void main(String[] args) {
        Person p = new Person("たかし", 72);
        System.out.println("初期状態: " + p.getName() + "さん、" + p.getScore() + "点");   

        p.setScore(95);
        p.setName("たけし");

        System.out.println("変更後: " + p.getName() + "さん、" + p.getScore() + "点");
    }
}

class Person {
    private String name;
    private int score;

    public Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
