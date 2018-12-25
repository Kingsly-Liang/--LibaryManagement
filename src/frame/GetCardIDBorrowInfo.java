package frame;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import dao.DAO;

public class GetCardIDBorrowInfo extends JFrame{
	private DAO dao;
	private JTable jtable;
	
	public GetCardIDBorrowInfo(String cardid){
		super("借阅查询");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"姓名","借阅证号","书名","图书编号","借出日期"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
	
		String sql = "select name,borrow.reader_id,bookname,borrow.book_id,borrowDate from borrow,bookinfo,reader "
				+ "where borrow.reader_id=reader.indentityCard "
				+ "AND bookinfo.book_id=borrow.book_id "
				+ "AND borrow.reader_id = '" + cardid +"';";
		dao = new DAO();
		ResultSet rs = dao.getRS(sql);
		try {
			while (rs.next()) {
			String readername = rs.getString("name");
			String indentityCard = rs.getString("reader_id");
			String bookname1 = rs.getString("bookname");
			String bookid = String.valueOf(rs.getInt("book_id"));
			String borrowDate = rs.getString("borrowDate");
			String[] str_row = {readername,indentityCard,bookname1,bookid,borrowDate}; // 将一行的数据存在str_row字符串数组里
			mm.addRow(str_row);// 添加在表模板中
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