package com.explorer.tfms.utils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
/**
 * MD5加密工具类
 * @author Administrator
 */
public class MD5Utils {
	private static final String TFMS_SALT= "43f2c8d24089eb4708e26faf703f5465";
	private static MD5Utils md5Utils = null;
	private static Md5PasswordEncoder md5 = null;
	
	private MD5Utils(){}
	
	public static MD5Utils getInstance(){
		if(md5Utils==null){
			md5Utils = new MD5Utils();
			
			if(md5==null){
				md5 = new Md5PasswordEncoder();
			}
		}
		return md5Utils;
	}
	
	/**
	 * 生成MD5
	 * @param password：用户密码
	 * @date: 3-21 下午04:30:40
	 * @version: V1.0
	 *
	 */
	public String genMD5Pwd(String password){
		String md5Pwd = md5.encodePassword(password,TFMS_SALT);
		return md5Pwd;
	}
	
	/**
	 * 判断是否是密码
	 * @param  password:  密码
	 * @param  md5password: 数据库中MD5密码
	 * @date: 3-21 下午04:49:33
	 * @version: V1.0
	 *
	 */
	public boolean isCheckPassword(String password,String md5password){
		boolean flag = md5.isPasswordValid(md5password,password,TFMS_SALT);
		return flag;
	}
	
	/**
	 * 测试
	 * @date: 3-21 下午05:16:00
	 * @version: V1.0
	 *
	 */
	public static void main(String[] args) {
		//ea7d2164920e76efc21c5817478d837e
		System.out.println(MD5Utils.getInstance().genMD5Pwd("chentao123"));
		System.out.println(MD5Utils.getInstance().isCheckPassword("yujin254712","34c575c936dc59726248b63b1c80a725"));
	}
}
