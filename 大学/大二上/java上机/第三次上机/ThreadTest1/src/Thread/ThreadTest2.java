package Thread;

public class ThreadTest2 {
    public static void main(String[] args) {
        ThreadTestt test1 = new ThreadTestt("add");
        ThreadTestt test2 = new ThreadTestt("reduce");
        Thread t1 = new Thread(test1,"1号");
        Thread t2 = new Thread(test2,"2号");
        t1.start();
        t2.start();
    }
}

class account {
    private int balance = 0; //余额
    public account(int balance) {
        this.balance = balance;
    }
    //显示余额
    public int getBalance() {
        return balance;
    }
    //存款

}

class Bank {
    private static int sum = 0;
    private static int temp = sum;
    //存钱
    public synchronized static void add(int money) {
        temp = sum;
        sum += money;
        System.out.println("当前余额为："+temp+","+Thread.currentThread().getName()+"存进"+money+"钱"+"现在余额为："+sum);
    }

    //取钱
    public synchronized static void reduce(int money) {
        temp = sum;
        sum -= money;
        System.out.println("当前余额为："+temp+","+Thread.currentThread().getName()+"取出"+money+"钱"+"现在余额为："+sum);
    }

}

class ThreadTestt implements Runnable {
    String type = "add";
    public ThreadTestt(String type){
        this.type = type;
    }
    @Override
    public void run() {
        for (int i=0;i<5;i++){
            if (this.type == "add"){
                Bank.add(500);
            }else {
                Bank.reduce(500);
            }
        }
    }
}

