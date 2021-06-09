public class Figure {
    double pi;
    public Figure(double Pi) {
        pi = Pi;
    }
    void circle() {
        System.out.println("Pi:" + pi);
    }
    void rectangle() {
        System.out.println("Pi:" + pi);
    }
    void triangle() {
        System.out.println("Pi:" + pi);
    }
}
class rRectangle extends Figure{
    double W, H;
    public rRectangle(double pi, double w, double h){
        super(pi);
        W = w;
        H = h;
    }
    void rectangle(){
        System.out.println("square:" + W * H);
    }
}
class Triangle extends Figure{
    double tW, tH;
    public Triangle(double pi, double tw, double th){
        super(pi);
        tW = tw;
        tH = th;
    }

    void triangle() {
        System.out.println("square:" + tW * tH / 2);
    }
}
class Circle extends Figure{
    double r;
    public Circle(double pi, double rad){
        super(pi);
        r = rad;
    }
    void circle(){
        System.out.println("square:" + pi * r * r);
    }
}
class test{
    public static void main(String[] args){
        Figure a = new Figure(3.14);
        a.circle();
        a.rectangle();
        a.triangle();

        rRectangle b = new rRectangle(3.14,5,10);
        b.rectangle();

        Circle c = new Circle(3.14,4);
        c.circle();

        Triangle d = new Triangle(3.14, 2,5);
        d.triangle();
    }
}