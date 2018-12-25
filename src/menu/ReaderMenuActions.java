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
	private static Map<String,JInternalFrame> frames;		//子窗体集合

	public static ChangePassword change_password;          //修改密码
	public static BookSearch book_search;          
	public static BookAppointment book_appointment;         


	//初始化上述功能
	static {
		//实例化

		frames = new HashMap<String, JInternalFrame>();

		change_password=new ChangePassword();          //修改密码
		book_search = new BookSearch();
		book_appointment = new BookAppointment();

	}

	//修改密码
	private static class ChangePassword extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		//构造函数
		ChangePassword(){
			super("修改密码", null);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new ReaderChangePasswdFrame().setSize(600, 450);				
		}
	}
	//修改密码
		private static class BookSearch extends AbstractAction {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			//构造函数
			 BookSearch(){
				super("图书查询", null);

			}

			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderBookQueryFrame().setSize(600,450);			
			}
		}
		//修改密码
		private static class BookAppointment extends AbstractAction {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			//构造函数
			BookAppointment(){
				super("图书预约", null);

			}

			@Override
			public void actionPerformed(ActionEvent e) {
				new BookAppointmentFrame().setSize(600, 450);				
			}
		}


}
