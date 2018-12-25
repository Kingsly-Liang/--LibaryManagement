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
	private JLabel jl;   //最顶的标签
	private JButton search;   //查询按钮
	private JButton reset;  //重置按钮
	private DAO dao;
	
	public PasswordQueryFrame(){
		super();
		setTitle("密码查询");                           //设置窗体标题－－－必须
		
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
		label_1.setBounds(160, 150, 230, 30);
		panel.add(label_1);
		indentityCard = new JTextField(10);
		indentityCard.setBounds(270, 150, 150, 30);
		panel.add(indentityCard);
		
		search = new JButton("查询");
		search.setBounds(200, 230, 60, 30);
		panel.add(search);
		
		reset = new JButton("重置");
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
				JOptionPane.showMessageDialog(null, "你输入的借阅证卡号不存在，请重新入！","系统提示",JOptionPane.ERROR_MESSAGE);
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