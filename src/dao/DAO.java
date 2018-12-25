package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class DAO {
	private String dbClassName = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/library?autoReconnect=true";
	private String dbUser = "root";
	private String dbPwd = "root";
	private Connection conn = null;
	private Statement stmt;
	private ResultSet rs;


	//连接数据库
	public DAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("驱动已加载！");
			conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
			System.out.println("连接成功！");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("驱动找不到" + e.getMessage());
		} catch(SQLException e){
			System.out.println("SQLException" + e.getMessage());
		}
	}

	//关闭与数据库的连接
	public void close() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}


	/*
	 * 管理员登录方法
	 */
	public boolean checkadmin(String cardid, String cardpassword) {
		String sql = "select *  from operator where identityCard='" + cardid
				+ "' and password='" + cardpassword + "'and type=1";

		try {
			//stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String admincardid = rs.getString("identityCard");
				String admincardpassword = rs.getString("password");

				if((cardid.equals(admincardid)) && (cardpassword.equals(admincardpassword))){
					return true;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			close();
		}

		return false;
	}


	/*
	 * 读者登录方法
	 */
	public boolean checkreader(String cardid, String cardpassword) {
		String sql = "select *  from reader where indentityCard='" + cardid
				+ "' and password='" + cardpassword + "'";

		try {
			//stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String readercardid = rs.getString("indentityCard");
				String readercardpassword = rs.getString("password");

				if((cardid.equals(readercardid)) && (cardpassword.equals(readercardpassword))){
					return true;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			close();
		}

		return false;
	}

	/*
	 * 管理员修改密码方法
	 */
	public boolean changePasswd(String cardid, String oldpassword, String newpassword) {
		String sql = "update operator set password ='" + newpassword + "' where identityCard='" + cardid
				+ "' and password='" + oldpassword + "'";

		try {
			//stmt = conn.createStatement();
			int i = stmt.executeUpdate(sql);

			if(i == 1){
				return true;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			close();
		}

		return false;
	}

	/*
	 * 添加读者方法
	 */
	public int addReader(String name, String sex, String age, String indentityCard, String date, int maxNum, String tel, String password, String zj,String bztime, String cardType) {
		String sql = "insert into reader (name,sex,age,indentityCard,date,maxNum,tel,password,zj,bztime,cardType) ";
		sql += "values('"+name+"','"+sex+"','"+age+"','"+indentityCard+"','"+date+"','"+maxNum+"','"+tel+"','"+password+"','"+zj+"','"+bztime+"','"+cardType+"')";

		String sql1 = "select * from reader";
		int i = 1;
		try {
			//stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				String cardid = rs.getString("indentityCard");
				System.out.println(cardid);
				if((cardid.equals(indentityCard))){
					i = 2;
				}
			}
			if(i == 2){
				return 2;
			}else if(i == 1){
				int j = stmt.executeUpdate(sql);
				if(j == 1){
					return 1;
				}
			}


		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			//close();
		}

		return 0;
	}

	/*
	 * 管理员修改读者状态方法
	 */
	public boolean changeCardType(String cardid, String name, String type) {
		String sql = "update reader set cardType ='" + type + "' where indentityCard='" + cardid
				+ "' and name='" + name + "'";

		System.out.println(sql);

		try {
			//stmt = conn.createStatement();
			int i = stmt.executeUpdate(sql);

			if(i == 1){
				return true;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			close();
		}

		return false;
	}


	//查询的记录存储到集合对象中 
	public ResultSet getRS(String sql){  
		try{   
			//stmt=conn.createStatement();   
			rs=stmt.executeQuery(sql);  
		}
		catch(Exception evt){   
			evt.printStackTrace();  
		}  
		return rs; 
	}

	//借阅卡查重
	public boolean searchCopy(String sql,String card_id) {

		try {
			//stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String cardid = rs.getString("card_id");
				//System.out.println(cardid);
				if((cardid.equals(card_id))){
					return true;
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			close();
		}

		return false;
	}

	/**
	 * 更改fine表
	 */
	public int changeFine(String card_id,String choose) {
		String sql1 = "select * from fine where card_id = '" + card_id+"';";
		String sql2 = "update fine set ispay ='" + choose + "' where card_id='" + card_id
				+ "'";

		int flag = 2;  //表示无重复，即表中没有这个元素

		try {

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
					return 1;
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			//close();
		}

		return 0;
	}

	/*
	 * 管理员修改密码方法
	 */
	public boolean readerchangePasswd(String cardid, String oldpassword, String newpassword) {
		String sql = "update reader set password ='" + newpassword + "' where indentityCard='" + cardid
				+ "' and password='" + oldpassword + "'";
		System.out.println(sql);

		try {
			//stmt = conn.createStatement();
			int i = stmt.executeUpdate(sql);

			if(i == 1){
				return true;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			close();
		}

		return false;
	}



	public int addBookAppointment(String card_id, String book_id, String date) {
		String sql = "insert into bespeak (reader_card_id,book_id,date)";
		sql += "values('"+card_id+"','"+book_id+"','"+date+"')";

		try{

			int k = stmt.executeUpdate(sql);
			if(k == 1){
				return 1;
			}


		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			//close();
		}

		return 0;
	}

	/*
	 * 增加图书类别
	 */
	public boolean addtype(String bktype, int days, double fk) {
		String sql = "insert into booktype(typeName,days,fk) values('"+ bktype + "',"+days+"," +fk+");";
		String sql2 = "select typeName  from booktype";


		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql2);//查询是否重复

			while (rs.next()) {
				String type = rs.getString("typeName");			
				if(type.equals(bktype)){
					return false;
				}
			}
			int i = stmt.executeUpdate(sql);//插入图书类别
			if(i == 1){
				return true;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			close();
		}
		return false;
	}



	/*
	 * 增加图书
	 */
	public boolean addbook(String bookID,String typeId,String bookname,String writer, 
			String translator,String publisher,String days, String price,int number,int can_lendnum) {


		try{

			stmt = conn.createStatement();			

			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sdf.parse(days);
			float p=Float.parseFloat(price);

			String sql = "insert into bookinfo(book_id,typeId,bookname,writer,translator,publisher,date,price,numbers,can_lend)";//特别注意这里，注意注意。
			sql += "values('"+bookID+"','"+typeId+"','"+bookname+"','"+writer+"','"+translator+"','"+publisher+"','"+days+"',"+p+","+number+","+can_lendnum+")";

			int i=stmt.executeUpdate(sql);
			if(i==1){
				return true;
			}
		}catch(ParseException e2){
			JOptionPane.showMessageDialog(null,"出版时间格式错误，正确为（xxxx-xx-xx）");
		}catch(NumberFormatException e1){
			JOptionPane.showMessageDialog(null,"库存数量，价格，可借书籍数，应为数字");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			close();
		}
		return false;
	}

	/*
	 * 图书信息修改
	 */
	public  boolean savebook(String bookID,String typeID,String bookname,String writer, 
			String translator,String publisher,String days, String price,String number,String can_lendnum,String id){


		try{

			stmt = conn.createStatement();

			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sdf.parse(days);
			float p=Float.parseFloat(price);
			int num= Integer.parseInt(number);
			int lend=Integer.parseInt(can_lendnum);
			String sql="update bookinfo set book_id='"+bookID+"',typeId='"+typeID+"',bookname='"+bookname+"',writer='"+writer;
			sql=sql+"',translator='"+translator+"',publisher='"+publisher+"',date='"+days;
			sql=sql+"' , price="+p+" ,numbers='"+num+"', can_lend='"+lend+"' where book_id='"+id+"'";

			int i=stmt.executeUpdate(sql);
			if(i==1){
				return true;

			}
		}catch(ParseException e1){
			JOptionPane.showMessageDialog(null,"出版时间格式错误，正确为（xxxx-xx-xx）");
		}catch(NumberFormatException e2){
			JOptionPane.showMessageDialog(null,"库存数量，价格，可借阅数错误，应为数字");
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		finally{
			close();
		}
		return false;
	}

	/*
	 * 判断图书是否存在
	 */
	public  boolean IfBookIdExit(String id){
		boolean right=false;
		String sql="select* from bookinfo where book_id='"+id+"'";

		try{

			stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()){

				right = true;//相同的图书ID存在
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"无法正常读取数据");

		}

		return right;
	}

	/*
	 * 图书借阅，减少图书信息表的库存数量
	 */
	public  boolean BookBorrowLendNum(String id,String can_lend){

		Integer lendnums=Integer.valueOf(can_lend);
		if(lendnums==0) {
			JOptionPane.showMessageDialog(null,"该图书没有了");
			return false;
		}
		else {
			lendnums--;
		}

		String sql="update bookinfo set can_lend='"+lendnums+"' where book_id='"+id+"'";
		System.out.println(lendnums);
		try{

			stmt = conn.createStatement();
			int i=stmt.executeUpdate(sql);
			if(i==1){
				return true;
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"无法正常读取数据");

		}	

		return false;
	}

	/*
	 * 图书借阅，添加借阅表的借阅图书信息
	 */
	public  boolean BookBorrow(String bookID,String operatorId,String reader_id,String isback, 
			String borrowDate,String backDate){			

		try{

			stmt = conn.createStatement();

			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sdf.parse(borrowDate);
			sdf.parse(backDate);

			//判读操作员的ID或读者ID是否存在
			String sql2="select * from operator where identityCard='"+operatorId+"'";
			Statement stmt2=conn.createStatement();
			ResultSet rs2=stmt2.executeQuery(sql2);
			rs2.last();
		
			if(rs2.getRow()==0) {
				JOptionPane.showMessageDialog(null,"该操作员的ID不存在，请重新输入！");
				return false;
			}
			else {			
				String sql = "insert into borrow(book_id,operatorId,reader_id,isback,borrowDate,backDate)";//特别注意这里，注意注意。
				sql += " values('"+bookID+"','"+operatorId+"','"+reader_id+"','"+isback+"','"+borrowDate+"','"+backDate+"')";

				int i=stmt.executeUpdate(sql);
				if(i==1){
					String sql3="select * from bookinfo where book_id='"+bookID+"'";
					
					Statement stmt3=conn.createStatement();
					ResultSet rs3=stmt3.executeQuery(sql3);
					rs3.next();
					int x=Integer.parseInt(rs3.getString("can_lend"))-1;
					System.out.println(x);
					
					Statement stmt4=conn.createStatement();
					String sql4="update bookinfo set can_lend="+x+" where book_id='"+bookID+"'";
					int rs4=stmt4.executeUpdate(sql4);
					if(rs4==1){
						JOptionPane.showMessageDialog(null,"成功了");									
					}
				return true;//借阅证可以使用
			}
			}
		}catch(ParseException e1){
			JOptionPane.showMessageDialog(null,"时间格式错误，正确为（xxxx-xx-xx）");		
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null,"sql错误）");	
		}
		finally{
			close();
		}
		return false;
	}

	/*
	 * 读者借阅证是否可用
	 */
	public  boolean IsReaderIdCardUsable(String Readerid){


		String sql="select * from reader where indentityCard='"+Readerid+"'";

		
		try{

			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			rs.last();
			if(rs.getRow()==0) {   //借阅证ID不存在
				JOptionPane.showMessageDialog(null,"不存在该读者ID");
				return false;
			}else {   //借阅证ID存在
				
				rs.beforeFirst();

				while(rs.next()) 
				{ 
					if(rs.getString("cardType").equals("正常")) 
						
					 {
						Statement stmt2=conn.createStatement();
						int x=Integer.parseInt(rs.getString("hasLend"))+1;
						String sql2="update reader set hasLend="+x+" where indentityCard='"+Readerid+"'";
						int rs2=stmt2.executeUpdate(sql2);
						if(rs2==1){
							JOptionPane.showMessageDialog(null,"成功");					
						return true;//借阅证可以使用
						}else
						{JOptionPane.showMessageDialog(null,"无法修改reader表");}
					}else{
						JOptionPane.showMessageDialog(null,"该读者借阅证处于暂停或挂失或注销状态，不可借阅图书");
						return false;
					}
				}
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"无法正常读取数据");

		}		
		return false;
	}

	/*
	 * 图书借阅，增加图书信息表的库存数量（归还）
	 */
	public  boolean BookBack(String id,String can_lend){

		Integer lendnums=Integer.valueOf(can_lend);
		lendnums++;

		String sql="update bookinfo set can_lend='"+lendnums+"' where book_id='"+id+"'";

		try{

			stmt = conn.createStatement();
			int i=stmt.executeUpdate(sql);
			if(i==1){
				return true;
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"无法正常读取数据");

		}	

		return false;
	}

	/*
	 * 判断借阅记录是否存在
	 */
	//TODO Auto-generated catch block
	public  boolean IfBookBorrowExit(String id){
		boolean right=false;
		String sql="select* from borrow where book_id='"+id+"'";

		try{

			stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()){

				right = true;
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"无法正常读取数据");

		}

		return right;
	}

	/*
	 * 图书借阅，删除借阅表的借阅图书信息(图书归还)
	 */
	public  boolean BookBorrowDelete(String bookid,String backtime){

		Date nowtime;
		Date Backtime;
		try{
			/*
			 * 先判断图书是否逾期
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			nowtime=sdf.parse(sdf.format(new Date()));
			// new Date()为获取当前系统时间
			Backtime=  sdf.parse(backtime);
			//逾期
			if(nowtime.getTime()>Backtime.getTime()) {
				JOptionPane.showMessageDialog(null,"您借阅的图书已经逾期，请缴纳罚款");	
				return false;
			}else { //不逾期

				stmt = conn.createStatement();

				String sql = "delete from borrow where book_id='"+bookid+"'";//特别注意这里，注意注意。

				int i=stmt.executeUpdate(sql);
				if(i==1){
					JOptionPane.showMessageDialog(null,"图书归还成功");
					return true;
				}
			}
		}catch(ParseException e1){
			JOptionPane.showMessageDialog(null,"时间格式错误，正确为（年-月-日）");		
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null,"sql错误）");	
		}
		finally{
			close();
		}
		return false;
	}

	/*
	 * 判断图书逾期表修改
	 */
	public boolean OutDateModify(String id,String name,String number,String reason)  {

		try{	      
			stmt = conn.createStatement();

			String sql = "insert into outage values ("+Integer.parseInt(id)+",'"+name+"',"+Integer.parseInt(number)+",'"+reason+"')";//特别注意这里，注意注意。

			int i=stmt.executeUpdate(sql);
			if(i==1) {
				JOptionPane.showMessageDialog(null,"图书逾期表修改成功");		
				return true;
			}
		}catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null,"sql错误）");	
		}
		finally{
			close();
		}
		return false;
	}

	/*
	 *  添加逾期图书信息到罚款表
	 */
	public boolean addfineinfo()  {
		Date nowtime;  //当前时间
		Date Backtime; //逾期时间
		
		String bookname = null; //存放逾期图书名称
		String readername = null; //存放借阅该图书的读者名
		String readerId;
		String booktype;

		int num=0;
		//逾期图书信息需要从借阅表中通过比较归还时间和现在时间获取

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//设置日期格式
			nowtime=sdf.parse(sdf.format(new Date()));
			stmt = conn.createStatement();
			Statement stmt1 = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			Statement stmt3 = conn.createStatement();
			Statement stmt4 = conn.createStatement();

			System.out.println(nowtime.getYear());
			System.out.println(nowtime.getDate());
			System.out.println(nowtime.getMonth());
			int year=nowtime.getYear();
			int month=nowtime.getMonth();
			int date=nowtime.getDate();
			
			String sql="select * from borrow";

			rs=stmt.executeQuery(sql);	
//			rs.last();
//			if(rs.getRow()==0) {
//				JOptionPane.showMessageDialog(null,"没有逾期图书");	
//				return false;
//			}
			//rs.beforeFirst();
			while(rs.next()) {

				
				Backtime= (Date) sdf.parse(rs.getString("backDate"));
				System.out.println(Backtime.getDate());
				//if(nowtime.getTime()>Backtime.getTime())
				int x=nowtime.compareTo(Backtime);
				System.out.println(x);
					if(x==1){    //在借阅表中查找逾期图书
					int time=(int) (nowtime.getTime()-Backtime.getTime());//

					readerId=rs.getString("reader_id");

					String sql2="select bookname,typeId from bookinfo where book_id='"+rs.getString("book_id")+"'";

					ResultSet rs2=stmt2.executeQuery(sql2);

					while(rs2.next())
					{
						bookname=rs2.getString("bookname");//显示逾期图书名称
						booktype=rs2.getString("typeId");// 获取该类型的图书类型Id;
					}			


					String sql3="select name from reader where indentityCard='"+readerId+"'";	//从读者表查找读者名字
					ResultSet  rs3=stmt3.executeQuery(sql3);
					while(rs3.next()) 
					{
						readername=rs3.getString("name");//显示逾期图书名称
					}	

					/*
					 * 修改罚款表
					 */
					String sql1="select * from fine where book_name='"+bookname+"'";//在罚款表查看是不是重复添加罚款信息
					ResultSet rs1=stmt1.executeQuery(sql1);	
					rs1.last();
					if(rs1.getRow()!=0) {	//找到说明有重复的信息，不做处理					
						return true;
					}else 
					{											

						String sql4="insert into fine(name,card_id,book_name,price) values('"+readername+"','";
						sql4=sql4+readerId+"','"+bookname+"',"+30+")";
						int i=stmt4.executeUpdate(sql4);
						if(i==1) {
							num++;
							JOptionPane.showMessageDialog(null,"逾期信息已添加到罚款表");	
							return true;
						}

					}
				}
				else {
					num=0;
					//JOptionPane.showMessageDialog(null,"没有逾期图书");	
					//return false;
				}

					
			}
			if(num!=0){
				return true;
			}else{
				JOptionPane.showMessageDialog(null,"没有逾期图书");
			}
		} catch(ParseException e1){
			JOptionPane.showMessageDialog(null,"时间格式错误，正确为（年-月-日）");		
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null,"sql错误）");	
		}finally{
			close();
		}
		return false;
	}

	/**
	 * 借阅证查询，读者查询，借阅历史查询
	 */
	public int SearchCardID(String card_id){
		String sql = "select * from reader where indentityCard = '" + card_id+"';";
		int flag = 0;

		try{
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String cardid = rs.getString("indentityCard");
				if(cardid.equals(card_id)){
					flag = 1;
					break;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}
	/**
	 * 读者查询，借阅证查询
	 */
	public int SearchName(String name1){
		String sql = "select * from reader where name like '%"+name1+"%';";
		int flag = 0;
		try{
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String name2 = rs.getString("name");
				if(name2!=null){
					flag = 1;
					break;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}
	/**
	 * 借阅查询，罚款历史查询
	 */
	public int SearchIndentityCard(String cardid){
		String sql = "select * from reader where indentityCard = '" + cardid+"';";
		int flag = 0;

		try{
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String cardid1 = rs.getString("indentityCard");
				if(cardid1.equals(cardid)){
					flag = 1;
					break;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}
	/**
	 * 借阅历史查询，罚款历史查询
	 */
	public int SearchBookid(String bookid1){
		String sql = "select * from bookInfo where book_id = '" + bookid1+"';";
		int flag = 0;

		try{
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String bookid2 = rs.getString("book_id");
				if(bookid1.equals(bookid2)){
					flag = 1;
					break;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}

	/**
	 * 借阅查询
	 */
	public int SearchBookName(String name1){
		String sql = "select * from bookinfo where bookname like '%"+name1+"%';";
		int flag = 0;
		try{
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String name2 = rs.getString("bookname");
				if(name2!=null){
					flag = 1;
					break;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}


	/*
	 * 图书出库
	 */
	public  boolean BookDelete(String bookID,String bookname,String number,String reason){



		try{

			stmt = conn.createStatement();						
			int num= Integer.parseInt(number);		
			String sql="delete from bookinfo where book_id='"+bookID+"'";

			int i=stmt.executeUpdate(sql);

			if(i==1){
				JOptionPane.showMessageDialog(null,"图书删除成功！");
			}	
			else {
				JOptionPane.showMessageDialog(null,"图书删除失败");
				return false;
			}
			String sql2="insert into outage(id,name,number,reason) values('"+bookID+"','"+bookname+"',"+num+",'"+reason+"')";
			Statement stmt2=conn.createStatement();
			int i2=stmt2.executeUpdate(sql2);
			if(i2==1) {
				JOptionPane.showMessageDialog(null,"图书出库表修改成功！");
				return true;
			}	
			else {
				JOptionPane.showMessageDialog(null,"图书出库表修改失败");
				return false;
			}


		}catch(NumberFormatException e2){
			JOptionPane.showMessageDialog(null,"库存数量，价格，可借阅数错误，应为数字");
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		finally{
			close();
		}
		return false;
	}
	
	public int addOperator(String name, String sex, String age, String indentityCard, String date, String tel, String password) {
		String sql = "insert into operator (name,sex,age,identityCard,workdate,tel,password) ";
		sql += "values('"+name+"','"+sex+"','"+age+"','"+indentityCard+"','"+date+"','"+tel+"','"+password+"')";

		String sql1 = "select * from operator";
		int i = 1;
		try {
			//stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				String cardid = rs.getString("identityCard");
				System.out.println(cardid);
				if((cardid.equals(indentityCard))){
					i = 2;
				}
			}
			if(i == 2){
				return 2;
			}else if(i == 1){
				int j = stmt.executeUpdate(sql);
				if(j == 1){
					return 1;
				}
			}


		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			//close();
		}

		return 0;
	}
}
