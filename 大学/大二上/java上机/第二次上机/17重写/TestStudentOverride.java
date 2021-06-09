

public class TestStudentOverride {
    public static void main(String[] args) {
        StudentOverride undergraduate = averageScore -> Math.abs(averageScore / 10 - 10);
        StudentOverride postgraduate = averageScore -> Math.abs(averageScore / 10 - 11);
        System.out.println(undergraduate.scoreToGrade(80) + "等奖");
        System.out.println(postgraduate.scoreToGrade(80) + "等奖");
    }
}
