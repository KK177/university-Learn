package Thread;

public class ThreadTest1 {
    public static void main(String[] args) {
//       Threadtest test = new Threadtest();
//        Thread thread1 = new Thread(test,"1号窗");
//        Thread thread2 = new Thread(test,"2号窗");
//        Thread thread3 = new Thread(test,"3号窗");
//        thread3.start();
//        thread2.start();
//        thread1.start();

        Threadtestsame test1 = new Threadtestsame();
        Thread thread1 = new Thread(test1,"1号窗");
        Thread thread2 = new Thread(test1,"2号窗");
        Thread thread3 = new Thread(test1,"3号窗");
        thread1.start();
        thread3.start();
        thread2.start();


    }

}
//没有用到同步方法
class Threadtest implements Runnable {
    int ticket = 10;
    @Override
    public void run() {
        while (true){
            if (ticket>=0){
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "售票，票号为:" + ticket--);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else {
                break;
            }
        }
    }
}

//用到同步设计
class Threadtestsame implements Runnable {
    int ticket = 10;
    @Override
    public synchronized void run() {
        while(true) {
            if (ticket>=0){
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "售票，票号为:" + ticket--);
            }else {
                break;
            }
        }
    }
}




