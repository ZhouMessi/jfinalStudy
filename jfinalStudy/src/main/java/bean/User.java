package bean;

import com.jfinal.plugin.activerecord.Model;

/*
 * User实体类
 * */
public class User extends Model<User> {
    public static final User dao=new User();


    public static void main(String[] args) {
        String s1="/t110";

        String replace = s1.replace("/t" ,"");
        System.out.println(replace);


    }
}