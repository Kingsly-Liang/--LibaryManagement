package menu;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

import frame.BookAppointmentFrame;
import frame.CardTypeFrame;
import frame.ChangePasswdFrame;
import frame.MainUI;
import frame.PayFineFrame;
import frame.ReaderAddFrame;
import frame.ReaderBookQueryFrame;
import frame.ReaderChangePasswdFrame;




//import testMenu.MainDialog;


public class ReaderMenuActions {
	private static Map<String,JInternalFrame> frames;		//�Ӵ��弯��

	public static ChangePassword change_password;          //�޸�����
	public static BookSearch book_search;          
	public static BookAppointment book_appointment;         


	//��ʼ����������
	static {
		//ʵ����

		frames = new HashMap<String, JInternalFrame>();

		change_password=new ChangePassword();          //�޸�����
		book_search = new BookSearch();
		book_appointment = new BookAppointment();

	}

	//�޸�����
	private static class ChangePassword extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		//���캯��
		ChangePassword(){
			super("�޸�����", null);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new ReaderChangePasswdFrame().setSize(600, 450);				
		}
	}
	//�޸�����
		private static class BookSearch extends AbstractAction {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			//���캯��
			 BookSearch(){
				super("ͼ���ѯ", null);

			}

			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderBookQueryFrame().setSize(600,450);			
			}
		}
		//�޸�����
		private static class BookAppointment extends AbstractAction {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			//���캯��
			BookAppointment(){
				super("ͼ��ԤԼ", null);

			}

			@Override
			public void actionPerformed(ActionEvent e) {
				new BookAppointmentFrame().setSize(600, 450);				
			}
		}


}
