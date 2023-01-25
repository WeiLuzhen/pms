package util;

import dao.EmployeeDao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 知识点：
 * （1）通过反射，提供了一个创建对象的通用性方法。
 */
public class ComponentFactory {
	//类加载器查询的基点路径是classpath根路径,所以要添加包路径
	private static final String PATH="componentConfig.properties";
	private static Properties props=new Properties();
	
	static{
		ClassLoader loader=ComponentFactory.class.getClassLoader();
		InputStream is=loader.getResourceAsStream(PATH);
		try {
			props.load(is);//填充Properties对象
		} catch (IOException e) {
			System.out.println("填充Properties对象失败!");
			e.printStackTrace();
		}
	}
	
	//私有化构造器
	private ComponentFactory(){}
	
	public static Object getInstance(String type){
		Object obj=null;
		String clazzname=props.getProperty(type);
		System.out.println("clazzname="+clazzname);
		try {
			//（1）利用类名产生一个Class对象，
			//（2）通过Class对象的newInstance()方法创建对象
			obj=Class.forName(clazzname).newInstance();
		    
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void main(String[] args){
		EmployeeDao dao=(EmployeeDao)ComponentFactory.
				 getInstance("EmployeeDAO");
		System.out.println(dao.toString());
		
	}
}
