package homework2;
import java.util.*;

public class TestStudent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入本科生的成绩");
        int score1 = sc.nextInt();
        Undergraduate undergraduate = new Undergraduate(score1);
        Character grade1 = undergraduate.scoreToGrade();
        System.out.println("本科生的成绩等级为"+grade1);

        System.out.println("请输入研究生的成绩");
        int score2 = sc.nextInt();
        Postgraduate postgraduate = new Postgraduate(score2);
        Character grade2 = postgraduate.scoreToGrade();
        System.out.println("研究生的成绩等级为"+grade2);
    }
}
