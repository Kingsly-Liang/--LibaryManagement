package frame;
/*
 * һ���������ͼ����Ϣ
 */
import javax.swing.*;

import dao.DAO;
public class AddFineInfo {
	
	public AddFineInfo() {
		DAO dao=new DAO();
		if(dao.addfineinfo()) {
			JOptionPane.showMessageDialog(null,"��ӷ�����Ϣ�ɹ�");
		}
	}
	
	public static void main(String[] args){
		new AddFineInfo();
	}
}
