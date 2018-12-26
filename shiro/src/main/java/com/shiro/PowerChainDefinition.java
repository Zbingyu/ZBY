package com.shiro;

import java.util.List;

import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.Power;
import com.service.PowerService;

/**
 * 注册所有权限
 * @author lgh-pc
 *
 */
public class PowerChainDefinition implements FactoryBean<Ini.Section> {

	private String urls;
	
	
	public void setUrls(String urls) {
		this.urls = urls;
	}

	@Autowired
	private PowerService  powerService;
	
	@Override
	public Section getObject() throws Exception {
		Ini  ini = new Ini();//记取配置文件
		ini.load(urls); //加载静态
		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}
		List<Power>  list = powerService.findAllPower();
		for (Power p : list) {
			//注册所有权限
			String url = p.getUrl();
			if (url!=null && url.length()>0)
			section.put(url, "perms["+p.getName()+"]");
		}
		
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		 return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		//是否单例
		return false;
	}

}
