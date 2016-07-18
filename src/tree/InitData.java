package tree;

import java.sql.SQLException;

public class InitData {
	public static void main(String args[]) throws SQLException {
		
		DataProcess.createRoot(1);//创建一个根目录
		DataProcess.createDirectory(1, 1); //在根目录下创建一个目录
		DataProcess.createDirectory(1, 1); //在根目录下创建一个目录
		DataProcess.createDirectory(1, 1); //在根目录下创建一个目录
		DataProcess.createFile(1, 100, 2); //在根目录下的第一个子目录创建一个文件
		DataProcess.createFile(1, 100, 3); //在根目录下的第二个子目录创建一个文件
		DataProcess.createFile(1, 100, 4); //在根目录下的第三个子目录创建一个文件	
		DataProcess.createFile(1, 100, 4); //在根目录下的第三个子目录创建一个文件
	}
}
