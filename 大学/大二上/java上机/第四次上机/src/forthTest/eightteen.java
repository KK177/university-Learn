package forthTest;
import java.util.Scanner;
import java.io.*;

public class eightteen {
    public static void main(String[] args)throws IOException {
        System.out.println("请输入文件路径及文件名：");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        BufferedReader b=new BufferedReader(new FileReader("str.rtf"));
        int words = 0,lines = 0,chars = 0;
        String str;
        while((str=b.readLine())!=null){
            String[] strs = str.split(" ");
            for (int i=0;i< strs.length;i++){
                words++;
                chars +=strs[i].length();
            }
            lines++;

        }
        b.close();
        System.out.println("character: " + chars);
        System.out.println("word: " + words);
        System.out.println("line :" + lines);
    }
}
