package frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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


public class PayFineFrame extends JFrame implements ActionListener{

	/**
	 * 交纳罚款
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jl;   //最顶的标签
	
	private JButton refresh;   //每天更新按钮
	
	private JTextField indentityCard;
	private JButton search;   //查询按钮
	private JRadioButton hasPay;   
	private JRadioButton notPay;   
	
	private JButton confirm;   //确定按钮
	private JButton reset;  //重置按钮
	
	private DAO dao;
	
	
	private String dbClassName = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/library?autoReconnect=true";
	private String dbUser = "root";
	private String dbPwd = "root";
	private Connection conn = null;
	private Statement stmt;
	private ResultSet rs;

	public PayFineFrame(){
		super();
		setTitle("交纳罚款");                           //设置窗体标题－－－必须
		
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
		search = new JButton("查询");
		search.setBounds(400, 120, 60, 30);
		panel.add(search);
		panel.add(indentityCard);
		
		
		

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder("是否交纳罚款"));
		panel_1.setBounds(250, 200, 100, 80);
		hasPay = new JRadioButton("是");
		notPay = new JRadioButton("否");
		panel_1.setLayout(new GridLayout(1, 2));
		panel_1.add(hasPay);
		panel_1.add(notPay);
		ButtonGroup group = new ButtonGroup();
		group.add(this.hasPay);
		group.add(this.notPay);
		panel.add(panel_1);
		
		
		confirm = new JButton("确定");
		confirm.setBounds(230, 350, 60, 30);
		panel.add(confirm);
		
		reset = new JButton("重置");
		reset.setBounds(310, 350, 60, 30);
		panel.add(reset);

		confirm.addActionListener(this);
		reset.addActionListener(this);
		search.addActionListener(this);
		//refresh.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		dao = new DAO();
		String card_id = indentityCard.getText();
		String chachong_sql = "select * from fine";
		String choose = "";
		if(hasPay.isSelected()){
			choose = hasPay.getText();
		}if(notPay.isSelected()){
			choose = notPay.getText();
		}
		String sql1 = "select * from fine where card_id = '" + card_id+"';";
		String sql2 = "update fine set ispay ='" + choose + "' where card_id='" + card_id+ "'";
		if(e.getSource() == confirm){
			
			int flag = 2;
			try {

				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("驱动已加载！");
				conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				System.out.println("连接成功！");
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql1);
				while (rs.next()) {
					String cardid = rs.getString("card_id");
					//System.out.println(cardid);
					if((cardid.equals(card_id))){
						flag = 1;
						break;
					}
				}
				
				if(flag == 1){
					int j = stmt.executeUpdate(sql2);
					if(j == 1){
						JOptionPane.showMessageDialog(null, "交纳成功");
						this.dispose();
					}
				}else if(flag == 2){
					JOptionPane.showMessageDialog(null, "你输入的借阅证卡号不存在，请重新入！","系统提示",JOptionPane.ERROR_MESSAGE);
				}
				
				stmt.close();
				rs.close();
				conn.close();

			}catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("驱动找不到" + e1.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == reset){
			indentityCard.setText(null);
			
		}else if(e.getSource() == refresh){
			
		}else if(e.getSource() == search){
			boolean flag1 = false;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("驱动已加载！");
				conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
				System.out.println("连接成功！");
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql1);
				while (rs.next()) {
					String cardid = rs.getString("card_id");
					//System.out.println(cardid);
					if((cardid.equals(card_id))){
						flag1 = true;
						break;
					}
				}
				stmt.close();
				rs.close();
				conn.close();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("驱动找不到" + e1.getMessage());
			} catch(SQLException e2){
				System.out.println("SQLException" + e2.getMessage());
			}
			
			if(flag1 != true){
				JOptionPane.showMessageDialog(null, "你输入的借阅证卡号不存在，请重新入！","系统提示",JOptionPane.ERROR_MESSAGE);

			}
			else{
				new GetCardIDFine(card_id);
			}
		}
		
	}
	
	public static void main(String[] args) {
		new PayFineFrame().setSize(600, 450);
	}



}
