
public abstract class Student {
    private String studentID;
    private String name;
    private int averageScore;

    public abstract Character scoreToGrade();
}

class Undergraduate extends Student {
    private int averageScore;

    public Undergraduate(int averageScore) {
        this.averageScore = averageScore;
    }

    public Character scoreToGrade() {
        if(averageScore <= 60) {
            return 'D';
        } else if(averageScore <= 75) {
            return 'C';
        } else if(averageScore <= 85) {
            return 'B';
        } else {
            return 'A';
        }
    }
}

class Postgraduate extends  Student {
    private int averageScore;

    public Postgraduate(int averageScore) {
        this.averageScore = averageScore;
    }

    public Character scoreToGrade() {
        if(averageScore <= 70) {
            return 'D';
        } else if(averageScore <= 75) {
            return 'C';
        } else if(averageScore <= 90) {
            return 'B';
        } else {
            return 'A';
        }
    }
}
