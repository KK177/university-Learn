package forthTest;
import java.util.Scanner;
import java.io.*;


public class sixteen {
    public static void main(String[] args)throws IOException {
        String a;
        String input="";
        Scanner sc=new Scanner(System.in);
        //字符缓冲流
//        BufferedWriter brout = new BufferedWriter(new FileWriter("/Users/macbookpro/Desktop/1.rtf"));
//        while(true){
//            input = sc.nextLine();
//            if(input.equals("quit")){
//                System.out.println("finish");
//                break;
//            }else {
//                brout.write(input);
//                brout.newLine();
//            }
//        }
//        brout.flush();
//        brout.close();

        //字符打印流
        PrintWriter pout = new PrintWriter(new FileWriter("/Users/macbookpro/Desktop/1.rtf"));
        while(true){
            input = sc.nextLine();
            if(input.equals("quit")){
                System.out.println("finish");
                break;
            }else {
                pout.println(input);
            }
        }
        pout.close();
    }
}
