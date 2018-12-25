package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DAO;


public class ChangePasswdFrame extends JFrame implements ActionListener{

	/**
	 * �޸Ĺ���Ա������
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jl;   //��ı�ǩ
	private JTextField indentityCard;
	private JTextField oldpassword;
	private JTextField newpassword;
	
	private JButton confirm;   //ȷ����ť
	private JButton reset;  //���ð�ť

	private DAO dao;
	
	public ChangePasswdFrame(){
		super();
		setTitle("�޸�����");                           //���ô�����⣭��������
		
		this.setVisible(true);
		this.setLocation(200, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
		label_1.setBounds(150, 120, 80, 30);
		panel.add(label_1);
		indentityCard = new JTextField(10);
		indentityCard.setBounds(230, 120, 150, 30);
		panel.add(indentityCard);

		final JLabel label_2 = new JLabel();
		label_2.setText("������:");
		label_2.setBounds(150, 160, 80, 30);
		panel.add(label_2);
		oldpassword = new JTextField(10);
		oldpassword.setBounds(230, 160, 150, 30);
		panel.add(oldpassword);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("������:");
		label_3.setBounds(150, 200, 80, 30);
		panel.add(label_3);
		newpassword = new JTextField(10);
		newpassword.setBounds(230, 200, 150, 30);
		panel.add(newpassword);
		
		
		confirm = new JButton("ȷ��");
		confirm.setBounds(230, 300, 60, 30);
		panel.add(confirm);
		
		reset = new JButton("����");
		reset.setBounds(310, 300, 60, 30);
		panel.add(reset);

		confirm.addActionListener(this);
		reset.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirm){
			dao = new DAO();
			if(dao.changePasswd(indentityCard.getText(), oldpassword.getText(), newpassword.getText())){
				JOptionPane.showMessageDialog(null, "���ĳɹ�");
				this.dispose();
			}
			
			else {
					JOptionPane.showMessageDialog(null, "������Ľ���֤���Ų����ڻ�ԭ�����д����������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
				}
			
		}else if(e.getSource() == reset){
			indentityCard.setText(null);
			oldpassword.setText(null);
			newpassword.setText(null);
		}
		
	}
	
	public static void main(String[] args) {
		new ChangePasswdFrame().setSize(600, 450);
	}



}
