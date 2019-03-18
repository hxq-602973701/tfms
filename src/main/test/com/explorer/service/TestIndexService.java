package com.explorer.service;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.explorer.tfms.service.IndexService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/bean.xml")
public class TestIndexService {
	@Resource(name="indexService")
	private IndexService indexService;
	
	@Test
	public void IndexServiceTest(){
		indexService.genShopLabel();
	}
	
	@Test
	public void IndexServiceTest1(){
		indexService.genShop();
	}
}
