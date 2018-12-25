package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class BorrowHistoryQueryFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField readerid;
	private JTextField book_id;
	private JLabel jl;   //��ı�ǩ
	private JButton search1;   //��ѯ��ť1
	private JButton search2;   //��ѯ��ť2
	private JButton reset;  //���ð�ť
	private DAO dao;
	
	public BorrowHistoryQueryFrame(){
		super();
		setTitle("������ʷ��ѯ");
		
		this.setVisible(true);
		this.setLocation(200, 250);
		
		//����һ�������������
		final JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 100, 500, 370);
		getContentPane().add(panel);
		
		jl=new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\ͼ��ݹ���ϵͳ_2.png");		
		jl.setIcon(image);
		panel.add(jl);
		
		final JLabel label_1 = new JLabel();
		label_1.setText("��ѡ����߽���֤: ");
		label_1.setBounds(120, 120, 230, 30);
		panel.add(label_1);
		readerid = new JTextField(10);
		readerid.setBounds(250, 120, 150, 30);
		panel.add(readerid);
		
		search1 = new JButton("��ѯ");
		search1.setBounds(450, 120, 60, 30);
		panel.add(search1);
		
		final JLabel label_2 = new JLabel();
		label_2.setText("��ѡ�ͼ����:");
		label_2.setBounds(120, 160, 150, 30);
		panel.add(label_2);
		book_id = new JTextField(10);
		book_id.setBounds(250, 160, 150, 30);
		panel.add(book_id);
		
		search2 = new JButton("��ѯ");
		search2.setBounds(450, 160, 60, 30);
		panel.add(search2);
		
		reset = new JButton("����");
		reset.setBounds(250, 230, 60, 30);
		panel.add(reset);
		
		search1.addActionListener(this);
		search2.addActionListener(this);
		reset.addActionListener(this);
		this.setSize(600, 450);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		dao = new DAO();
		String reader_id = readerid.getText();
		String bookid = book_id.getText();
		
		if(e.getSource() == search1){
			int flag = dao.SearchCardID(reader_id);
			if(flag == 0){
				JOptionPane.showMessageDialog(null, "������Ķ��߽���֤�Ų����ڣ����������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			else if(flag == 1){
				new GetReaderIDHistoryInfo(reader_id);
			}
		}
		else if(e.getSource() == search2){
			int flag = dao.SearchBookid(bookid);
			if(flag == 0){
				JOptionPane.showMessageDialog(null, "�������ͼ���Ų����ڣ����������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			else if(flag == 1){
				new GetBookidHistoryInfo(bookid);
			}
		}
		else if(e.getSource() == reset){
			readerid.setText(null);
			book_id.setText(null);
		}
	}
	
	public static void main(String[] args) {
		new BorrowHistoryQueryFrame().setSize(600,450);
	}
}
