package frame;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class StatisticsReaderFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private DAO dao;
	private ResultSet rs;
	private JTable jtable;
	
	public StatisticsReaderFrame(){
		super();
		setTitle("读者统计"); 
		this.setVisible(true);
		this.setLocation(700, 250);            //设置窗体标题－－－必须
		
		String[] col = {"姓名","年龄","身份","借阅本数"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
		String sql = "select name,age,zj,hasLend "
				+ "from reader "
				+ "order by hasLend desc ";
				

		dao = new DAO();
		rs = dao.getRS(sql);
		try{
			while (rs.next()) {
				String name = rs.getString("name");
				String age = rs.getString("age");
				String zj = rs.getString("zj");
				String hasLend = rs.getString("hasLend");
				String[] str_row = {name,age,zj,hasLend}; // 将一行的数据存在str_row字符串数组里
				mm.addRow(str_row);// 添加在表模板中
				break;
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
	public static void main(String[] args) {
		new StatisticsReaderFrame();
	}
}
