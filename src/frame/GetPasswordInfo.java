package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class GetPasswordInfo extends JFrame{
	private DAO dao;
	private JTable jtable;
	
	public GetPasswordInfo(String cardid){
		super("密码查询");
		this.setVisible(true);
		this.setLocation(700,250);
		String[] col = {"姓名","借阅证号码","密码"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
		
		String sql = "select name,indentityCard,password from reader where indentityCard = '" + cardid+"';";
		dao = new DAO();
		ResultSet rs = dao.getRS(sql);
		try{
			while(rs.next()){
				String name1 = rs.getString("name");
				String cardid1 = rs.getString("indentityCard");
				String password1 = rs.getString("password");
				String[] str_row = {name1,cardid1,password1}; // 将一行的数据存在str_row字符串数组里
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
