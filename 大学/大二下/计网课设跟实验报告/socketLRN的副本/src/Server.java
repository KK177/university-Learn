

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;//Vector类实现了一个动态数组，主要用于事先不知道数组的大小，或者只需要一个可以改变大小的数组
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {//服务器
    public static void main(String[] args) {
        Vector<UserThread> vector = new Vector<>();
        ExecutorService es = Executors.newFixedThreadPool(5);
        try {
            ServerSocket server = new ServerSocket(9988);
            System.out.println("服务器启动，等待客户端连接");
            while(true) {
                Socket socket = server.accept();//等待客户端连接，此方法会阻塞当前线程，直到有客户端连接
                UserThread user = new UserThread(socket,vector);
                es.execute(user);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//处理客户消息的进程
class UserThread implements Runnable {
    private String name; //客户端唯一的呢称
    private Socket socket;//代表此线程任务服务的客户端socket
    private Vector<UserThread> vector;
    private ObjectInputStream inputStream;//对象输入流
    private ObjectOutputStream outputStrem;//对象输出流
    private boolean flag = true; //信息循环读取标记

    public UserThread(Socket socket, Vector<UserThread> vector) {
        this.socket = socket;
        this.vector = vector;
        this.vector.add(this);
    }
    @Override
    public void run() {
        //新增连接的客户端
        System.out.println("客户端" + socket.getInetAddress().getHostAddress() + "已连接");
        //初始化输入输出流
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStrem = new ObjectOutputStream(socket.getOutputStream());
            //循环读取信息
            while (flag) {
                MessageInfo message = (MessageInfo) inputStream.readObject();
                int size;
                UserThread user;
                switch (message.getType()) {
                    case MessageType.TYPE_SEND_SINGLE:
                        String to = message.getTo();
                        size = vector.size();
                        for (int i=0; i < size; i++) {
                            user = vector.get(i);
                            if (to.equals(user.name) && user != this) {
                                user.outputStrem.writeObject(message);
                                break;
                            }
                        }
                        break;

                    case MessageType.TYPE_SEND_GROUP:
                       size = vector.size();
                       for (int i=0; i<size; i++) {
                           user = vector.get(i);
                           if (user != this) {
                               user.outputStrem.writeObject(message);
                           }
                       }
                       break;
                    case MessageType.TYPE_LOGIN:
                        name = message.getFrom();
                        getUserInfo(this.vector, message);
                        break;
                    default:
                        break;
                }

            }
            inputStream.close();
            outputStrem.close();
        }catch (EOFException e) {
           flag = false;
           System.out.println("客户端" + socket.getInetAddress().getHostAddress() + "已断开");
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            flag = false;
        }
    }
    public void getUserInfo(Vector<UserThread> users, MessageInfo message) {
        String userGroup = "欢迎你加入聊天室,当前在线客户端有：";
        UserThread user;
        int size = users.size();
        for (int i=0; i<size; i++) {
            user = users.get(i);
            userGroup = userGroup + " " + user.name;
        }
        message.setInfo(userGroup);
        for (int i=0; i<size; i++) {
            try {
                user = users.get(i);
                outputStrem.writeObject(message);  user = users.get(i);
                outputStrem.writeObject(message);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


