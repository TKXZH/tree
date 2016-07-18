package tree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataProcess {
	
	private static Statement statement;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf-8","root", "941031");
			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("驱动加载失败");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param fsid 根
	 * @throws SQLException
	 * 创建一个根目录，设置其attr标志为1，表示为一个文件夹 
	 * parent为空，表示该目录为根路径
	 */
	public static void createRoot(int fsid) throws SQLException {
		statement.execute("insert into file(fsid,attr) values ('" + fsid + "','1');");
	}
	
	/**
	 * 
	 * @param fsid 根
	 * @param parent 父目录
	 * @throws SQLException
	 */
	public static void createDirectory(int fsid, int parent) throws SQLException {
		statement.execute("insert into file(fsid,attr,parent) values ('" +
	fsid + "','1','" + parent + "');");
	}
	
	/**
	 * 
	 * @param fsid 根
	 * @param size 文件大小
	 * @param parent 父目录
	 * @throws SQLException
	 */
	public static void createFile(int fsid, int size, int parent) throws SQLException {
		statement.execute("insert into file(fsid,attr,parent,size) values ('" +
				fsid + "','0','" + parent + "','" + size+ "');");
	}
	
	/**
	 * 
	 * @param fid 待查询文件id号
	 * @return 所有直接子目录或文件的fid
	 */
	public static List<Integer> getSons(int fid) {
		List<Integer> sons = new ArrayList<Integer>();
		try {
			ResultSet res = statement.executeQuery("select fid from file where parent = '" +
fid+ "';");
			while(res.next()) {
				sons.add(res.getInt("fid"));
			}
		
		} catch (SQLException e) {
			System.err.println("该目录不存在子节点或者该目录不存在");
			e.printStackTrace();
		}
		return sons;
	}
	
	/**
	 * 
	 * @param fid 
	 * @return true：该fid是一个目录 false：该fid是一个文件
	 * @throws SQLException
	 */
	public static boolean isDirectory(int fid) throws SQLException {
		ResultSet res = statement.executeQuery("select attr from file where fid = '" +
				fid+ "';");
		
		res.next();
		
		if(res.getInt("attr") == 1) {
			return true;
		}	else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param fid
	 * @return true:该节点有孩子节点  false:该节点没有孩子节点
	 */
	public static boolean hasSon(int fid) {
		List<Integer> sons = new ArrayList<Integer>();
		try {
			ResultSet res = statement.executeQuery("select fid from file where parent = '" +
fid+ "';");
			while(res.next()) {
				sons.add(res.getInt("fid"));
			}
		
		} catch (SQLException e) {
			System.err.println("该目录不存在子节点或者该目录不存在");
			e.printStackTrace();
		}
		if(sons == null) {
			return false;
		}	else{
			return true;
		}
	}
	
	public static int getSize(int fid) throws SQLException {
		int size = 0;
		ResultSet res = statement.executeQuery("select size from file where fid = '" +
				fid+ "';");
		res.next();
		size = res.getInt("size");
		return size;
	}
}
