package textPrice;

class Vehicle {
    String name;
    int speed;
    int price;

    public  Vehicle(String name,int speed,int price) {
        this.name = name;
        this.speed = speed;
        this.price = price;
    }
    public int addPrice(int add) {
        return price + add;
    }
    public  int subtraction(int cancel) {
        return price - cancel;
    }

}


public class testPrice {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("akk",6,60);
        System.out.println(vehicle.addPrice(10));
        System.out.println(vehicle.subtraction(10));
    }
}
