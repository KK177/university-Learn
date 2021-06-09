package homework2;

public class Test extends Father implements Interface12 {
    public Test() {
        System.out.println("继承类Father。");
    }

    @Override
    public void method() {
        System.out.println("重写接口Interface12中的method方法。" );
    }

    @Override
    public void method11() {
        System.out.println("重写接口Interface1中的method11方法。" );
    }

    @Override
    public void method12() {
        System.out.println("重写接口Interface1中的method12方法。" );
    }

    @Override
    public void method21() {
        System.out.println("重写接口Interface2中的method21方法。" );
    }

    @Override
    public void method22() {
        System.out.println("重写接口Interface2中的method22方法。" );
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.method();
        test.method11();
        test.method12();
        test.method21();
        test.method22();
    }
}
