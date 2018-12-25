package frame;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class StatisticsBorrowFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private DAO dao;
	private ResultSet rs;
	private JTable jtable;

	public StatisticsBorrowFrame(){
		super();
		setTitle("借阅统计(上月份热门书)"); 
		this.setVisible(true);
		this.setLocation(700, 250);//设置窗体标题－－－必须	
		
		String[] col = {"书名", "作者","借出数量"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
		
		String sql = "select bookname,writer,count(borrow.book_id) as borrow_count  "
				+ "from borrow,bookinfo "
				+ "where borrow.book_id=bookinfo.book_id "
				+ "AND PERIOD_DIFF(date_format(now( ),'%Y%m'),date_format(borrowDate,'%Y%m'))=1 ";
		
		dao = new DAO();
		rs = dao.getRS(sql);

		try{
			while(rs.next()){
				String bookname = rs.getString("bookname");
				String writer = rs.getString("writer");
				String count = rs.getString("borrow_count");
				String[] str = {bookname,writer,count}; // 将一行的数据存在str_row字符串数组里
				mm.addRow(str);// 添加在表模板中
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
		new StatisticsBorrowFrame();
	}
}
