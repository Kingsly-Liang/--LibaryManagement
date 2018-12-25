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
		private static Map<String,JInternalFrame> frames;		//子窗体集合
	
		//图书管理模块
		public static BookTypeAdd  BOOKTYPE_add;            //图书类别添加
		public static BookAddAction BOOK_add;			     //新书入库
		public static BookOutAction BOOK_out;                //图书出库
		public static BookSetNumber BOOK_setNumber;		    //图书编码
		public static BookModify BOOK_modify;               //图书信息修改
		//图书流通模块
		public static BookBorrow BOOK_borrow;             //图书借阅
		public static BookReturn BOOK_return;             //图书归还
		public static BookTransfer BOOK_transfer;         //图书转移
		
		public static BookBorrowReturn BOOK_borrow_return;     //图书借还
		//统计查询模块
		public static QueryReader query_reader;                       //读者查询
		public static QueryBorrowing query_borrowing;                 //借阅查询
		public static QueryBorrowingHisty query_borrowing_histy;      //借阅历史查询
		public static QueryFineHisty query_fine_histy;               //罚款历史查询		
		public static QueryPassword query_password;                 //用户密码查询
		public static StatisticsReader statistics_reader;           //读者统计
		public static StatisticsBorrow statistics_borrow;           //借阅统计
		//系统管理模块
		public static AddUser addUser;                         //添加用户
		public static AddOperator addOperator;                         //添加用户
		public static ChangePassword change_password;          //修改密码
		public static ChangeCard changeCard;				//卡的状态改变
		public static PayFine payFine;                       //交纳罚金
		public static ReFreshFineTable reFreshFineTable;                       //交纳罚金
		
		
		//初始化上述功能
		static {
			//实例化
			
			frames = new HashMap<String, JInternalFrame>();
			
			//图书管理
			BOOKTYPE_add=new BookTypeAdd();                    //图书类别添加
			BOOK_setNumber = new BookSetNumber();				//图书编码动作
			BOOK_add = new BookAddAction();					//图书信息添加窗体动作
			BOOK_out = new BookOutAction();                //删除已有图书
			BOOK_modify = new BookModify();              //图书信息修改窗体动作
			
			//图书流通
			BOOK_borrow=new BookBorrow();              //图书借阅动作
			BOOK_return=new BookReturn();             //  图书归还动作
			BOOK_transfer=new BookTransfer();           // 图书转移动作
		
			BOOK_borrow_return=new BookBorrowReturn();  // 图书借还动作
			
			//统计查询
			query_reader=new QueryReader();                         //读者查询
		    query_borrowing=new QueryBorrowing();                   //借阅查询
			query_borrowing_histy=new QueryBorrowingHisty();        //借阅历史查询
			query_fine_histy=new QueryFineHisty();                 //罚款历史查询
			query_password=new QueryPassword();                    //用户密码查询
			statistics_reader=new StatisticsReader();              //读者统计
			statistics_borrow=new StatisticsBorrow();              //借阅统计
			
			//系统管理模块
			addUser=new AddUser();                         //添加用户
			addOperator=new AddOperator();                         //添加用户
			change_password=new ChangePassword();          //修改密码
			changeCard=new  ChangeCard();
			payFine=new  PayFine() ;                       //交纳罚金
			reFreshFineTable = new ReFreshFineTable();
			
}
                 /* * * *          图书管理模块动作              * * */
		//图书类别添加
				private static class BookTypeAdd extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					BookTypeAdd(){
						super("图书类别添加", null);
					}

					@Override
					//创建监听
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new BookTypeFrame();
					}
				}
		//图书入库
		private static class BookAddAction extends AbstractAction {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			//构造函数
			BookAddAction(){
				super("新书入库", null);
			}

			@Override
			//创建监听
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new BookAddFrame();
			}
		}
		
		//图书出库
				private static class BookOutAction extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					BookOutAction(){
						super("图书出库", null);
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookDeleteFrame();				
					}
				}
				
		//新书编码
				private static class BookSetNumber extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					BookSetNumber(){
						super("新书编码", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub				
					}
				}
		//图书信息修改
				private static class BookModify extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					BookModify(){
						super("图书信息修改", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookModifyFrame();				
					}
				}
				/* * * *          图书流通管理模块动作              * * */				
		//图书借阅
				private static class BookBorrow extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					BookBorrow(){
						super("图书借阅", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookBorrowFrame();				
					}
				}
		//图书转移
				private static class BookTransfer extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					BookTransfer(){
						super("图书转移", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookTypeModifyFrame();			
					}
				}
		//图书归还
				private static class BookReturn extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					BookReturn(){
						super("图书归还", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BookBackFrame();				
					}
				}
		
		//图书催还
				private static class BookBorrowReturn extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					BookBorrowReturn(){
						super("图书催还", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						//new AddFineInfo();
						new GetAllFine();
					}
				}
				/* * * *          统计查询模块动作              * * */		
		//读者查询
				private static class QueryReader extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					QueryReader(){
						super("读者查询", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new ReaderQueryFrame().setSize(600, 450);				
					}
				}
				
		//借阅查询
				private static class QueryBorrowing extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					QueryBorrowing(){
						super("借阅查询", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BorrowingQueryFrame().setSize(600, 450);			
					}
				}
		//借阅历史查询
				private static class QueryBorrowingHisty extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					QueryBorrowingHisty(){
						super("借阅历史查询", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new BorrowHistoryQueryFrame().setSize(600, 450);				
					}
				}
		//罚款历史查询
				private static class QueryFineHisty extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					QueryFineHisty(){
						super("罚款历史查询", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new FineHistoryQueryFrame().setSize(600, 450);				
					}
				}
										
		//用户密码查询
				private static class QueryPassword extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					QueryPassword(){
						super("用户密码查询", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new PasswordQueryFrame().setSize(600, 450);				
					}
				}
				
		//读者统计
				private static class StatisticsReader extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					StatisticsReader(){
						super("读者统计", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new StatisticsReaderFrame();				
					}
				}
				
		//借阅统计
				private static class StatisticsBorrow extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					StatisticsBorrow(){
						super("借阅统计", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new StatisticsBorrowFrame();				
					}
				}
				/* * * *          系统管理模块动作              * * */	
		//添加用户
				private static class AddUser extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					AddUser(){
						super("添加用户", null);
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

					//构造函数
					AddOperator(){
						super("添加管理员", null);
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub	
						new OperatorAddFrame().setSize(600, 450);
					}
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
						new ChangePasswdFrame().setSize(600, 450);				
					}
				}
				
				private static class ChangeCard extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					ChangeCard(){
						super("改变借阅证状态", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new CardTypeFrame().setSize(600, 450);			
					}
				}
		//交纳罚款
				private static class PayFine extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					PayFine(){
						super("交纳罚款", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new PayFineFrame().setSize(600, 450);			
					}
				}
		//刷新Fine表
				private static class ReFreshFineTable extends AbstractAction {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					//构造函数
					ReFreshFineTable(){
						super("刷新罚款名单", null);
						
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						new AddFineInfo();
					}
				}
				
}
