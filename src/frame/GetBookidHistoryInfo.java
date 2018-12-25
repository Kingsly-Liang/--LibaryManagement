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
		super("借阅历史查询");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"图书编号","书名", "借阅者姓名","借阅证号码", "是否已还", "借出日期", "归还日期"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
		
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
				String[] str_row = {bookid1,bookname1,readername,indentityCard,isback,borrowDate,backDate}; // 将一行的数据存在str_row字符串数组里
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