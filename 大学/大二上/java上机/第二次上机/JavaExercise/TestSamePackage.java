package JavaExercise;

public class TestSamePackage {
    public static void main(String[] args) {
        Test instance = new Test();
        System.out.println(instance.a);
        System.out.println(instance.b);
        System.out.println(instance.c);
        //System.out.println(instance.d);

        instance.A();
        instance.B();
        instance.C();
        //instance.D();
    }
}
