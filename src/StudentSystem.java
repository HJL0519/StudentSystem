import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void startStudentSystem() {
        ArrayList<Student> list = new ArrayList<>();
        loop:while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("-----欢迎来到黑马学生管理系统-----");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出");
            System.out.println("请输入您的选择：");
            String choose = sc.next();
            loop1:switch (choose){
                case "1":
                    addStu(list);
                    break;
                case "2":
                    if(list.size() == 0){
                        System.out.println("当前无学生信息，删除失败！");
                        break loop1;
                    }
                    System.out.println("请输入想要删除的学生id：");
                    String sid1 = sc.next();
                    deleteStu(list,sid1);
                    break;
                case "3":
                    if(list.size() == 0){
                        System.out.println("当前无学生信息，修改失败！");
                        break loop1;
                    }
                    System.out.println("请输入想要修改的学生id：");
                    String sid2 = sc.next();
                    updateStu(list,sid2);
                    break;
                case "4":
                    queryStu(list);
                    break;
                case "5":
                    break loop; //直接跳出loop循环
                default:
                    System.out.println("输入错误请重新输入！");
                    break;
            }
        }
    }

    //添加学生
    //要求：id唯一，不唯一提示不唯一后返回主菜单
    public static ArrayList<Student> addStu(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        Student stu = new Student();
        System.out.println("请输入学生的id：");
        String id = sc.next();
        stu.setId(id);
        System.out.println("请输入学生的姓名：");
        String name = sc.next();
        stu.setName(name);
        System.out.println("请输入学生的年龄：");
        int age = sc.nextInt();
        stu.setAge(age);
        System.out.println("请输入学生的家庭住址：");
        String address = sc.next();
        stu.setAddress(address);
        loop:if(list.size() == 0){
            list.add(stu);
        }
        else{
            for (int i = 0; i < list.size(); i++) {
                Student stu1 = new Student();
                stu1 = list.get(i);
                if(stu1.getId().equals(id)){
                    System.out.println("id重复，返回主菜单！");
                }
                else{
                    list.add(stu);
                    System.out.println("添加成功！");
                    break loop;
                }
            }
        }
        return list;

    }

    //删除学生
    //要求：id存在则删除，不存在提示不存在后返回主菜单
    public static ArrayList<Student> deleteStu(ArrayList<Student> list,String id){
        Student stu = new Student();
        for (int i = 0; i < list.size(); i++) {
            stu = list.get(i);
            if(stu.getId().equals(id)){
                list.remove(i);
                System.out.println("删除成功！");
                return list;
            }
        }
        System.out.println("当前id不存在，删除失败！");
        return list;
    }


    //修改学生
    //要求：id存在，则继续录入信息，否则提示不存在后返回主菜单
    public static ArrayList<Student> updateStu(ArrayList<Student> list,String id){
        Student stu = new Student();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id)){
                System.out.println("请输入新的学生姓名：");
                String newName = sc.next();
                stu.setName(newName);
                System.out.println("请输入新的学生年龄：");
                int newAge = sc.nextInt();
                stu.setAge(newAge);
                System.out.println("请输入新的学生家庭住址：");
                String newAddress = sc.next();
                stu.setAddress(newAddress);
                stu.setId(id);
                list.set(i,stu);
                System.out.println("修改成功");
                System.out.println("修改后的学生id为：" + list.get(i).getId() + ",姓名为："+ list.get(i).getName() +",年龄为：" +list.get(i).getAge()+ ",家庭住址为："+list.get(i).getAddress()+"。");
            }
        }
        return list;

    }

    //查询学生
    //要求：如果没有学生，提示添加后再查询；若有则按格式输出
    public static void queryStu(ArrayList<Student> list){
        Student stu = new Student();
        if(list.size() == 0){
            System.out.println("当前无学生信息，请添加后再查询！");
        }
        else{
            System.out.println("id\t姓名\t年龄\t家庭住址");
            for (int i = 0; i < list.size(); i++) {
                stu = list.get(i);
                System.out.println(stu.getId()+"\t"+stu.getName()+"\t"+stu.getAge()+"\t"+stu.getAddress()+"\t");
            }
        }
    }
}
