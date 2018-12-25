package frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.DAO;


public class CardTypeFrame extends JFrame implements ActionListener{

	/**
	 * ��ͣ����֤
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jl;   //��ı�ǩ
	private JTextField indentityCard;
	private JTextField name;
	private JRadioButton normal;   //����
	private JRadioButton suspend;   //��ͣ
	private JRadioButton reportLoss;   //��ʧ
	private JRadioButton cancellation ;   //ע��
	
	private JButton confirm;   //ȷ����ť
	private JButton reset;  //���ð�ť
	
	private DAO dao;

	public CardTypeFrame(){
		super();
		setTitle("�ı����֤״̬");                           //���ô�����⣭��������
		
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
		label_1.setBounds(150, 120, 80, 30);
		panel.add(label_1);
		indentityCard = new JTextField(10);
		indentityCard.setBounds(230, 120, 150, 30);
		panel.add(indentityCard);

		final JLabel label_2 = new JLabel();
		label_2.setText("����:");
		label_2.setBounds(150, 160, 80, 30);
		panel.add(label_2);
		name = new JTextField(10);
		name.setBounds(230, 160, 150, 30);
		panel.add(name);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder("ѡ��״̬"));
		panel_1.setBounds(100, 200, 400, 80);
		normal = new JRadioButton("����");
		suspend = new JRadioButton("��ͣ");
		reportLoss = new JRadioButton("��ʧ");
		cancellation = new JRadioButton("ע��");
		panel_1.setLayout(new GridLayout(1, 4));
		panel_1.add(normal);
		panel_1.add(suspend);
		panel_1.add(reportLoss);
		panel_1.add(cancellation);
		ButtonGroup group = new ButtonGroup();
		group.add(this.normal);
		group.add(this.suspend);
		group.add(this.reportLoss);
		group.add(this.cancellation);
		panel.add(panel_1);
		
		
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
			String name1 = name.getText();
			String cardid = indentityCard.getText();
			String type = "";
			if(normal.isSelected()){
				type = normal.getText();
			}
			if(suspend.isSelected()){
				type = suspend.getText();
			}
			if(reportLoss.isSelected()){
				type = reportLoss.getText();
			}
			if(cancellation.isSelected()){
				type = cancellation.getText();
			}
			
			dao = new DAO();
			if(dao.changeCardType(cardid, name1, type)){
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "������Ľ���֤���Ų����ڻ������д����������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(e.getSource() == reset){
			indentityCard.setText(null);
			name.setText(null);
		}
		
	}
	
	public static void main(String[] args) {
		new CardTypeFrame().setSize(600, 450);
	}



}
