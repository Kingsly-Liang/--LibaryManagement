package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class PasswordQueryFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField indentityCard;
	private JLabel jl;   //��ı�ǩ
	private JButton search;   //��ѯ��ť
	private JButton reset;  //���ð�ť
	private DAO dao;
	
	public PasswordQueryFrame(){
		super();
		setTitle("�����ѯ");                           //���ô�����⣭��������
		
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
		label_1.setText("����֤����: ");
		label_1.setBounds(160, 150, 230, 30);
		panel.add(label_1);
		indentityCard = new JTextField(10);
		indentityCard.setBounds(270, 150, 150, 30);
		panel.add(indentityCard);
		
		search = new JButton("��ѯ");
		search.setBounds(200, 230, 60, 30);
		panel.add(search);
		
		reset = new JButton("����");
		reset.setBounds(300, 230, 60, 30);
		panel.add(reset);
		
		search.addActionListener(this);
		reset.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		dao = new DAO();
		String card_id = indentityCard.getText();
		
		if(e.getSource() == search){
			int flag = dao.SearchCardID(card_id);
			if(flag == 0){
				JOptionPane.showMessageDialog(null, "������Ľ���֤���Ų����ڣ��������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			else if(flag == 1){
				new GetPasswordInfo(card_id);
			}
		}
		else if(e.getSource() == reset){
			indentityCard.setText(null);
		}
	}

	public static void main(String[] args) {
		new PasswordQueryFrame().setSize(600, 450);
	}
}