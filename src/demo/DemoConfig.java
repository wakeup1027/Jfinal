package demo;

//import gxTest.gxFunc;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.config.HtmlHandler;
import com.config.BasePathHandler;
import com.config.AutoBindModels;
import com.config.AutoBindRoutes;

public class DemoConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		//设置解析渲染html的工厂
		me.setViewType(ViewType.JSP); // 设置视图类型为Jsp，否则默认为FreeMarker
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
		me.setRenderFactory(rf);
		
		// 获取GroupTemplate ,可以设置共享变量等操作  作用是在渲染界面的时候如果出现界面中的方法 也可以执行
		GroupTemplate groupTemplate = rf.groupTemplate;
		//roupTemplate.registerFunctionPackage("flyfox", gxFunc.class);
	}

	@Override
	public void configRoute(Routes me) {
		//配置路由：就是访问地址的
		//me.add("/hello",HelloController.class);
		me.add(new AutoBindRoutes());
	}

	@Override
	public void configEngine(Engine me) {
		//添加共享函数的模板文件
	}

	@Override
	public void configHandler(Handlers me) {
		//设置路径html伪路径
		me.add(new HtmlHandler());
		// 全路径获取
		me.add(new BasePathHandler("BASE_PATH"));
	}

	@Override
	public void configInterceptor(Interceptors me) {
		//配置拦截器
	}

	@Override
	public void configPlugin(Plugins me) {
		//用来操作连接数据库的连接池的方法
		//String db_type = "mysql.";

		//String webRoot = PathKit.getWebRootPath();
		//D:\RDtekcit\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jfinal_cms
		//String DBPath = webRoot + "\\WEB-INF\\";
		//D:\RDtekcit\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jfinal_cms\WEB-INF\
		//DBPath = StrUtils.replace(DBPath, "\\", "/");
		//D:/RDtekcit/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/jfinal_cms/WEB-INF/
		String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/jfinal_demo2?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
		//jdbc:mysql://127.0.0.1:3306/jfinal_cms?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull

		C3p0Plugin c3p0Plugin = new C3p0Plugin(jdbcUrl, "root", "123456", "com.mysql.jdbc.Driver");
		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.setShowSql(true);

		new AutoBindModels(arp);
	}

}
