package JavaExercise2;

import JavaExercise.Test;

public class Testchild extends Test {
    public static void main(String[] args) {
        Testchild instance = new Testchild();
        System.out.println(instance.a);
        System.out.println(instance.b);
        //System.out.println(instance.c);
        //System.out.println(instance.d);

        instance.A();
        instance.B();
        //instance.C();
        //instance.D();

    }
}
