package com.bean;

import com.config.ModelBind;
import com.jfinal.plugin.activerecord.Model;

@ModelBind(table = "userinfo")
public class UserMess extends Model<UserMess>{

	private static final long serialVersionUID = 1L;
	public static final UserMess dao = new UserMess();

}
