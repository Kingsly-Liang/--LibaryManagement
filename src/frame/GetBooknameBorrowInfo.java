package frame;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import dao.DAO;

public class GetBooknameBorrowInfo extends JFrame{
	private DAO dao;
	private JTable jtable;
	
	public GetBooknameBorrowInfo(String bookname1){
		super("借阅查询");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"书名","图书编号","可借数量","借书人姓名"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
		
		String sql = "select bookname,bookinfo.book_id,can_lend,name from borrow,reader,bookinfo "
				+ "where borrow.book_id=bookinfo.book_id "
				+ "AND borrow.reader_id=reader.indentityCard "
				+ "AND bookname like '%" + bookname1 +"%';";
		dao = new DAO();
		ResultSet rs = dao.getRS(sql);
		try {
			while (rs.next()) {
			String bookname2 = rs.getString("bookname");
			String bookid1 = rs.getString("book_id");
			String can_lend1 = rs.getString("can_lend");
			String readername = rs.getString("name");
			
			String[] str_row = {bookname2,bookid1,can_lend1,readername}; // 将一行的数据存在str_row字符串数组里
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