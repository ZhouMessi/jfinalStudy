package controller;

import bean.User;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;


public class DemoConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        //读取文件
        PropKit.use("config.properties");

        JFinal3BeetlRenderFactory rf=new JFinal3BeetlRenderFactory();
        rf.config();
        constants.setRenderFactory(rf);

        GroupTemplate gt=rf.groupTemplate;

        //根据gt可以添加扩展函数，格式化函数，共享变量等


    }

    @Override
    public void configRoute(Routes routes) {
        //设置项目启动默认访问页，此不设置无需在web中设置了。不过好像web设置了访问页也无效照样报404 -.-
        routes.add("/", HelloController.class,"/WEB-INF/view/");
        //
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("db.url"), PropKit.get("db.username"),
                PropKit.get("db.password"), PropKit.get("db.driver"));

        // StatFilter提供JDBC层的统计信息
        druidPlugin.addFilter(new StatFilter());
        // WallFilter的功能是防御SQL注入攻击
        WallFilter wallFilter = new WallFilter();
        wallFilter.setDbType(JdbcConstants.MYSQL);

        druidPlugin.addFilter(wallFilter);

      /*  druidPlugin.setInitialSize(PropKit.getInt("db.poolInitialSize"));
        druidPlugin.setMaxPoolPreparedStatementPerConnectionSize(PropKit.getInt("db.poolMaxSize"));
        druidPlugin.setTimeBetweenConnectErrorMillis(PropKit.getInt("connectionTimeoutMillis"));*/

        plugins.add(druidPlugin);

        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        //添加与表的映射关系
        activeRecordPlugin.addMapping("user", User.class);
        plugins.add(activeRecordPlugin);

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
