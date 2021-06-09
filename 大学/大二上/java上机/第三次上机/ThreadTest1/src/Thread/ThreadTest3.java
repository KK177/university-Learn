package Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest3 {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Thread thread1 = new Thread(customer,"1号");
        Thread thread2 = new Thread(customer,"2号");
        thread1.start();
        thread2.start();
    }
}

class Customer implements Runnable {
    public void addBalance(int money) {
        balance += money;
        System.out.println(Thread.currentThread().getName()+"存进100元"+"银行总余额为："+balance);
    }
    int balance = 0;
//    //同步方法
//    @Override
//    public synchronized void run() {
//        for (int i=0;i<3;i++){
//            addBalance(100);
//        }
//    }

    //同步代码块
    @Override
    public void run() {
        for (int i=0;i<3;i++){
            synchronized (this){
                addBalance(100);
            }
        }
    }

//    //加锁
//    private Lock lock = new ReentrantLock();
//    @Override
//    public void run() {
//        for (int i=0;i<3;i++){
//            lock.lock();
//            try{
//                addBalance(100);
//            }catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                lock.unlock();
//            }
//
//        }
//    }
}

