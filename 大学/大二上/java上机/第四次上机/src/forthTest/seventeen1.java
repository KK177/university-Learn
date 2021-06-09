package forthTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class seventeen1 {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入文件路径及文件名：");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        System.out.println("请输入要删除的字符：");
        String str2 = sc.nextLine();
        BufferedReader b=new BufferedReader(new FileReader("str.rtf"));
        BufferedWriter w=new BufferedWriter(new FileWriter("/Users/macbookpro/Desktop/3.rtf"));
        List<String> s = new ArrayList<>();
        String str;
        int k=0;
        while((str=b.readLine())!=null){
            StringBuilder sb=new StringBuilder(str);
            str=str.replace("quit","");
            s.add(str);
            k=0;
            w.write(s.get(k++));
            w.newLine();
        }
        w.flush();
        b.close();
        w.close();
    }
}
