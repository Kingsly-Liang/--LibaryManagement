package menu;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;

import frame.AddFineInfo;
import frame.BookAddFrame;
import frame.BookBackFrame;
import frame.BookBorrowFrame;
import frame.BookDeleteFrame;
import frame.BookModifyFrame;
import frame.BookTypeFrame;
import frame.BookTypeModifyFrame;
import frame.BorrowHistoryQueryFrame;
import frame.BorrowingQueryFrame;
import frame.CardTypeFrame;
import frame.ChangePasswdFrame;
import frame.FineHistoryQueryFrame;
import frame.GetAllFine;
import frame.MainUI;
import frame.OperatorAddFrame;
import frame.PasswordQueryFrame;
import frame.PayFineFrame;
import frame.ReaderAddFrame;
import frame.ReaderQueryFrame;
import frame.StatisticsBorrowFrame;
import frame.StatisticsReaderFrame;




//import testMenu.MainDialog;


public class MenuActions {
		private static Map<String,JInternalFrame> frames;		//�Ӵ��弯��
	
		//ͼ�����ģ��
		public static BookTypeAdd  BOOKTYPE_add;            //ͼ��������
		public static BookAddAction BOOK_add;			     //�������
		public static BookOutAction BOOK_out;                //ͼ�����
		public static BookSetNumber BOOK_setNumber;		    //ͼ�����
		public static BookModify BOOK_modify;               //ͼ����Ϣ�޸�
		//ͼ����ͨģ��
		public static BookBorrow BOOK_borrow;             //ͼ�����
		public static BookReturn BOOK_return;             //ͼ��黹
		public static BookTransfer BOOK_transfer;         //ͼ��ת��
		
		public static BookBorrowReturn BOOK_borrow_return;     //ͼ��軹
		//ͳ�Ʋ�ѯģ��
		public static QueryReader query_reader;                       //���߲�ѯ
		public static QueryBorrowing query_borrowing;                 //���Ĳ�ѯ
		public static QueryBorrowingHisty query_borrowing_histy;      //������ʷ��ѯ
		public static QueryFineHisty query_fine_histy;               //������ʷ��ѯ		
		public static QueryPassword query_password;                 //�û������ѯ
		public static StatisticsReader statistics_reader;           //����ͳ��
		public static StatisticsBorrow statistics_borrow;           //����ͳ��
		//ϵͳ����ģ��
		public static AddUser addUser;                         //����û�
		public static AddOperator addOperator;                         //����û�
		public static ChangePassword change_password;          //�޸�����
		public static ChangeCard changeCard;				//����״̬�ı�
		public static PayFine payFine;                       //���ɷ���
		public static ReFreshFineTable reFreshFineTable;                       //���ɷ���
		
		
		//��ʼ����������
		static {
			//ʵ����
			
			frames = new HashMap<String, JInternalFrame>();
			
			//ͼ�����
			BOOKTYPE_add=new BookTypeAdd();                    //ͼ��������
			BOOK_setNumber = new BookSetNumber();				//ͼ����붯��
			BOOK_add = new BookAddAction();					//ͼ����Ϣ��Ӵ��嶯��
			BOOK_out = new BookOutAction();                //ɾ������ͼ��
			BOOK_modify = new BookModify();              //ͼ����Ϣ�޸Ĵ��嶯��
			
			//ͼ����ͨ
			BOOK_borrow=new BookBorrow();              //ͼ����Ķ���
			BOOK_return=new BookReturn();             //  ͼ��黹����
			BOOK_transfer=new BookTransfer();           // ͼ��ת�ƶ���
		
			BOOK_borrow_return=new BookBorrowReturn();  // ͼ��軹����
			
			//ͳ�Ʋ�ѯ
			query_reader=new QueryReader();                         //���߲�ѯ
		    query_borrowing=new QueryBorrowing();                   //���Ĳ�ѯ
			query_borrowing_histy=new QueryBorrowingHisty();        //������ʷ��ѯ
			query_fine_histy=new QueryFineHisty();                 //������ʷ��ѯ
			query_password=new QueryPassword();                    //�û������ѯ
			statistics_reader=new StatisticsReader();              //����ͳ��
			statistics_borrow=new StatisticsBorrow();              //����ͳ��
			
			//ϵͳ����ģ��
			addUser=new AddUser();                         //����û�
			addOperator=new AddOperator();                         //����û�
			change_password=new ChangePassword();          //�޸�����
			changeCard=new  ChangeCard();
			payFine=new  PayFine() ;                       //���ɷ���
			reFreshFineTable = new ReFreshFineTable();
			
}
                 /* * * *          ͼ�����ģ�鶯��              * * */
		//ͼ��������
				private static class BookTypeAdd extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					BookTypeAdd(){
						super("ͼ��������", null);
					}

					@Override
					//��������
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new BookTypeFrame();
					}
				}
		//ͼ�����
		private static class BookAddAction extends AbstractAction {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			//���캯��
			BookAddAction(){
				super("�������", null);
			}

			@Override
			//��������
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new BookAddFrame();
			}
		}
		
		//ͼ�����
				private static class BookOutAction extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					BookOutAction(){
						super("ͼ�����", null);
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookDeleteFrame();				
					}
				}
				
		//�������
				private static class BookSetNumber extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					BookSetNumber(){
						super("�������", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub				
					}
				}
		//ͼ����Ϣ�޸�
				private static class BookModify extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					BookModify(){
						super("ͼ����Ϣ�޸�", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookModifyFrame();				
					}
				}
				/* * * *          ͼ����ͨ����ģ�鶯��              * * */				
		//ͼ�����
				private static class BookBorrow extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					BookBorrow(){
						super("ͼ�����", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookBorrowFrame();				
					}
				}
		//ͼ��ת��
				private static class BookTransfer extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					BookTransfer(){
						super("ͼ��ת��", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookTypeModifyFrame();			
					}
				}
		//ͼ��黹
				private static class BookReturn extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					BookReturn(){
						super("ͼ��黹", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookBackFrame();				
					}
				}
		
		//ͼ��߻�
				private static class BookBorrowReturn extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					BookBorrowReturn(){
						super("ͼ��߻�", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						//new AddFineInfo();
						new GetAllFine();
					}
				}
				/* * * *          ͳ�Ʋ�ѯģ�鶯��              * * */		
		//���߲�ѯ
				private static class QueryReader extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					QueryReader(){
						super("���߲�ѯ", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new ReaderQueryFrame().setSize(600, 450);				
					}
				}
				
		//���Ĳ�ѯ
				private static class QueryBorrowing extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					QueryBorrowing(){
						super("���Ĳ�ѯ", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BorrowingQueryFrame().setSize(600, 450);			
					}
				}
		//������ʷ��ѯ
				private static class QueryBorrowingHisty extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					QueryBorrowingHisty(){
						super("������ʷ��ѯ", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BorrowHistoryQueryFrame().setSize(600, 450);				
					}
				}
		//������ʷ��ѯ
				private static class QueryFineHisty extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					QueryFineHisty(){
						super("������ʷ��ѯ", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new FineHistoryQueryFrame().setSize(600, 450);				
					}
				}
										
		//�û������ѯ
				private static class QueryPassword extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					QueryPassword(){
						super("�û������ѯ", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new PasswordQueryFrame().setSize(600, 450);				
					}
				}
				
		//����ͳ��
				private static class StatisticsReader extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					StatisticsReader(){
						super("����ͳ��", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new StatisticsReaderFrame();				
					}
				}
				
		//����ͳ��
				private static class StatisticsBorrow extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					StatisticsBorrow(){
						super("����ͳ��", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new StatisticsBorrowFrame();				
					}
				}
				/* * * *          ϵͳ����ģ�鶯��              * * */	
		//����û�
				private static class AddUser extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					AddUser(){
						super("����û�", null);
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub	
						new ReaderAddFrame().setSize(600, 450);
					}
				}
				
				private static class AddOperator extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					AddOperator(){
						super("��ӹ���Ա", null);
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub	
						new OperatorAddFrame().setSize(600, 450);
					}
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
						new ChangePasswdFrame().setSize(600, 450);				
					}
				}
				
				private static class ChangeCard extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					ChangeCard(){
						super("�ı����֤״̬", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new CardTypeFrame().setSize(600, 450);			
					}
				}
		//���ɷ���
				private static class PayFine extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					PayFine(){
						super("���ɷ���", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new PayFineFrame().setSize(600, 450);			
					}
				}
		//ˢ��Fine��
				private static class ReFreshFineTable extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//���캯��
					ReFreshFineTable(){
						super("ˢ�·�������", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new AddFineInfo();
					}
				}
				
}
