package com.explorer.tfms.utils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;
/**
 * 文件上传工具类
 * @author Administrator
 */
public class UploadUtils {
	public static final String SHOP = "1";
	public static final String ACTIVITY = "2";
	public static final String FOOD = "3";
	
	private static UploadUtils uploadUtils = null;
	private static Properties properties = null;
	private UploadUtils(){}
	
	public static UploadUtils getInstance(){
		if(uploadUtils == null){
			uploadUtils = new UploadUtils();
		}
		return uploadUtils;
	}
	
	private static Properties getProperties(){
		if(properties == null){
			properties = PropertiesUtils.getInstance().load("system");
		}
		return properties;
	}
	
	
	/**
	 * 保存图片文件到路径
	 * @param oldfilename  源文件名称
	 * @param in   输入流
	 * @param type:
	 * @throws: 
	 * @date: 3-15 上午02:49:46
	 * @version: V1.0
	 *
	 * @throws IOException 
	 */
	public String saveImageFileToPath(String oldfilename,InputStream in,String type) throws IOException{
		int IMG_WIDTH = 0,THUMBNAIL_WIDTH = 0,THUMBNAIL_HEIGHT = 0;
		String realPath = getProperties().get("tfms.project.picture.realpath").toString();
		String path = null;//realPath+"/resources/upload/shop/";
		String thumbPath = null; //realPath+"/resources/upload/shop/thumbnail/";
		Properties prop= getProperties();
		if(type.equals(SHOP)){ //商店
			path = realPath+prop.get("shop.path");
			thumbPath = realPath+prop.get("shop.thumbPath");
			IMG_WIDTH = Integer.parseInt(prop.get("shop.width").toString());
			THUMBNAIL_WIDTH = Integer.parseInt(prop.get("shop.thumb.width").toString());
			THUMBNAIL_HEIGHT = Integer.parseInt(prop.get("shop.thumb.height").toString());
		}else if(type.equals(FOOD)){//美食
			path = realPath+prop.get("food.path");
			thumbPath = realPath+prop.get("food.thumbPath");
			IMG_WIDTH = Integer.parseInt(prop.get("food.width").toString());
			THUMBNAIL_WIDTH = Integer.parseInt(prop.get("food.thumb.width").toString());
			THUMBNAIL_HEIGHT = Integer.parseInt(prop.get("food.thumb.height").toString());
		}else if(type.equals(ACTIVITY)){//活动
			path = realPath+prop.get("activity.path");
			thumbPath = realPath+prop.get("activity.thumbPath");
			IMG_WIDTH = Integer.parseInt(prop.get("activity.width").toString());
			THUMBNAIL_WIDTH = Integer.parseInt(prop.get("activity.thumb.width").toString());
			THUMBNAIL_HEIGHT = Integer.parseInt(prop.get("activity.thumb.height").toString());
		}
		File fp = new File(path);
		File tfp = new File(thumbPath);
		if(!fp.exists()) fp.mkdirs();
		if(!tfp.exists()) tfp.mkdirs();
		String newFile = UUID.randomUUID().toString()+"_"+oldfilename;;
		path = path+newFile;
		thumbPath = thumbPath +newFile;
		BufferedImage oldBi = ImageIO.read(in);
		int width = oldBi.getWidth();
		Builder<BufferedImage> bf = Thumbnails.of(oldBi);
		
		if(width>IMG_WIDTH) {
			bf.scale((double)IMG_WIDTH/(double)width);
		} else {
			bf.scale(1.0f);
		}
		bf.toFile(path);
		//缩略图的处理
		//1、将原图进行压缩
		BufferedImage tbi = Thumbnails.of(oldBi)
					.scale((THUMBNAIL_WIDTH*1.2)/width).asBufferedImage();
		//2、进行切割并且保持
		Thumbnails.of(tbi).scale(1.0f)
			.sourceRegion(Positions.CENTER, THUMBNAIL_WIDTH,THUMBNAIL_HEIGHT)
			.toFile(thumbPath);
		return newFile;
	}
}
