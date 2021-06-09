import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountRegister {

    /**
     *
     * @param number 输入的手机号码
     * @return 验证手机号码
     */
    public boolean isPhoneNumber(String number) {
        String regex = "^[1][3,4,5,7,8][0-9]{9}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(number);
        return m.matches();
    }

    /**
     *
     * @param email 输入的邮箱地址
     * @return 验证邮箱地址
     */
    public boolean isEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@(?!.*\\.\\..*)[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     *
     * @param id 输入的身份证
     * @return 验证身份证
     */
    public boolean isId(String id) {
        String regex = "\\d{15}(\\d{2}[0-9xX])?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(id);
        return m.matches();
    }

    public static void main(String[] args) {
        AccountRegister test = new AccountRegister();
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎来到注册界面");
        System.out.println("请输入手机号码：");
        String phoneNumber = scanner.next();
        while (!test.isPhoneNumber(phoneNumber)) {
            System.out.println("请正确的手机号码：");
            phoneNumber = scanner.next();
        }

        System.out.println("请输入邮箱地址：");
        String email = scanner.next();
        while (!test.isEmail(email)) {
            System.out.println("请输入正确的邮箱地址：");
            email = scanner.next();
        }

        System.out.println("请输入身份证：");
        String id = scanner.next();
        while (!test.isId(id)) {
            System.out.println("请输入正确的身份证：");
            id = scanner.next();
        }
        System.out.println("注册成功！");
    }
}


