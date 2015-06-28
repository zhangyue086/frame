package com.zline.zlogistics.ws.member.impl;

import com.alibaba.fastjson.JSON;
import com.zline.zlogistics.ws.entity.AppParam;
import com.zline.zlogistics.ws.entity.AppResult;
import com.zline.zlogistics.ws.member.IMemberAppService;

/**
 * Description: 小哥app 使用接口

 * @author zhangzhitao

 * @date 2015-6-23

 * @version 1.0

 */
public class MemberAppServiceImpl implements IMemberAppService {

	public String checkLogin(String paramJSON) {
		
		AppParam inParam = JSON.parseObject(paramJSON, AppParam.class);
		
		AppResult result = new AppResult();
		
		result.setMessage("成功");
		result.setStatus("0");
		
		return JSON.toJSONString(result);
	}


}
