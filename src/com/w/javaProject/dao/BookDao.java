package com.w.javaProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.w.javaProject.polo.Book;
import com.w.javaProject.polo.BookBean;

public class BookDao {
	private static final String DB_DEIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
	private static final String DB_NAME = "Books";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "1";
	private static Connection connection = null;
	
	public static String query() throws SQLException, ClassNotFoundException {
		PreparedStatement ps;
		Gson gson = new Gson();
		List<Book> bookList = new ArrayList<>();
		
		Class.forName(DB_DEIVER);
		connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
		ps = connection.prepareStatement("SELECT * FROM books");
		// ps.executeUpdate()
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
//			System.out.println(rs.getInt("Bid"));
//			System.out.println(rs.getString("Bname"));
//			System.out.println(rs.getInt("Bnumber"));
			Book book = new Book();
			book.setBid(rs.getInt("Bid"));
			book.setBname(rs.getString("Bname"));
			book.setBnumber(rs.getInt("Bnumber"));
			bookList.add(book);
//			System.out.println(book);
//			System.out.println(gson.toJson(book));
		}
//		System.out.println(bookList);
		BookBean bookBean = new BookBean();
		bookBean.setRows(bookList);
		return gson.toJson(bookBean);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		query();
	}

}
