/**
 * 
 */
package com.ss.march.lib;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lukej
 *
 */
public class LibraryPublisherDAO implements LibraryDAO<LibraryPublisher> {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LibraryPublisherDAO d= new LibraryPublisherDAO();
		for(LibraryPublisher t: d.getAll()) {
			System.out.println(t.toString());
		}
		LibraryPublisher l = new LibraryPublisher(3, "Oxford University Press", "Oxford, EN", "5689-5469-6588");
		String[] update = new String[3];
		update[0] = "ben";
		update[1] = "hen";
		update[2] = "lend";
//		d.update(l, update);
//		d.save(l);
//		d.delete(l);
		for(LibraryPublisher t: d.getAll()) {
			System.out.println(t.toString());
		}

	}

	@Override
	public List<LibraryPublisher> getAll() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_publisher");
		ResultSet rs = pstmt.executeQuery(); 
		List<LibraryPublisher> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new LibraryPublisher(rs.getInt("publisherId"),rs.getString("publisherName"),rs.getString("publisherAddress"),rs.getString("publisherPhone")));
		}
		return all;
	}

	@Override
	public void save(LibraryPublisher t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)");
		pstmt.setString(1, t.getPublisherName());
		pstmt.setString(2, t.getPublisherAddress());
		pstmt.setString(3, t.getPublisherPhone());
		pstmt.executeUpdate(); 
	}

	@Override
	public void update(LibraryPublisher t, String[] params) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_publisher set publisherName = ?,publisherAddress = ?,publisherPhone = ? where publisherId = ?");
		pstmt.setString(1,params[0]);
		pstmt.setString(2,params[1]);
		pstmt.setString(3,params[2]);
        pstmt.setInt(4, t.getPublisherId());
		pstmt.executeUpdate(); 
	}

	@Override
	public void delete(LibraryPublisher t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_publisher where publisherId = ?");
		pstmt.setInt(1, t.getPublisherId());
		pstmt.executeUpdate(); 
	}

}
