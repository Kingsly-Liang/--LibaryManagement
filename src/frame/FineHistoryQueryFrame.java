package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class FineHistoryQueryFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField indentityCard;
	private JTextField book_id;
	private JLabel jl;   //最顶的标签
	private JButton search1;   //查询按钮1
	private JButton search2;   //查询按钮2
	private JButton reset;  //重置按钮
	private DAO dao;
	
	public FineHistoryQueryFrame(){
		super();
		setTitle("罚款历史查询");
		
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
		label_1.setText("（选填）借阅证号码: ");
		label_1.setBounds(120, 120, 230, 30);
		panel.add(label_1);
		indentityCard = new JTextField(10);
		indentityCard.setBounds(250, 120, 150, 30);
		panel.add(indentityCard);
		
		search1 = new JButton("查询");
		search1.setBounds(450, 120, 60, 30);
		panel.add(search1);
		
		final JLabel label_2 = new JLabel();
		label_2.setText("（选填）图书编号:");
		label_2.setBounds(120, 160, 150, 30);
		panel.add(label_2);
		book_id = new JTextField(10);
		book_id.setBounds(250, 160, 150, 30);
		panel.add(book_id);
		
		search2 = new JButton("查询");
		search2.setBounds(450, 160, 60, 30);
		panel.add(search2);
		
		reset = new JButton("重置");
		reset.setBounds(250, 230, 60, 30);
		panel.add(reset);
		
		search1.addActionListener(this);
		search2.addActionListener(this);
		reset.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		dao = new DAO();
		String card_id = indentityCard.getText();
		String bookid = book_id.getText();
		
		if(e.getSource() == search1){
			int flag = dao.SearchIndentityCard(card_id);
			if(flag == 0){
				JOptionPane.showMessageDialog(null, "你输入的借阅证卡号不存在，请重新输入！","系统提示",JOptionPane.ERROR_MESSAGE);
			}
			else if(flag == 1){
				new GetCardIDFineHistoryInfo(card_id);
			}
		}
		else if(e.getSource() == search2){
			int flag = dao.SearchBookid(bookid);
			if(flag == 0){
				JOptionPane.showMessageDialog(null, "你输入的图书编号不存在，请重新输入！","系统提示",JOptionPane.ERROR_MESSAGE);
			}
			else if(flag == 1){
				new GetBookidFineHistoryInfo(bookid);
			}
		}
		else if(e.getSource() == reset){
			indentityCard.setText(null);
			book_id.setText(null);
		}
	}
	
	public static void main(String[] args) {
		new FineHistoryQueryFrame().setSize(600,450);
	}
}