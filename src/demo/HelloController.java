package demo;

import java.util.List;

import com.jfinal.core.Controller;
import com.bean.UserMess;
import com.config.ControllerBind;

@ControllerBind(controllerKey = "/front/article")
public class HelloController extends Controller{
	/**
	 * ֪ʶ��:
	 * 1��Controller�ṩ��getPara()ϵ�з��������������л�ȡ����
	 * 2��Controller�ṩ��getModel()��������ҳ����򴫵ݹ�����model����
	 */
	
	public void index(){
		List<UserMess> userInfo = UserMess.dao.find("select * from userinfo");
		setAttr("namelist", userInfo);
		setAttr("upstatus",0);
		render("/admin/index.html");
	}
	
	public void addUser(){
		UserMess model = getModel(UserMess.class,"userInfo");
		model.save();
		index();
	}
	
	public void delUser(){
		UserMess model = getModel(UserMess.class);
		model.put("id", getParaToInt("ID"));
		model.delete();
		index();
	}
	
	/*public void updUser(){
		UserMess model = getModel(UserMess.class);
		List<UserMess> umesList = model.find("SELECT * FROM userinfo WHERE id = '"+getParaToInt("ID")+"'");
		setAttr("upInfo",umesList.get(0));
		setAttr("upstatus",1);
		render("/admin/index.html");
	}*/
	
	public void saveUpd(){
		UserMess model = getModel(UserMess.class,"userInfo");
		model.update();
		index();
	}
	
	public void findDate(){
		List<UserMess> userInfo = UserMess.dao.find("select * from userinfo where username like '%"+getPara("username").trim()+"%'");
		setAttr("namelist", userInfo);
		setAttr("upstatus",0);
		render("/admin/index.html");
	}

}
