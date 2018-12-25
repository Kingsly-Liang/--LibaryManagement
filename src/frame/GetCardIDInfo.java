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
		super("读者查询");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"读者编号", "姓名", "性别", "年龄", "证件号码","证件有效日期","电话","证件状态"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
		
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
			String[] str_row = {id,name,sex,age,indentityCard,date,maxNum,tel,cardType}; // 将一行的数据存在str_row字符串数组里
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
