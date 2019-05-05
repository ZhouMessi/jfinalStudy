package controller;

import bean.User;
import com.jfinal.core.Controller;

import java.util.List;

public class HelloController extends Controller {



    public void index(){
        setAttr("hello", "hello jfinal+beetl");
        renderTemplate("index.html");
    }

    public void getData(){
        try{
           List<User> userList= User.dao.find("select * from user");

           renderJson(userList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void modify(){

        List<User> userList=User.dao.find("select * from user");
        //模板里访问cateLists,atr,

        for (User user : userList) {
            user.set("high",user.getInt("id")+1);
        }
        setAttr("userList", userList);
       // System.out.println(userList);
        render("test.html");
    }
}
