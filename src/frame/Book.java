package frame;

//Book.java;
//图书信息的存取
import java.sql.Date;
public class Book {
	private static String id,bookname,typeId,writer,translator,publisher;
	private static String date;
	private static int numbers,can_lendnum;
	private static float price;
	public static String getId() {
		return id;
	}
	public static void setId(String id) {
		Book.id = id;
	}
	public static String getBookname() {
		return bookname;
	}
	public static void setBookname(String bookname) {
		Book.bookname = bookname;
	}
	public static String getTypeId() {
		return typeId;
	}
	public static void setTypeId(String typeId) {
		Book.typeId = typeId;
	}
	public static String getWriter() {
		return writer;
	}
	public static void setWriter(String writer) {
		Book.writer = writer;
	}
	public static String getTranslator() {
		return translator;
	}
	public static void setTranslator(String translator) {
		Book.translator = translator;
	}
	public static String getPublisher() {
		return publisher;
	}
	public static void setPublisher(String publisher) {
		Book.publisher = publisher;
	}
	public static String getDate() {
		return date;
	}
	public static void  setDate(String publish_time) {
		Book.date = publish_time;
	}
	public static int getNumbers() {
		return numbers;
	}
	public static void setNumbers(int numbers) {
		Book.numbers = numbers;
	}
	public static int getCan_lendnum() {
		return can_lendnum;
	}
	public static void setCan_lendnum(int can_lendnum) {
		Book.can_lendnum = can_lendnum;
	}
	public static float getPrice() {
		return price;
	}
	public static void setPrice(float price) {
		Book.price = price;
	}
	public static String[] getBookinformation() {
		String[] bookinformation={id,typeId,bookname,writer,translator,				
				publisher,date,String.valueOf(price),String.valueOf(numbers),String.valueOf(can_lendnum)};
		return bookinformation;
	}
}