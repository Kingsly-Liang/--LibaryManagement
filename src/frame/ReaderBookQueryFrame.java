package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class ReaderBookQueryFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JTextField indentityCard;
	private JLabel jl;   //��ı�ǩ
	private JButton search1;   //��ѯ��ť1
	private JButton search2;   //��ѯ��ť2
	private JButton reset;  //���ð�ť
	private DAO dao;
	
	public ReaderBookQueryFrame(){
		super();
		setTitle("ͼ���ѯ");                           //���ô�����⣭��������
		
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
		
		final JLabel label_2 = new JLabel();
		label_2.setText("����:");
		label_2.setBounds(120, 120, 230, 30);
		panel.add(label_2);
		name = new JTextField(10);
		name.setBounds(180, 120, 150, 30);
		panel.add(name);
		
		search2 = new JButton("��ѯ");
		search2.setBounds(350, 120, 60, 30);
		panel.add(search2);
		
		search1 = new JButton("һ����ѯ");
		search1.setBounds(200, 230, 100, 30);
		panel.add(search1);
		
		reset = new JButton("����");
		reset.setBounds(320, 230, 60, 30);
		panel.add(reset);
		
		search1.addActionListener(this);
		search2.addActionListener(this);
		reset.addActionListener(this);
		this.setSize(600, 450);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		dao = new DAO();
		String name1 = name.getText();
		
		if(e.getSource() == search1){
			new GetReaderAllBook();	
		}
		else if(e.getSource() == search2){
			new GetReaderBook(name1);
		}
		else if(e.getSource() == reset){
			name.setText(null);
		}
	}
	
	public static void main(String[] args) {
		new ReaderBookQueryFrame().setSize(600, 450);
	}
}