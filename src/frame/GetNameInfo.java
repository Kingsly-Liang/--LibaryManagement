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
		super("读者查询");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"姓名", "性别", "年龄", "证件号码","证件开卡日期","电话","证件状态"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
		
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
			String[] str_row = {name,sex,age,indentityCard,date,tel,cardType}; // 将一行的数据存在str_row字符串数组里
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