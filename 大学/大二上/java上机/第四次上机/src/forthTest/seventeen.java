package forthTest;
import java.util.Scanner;
import java.io.*;

public class seventeen {
    public static void main(String[] args)throws IOException {
        System.out.println("请输入文件路径及文件名：");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        System.out.println("请输入要删除的字符：");
        String str2 = sc.nextLine();
        BufferedReader b=new BufferedReader(new FileReader("str.rtf"));
        BufferedWriter w=new BufferedWriter(new FileWriter("/Users/macbookpro/Desktop/2.rtf"));
        String s;
        int k;
        while((s=b.readLine())!=null){
            StringBuilder sb=new StringBuilder(s);
            while((k= sb.indexOf("quit"))!=-1){
                sb.delete(k,k+4);
            w.write(sb.toString());
            w.newLine();
            }
        }
        w.flush();
        b.close();
        w.close();

    }
}
///Users/macbookpro/Desktop/2.rtf