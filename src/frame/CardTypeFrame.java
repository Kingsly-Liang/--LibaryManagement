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
	 * 暂停借阅证
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jl;   //最顶的标签
	private JTextField indentityCard;
	private JTextField name;
	private JRadioButton normal;   //正常
	private JRadioButton suspend;   //暂停
	private JRadioButton reportLoss;   //挂失
	private JRadioButton cancellation ;   //注销
	
	private JButton confirm;   //确定按钮
	private JButton reset;  //重置按钮
	
	private DAO dao;

	public CardTypeFrame(){
		super();
		setTitle("改变借阅证状态");                           //设置窗体标题－－－必须
		
		this.setVisible(true);
		this.setLocation(200, 250);

		//设置一个添加组件的面板
		final JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 100, 500, 370);
		getContentPane().add(panel);

		jl=new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\图书馆管理系统_2.png");		
		jl.setIcon(image);
		panel.add(jl);

		final JLabel label_1 = new JLabel();
		label_1.setText("借阅证号码: ");
		label_1.setBounds(150, 120, 80, 30);
		panel.add(label_1);
		indentityCard = new JTextField(10);
		indentityCard.setBounds(230, 120, 150, 30);
		panel.add(indentityCard);

		final JLabel label_2 = new JLabel();
		label_2.setText("姓名:");
		label_2.setBounds(150, 160, 80, 30);
		panel.add(label_2);
		name = new JTextField(10);
		name.setBounds(230, 160, 150, 30);
		panel.add(name);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder("选择状态"));
		panel_1.setBounds(100, 200, 400, 80);
		normal = new JRadioButton("正常");
		suspend = new JRadioButton("暂停");
		reportLoss = new JRadioButton("挂失");
		cancellation = new JRadioButton("注销");
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
		
		
		confirm = new JButton("确定");
		confirm.setBounds(230, 300, 60, 30);
		panel.add(confirm);
		
		reset = new JButton("重置");
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
				JOptionPane.showMessageDialog(null, "修改成功");
				this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "你输入的借阅证卡号不存在或姓名有错误，请重新入！","系统提示",JOptionPane.ERROR_MESSAGE);
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
