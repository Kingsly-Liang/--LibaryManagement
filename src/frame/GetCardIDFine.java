package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import dao.DAO;

public class GetCardIDFine extends JFrame{
	private DAO dao;
	private JTable jtable;
	
	public GetCardIDFine(String cardid){
		super("���ɷ����ѯ");
		this.setVisible(true);
		this.setLocation(700, 250);
		String[] col = { "���", "����", "����֤����","����","���","�Ƿ��Ǯ" ,"ԭ��"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // ����һ�����ģ��
		
		String sql = "select * from fine where card_id = '" + cardid+"';";
		dao = new DAO();
		ResultSet rs = dao.getRS(sql);
		try {
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");
				String card_id = rs.getString("card_id");
				String book_name = rs.getString("book_name");
				String price = rs.getString("price");
				String ispay = rs.getString("ispay");
				String reason = rs.getString("reason");
				
				String[] str_row = {id, name,card_id,book_name,price,ispay,reason}; // ��һ�е����ݴ���str_row
				
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
