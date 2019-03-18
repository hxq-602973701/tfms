package com.explorer.tfms.service.impl;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.omg.CORBA_2_3.portable.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.explorer.tfms.domain.Activity;
import com.explorer.tfms.domain.Channel;
import com.explorer.tfms.domain.Food;
import com.explorer.tfms.domain.FoodType;
import com.explorer.tfms.domain.Notice;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.domain.ShopLabel;
import com.explorer.tfms.service.ActivityService;
import com.explorer.tfms.service.ChannelService;
import com.explorer.tfms.service.FoodService;
import com.explorer.tfms.service.FoodTypeService;
import com.explorer.tfms.service.IndexService;
import com.explorer.tfms.service.NoticeService;
import com.explorer.tfms.service.ShopLabelService;
import com.explorer.tfms.service.ShopService;
import com.explorer.tfms.utils.FreemarkerUtils;
import com.explorer.tfms.utils.PropertiesUtils;

@Service("indexService")
public class IndexServiceImpl implements IndexService{
	
	@Resource(name="shopLabelService")
	private ShopLabelService shopLabelService;
	@Resource(name="shopService")
	private ShopService shopService;
	@Resource(name="foodService")
	private FoodService foodService;
	@Resource(name="foodTypeService")
	private FoodTypeService foodTypeService;
	@Resource(name="noticeService")
	private NoticeService noticeService;
	@Resource(name="channelService")
	private ChannelService channelService;
	@Resource(name="activityService")
	private ActivityService activityService;
	
	/**
	 * ftl文件路径
	 */
	@SuppressWarnings("unused")
	private String ftlpath;
	/**
	 * 输出路径
	 */
	private String outpath;
	private FreemarkerUtils freemarkerUtils;
	
	/**
	 * spring通过构造方法创建对象(这个有参数的构造方法需要在bean.xml中配置即注入构造函数参数)
	 * <bean id="ftlpath" class="java.lang.String">
	 *	<constructor-arg value="/ftl"/>
	 * </bean>
	 * <bean id="outpath" class="java.lang.String">
	 *	<constructor-arg value="/jsp/template"/>
	 * </bean>
	 * @param ftlpath：
	 * @param outpath：输出
	 */
	@Autowired(required=true)
	public IndexServiceImpl(String ftlpath, String outpath) {
		if(freemarkerUtils==null){
			this.ftlpath = ftlpath;
			this.outpath = outpath;
			freemarkerUtils = FreemarkerUtils.getInstance(ftlpath);
		}
	}

	/**
	 * 生成头部
	 */
	public void genShopLabel() {
System.out.println("=========重新生成top.jsp==========");
		List<ShopLabel> shopLabelList = this.shopLabelService.listActiveShopLabels();
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("shopLabelList",shopLabelList);
		Properties prop = PropertiesUtils.getInstance().load("system");
		String rp = prop.get("tfms.project.realpath").toString();
		String outfile = rp+outpath+"/nav.jsp";
		freemarkerUtils.printToLocalFile(root,"/nav.ftl",outfile);
	}
	
	/**
	 * 生成店铺
	 * :
	 * @throws: 
	 * @date: 3-13 下午11:42:19
	 * @version: V1.0
	 *
	 */
	public void genShop(){
System.out.println("=========重新生成shop.jsp==========");		
		List<Shop> shopList = this.shopService.listAllPublicShops();
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("shopList",shopList);
		Properties prop = PropertiesUtils.getInstance().load("system");
		String rp = prop.get("tfms.project.realpath").toString();
		String outfile = rp+outpath+"/shop.jsp";
		freemarkerUtils.printToLocalFile(root,"/shop.ftl",outfile);
	}
	
	public void  genRecommendShop(){
System.out.println("=========重新生成recommendShop.jsp==========");
		List<Shop> shopList = this.shopService.findAllRecommendShops();
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("shopList",shopList);
		Properties prop = PropertiesUtils.getInstance().load("system");
		String rp = prop.get("tfms.project.realpath").toString();
		String outfile = rp+outpath+"/recommendShop.jsp";
		freemarkerUtils.printToLocalFile(root,"/recommendShop.ftl",outfile);
	}
	
	public void  genRecommendFood(){
System.out.println("=========重新生成recommendFood.jsp==========");		
		List<Food> foodList = this.foodService.listRecommendFoods();
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("foodList",foodList);
		Properties prop = PropertiesUtils.getInstance().load("system");
		String rp = prop.get("tfms.project.realpath").toString();
		String outfile = rp+outpath+"/recommendFood.jsp";
		freemarkerUtils.printToLocalFile(root,"/recommendFood.ftl",outfile);
	}

	/**
	 * 生成商品类型
	 * @date: 3-17 上午12:24:08
	 * @version: V1.0
	 *
	 */
	public void genFoodType(){
System.out.println("=========重新生成foodType.jsp==========");
		List<FoodType> foodTypeList = this.foodTypeService.listPublicFoodTypes();
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("foodTypeList",foodTypeList);
		Properties prop = PropertiesUtils.getInstance().load("system");
		String rp = prop.get("tfms.project.realpath").toString();
		String outfile = rp+outpath+"/foodType.jsp";
		freemarkerUtils.printToLocalFile(root,"/foodType.ftl",outfile);
	}
	
	/**
	 * 生成公告页面
	 * @date: 3-17 上午12:24:08
	 * @version: V1.0
	 *
	 */
	public void genNotice(){
System.out.println("=========重新生成notice.jsp  fnotice.jsp==========");
		List<Notice> noticeList = this.noticeService.list6Notices();
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("noticeList",noticeList);
		Properties prop = PropertiesUtils.getInstance().load("system");
		String rp = prop.get("tfms.project.realpath").toString();
		String outfile = rp+outpath+"/fnotice.jsp";
		String outfile1 = rp+outpath+"/notice.jsp";
		freemarkerUtils.printToLocalFile(root,"/fnotice.ftl",outfile);//首页显示的公告
		freemarkerUtils.printToLocalFile(root,"/notice.ftl",outfile1); //其他页面显示的公告
	}
	
	/**
	 * 生成文章模块页面
	 * @date: 3-17 上午12:24:08
	 * @version: V1.0
	 *
	 */
	public void genModel(){
System.out.println("=========重新生成model.jsp  fmodel.jsp==========");
		List<Channel> channelList = this.channelService.listSuccessAll();
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("channelList",channelList);
		Properties prop = PropertiesUtils.getInstance().load("system");
		String rp = prop.get("tfms.project.realpath").toString();
		String outfile = rp+outpath+"/fmodel.jsp";
		String outfile1 = rp+outpath+"/model.jsp";
		freemarkerUtils.printToLocalFile(root,"/fmodel.ftl",outfile);//首页显示的公告
		freemarkerUtils.printToLocalFile(root,"/model.ftl",outfile1); //其他页面显示的公告
	}
	
	public void genActivityImage(){
System.out.println("=========重新生成activityImage.jsp==========");
		List<Activity> activityList = this.activityService.list6SuccessActivitys();
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("activityList",activityList);
		Properties prop = PropertiesUtils.getInstance().load("system");
		String rp = prop.get("tfms.project.realpath").toString();
		String outfile = rp+outpath+"/activityImage.jsp";
		freemarkerUtils.printToLocalFile(root,"/activityImage.ftl",outfile);
	}
}
