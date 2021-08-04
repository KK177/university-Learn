
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Client { //客户端
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ExecutorService es = Executors.newSingleThreadExecutor(); //一个客户端对应一个单线程
        try {
            Socket socket = new Socket("localhost",9988); //Localhost代表的是本地的服务器，要是连别的服务器，就带IP地址
            System.out.println("连接服务器成功");
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            //客户端登录
            System.out.println("请输入呢称：");
            String name = input.nextLine();

            //对输入的信息进行封装，然后发给服务器
            MessageInfo messageInfo = new MessageInfo(name,null,MessageType.TYPE_LOGIN,null);
            outputStream.writeObject(messageInfo);

            //从服务器读取信息
            messageInfo = (MessageInfo) inputStream.readObject();
            System.out.println(messageInfo.getInfo());
            //登录结束

            //启动子线程去获取信息
            es.execute(new ReadInfoThread(inputStream));

            //在主线程发送信息
            while (true) {
                messageInfo = new MessageInfo();
                System.out.println("to:");
                messageInfo.setTo(input.nextLine());
                System.out.println("Info:");
                messageInfo.setInfo(input.nextLine());
                messageInfo.setFrom(name);
                if (messageInfo.getTo().equals("all")) {
                    messageInfo.setType(MessageType.TYPE_SEND_GROUP);
                }else {
                    messageInfo.setType(MessageType.TYPE_SEND_SINGLE);
                }
                outputStream.writeObject(messageInfo);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
class ReadInfoThread implements Runnable {
    private ObjectInputStream inStream;
    private boolean flag = true;

    public ReadInfoThread(ObjectInputStream inStream) {
        this.inStream = inStream;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        //利用flag来判断是否一直开辟线程读取信息
        while(flag) {
            try {
                MessageInfo info = (MessageInfo) inStream.readObject();
                if (info.getType() == MessageType.TYPE_SEND_SINGLE) {
                    System.out.println("[" + info.getFrom() + "] 对我说 ：" + info.getInfo());
                }else {
                    System.out.println("[" + info.getFrom() + "] 广播 ：" + info.getInfo());
                }
            }catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //flag为false就会执行下面的代码
        if (inStream != null) {
            try {
                inStream.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

