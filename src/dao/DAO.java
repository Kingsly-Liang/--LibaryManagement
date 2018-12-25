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


	//�������ݿ�
	public DAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("�����Ѽ��أ�");
			conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
			System.out.println("���ӳɹ���");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�����Ҳ���" + e.getMessage());
		} catch(SQLException e){
			System.out.println("SQLException" + e.getMessage());
		}
	}

	//�ر������ݿ������
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
	 * ����Ա��¼����
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
	 * ���ߵ�¼����
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
	 * ����Ա�޸����뷽��
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
	 * ��Ӷ��߷���
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
	 * ����Ա�޸Ķ���״̬����
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


	//��ѯ�ļ�¼�洢�����϶����� 
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

	//���Ŀ�����
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
	 * ����fine��
	 */
	public int changeFine(String card_id,String choose) {
		String sql1 = "select * from fine where card_id = '" + card_id+"';";
		String sql2 = "update fine set ispay ='" + choose + "' where card_id='" + card_id
				+ "'";

		int flag = 2;  //��ʾ���ظ���������û�����Ԫ��

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
	 * ����Ա�޸����뷽��
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
	 * ����ͼ�����
	 */
	public boolean addtype(String bktype, int days, double fk) {
		String sql = "insert into booktype(typeName,days,fk) values('"+ bktype + "',"+days+"," +fk+");";
		String sql2 = "select typeName  from booktype";


		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql2);//��ѯ�Ƿ��ظ�

			while (rs.next()) {
				String type = rs.getString("typeName");			
				if(type.equals(bktype)){
					return false;
				}
			}
			int i = stmt.executeUpdate(sql);//����ͼ�����
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
	 * ����ͼ��
	 */
	public boolean addbook(String bookID,String typeId,String bookname,String writer, 
			String translator,String publisher,String days, String price,int number,int can_lendnum) {


		try{

			stmt = conn.createStatement();			

			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sdf.parse(days);
			float p=Float.parseFloat(price);

			String sql = "insert into bookinfo(book_id,typeId,bookname,writer,translator,publisher,date,price,numbers,can_lend)";//�ر�ע�����ע��ע�⡣
			sql += "values('"+bookID+"','"+typeId+"','"+bookname+"','"+writer+"','"+translator+"','"+publisher+"','"+days+"',"+p+","+number+","+can_lendnum+")";

			int i=stmt.executeUpdate(sql);
			if(i==1){
				return true;
			}
		}catch(ParseException e2){
			JOptionPane.showMessageDialog(null,"����ʱ���ʽ������ȷΪ��xxxx-xx-xx��");
		}catch(NumberFormatException e1){
			JOptionPane.showMessageDialog(null,"����������۸񣬿ɽ��鼮����ӦΪ����");

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
	 * ͼ����Ϣ�޸�
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
			JOptionPane.showMessageDialog(null,"����ʱ���ʽ������ȷΪ��xxxx-xx-xx��");
		}catch(NumberFormatException e2){
			JOptionPane.showMessageDialog(null,"����������۸񣬿ɽ���������ӦΪ����");
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
	 * �ж�ͼ���Ƿ����
	 */
	public  boolean IfBookIdExit(String id){
		boolean right=false;
		String sql="select* from bookinfo where book_id='"+id+"'";

		try{

			stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()){

				right = true;//��ͬ��ͼ��ID����
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"�޷�������ȡ����");

		}

		return right;
	}

	/*
	 * ͼ����ģ�����ͼ����Ϣ��Ŀ������
	 */
	public  boolean BookBorrowLendNum(String id,String can_lend){

		Integer lendnums=Integer.valueOf(can_lend);
		if(lendnums==0) {
			JOptionPane.showMessageDialog(null,"��ͼ��û����");
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
			JOptionPane.showMessageDialog(null,"�޷�������ȡ����");

		}	

		return false;
	}

	/*
	 * ͼ����ģ���ӽ��ı�Ľ���ͼ����Ϣ
	 */
	public  boolean BookBorrow(String bookID,String operatorId,String reader_id,String isback, 
			String borrowDate,String backDate){			

		try{

			stmt = conn.createStatement();

			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sdf.parse(borrowDate);
			sdf.parse(backDate);

			//�ж�����Ա��ID�����ID�Ƿ����
			String sql2="select * from operator where identityCard='"+operatorId+"'";
			Statement stmt2=conn.createStatement();
			ResultSet rs2=stmt2.executeQuery(sql2);
			rs2.last();
		
			if(rs2.getRow()==0) {
				JOptionPane.showMessageDialog(null,"�ò���Ա��ID�����ڣ����������룡");
				return false;
			}
			else {			
				String sql = "insert into borrow(book_id,operatorId,reader_id,isback,borrowDate,backDate)";//�ر�ע�����ע��ע�⡣
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
						JOptionPane.showMessageDialog(null,"�ɹ���");									
					}
				return true;//����֤����ʹ��
			}
			}
		}catch(ParseException e1){
			JOptionPane.showMessageDialog(null,"ʱ���ʽ������ȷΪ��xxxx-xx-xx��");		
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null,"sql����");	
		}
		finally{
			close();
		}
		return false;
	}

	/*
	 * ���߽���֤�Ƿ����
	 */
	public  boolean IsReaderIdCardUsable(String Readerid){


		String sql="select * from reader where indentityCard='"+Readerid+"'";

		
		try{

			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			rs.last();
			if(rs.getRow()==0) {   //����֤ID������
				JOptionPane.showMessageDialog(null,"�����ڸö���ID");
				return false;
			}else {   //����֤ID����
				
				rs.beforeFirst();

				while(rs.next()) 
				{ 
					if(rs.getString("cardType").equals("����")) 
						
					 {
						Statement stmt2=conn.createStatement();
						int x=Integer.parseInt(rs.getString("hasLend"))+1;
						String sql2="update reader set hasLend="+x+" where indentityCard='"+Readerid+"'";
						int rs2=stmt2.executeUpdate(sql2);
						if(rs2==1){
							JOptionPane.showMessageDialog(null,"�ɹ�");					
						return true;//����֤����ʹ��
						}else
						{JOptionPane.showMessageDialog(null,"�޷��޸�reader��");}
					}else{
						JOptionPane.showMessageDialog(null,"�ö��߽���֤������ͣ���ʧ��ע��״̬�����ɽ���ͼ��");
						return false;
					}
				}
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"�޷�������ȡ����");

		}		
		return false;
	}

	/*
	 * ͼ����ģ�����ͼ����Ϣ��Ŀ���������黹��
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
			JOptionPane.showMessageDialog(null,"�޷�������ȡ����");

		}	

		return false;
	}

	/*
	 * �жϽ��ļ�¼�Ƿ����
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
			JOptionPane.showMessageDialog(null,"�޷�������ȡ����");

		}

		return right;
	}

	/*
	 * ͼ����ģ�ɾ�����ı�Ľ���ͼ����Ϣ(ͼ��黹)
	 */
	public  boolean BookBorrowDelete(String bookid,String backtime){

		Date nowtime;
		Date Backtime;
		try{
			/*
			 * ���ж�ͼ���Ƿ�����
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
			nowtime=sdf.parse(sdf.format(new Date()));
			// new Date()Ϊ��ȡ��ǰϵͳʱ��
			Backtime=  sdf.parse(backtime);
			//����
			if(nowtime.getTime()>Backtime.getTime()) {
				JOptionPane.showMessageDialog(null,"�����ĵ�ͼ���Ѿ����ڣ�����ɷ���");	
				return false;
			}else { //������

				stmt = conn.createStatement();

				String sql = "delete from borrow where book_id='"+bookid+"'";//�ر�ע�����ע��ע�⡣

				int i=stmt.executeUpdate(sql);
				if(i==1){
					JOptionPane.showMessageDialog(null,"ͼ��黹�ɹ�");
					return true;
				}
			}
		}catch(ParseException e1){
			JOptionPane.showMessageDialog(null,"ʱ���ʽ������ȷΪ����-��-�գ�");		
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null,"sql����");	
		}
		finally{
			close();
		}
		return false;
	}

	/*
	 * �ж�ͼ�����ڱ��޸�
	 */
	public boolean OutDateModify(String id,String name,String number,String reason)  {

		try{	      
			stmt = conn.createStatement();

			String sql = "insert into outage values ("+Integer.parseInt(id)+",'"+name+"',"+Integer.parseInt(number)+",'"+reason+"')";//�ر�ע�����ע��ע�⡣

			int i=stmt.executeUpdate(sql);
			if(i==1) {
				JOptionPane.showMessageDialog(null,"ͼ�����ڱ��޸ĳɹ�");		
				return true;
			}
		}catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null,"sql����");	
		}
		finally{
			close();
		}
		return false;
	}

	/*
	 *  �������ͼ����Ϣ�������
	 */
	public boolean addfineinfo()  {
		Date nowtime;  //��ǰʱ��
		Date Backtime; //����ʱ��
		
		String bookname = null; //�������ͼ������
		String readername = null; //��Ž��ĸ�ͼ��Ķ�����
		String readerId;
		String booktype;

		int num=0;
		//����ͼ����Ϣ��Ҫ�ӽ��ı���ͨ���ȽϹ黹ʱ�������ʱ���ȡ

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//�������ڸ�ʽ
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
//				JOptionPane.showMessageDialog(null,"û������ͼ��");	
//				return false;
//			}
			//rs.beforeFirst();
			while(rs.next()) {

				
				Backtime= (Date) sdf.parse(rs.getString("backDate"));
				System.out.println(Backtime.getDate());
				//if(nowtime.getTime()>Backtime.getTime())
				int x=nowtime.compareTo(Backtime);
				System.out.println(x);
					if(x==1){    //�ڽ��ı��в�������ͼ��
					int time=(int) (nowtime.getTime()-Backtime.getTime());//

					readerId=rs.getString("reader_id");

					String sql2="select bookname,typeId from bookinfo where book_id='"+rs.getString("book_id")+"'";

					ResultSet rs2=stmt2.executeQuery(sql2);

					while(rs2.next())
					{
						bookname=rs2.getString("bookname");//��ʾ����ͼ������
						booktype=rs2.getString("typeId");// ��ȡ�����͵�ͼ������Id;
					}			


					String sql3="select name from reader where indentityCard='"+readerId+"'";	//�Ӷ��߱���Ҷ�������
					ResultSet  rs3=stmt3.executeQuery(sql3);
					while(rs3.next()) 
					{
						readername=rs3.getString("name");//��ʾ����ͼ������
					}	

					/*
					 * �޸ķ����
					 */
					String sql1="select * from fine where book_name='"+bookname+"'";//�ڷ����鿴�ǲ����ظ���ӷ�����Ϣ
					ResultSet rs1=stmt1.executeQuery(sql1);	
					rs1.last();
					if(rs1.getRow()!=0) {	//�ҵ�˵�����ظ�����Ϣ����������					
						return true;
					}else 
					{											

						String sql4="insert into fine(name,card_id,book_name,price) values('"+readername+"','";
						sql4=sql4+readerId+"','"+bookname+"',"+30+")";
						int i=stmt4.executeUpdate(sql4);
						if(i==1) {
							num++;
							JOptionPane.showMessageDialog(null,"������Ϣ����ӵ������");	
							return true;
						}

					}
				}
				else {
					num=0;
					//JOptionPane.showMessageDialog(null,"û������ͼ��");	
					//return false;
				}

					
			}
			if(num!=0){
				return true;
			}else{
				JOptionPane.showMessageDialog(null,"û������ͼ��");
			}
		} catch(ParseException e1){
			JOptionPane.showMessageDialog(null,"ʱ���ʽ������ȷΪ����-��-�գ�");		
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null,"sql����");	
		}finally{
			close();
		}
		return false;
	}

	/**
	 * ����֤��ѯ�����߲�ѯ��������ʷ��ѯ
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
	 * ���߲�ѯ������֤��ѯ
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
	 * ���Ĳ�ѯ��������ʷ��ѯ
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
	 * ������ʷ��ѯ��������ʷ��ѯ
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
	 * ���Ĳ�ѯ
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
	 * ͼ�����
	 */
	public  boolean BookDelete(String bookID,String bookname,String number,String reason){



		try{

			stmt = conn.createStatement();						
			int num= Integer.parseInt(number);		
			String sql="delete from bookinfo where book_id='"+bookID+"'";

			int i=stmt.executeUpdate(sql);

			if(i==1){
				JOptionPane.showMessageDialog(null,"ͼ��ɾ���ɹ���");
			}	
			else {
				JOptionPane.showMessageDialog(null,"ͼ��ɾ��ʧ��");
				return false;
			}
			String sql2="insert into outage(id,name,number,reason) values('"+bookID+"','"+bookname+"',"+num+",'"+reason+"')";
			Statement stmt2=conn.createStatement();
			int i2=stmt2.executeUpdate(sql2);
			if(i2==1) {
				JOptionPane.showMessageDialog(null,"ͼ�������޸ĳɹ���");
				return true;
			}	
			else {
				JOptionPane.showMessageDialog(null,"ͼ�������޸�ʧ��");
				return false;
			}


		}catch(NumberFormatException e2){
			JOptionPane.showMessageDialog(null,"����������۸񣬿ɽ���������ӦΪ����");
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
