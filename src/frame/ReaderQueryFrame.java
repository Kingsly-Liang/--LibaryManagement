package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class ReaderQueryFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JTextField indentityCard;
	private JLabel jl;   //��ı�ǩ
	private JButton search1;   //��ѯ��ť1
	private JButton search2;   //��ѯ��ť2
	private JButton reset;  //���ð�ť
	private DAO dao;
	
	public ReaderQueryFrame(){
		super();
		setTitle("���߲�ѯ");                           //���ô�����⣭��������
		
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
		indentityCard = new JTextField(10);
		indentityCard.setBounds(250, 120, 150, 30);
		panel.add(indentityCard);
		
		search1 = new JButton("��ѯ");
		search1.setBounds(450, 120, 60, 30);
		panel.add(search1);
		
		final JLabel label_2 = new JLabel();
		label_2.setText("��ѡ�����:");
		label_2.setBounds(120, 160, 150, 30);
		panel.add(label_2);
		name = new JTextField(10);
		name.setBounds(250, 160, 150, 30);
		panel.add(name);
		
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
		String reader_id = indentityCard.getText();
		String name1 = name.getText();
		
		if(e.getSource() == search1){
			int flag = dao.SearchCardID(reader_id);
			if(flag == 0){
				JOptionPane.showMessageDialog(null, "������Ķ���ID�����ڣ����������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			else if(flag == 1){
				new GetReaderIDInfo(reader_id);
			}
		}
		else if(e.getSource() == search2){
			int flag = dao.SearchName(name1);
			if(flag == 0){
				JOptionPane.showMessageDialog(null, "����������������ڣ����������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			else if(flag == 1){
				new GetNameInfo(name1);
			}
		}
		else if(e.getSource() == reset){
			indentityCard.setText(null);
			name.setText(null);
		}
	}
	
	public static void main(String[] args) {
		new ReaderQueryFrame().setSize(600, 450);
	}
}