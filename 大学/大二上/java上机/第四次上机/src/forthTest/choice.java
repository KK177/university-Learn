package forthTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class choice {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入文件路径及文件名：");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        BufferedReader b=new BufferedReader(new FileReader("str.rtf"));
//        String[] strs = new String[100];
//        int[] ints = new int[100];
//        String s;
//        int flag = 0;
//        int index = 0;
//        int count = 0;
//        while((s=b.readLine())!=null){
//            for (int i=0;i<strs.length;i++){
//               if (strs[i].equals(s)){
//                   flag = 1;
//                   index = i;
//                   break;
//               }
//               index = i;
//            }
//            if (flag==1){
//                flag = 0;
//                ints[index]++;
//            }else{
//               strs[index+1] = s;
//               ints[index+1] = 1;
//            }
//            System.out.println(s);
//        }
//        System.out.println(strs);
//        System.out.println(ints);
        String s;
        int flag = 0;
        int index = 0;
        int count = 0;
        char ch;
        String sh;
        List<String> strlist = new ArrayList<String>();
        List<Integer> intList = new ArrayList<Integer>();
        while((s=b.readLine())!=null) {
            for (int i=0;i<s.length();i++) {
                ch = s.charAt(i);
                sh = String.valueOf(ch);
                if (strlist.contains(sh)) {
                    index = strlist.indexOf(sh);
                    count = intList.get(index);
                    intList.set(index, count + 1);
                } else {
                    strlist.add(sh);
                    intList.add(1);
                }
                System.out.println(ch);
            }
        }
        System.out.println(strlist);
        System.out.println(intList);

    }
}
