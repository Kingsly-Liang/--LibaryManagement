package frame;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import dao.DAO;

public class GetCardIDFineHistoryInfo extends JFrame{
	private DAO dao;
	private JTable jtable;
	
	public GetCardIDFineHistoryInfo(String cardid){
		super("������ʷ��ѯ");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"����","����֤����","����","������","�Ƿ񸶿�"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // ����һ�����ģ��
		
		String sql = "select name,card_id,book_name,fine.price,ispay from fine,bookinfo "
				+ "where fine.card_id='" + cardid+"' AND fine.book_name=bookinfo.bookname;";
		dao = new DAO();
		ResultSet rs = dao.getRS(sql);
		try {
			while (rs.next()) {
				String readername = rs.getString("name");
				String indentityCard = rs.getString("card_id");
				String bookname1 = rs.getString("book_name");
				String price = String.valueOf(rs.getString("price"));
				String ispay = rs.getString("ispay");
				String[] str_row = {readername,indentityCard,bookname1,price,ispay}; // ��һ�е����ݴ���str_row�ַ���������
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