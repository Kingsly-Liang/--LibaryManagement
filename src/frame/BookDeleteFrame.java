//ͼ�����
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
		this.setLayout(null);//�����ò���
		
		JLabel jl = new JLabel();
		jl.setBounds(80, 20, 600, 40);
		ImageIcon image=new ImageIcon(".\\image\\ͼ��ݹ���ϵͳ_2.png");		
		jl.setIcon(image);
		con.add(jl);
		
		//ͨ������ͼ���Ų���ͼ���Ƿ����
		Label idlb=new Label("ͼ����:");
		idlb.setBounds(130, 70, 80, 30);
		add(idlb);
		idtxt=new TextField();
		idtxt.setBounds(220, 70, 150, 30);
		add(idtxt);
		
		//����ͼ��id
		//��ǩ
		jlbookid=new JLabel("����ͼ��ID:");
		jlbookid.setBounds(100, 120, 120, 30);
		con.add(jlbookid);
		//�ı�
		jtbookid=new JTextField();
		jtbookid.setBounds(220, 120, 200, 30);
		con.add(jtbookid);
		jtbookid.setEditable(false);
		
		//����ͼ������
		//��ǩ
		jlbookname=new JLabel("����ͼ������:");
		jlbookname.setBounds(100, 160, 120, 30);
		con.add(jlbookname);
		//�ı�
		jtbookname=new JTextField();
		jtbookname.setBounds(220, 160, 200, 30);
		con.add(jtbookname);
		jtbookname.setEditable(false);
		
				
		//��������
		//��ǩ
		jlbooknum=new JLabel("����ͼ������:");
		jlbooknum.setBounds(100, 200, 120, 30);
		con.add(jlbooknum);
		//�ı�
		jtbooknum=new JTextField();
		jtbooknum.setBounds(220, 200, 200, 30);
		con.add(jtbooknum);
		
		//����ԭ��
		jlreason=new JLabel("�����ͼ��ԭ��:");
		jlreason.setBounds(100, 250, 120, 30);
		con.add(jlreason);		
		//�ı�
		jtreason=new JTextField();
		jtreason.setBounds(220, 250, 200, 30);
		con.add(jtreason);
		
		//��ѯ��ť
		query=new Button("��ѯ");
		query.setBounds(380, 70, 60, 30);
	    add(query);
		//���Ͱ�ť
	    delete=new JButton("ɾ��");
	    delete.setBounds(180, 300, 60, 30);
		con.add(delete);
		//���ð�ť
        close=new JButton("�ر�");
		close.setBounds(280, 300, 60, 30);
		con.add(close);
		
		this.setTitle("ͼ�����");
		this.setBounds(200, 250, 600, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf=this;
		
		//��Ӽ�������
		delete.addActionListener(this);
		close.addActionListener(this);
	//�ر�
	close.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dao=new DAO();
			dao.close();
			dispose();			
		}
	});
	//��ѯ�¼�
	query.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		jtbookname.setText(idtxt.getText());
		querybtnActionPerformed();
				
		}
	});

	//ɾ����ť�¼�
	delete.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
	//�޸ĳɹ�
			dao=new DAO();
	if(Integer.parseInt(jtbooknum.getText())<=0){
		JOptionPane.showMessageDialog(null,"�������������Ǹ�����");
	}else{
	if(( dao.BookDelete(idtxt.getText(),jtbookname.getText(),jtbooknum.getText(),jtreason.getText())))
	{
		JOptionPane.showMessageDialog(null,"����ɹ���");
		
	}	
		}
		}
	});
	setLocationRelativeTo(null);
	setVisible(true);
}
	
@SuppressWarnings("static-access")
//�ж�ͼ���Ƿ����
public  void querybtnActionPerformed(){
	String id=idtxt.getText();
	if(id.equals("")){
		JOptionPane.showMessageDialog(null,"ͼ���Ų���Ϊ��");
		jtbookid.setText("");//���
		jtbookname.setText("");//����
		return;
	}
	dao=new DAO();
	if( dao.IfBookIdExit(id)){
		
		Book book =BookSelectFrame.SelectBookByID(id);		
		
		if(book!=null){						
			jtbookid.setText(book.getId());//��ʾ���ҵ���ͼ��ID
			jtbookname.setText(book.getBookname());//��ʾ���ҵ���ͼ������
		}		
	}else{	
		JOptionPane.showMessageDialog(null,"û�и�ͼ��");
		jtbookname.setText("");//����
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
