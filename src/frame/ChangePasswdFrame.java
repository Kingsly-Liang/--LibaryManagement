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
	 * 修改管理员的密码
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jl;   //最顶的标签
	private JTextField indentityCard;
	private JTextField oldpassword;
	private JTextField newpassword;
	
	private JButton confirm;   //确定按钮
	private JButton reset;  //重置按钮

	private DAO dao;
	
	public ChangePasswdFrame(){
		super();
		setTitle("修改密码");                           //设置窗体标题－－－必须
		
		this.setVisible(true);
		this.setLocation(200, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
		label_2.setText("旧密码:");
		label_2.setBounds(150, 160, 80, 30);
		panel.add(label_2);
		oldpassword = new JTextField(10);
		oldpassword.setBounds(230, 160, 150, 30);
		panel.add(oldpassword);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("新密码:");
		label_3.setBounds(150, 200, 80, 30);
		panel.add(label_3);
		newpassword = new JTextField(10);
		newpassword.setBounds(230, 200, 150, 30);
		panel.add(newpassword);
		
		
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
			dao = new DAO();
			if(dao.changePasswd(indentityCard.getText(), oldpassword.getText(), newpassword.getText())){
				JOptionPane.showMessageDialog(null, "更改成功");
				this.dispose();
			}
			
			else {
					JOptionPane.showMessageDialog(null, "你输入的借阅证卡号不存在或原密码有错误，请重新入！","系统提示",JOptionPane.ERROR_MESSAGE);
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
