package com.explorer.dao.test;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.explorer.tfms.domain.FoodType;
import com.explorer.tfms.domain.PageBean;
public class FoodTypeDaoTest {
	
	@Test
	public void test(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
		FoodTypeDaoTest foodTypeDao = (FoodTypeDaoTest) ac.getBean("foodTypeDao");
	}

}
