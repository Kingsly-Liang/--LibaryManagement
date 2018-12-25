package frame;
/*
 * 一键添加逾期图书信息
 */
import javax.swing.*;

import dao.DAO;
public class AddFineInfo {
	
	public AddFineInfo() {
		DAO dao=new DAO();
		if(dao.addfineinfo()) {
			JOptionPane.showMessageDialog(null,"添加罚款信息成功");
		}
	}
	
	public static void main(String[] args){
		new AddFineInfo();
	}
}
