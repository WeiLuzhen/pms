package util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.*;

/**
 * 定义一个访问数据库的工具类,提供如下功能:
 * (1)解析xml文档,获得访问数据库的4大参数;
 * (2)连接数据库;
 * (3)关闭数据库资源.
 */
public class DBUtil {
	//类加载器查询的基点路径是classpath根路径,所以要添加包路径
	private static final String PATH="mysql-connection.xml";
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		//①准备一个File对象
		//File file=new File(PATH);
		//①获得类加载器
		ClassLoader loader=DBUtil.class.getClassLoader();
		//利用类加载器将xml文件加载到内存中,形成输入流对象
		InputStream is=loader.getResourceAsStream(PATH);
		//②创建一个读入器
		SAXReader reader=new SAXReader();
		//③读入xml文档,并形成Document对象
		Document doc=null;
		try {
			doc=reader.read(is);
		} catch (DocumentException e) {
			System.out.println("读入xml文档失败!");
			e.printStackTrace();
		}
		//④获得根元素(根节点)
		Element rootEle=doc.getRootElement();
		//⑤提取参数数据
		driver=rootEle.elementTextTrim("driver");
		url=rootEle.elementTextTrim("url");
		username=rootEle.elementTextTrim("username");
		password=rootEle.elementTextTrim("password");
	}
	
	//私有化构造器
	private DBUtil(){}
	
	//获得数据库的连接
	public static Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("加载MySQL驱动失败!");
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("连接MySQL数据库失败!");
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭数据库的资源
	public static void close(ResultSet rs,Statement stmt, Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//定义一组重载方法
	public static void  close(Statement stmt, Connection conn){
		close(null,stmt,conn);
	}
	public static void close(ResultSet rs,Statement stmt){
		close(rs,stmt,null);
	}
	public static void close(Connection conn){
		close(null,null,conn);
	}
}
