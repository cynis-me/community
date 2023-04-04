package com.nowcoder.community;

import com.nowcoder.community.config.AlphaConfig;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;//容器
	}

	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);//获取容器中的Bean，
		// 只依赖接口(不依赖实现类)，所以当Bean要更换的时候，可以直接加一个bean，然后加上@Primary即可优先显示需要的
		//如果还有一块想使用其他的实现类，同一个接口
		System.out.println(alphaDao.select());
		alphaDao = applicationContext.getBean("alphaDaoHibernate",AlphaDao.class);
		//"name:"不是敲出来的,第一个参数是字符串型
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManagement() {
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);//调用两次，但是只实例化了一次，也只有一次
	}

	@Test
	public void testBeanconfig() {
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}
	//下面的是依赖注入，和上边是分别为两种使用方法

	@Autowired
	@Qualifier("alphaDaoHibernate")//同上，就是要用不一样的bean。没有这句话是优先的那个（加了@Primary那个）
	private AlphaDao alphaDao;

	@Autowired
	private AlphaService alphaService;//两句话意思是希望把AlphaService注入给alphaService这个属性

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI() {//DI:依赖注入
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}
}
