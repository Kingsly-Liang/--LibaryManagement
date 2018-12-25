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
	private JLabel jl;   //最顶的标签
	private JButton search1;   //查询按钮1
	private JButton search2;   //查询按钮2
	private JButton reset;  //重置按钮
	private DAO dao;
	
	public ReaderBookQueryFrame(){
		super();
		setTitle("图书查询");                           //设置窗体标题－－－必须
		
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
		
		final JLabel label_2 = new JLabel();
		label_2.setText("书名:");
		label_2.setBounds(120, 120, 230, 30);
		panel.add(label_2);
		name = new JTextField(10);
		name.setBounds(180, 120, 150, 30);
		panel.add(name);
		
		search2 = new JButton("查询");
		search2.setBounds(350, 120, 60, 30);
		panel.add(search2);
		
		search1 = new JButton("一键查询");
		search1.setBounds(200, 230, 100, 30);
		panel.add(search1);
		
		reset = new JButton("重置");
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