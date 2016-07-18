package tree;

import java.sql.SQLException;

public class Main {
	
	/**
	 * 测试获取子目录树以及文件数和总文件大小
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String args[]) throws SQLException {
		Travelsal.getFileInfo(3);
		Travelsal.getResult();
	}
}
