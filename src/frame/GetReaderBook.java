package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import dao.DAO;

public class GetReaderBook extends JFrame{
	private DAO dao;
	private JTable jtable;
	
	public GetReaderBook(String name){
		super("ͼ���ѯ");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = {"ͼ����","����", "����","������","��������","�۸�" ,"����","�Ƿ�ɽ�"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // ����һ�����ģ��
		
		String sql = "select * from bookinfo where bookname like '%" + name +"%';";
		dao = new DAO();
		ResultSet rs = dao.getRS(sql);
		try {
			while(rs.next()){
				String book_id = rs.getString("book_id");
				String bookname = rs.getString("bookname");
				String writer = rs.getString("writer");
				String publisher = rs.getString("publisher");
				String date = rs.getString("date");
				String price = rs.getString("price");
				String numbers = rs.getString("numbers");
				String can_lend = rs.getString("can_lend");
				
				String[] str_row = {book_id,bookname, writer,publisher,date,price,numbers,can_lend}; // ��һ�е����ݴ���str_row
				
				 mm.addRow(str_row);// ����ڱ�ģ����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jtable = new JTable();
		jtable.setModel(mm);
		
		this.add(new JScrollPane(jtable));
		this.pack();
	}
	
}
