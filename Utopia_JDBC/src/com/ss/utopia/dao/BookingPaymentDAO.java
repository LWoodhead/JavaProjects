/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.BookingPayment;

/**
 * @author lukej
 *
 */
public class BookingPaymentDAO extends TemplateDAO<BookingPayment>{

	public BookingPaymentDAO(Connection conn) {
		super(conn);
	}

	public void addBookingPayment(BookingPayment bp) throws ClassNotFoundException, SQLException {
		save("insert into booking_payment (booking_id,stripe_id,refunded) values (?,?,?)",
				new Object[] {bp.getBookingId(),bp.getStripeId(),bp.getRefunded()});
	}
	
	public void removeBookingPayment(BookingPayment bp) throws ClassNotFoundException, SQLException {
		delete("delete from booking_payment where booking_id = ?",new Object[] {bp.getBookingId()});
	}
	
	public void updateBookingPayment(BookingPayment bp) throws ClassNotFoundException, SQLException {
		update("update booking_payment set stripe_id = ?, refunded = ? where booking_id = ?",
				new Object[] {bp.getStripeId(),bp.getRefunded(),bp.getBookingId()});
	}
	
	public List<BookingPayment> readAllBookingPayments() throws ClassNotFoundException, SQLException {
		return read("select * from booking_payment",null);
	}
	
	@Override
	public List<BookingPayment> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingPayment> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new BookingPayment(rs.getInt("booking_id"),rs.getString("stripe_id"),rs.getInt("refunded")));
		}
		return all;
	}
}
