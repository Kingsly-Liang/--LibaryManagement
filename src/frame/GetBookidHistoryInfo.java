package frame;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import dao.DAO;

public class GetBookidHistoryInfo extends JFrame{
	private DAO dao;
	private JTable jtable;
	
	public GetBookidHistoryInfo(String bookid){
		super("������ʷ��ѯ");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"ͼ����","����", "����������","����֤����", "�Ƿ��ѻ�", "�������", "�黹����"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // ����һ�����ģ��
		
		String sql = "select borrow.book_id,bookname,name,borrow.reader_id,isback,borrowDate,backDate from borrow,reader,bookinfo "
				+ "where bookinfo.book_id = '" + bookid +"' AND reader.indentityCard=borrow.reader_id "
						+ "AND borrow.book_id=bookinfo.book_id;";
		dao = new DAO();
		ResultSet rs = dao.getRS(sql);
		try {
			while (rs.next()) {
				String bookid1 = rs.getString("book_id");
				String bookname1 = rs.getString("bookname");
				String readername = rs.getString("name");
				String indentityCard = rs.getString("reader_id");
				String isback = rs.getString("isback");
				String borrowDate = rs.getString("borrowDate");
				String backDate = rs.getString("backDate");
				String[] str_row = {bookid1,bookname1,readername,indentityCard,isback,borrowDate,backDate}; // ��һ�е����ݴ���str_row�ַ���������
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