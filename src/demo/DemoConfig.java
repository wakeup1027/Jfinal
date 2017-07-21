package demo;

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
		//���ý�����Ⱦhtml�Ĺ���
		me.setViewType(ViewType.JSP); // ������ͼ����ΪJsp������Ĭ��ΪFreeMarker
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
		me.setRenderFactory(rf);
	}

	@Override
	public void configRoute(Routes me) {
		//����·�ɣ����Ƿ��ʵ�ַ��
		//me.add("/hello",HelloController.class);
		me.add(new AutoBindRoutes());
	}

	@Override
	public void configEngine(Engine me) {
		//���ӹ���������ģ���ļ�
	}

	@Override
	public void configHandler(Handlers me) {
		//����·��htmlα·��
		me.add(new HtmlHandler());
		// ȫ·����ȡ
		me.add(new BasePathHandler("BASE_PATH"));
	}

	@Override
	public void configInterceptor(Interceptors me) {
		//����������
	}

	@Override
	public void configPlugin(Plugins me) {
		//���������������ݿ�����ӳصķ���
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

		// ����ActiveRecord���
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.setShowSql(true);

		new AutoBindModels(arp);
	}

}