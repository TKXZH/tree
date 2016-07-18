package tree;

import java.sql.SQLException;
import java.util.List;


public class Travelsal {
	private static int _counterOfDirectories = 0;
	private static int _counterOfFiles = 0;
	private static int _sizeOfFile = 0;
	
	/**
	 * 获取指定节点的所有子目录数，子文件数，子文件总大小
	 * @param fid 节点id号
	 * @throws SQLException
	 */
	public static void getFileInfo(int fid) throws SQLException {
		List<Integer> sons = DataProcess.getSons(fid);
		if(sons != null) {
			for(int son:sons) {
				if(DataProcess.isDirectory(son)) {
					_counterOfDirectories++;
				}	else {
					_counterOfFiles++;
					_sizeOfFile += DataProcess.getSize(son);
				}
				if(DataProcess.hasSon(fid)) {
					getFileInfo(son);
				}	else{
					return;
				}
			}
		}
		return;
	}
	
	public static void getResult() {
		System.out.println("文件夹:"   +  _counterOfDirectories  );
		System.out.println("文件:"    +  _counterOfFiles);
		System.out.println("文件总大小" + _sizeOfFile);
	}
}
