package testRectangle;

class Rectangle {
    int width;
    int height;

    public Rectangle(int w,int h){
        width = w;
        height = h;
    }

    public int getArea() {
        return  width * height;
    }

    public  int getPerimeter() {
        return (width + height) * 2;
    }

    public  void draw() {
        int i = 0;
        int j = 0;
        for(i=0;i<height;i++){
            for(j=0;j<width;j++){
                System.out.print("*");
            }
            System.out.println();
        }

    }

}


public class testRectangle {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5,2);
        System.out.println(rectangle.getArea());
        System.out.println(rectangle.getPerimeter());
        rectangle.draw();
    }
}
