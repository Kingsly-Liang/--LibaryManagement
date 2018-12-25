package frame;

//BookSelect.java;
//根据图书ID选择图书
import java.sql.*;
import javax.swing.JOptionPane;
import dao.DAO;

public class BookSelectFrame{
	
	private static String dbUrl = "jdbc:mysql://localhost:3306/library";
	private static String dbUser = "root";
	private static String dbPwd = "root";
	private static Connection conn = null;
	private static Statement stmt;
	private static ResultSet rs;
	public static Book SelectBookByID(String id){
		String sql="select * from bookinfo where book_id='"+id+"'";						
		Book book=null;
		
		try{
			
		book=new Book();
		conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
		stmt=conn.createStatement();		
	    rs=stmt.executeQuery(sql);
	  
		if(rs.next()){
			book.setId(rs.getString("book_id"));
			book.setTypeId(rs.getString("typeId"));
			book.setBookname(rs.getString("bookname"));
			book.setWriter(rs.getString("writer"));
			book.setTranslator(rs.getString("translator"));
			book.setPublisher(rs.getString("publisher"));
			book.setDate(rs.getString("date"));
			book.setPrice(rs.getFloat("price"));
			book.setNumbers(rs.getInt("numbers"));
			book.setCan_lendnum(rs.getInt("can_lend"));
			}else{
				book=null;	
				
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"无法正常读取数据库！"+e.getMessage());
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null,"无法正常读取数据库！"+e.getMessage());
		}
		return book;
	}
}