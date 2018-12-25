package frame;

import setButtonBG.setButtonBackground;

import java.util.Calendar;
import java.text.Format;
import java.text.SimpleDateFormat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.DAO;


public class ReaderAddFrame extends JFrame implements ActionListener{

	/**
	 * ���Ӷ��ߵ���Ϣ/Ϊ���߿���
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jl;   //��ı�ǩ
	private JTextField name;
	private JComboBox sex;
	private JTextField age;
	private JTextField indentityCard;
	private JTextField date;     //֤����Ч��
	private JTextField phone;
	private JTextField password;
	private JComboBox zj;    //֤������
	private JTextField bztime;   //��֤����
	private JComboBox cardType;    //����״̬
	
	private JButton send;   //�ύ��ť
	private JButton reset;  //���ð�ť
	
	private DAO dao;

	public ReaderAddFrame(){
		super();
		setTitle("������Ϣ����");                           //���ô�����⣭��������
		
		this.setVisible(true);
		this.setLocation(200, 250);

		//����һ��������������
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
		label_1.setText("�� ��:");
		label_1.setBounds(10, 70, 50, 30);
		panel.add(label_1);
		name = new JTextField(10);
		name.setBounds(100, 70, 150, 30);
		panel.add(name);



		final JLabel label_2 = new JLabel();
		label_2.setText("�� ��:");
		label_2.setBounds(300, 70, 40, 30);
		panel.add(label_2);
		String sex1[] = {"��", "Ů"};
		sex = new JComboBox(sex1);
		sex.setBounds(400, 70, 60, 30);
		panel.add(sex);

		final JLabel label_3 = new JLabel();
		label_3.setText("�� ��:");
		label_3.setBounds(10, 120, 50, 30);
		panel.add(label_3);
		age = new JTextField(10);
		age.setBounds(100, 120, 150, 30);
		panel.add(age);

		final JLabel label_4 = new JLabel();
		label_4.setText("����֤����: ");
		label_4.setBounds(300, 120, 80, 30);
		panel.add(label_4);
		indentityCard = new JTextField(10);
		indentityCard.setBounds(400, 120, 150, 30);
		panel.add(indentityCard);

		final JLabel label_5 = new JLabel();
		label_5.setText("��ϵ����: ");
		label_5.setBounds(10, 170, 80, 30);
		panel.add(label_5);
		phone = new JTextField(10);
		phone.setBounds(100, 170, 150, 30);
		panel.add(phone);

		final JLabel label_6 = new JLabel();
		label_6.setText("��֤����:");
		label_6.setBounds(300, 170, 80, 30);
		panel.add(label_6);
		bztime = new JTextField(10);
		Calendar c = Calendar.getInstance();
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		bztime.setText(f.format(c.getTime()));
		bztime.setBounds(400, 170, 150, 30);
		panel.add(bztime);

		final JLabel label_7 = new JLabel();
		label_7.setText("����:");
		label_7.setBounds(10, 220, 80, 30);
		panel.add(label_7);
		password = new JTextField(10);
		password.setBounds(100, 220, 150, 30);
		panel.add(password);

		final JLabel label_8 = new JLabel();
		label_8.setText("֤������:");
		label_8.setBounds(300, 220, 80, 30);
		panel.add(label_8);
		String zj1[] = {"ѧ��", "��ʦ"};
		zj = new JComboBox(zj1);
		zj.setBounds(400, 220, 70, 30);
		panel.add(zj);

		final JLabel label_9 = new JLabel();
		label_9.setText("����״̬:");
		label_9.setBounds(10, 270, 80, 30);
		panel.add(label_9);
		String cardType1[] = {"����","��ʧ","ע��","��ͣ"};
		cardType = new JComboBox(cardType1);
		cardType.setBounds(100, 270, 70, 30);
		panel.add(cardType);
		
		
		final JLabel label_10 = new JLabel();
		label_10.setText("֤����Ч��:");
		label_10.setBounds(300, 270, 80, 30);
		panel.add(label_10);
		date = new JTextField(10);
		c.add(Calendar.YEAR, +4);
		date.setText(f.format(c.getTime()));
		date.setBounds(400, 270, 150, 30);
		panel.add(date);
		
		
		send = new JButton("�ύ");
		send.setBounds(200, 320, 60, 30);
		panel.add(send);
		
		reset = new JButton("����");
		reset.setBounds(280, 320, 60, 30);
		panel.add(reset);

		send.addActionListener(this);
		reset.addActionListener(this);
		
		this.setSize(600, 450);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == send){
			String name1 = name.getText();
			String age1 = age.getText();
			String cardid = indentityCard.getText();
			String tel = phone.getText();
			String bzrq = bztime.getText();
			String yxrq = date.getText();
			String passwd = password.getText();
			
			String sex1 = sex.getSelectedItem().toString();
			String zj1 = zj.getSelectedItem().toString();
			String cardType1 = cardType.getSelectedItem().toString();
			int maxNum = 0;
			
			if(zj1 == "ѧ��"){
				maxNum = 200;
			}else if(zj1 == "��ʦ"){
				maxNum = 600;
			}
			
			dao = new DAO();
			int flag = dao.addReader(name1, sex1, age1, cardid, yxrq, maxNum, tel, passwd, zj1, bzrq, cardType1);
			if(indentityCard.getText().length() != 11){
				JOptionPane.showMessageDialog(null, "����֤�ű�����11λ��","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			if(password.getText().length() <= 6){
				JOptionPane.showMessageDialog(null, "���볤�ȱ������6λ��","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			if(flag == 1){
				JOptionPane.showMessageDialog(null, "���ӳɹ�");
				this.dispose();
			}
			else if(flag == 2){
				JOptionPane.showMessageDialog(null, "������Ľ���֤�����Ѵ���,�������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}else if(flag == 0){
				JOptionPane.showMessageDialog(null, "����ʧ��","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(e.getSource() == reset){
			name.setText(null);
			age.setText(null);
			indentityCard.setText(null);
			phone.setText(null);
			password.setText(null);
			
		}
		
	}
	
	public static void main(String[] args) {
		new ReaderAddFrame().setSize(600, 450);
	}



}