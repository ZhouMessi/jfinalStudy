package controller;

import bean.User;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class HelloController extends Controller {



    public void index(){
        setAttr("hello", "hello jfinal+beetl");
        renderTemplate("index.html");
    }

    public void getData(){
        try{
          // List<User> userList= User.dao.find("select * from user");
           List<Record> userList2= Db.use("jfinal1").find("select * from user");

           renderJson(userList2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getData2(){
        try{
            // List<User> userList= User.dao.find("select * from user");
            List<Record> userList2= Db.use("jfinal2").find("select * from user");

            renderJson(userList2);
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
