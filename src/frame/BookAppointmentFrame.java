package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DAO;


public class BookAppointmentFrame extends JFrame implements ActionListener{

	/**
	 * �޸Ĺ���Ա������
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jl;   //��ı�ǩ
	private JTextField indentityCard;
	private JTextField book_id;
	
	private JButton confirm;   //ȷ����ť
	private JButton reset;  //���ð�ť
	
	private String date;
	

	private DAO dao;
	
	public BookAppointmentFrame(){
		super();
		setTitle("ͼ��ԤԼ");                           //���ô�����⣭��������
		
		this.setVisible(true);
		this.setLocation(200, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
		label_2.setText("ͼ����:");
		label_2.setBounds(150, 160, 80, 30);
		panel.add(label_2);
		book_id = new JTextField(10);
		book_id.setBounds(230, 160, 150, 30);
		panel.add(book_id);
		
		
		confirm = new JButton("ȷ��");
		confirm.setBounds(230, 300, 60, 30);
		panel.add(confirm);
		
		reset = new JButton("����");
		reset.setBounds(310, 300, 60, 30);
		panel.add(reset);

		
		Calendar c = Calendar.getInstance();
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		date = f.format(c.getTime());
		
		confirm.addActionListener(this);
		reset.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirm){
			dao = new DAO();
			if(dao.addBookAppointment(indentityCard.getText(), book_id.getText(), date) == 1){
				JOptionPane.showMessageDialog(null, "ԤԼ�ɹ�");
				this.dispose();
			}
			
			else {
					JOptionPane.showMessageDialog(null, "������Ľ���֤���Ų����ڻ�ͼ�����д����������룡","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
				}
			
		}else if(e.getSource() == reset){
			indentityCard.setText(null);
			book_id.setText(null);
		}
		
	}
	
	public static void main(String[] args) {
		new BookAppointmentFrame().setSize(600, 450);
	}



}
