package frame;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import dao.DAO;

public class GetCardIDInfo extends JFrame{
	private DAO dao;
	private JTable jtable;
	
	public GetCardIDInfo(String cardid){
		super("���߲�ѯ");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"���߱��", "����", "�Ա�", "����", "֤������","֤����Ч����","�绰","֤��״̬"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // ����һ�����ģ��
		
		String sql = "select id,name,sex,age,indentityCard,date,maxNum,tel,cardType from reader where indentityCard = '" + cardid +"';";
		dao = new DAO();
		ResultSet rs = dao.getRS(sql);
		try {
			while (rs.next()) {
			String id = String.valueOf(rs.getInt("id"));
			String name = rs.getString("name");
			String sex = rs.getString("sex");
			String age = rs.getString("age");
			String indentityCard = rs.getString("indentityCard");
			String date = rs.getString("date");
			String maxNum = rs.getString("maxNum");
			String tel = rs.getString("tel");
			String cardType = rs.getString("cardType");
			String[] str_row = {id,name,sex,age,indentityCard,date,maxNum,tel,cardType}; // ��һ�е����ݴ���str_row�ַ���������
			mm.addRow(str_row);// ����ڱ�ģ����
			}
		}
			catch(Exception e){
				   e.printStackTrace();
			}
		jtable = new JTable();
		jtable.setModel(mm);
		
		this.add(new JScrollPane(jtable));
		this.pack();
	}
}
