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
		setTitle("����ͳ��"); 
		this.setVisible(true);
		this.setLocation(700, 250);            //���ô�����⣭��������
		
		String[] col = {"����","����","���","���ı���"};
		DefaultTableModel mm = new DefaultTableModel(col, 0); // ����һ�����ģ��
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
				String[] str_row = {name,age,zj,hasLend}; // ��һ�е����ݴ���str_row�ַ���������
				mm.addRow(str_row);// ����ڱ�ģ����
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
