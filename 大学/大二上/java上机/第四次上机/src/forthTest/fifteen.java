package forthTest;
import java.util.Scanner;
import java.io.*;
public class fifteen {
    public static void main(String[] args)throws IOException {
        System.out.println("请输入要复制的文件路径及文件名：");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        File file = new File(str);
        System.out.println("请输入目标文件路径及文件名：");
        Scanner sca = new Scanner(System.in);
        String str1 = sca.nextLine();
        File file1 = new File(str1);
//        //字节流
//        FileInputStream fis = new FileInputStream((file));
//        FileOutputStream fos = new FileOutputStream(file1);
//        int a;
//        while((a = fis.read())!=-1){
//            fos.write(a);
//        }
//        fis.close();
//        fos.close();

        //字符流
        BufferedReader brin = new BufferedReader(new FileReader(file));
        BufferedWriter brout = new BufferedWriter(new FileWriter(file1));
        String s;
        while((s = brin.readLine())!=null){
            System.out.println(s);
            brout.write(s);
            brout.newLine();
        }
        brout.flush();
        brin.close();
        brout.close();

///Users/macbookpro/Desktop/2.rtf

    }
}
