package testPerson;


class Person {
    String name;
    int age;
    int gender;

    public Person(){

    }

   public Person(String name,int age,int gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
   }

    public boolean marry(Person p) {
        if(this.gender != p.gender) {
            return true;
        }else {
            return false;
        }
   }

}

public class testPerson {
    public static void main(String[] args) {
        Person abc = new Person("xiao",20,1);
        Person bbb= new Person("xiaoge",20,0);
        Person ccc = new Person("dage",20,1);
        if(abc.marry(bbb)){
            System.out.println("可以结婚啦");
        }else {
            System.out.println("对方已婚");
        }
        if(abc.marry(ccc)){
            System.out.println("可以结婚啦");
        }else {
            System.out.println("对方已婚");
        }
    }


}
