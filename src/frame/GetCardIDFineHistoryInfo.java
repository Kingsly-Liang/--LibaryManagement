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
		super("罚款历史查询");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"姓名","借阅证号码","书名","罚款金额","是否付款"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
		
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
				String[] str_row = {readername,indentityCard,bookname1,price,ispay}; // 将一行的数据存在str_row字符串数组里
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