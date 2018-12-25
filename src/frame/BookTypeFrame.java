//ͼ��������

package frame;

import javax.swing.*;

import dao.DAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BookTypeFrame extends JFrame implements ActionListener {

	@SuppressWarnings("unused")
	private JFrame jf;
	private JLabel jlbooktype;
	private JLabel jlbookdays;
	private JLabel jlfine;
	private JTextField jtbooktype;
	private JTextField jtbookdays;
	private JTextField jtfine;
	private JButton send;
	private JButton reset;
	private Container con;
	private DAO dao;
	public BookTypeFrame(){
		super();
		jf=new JFrame();
		con=this.getContentPane();
		this.setLayout(null);//�����ò���
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 25, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\ͼ��ݹ���ϵͳ_2.png");		
		jl.setIcon(image);
		con.add(jl);
		
		//ͼ��������
		//��ǩ
		jlbooktype=new JLabel("ͼ�����:");
		jlbooktype.setBounds(100, 100, 120, 30);
		con.add(jlbooktype);
		//�ı�
		jtbooktype=new JTextField();
		jtbooktype.setBounds(220, 100, 200, 30);
		con.add(jtbooktype);
		
		
		//�ɽ�������
		//��ǩ
		jlbookdays=new JLabel("�ɽ�������:");
		jlbookdays.setBounds(100, 140, 120, 30);
		con.add(jlbookdays);
		//�ı�
		jtbookdays=new JTextField();
		jtbookdays.setBounds(220, 140, 200, 30);
		con.add(jtbookdays);
		
		//���ں�ÿ�շ�����
		jlfine=new JLabel("ÿ�շ�����:");
		jlfine.setBounds(100, 180, 120, 30);
		con.add(jlfine);
		
		//�ı�
		jtfine=new JTextField();
		jtfine.setBounds(220, 180, 200, 30);
		con.add(jtfine);
		
		//���Ͱ�ť
		send=new JButton("ȷ��");
		send.setBounds(180, 230, 60, 30);
		con.add(send);
		//���ð�ť
        reset=new JButton("����");
		reset.setBounds(280, 230, 60, 30);
		con.add(reset);
		
		this.setTitle("ͼ�����");
		this.setBounds(150, 250, 600, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf=this;
		
		//��Ӽ�������
		send.addActionListener(this);
		reset.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == send){
			dao=new DAO();
			if(jtbooktype.getText().equals("")||jtbooktype.getText().equals("")||jtfine.getText().equals(""))//û����������
			{JOptionPane.showMessageDialog(null, "���벻���пգ�����������");}
			else {
			     if(dao.addtype(jtbooktype.getText(), Integer.parseInt(jtbookdays.getText()), Double.parseDouble(jtfine.getText()))) {				
			     JOptionPane.showMessageDialog(null, "��ӳɹ�");
			     this.dispose();
			     }
			    else {
			 	JOptionPane.showMessageDialog(null, "���������Ϣ�д�������и�����������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			      }
			}
		}else if(e.getSource() == reset){
			jtbooktype.setText("");
			jtbookdays.setText("");
			jtfine.setText("");
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new BookTypeFrame();
	}

}
