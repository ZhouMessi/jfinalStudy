package controller;

import bean.Trade;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;



public class DemoConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        //读取文件
        PropKit.use("config.properties");

        constants.setBaseUploadPath("shangchuan");
        constants.setMaxPostSize(30*1000*1000);

     //  JFinal3BeetlRenderFactory rf=new JFinal3BeetlRenderFactory();
     //  rf.config();
     //  constants.setRenderFactory(rf);

     //  GroupTemplate gt=rf.groupTemplate;

        //根据gt可以添加扩展函数，格式化函数，共享变量等


    }

    @Override
    public void configRoute(Routes routes) {
        //设置项目启动默认访问页，此不设置无需在web中设置了。不过好像web设置了访问页也无效照样报404 -.-
        routes.add("/", HelloController.class,"/WEB-INF/view/");
        routes.add("/excel", ExcelController.class,"/WEB-INF/view/");
        //
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
       //DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("db.url"), PropKit.get("db.username"),
       //        PropKit.get("db.password"), PropKit.get("db.driver"));


       //plugins.add(druidPlugin);

       //ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
       ////添加与表的映射关系
       //activeRecordPlugin.addMapping("user", User.class);
       //plugins.add(activeRecordPlugin);

        //140环境数据源
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl140"), PropKit.get("username"),
                PropKit.get("password"), PropKit.get("driver"));//定义mysql连接数据库信息,并实例化新的数据库连接池
        druidPlugin.start();//2.启动连接池
        ActiveRecordPlugin arp = new ActiveRecordPlugin("140",druidPlugin);//3.实例化连接
        arp.start();//4.启动该连接
        arp.addMapping("t_trade", "no", Trade.class);

        //142环境数据源
        DruidPlugin druidPlugin2 = new DruidPlugin(PropKit.get("jdbcUrl142"), PropKit.get("username"),
                PropKit.get("password"), PropKit.get("driver"));//定义mysql连接数据库信息,并实例化新的数据库连接池
        druidPlugin2.start();//2.启动连接池
        ActiveRecordPlugin arp2 = new ActiveRecordPlugin("142",druidPlugin2);//3.实例化连接
        arp2.start();//4.启动该连接
        arp2.addMapping("t_trade", "no", Trade.class);

        //143环境数据源
        DruidPlugin druidPlugin3 = new DruidPlugin(PropKit.get("jdbcUrl143"), PropKit.get("username"),
                PropKit.get("password"), PropKit.get("driver"));//定义mysql连接数据库信息,并实例化新的数据库连接池
        druidPlugin3.start();//2.启动连接池
        ActiveRecordPlugin arp3 = new ActiveRecordPlugin("143",druidPlugin3);//3.实例化连接
        arp3.start();//4.启动该连接
        arp3.addMapping("t_trade", "no", Trade.class);

        //150数据源
        DruidPlugin druidPlugin4 = new DruidPlugin(PropKit.get("jdbcUrl150"), PropKit.get("username"),
                PropKit.get("password"), PropKit.get("driver"));//定义mysql连接数据库信息,并实例化新的数据库连接池
        druidPlugin4.start();//2.启动连接池
        ActiveRecordPlugin arp4 = new ActiveRecordPlugin("150",druidPlugin4);//3.实例化连接
        arp4.start();//4.启动该连接



        //141数据源
        DruidPlugin druidPlugin5 = new DruidPlugin(PropKit.get("jdbcUrl141"), PropKit.get("username"),
                PropKit.get("password"), PropKit.get("driver"));//定义mysql连接数据库信息,并实例化新的数据库连接池
        druidPlugin5.start();//2.启动连接池
        ActiveRecordPlugin arp5 = new ActiveRecordPlugin("141",druidPlugin5);//3.实例化连接
        arp5.start();//4.启动该连接
        arp5.addMapping("t_trade", "no", Trade.class);

    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }


    public static void main(String[] args) {
        //idea必须要加start
        JFinal.start("src/main/webapp", 8080, "/", 5);
    }
}
