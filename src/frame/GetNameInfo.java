package frame;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import dao.DAO;

public class GetNameInfo extends JFrame{
	private DAO dao;
	private JTable jtable;
	
	public GetNameInfo(String name1){
		super("���߲�ѯ");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"����", "�Ա�", "����", "֤������","֤����������","�绰","֤��״̬"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // ����һ�����ģ��
		
		String sql = "select name,sex,age,indentityCard,date,tel,cardType from reader "
				+ "where name like '%"+name1+"%';";
		dao = new DAO();
		ResultSet rs = dao.getRS(sql);
		try {
			while (rs.next()) {
			String name = rs.getString("name");
			String sex = rs.getString("sex");
			String age = rs.getString("age");
			String indentityCard = rs.getString("indentityCard");
			String date = rs.getString("date");

			String tel = rs.getString("tel");
			String cardType = rs.getString("cardType");
			String[] str_row = {name,sex,age,indentityCard,date,tel,cardType}; // ��һ�е����ݴ���str_row�ַ���������
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