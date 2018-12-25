package frame;
/*
 * 图书类别修改
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.DAO;

public class BookTypeModifyFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel[] jlbookinfo;  //显示需要填写的图书信息
	private JTextField[] jtbookwrite;  //填写图书信息
	private DAO dao;
	private Button send;
	private Button close;
	private Button query;
	private TextField idtxt;
	 Choice booktype=new Choice();
	
	public BookTypeModifyFrame(){
		setTitle("图书转移");
		setLayout(null);
		setSize(600,400);
		setResizable(false);
		
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 10, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\图书馆管理系统_2.png");		
		jl.setIcon(image);
		add(jl);
		
		Label idlb=new Label("图书编号:");
		idlb.setBounds(40, 70, 80, 30);
		add(idlb);
		idtxt=new TextField();
		idtxt.setBounds(120, 70, 150, 20);
		add(idtxt);
		
		//获取已经存在的图书类别
		String sql="select typeId from bookinfo";
		dao=new DAO();
		ResultSet rs=dao.getRS(sql);		
		try {
			while(rs.next()) {
				
				booktype.add(rs.getString("typeId"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		booktype.setBounds(370, 113, 80, 40);
		
		add(booktype);

		//显示信息
		String[] bookinfo={"图书编号","图书类别编号:","图书名称:","图书作者:","翻译者:","出 版 社:","出版时间:","价格:","库存数量:","可借阅数:"};
				
		//实例化数组
		jlbookinfo=new JLabel[10];
		jtbookwrite=new JTextField[10];
				
			
		for(int i=0;i<bookinfo.length;i++)
		{
			int labelx;  //每个标签的x坐标
			int textX; //每个输入框的x坐标
			jlbookinfo[i]=new JLabel(bookinfo[i]);// 实例化每一个label
			if(i%2==0) {
			labelx=20;	
			textX=110;
			}else
			{labelx=280;
			textX=370;
			}
					
			jlbookinfo[i].setBounds(labelx, 110+40*(i/2), 100, 30);					
			jtbookwrite[i]=new JTextField();
			jtbookwrite[i].setBounds(textX, 110+40*(i/2), 150, 30);
			jtbookwrite[i].setEditable(false);//不可编辑
			add(jlbookinfo[i]);
			add(jtbookwrite[i]);
					}
		
		   jtbookwrite[1].setVisible(false);//第二个输入框不可见
		   
		 
		//查询按钮
			query=new Button("查询");
			query.setBounds(300, 65, 60, 30);
		    add(query);
		//确定按钮	
			send=new Button("确定");
			send.setBounds(200, 320, 60, 30);
			add(send);			
			
		//关闭按钮	
			close=new Button("关闭");
			close.setBounds(330, 320, 60, 30);
			add(close);

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
			querybtnActionPerformed();
			
			}
		});
		
		//借阅按钮事件
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
		//修改成功
				dao=new DAO();
		if(dao.savebook(jtbookwrite[0].getText(), booktype.getSelectedItem(), jtbookwrite[2].getText(), jtbookwrite[3].getText(), jtbookwrite[4].getText(), 
					jtbookwrite[5].getText(), jtbookwrite[6].getText(), jtbookwrite[7].getText(), jtbookwrite[8].getText(), jtbookwrite[9].getText(), idtxt.getText()))
		{
			JOptionPane.showMessageDialog(null,"图书类别修改成功！");
			clearAllText();
		}						
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public  void querybtnActionPerformed(){
		
		String id=idtxt.getText();
		if(id.equals("")){
			JOptionPane.showMessageDialog(null,"图书编号不能为空");
			return;
		}
		dao=new DAO();
		if( dao.IfBookIdExit(id)){
			
			booktype.setEnabled(true);//只有存在图书，图书类别的下拉框才可用
			
			Book book =BookSelectFrame.SelectBookByID(id);		
			String[] infor=book.getBookinformation();
			
			if(book!=null){
				
				booktype.select(book.getTypeId());
				
				for(int i=0;i<infor.length;i++){
					jtbookwrite[i].setText(infor[i]);					
				}
			}		
		}else{	
			JOptionPane.showMessageDialog(null,"没有该图书");
		}
	}

	
	public void clearAllText(){
		for(int i=0;i<jtbookwrite.length;i++){
			jtbookwrite[i].setText("");
		}
	}

	public static void main(String[] args){
		new BookTypeModifyFrame();
	}
}