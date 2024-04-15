import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class APP {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作：1登录 2注册 3忘记密码 4退出系统");
            String choose = sc.next();
            loop1:switch (choose){
                case "1":
                    login(list);
                    break;
                case "2":
                    register(list);
                    break;
                case "3":
                    forgetPassword(list);
                    break;
                case "4":
                    System.out.println("感谢使用");
                    System.exit(0);
                default:
                    System.out.println("没有这个选项");
                    break;
            }
        }
    }

    //忘记密码
    //键盘录入用户名，判断当前用户名是否存在，如不存在，直接结束方法并提示未注册
    //键盘录入手机号和身份证号码
    //判断当前手机号和身份证号码是否一致
    //如果一致，则提示输入密码，进行修改
    //如果不一致，提示：账号信息不匹配修改失败
    public static ArrayList<User> forgetPassword(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = sc.next();
        boolean flag = contain(list,userName);
        if(!flag){
            System.out.println("当前用户名并不存在，请先注册！");
            return list;
        }
        int index = getUserNameIndex(list,userName);
        User user = list.get(index);
        while (true) {
            System.out.println("请输入手机号：");
            String phoneNum = sc.next();
            System.out.println("请输入身份号：");
            String id = sc.next();
            if(user.getPersonID().equals(id) && user.getPhoneNum().equals(phoneNum)){
                System.out.println("请输入新的密码：");
                String newPassword = sc.next();
                user.setPassword(newPassword);
                System.out.println("修改成功！");
                break;
            }
            else{
                System.out.println("账户信息不匹配，请重新输入！");
            }
        }
        list.set(index,user);
        return list;
    }

    //登录
    //键盘录入用户名、密码和验证码
    //用户名如果未注册，直接结束方法，并提示：用户名未注册，请先注册
    //判断验证码是否正确，如不正确，重新输入
    //在判断用户名和密码是否正确，共三次机会
    public static void login(ArrayList<User> list){
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            User user = new User();
            System.out.println("请输入用户名：");
            String userName = sc.next();
            if(list.size() == 0 || !contain(list,userName)){
                System.out.println("用户名未注册，请先注册！");
                return;
            }
            System.out.println("请输入密码：");
            String password = sc.next();
            int index = getUserNameIndex(list,userName);
            User u = list.get(index);
            while (true) {
                String code = getCaptcha();
                System.out.println("验证码为：" + code);
                System.out.println("请输入验证码：");
                String captcha = sc.next();
                if(!captcha.equals(code)){
                    System.out.println("验证码输入错误！");
                    continue;
                }
                else{
                    System.out.println("验证码输入正确！");
                    break;
                }
            }
            if(u.getUserName().equals(userName) && u.getPassword().equals(password)){
                System.out.println("登陆成功！");
                StudentSystem ss = new StudentSystem();
                ss.startStudentSystem();
                break;
            }
            else{
                System.out.println("登陆失败！");
                if (i == 2){
                    System.out.println("三次输入失败，账号锁定！");
                    return;
                }
                else{
                    System.out.println("还剩"+(2-i)+"次机会！");
                }
            }
        }


    }

    //注册
    //用户名要3-15位且唯一，只能是数字加字母的组合，不能是纯数字
    //键盘输入两次密码，两次一致才可以注册
    //身份证号要验证：长度为18，不能以0开头，前17位必须是数字，最后一位可以是数字，也可以是大小写x
    //手机号要验证：长度位11位，不能以0开头，必须全是数字
    public static ArrayList<User> register(ArrayList<User> list){
        User u = new User();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = sc.next();
        int numCount = numCount(userName);
        int letterCount = letterCount(userName);
        //一般先校验格式，再验证是否唯一
        if (numCount == userName.length() || !determineUserNameLength(userName) || (numCount + letterCount) != userName.length()){
            System.out.println("用户名不符合规则，注册失败！");
            return list;
        }
        u.setUserName(userName);
        System.out.println("请输入密码：");
        String password1 = sc.next();
        System.out.println("请再次输入密码：");
        String password2 = sc.next();
        if(!password1.equals(password2)){
            System.out.println("两次密码不一致，注册失败！");
            return list;
        }
        u.setPassword(password1);
        System.out.println("请输入身份证号：");
        String personId = sc.next();
        if(!personIdRightOrNot(personId)){
            System.out.println("身份证号码不符合规则，注册失败！");
            return list;
        }
        u.setPersonID(personId);
        System.out.println("请输入手机号：");
        String phoneNum = sc.next();
        if(!phoneNumRightOrNot(phoneNum)){
            System.out.println("手机号不符合规则，注册失败！");
            return list;
        }
        u.setPhoneNum(phoneNum);
        list.add(u);
        System.out.println("注册成功！");
        return list;
    }

    //判断字符串长度是否满足3-15位，是则返回true，否则返回false
    public static boolean determineUserNameLength(String s){
        if(s.length() >= 3 && s.length() <= 15){
            return true;
        }
        return false;
    }

    //判断字符串包含多少数字
    public static int numCount(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9'){
                count ++;
            }
        }
        return count;
    }

    //判断字符串包含多少字母（大小写均可）
    public static int letterCount(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') ){
                count ++;
            }
        }
        return count;
    }

    //判断身份证是否符合条件，符合返回true，否则返回false
    public static boolean personIdRightOrNot(String s){
        if (s.length() != 18){
            return false;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            char ch = s.charAt(i);
            if(ch < '0' || ch > '9'){
                return false;
            }
        }
        char ch1 = s.charAt(s.length() - 1);
        if((ch1 >= '9' || ch1 <= '0') && ch1 != 'x' && ch1 != 'X'){
            return false;
        }
        return true;
    }

    //判断手机号是否符合条件，符合返回true，否则返回false
    public static boolean phoneNumRightOrNot(String s){
        char ch = s.charAt(0);
        int count = numCount(s);
        if(s.length() != 11 || ch == '0' || count != 11){
            return false;
        }
        return true;
    }

    //判断list中是否存在与输入的用户名相同的数据,是则返回true，否则返回false
    public static boolean contain(ArrayList<User> list,String userName){
        User user = new User();
        for (int i = 0; i < list.size(); i++) {
            user = list.get(i);
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    //返回输入的用户名再list中的索引,返回-1说明没找到该用户名
    public static int getUserNameIndex(ArrayList<User> list,String userName){
        int index = 0;
        User user = new User();
        for (int i = 0; i < list.size(); i++) {
            user = list.get(i);
            if(user.getUserName().equals(userName)){
                index = i;
                return index;
            }
        }
        return -1;
    }

    //生成一个验证码
    //规则：长度为5，由4为大小写字母和1位数字组成，同一个字母可重复，数字可以出现在任意位置
    public static String getCaptcha(){
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));
            list.add((char)('A' + i));
        }
        Random r = new Random();
        String result = "";
        StringBuilder sb = new StringBuilder();
        int numIndex = r.nextInt(5);
        for (int i = 0; i < 5; i++) {
            if (i == numIndex){
                int num = r.nextInt(10);
                sb.append(num);
            }
            else{
                int letterIndex = r.nextInt(52);
                char ch = list.get(letterIndex);
                sb.append(ch);
            }
            result = sb.toString();
        }
        return result;
    }
}
