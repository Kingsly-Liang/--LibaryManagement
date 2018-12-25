//图书出库
package frame;

import java.awt.*;
import javax.swing.*;

import dao.DAO;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BookDeleteFrame extends JFrame implements ActionListener {

	private JFrame jf;
	private JLabel jlbookname;
	private JLabel jlbooknum;
	private JLabel jlreason;
	private JLabel jlbookid;
	private JTextField jtbookname;
	private JTextField jtbooknum;
	private JTextField jtreason;
	private JTextField jtbookid;
	private JButton delete;
	private JButton close;
	private Button query;
	private Container con;
	private TextField idtxt;
	private DAO dao;
	
	public BookDeleteFrame(){
		super();
		jf=new JFrame();
		con=this.getContentPane();
		this.setLayout(null);//不设置布局
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 20, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\图书馆管理系统_2.png");		
		jl.setIcon(image);
		con.add(jl);
		
		//通过查找图书编号查找图书是否存在
		Label idlb=new Label("图书编号:");
		idlb.setBounds(130, 70, 80, 30);
		add(idlb);
		idtxt=new TextField();
		idtxt.setBounds(220, 70, 150, 30);
		add(idtxt);
		
		//出库图书id
		//标签
		jlbookid=new JLabel("出库图书ID:");
		jlbookid.setBounds(100, 120, 120, 30);
		con.add(jlbookid);
		//文本
		jtbookid=new JTextField();
		jtbookid.setBounds(220, 120, 200, 30);
		con.add(jtbookid);
		jtbookid.setEditable(false);
		
		//出库图书名称
		//标签
		jlbookname=new JLabel("出库图书名称:");
		jlbookname.setBounds(100, 160, 120, 30);
		con.add(jlbookname);
		//文本
		jtbookname=new JTextField();
		jtbookname.setBounds(220, 160, 200, 30);
		con.add(jtbookname);
		jtbookname.setEditable(false);
		
				
		//出库数量
		//标签
		jlbooknum=new JLabel("出库图书数量:");
		jlbooknum.setBounds(100, 200, 120, 30);
		con.add(jlbooknum);
		//文本
		jtbooknum=new JTextField();
		jtbooknum.setBounds(220, 200, 200, 30);
		con.add(jtbooknum);
		
		//出库原因
		jlreason=new JLabel("出库该图书原因:");
		jlreason.setBounds(100, 250, 120, 30);
		con.add(jlreason);		
		//文本
		jtreason=new JTextField();
		jtreason.setBounds(220, 250, 200, 30);
		con.add(jtreason);
		
		//查询按钮
		query=new Button("查询");
		query.setBounds(380, 70, 60, 30);
	    add(query);
		//发送按钮
	    delete=new JButton("删除");
	    delete.setBounds(180, 300, 60, 30);
		con.add(delete);
		//重置按钮
        close=new JButton("关闭");
		close.setBounds(280, 300, 60, 30);
		con.add(close);
		
		this.setTitle("图书出库");
		this.setBounds(200, 250, 600, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf=this;
		
		//添加监听内容
		delete.addActionListener(this);
		close.addActionListener(this);
	//关闭
	close.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dao=new DAO();
			dao.close();
			dispose();			
		}
	});
	//查询事件
	query.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		jtbookname.setText(idtxt.getText());
		querybtnActionPerformed();
				
		}
	});

	//删除按钮事件
	delete.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
	//修改成功
			dao=new DAO();
	if(Integer.parseInt(jtbooknum.getText())<=0){
		JOptionPane.showMessageDialog(null,"出库数量不能是负数！");
	}else{
	if(( dao.BookDelete(idtxt.getText(),jtbookname.getText(),jtbooknum.getText(),jtreason.getText())))
	{
		JOptionPane.showMessageDialog(null,"出库成功！");
		
	}	
		}
		}
	});
	setLocationRelativeTo(null);
	setVisible(true);
}
	
@SuppressWarnings("static-access")
//判断图书是否存在
public  void querybtnActionPerformed(){
	String id=idtxt.getText();
	if(id.equals("")){
		JOptionPane.showMessageDialog(null,"图书编号不能为空");
		jtbookid.setText("");//清除
		jtbookname.setText("");//重置
		return;
	}
	dao=new DAO();
	if( dao.IfBookIdExit(id)){
		
		Book book =BookSelectFrame.SelectBookByID(id);		
		
		if(book!=null){						
			jtbookid.setText(book.getId());//显示查找到的图书ID
			jtbookname.setText(book.getBookname());//显示查找到的图书名称
		}		
	}else{	
		JOptionPane.showMessageDialog(null,"没有该图书");
		jtbookname.setText("");//重置
	}
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new BookDeleteFrame();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
