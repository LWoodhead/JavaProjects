/**
 * 
 */
package com.ss.utopia.driver;

import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.domain.Airport;
import com.ss.utopia.service.AdminMenu;
import com.ss.utopia.service.AdminService;

/**
 * @author lukej
 *
 */
public class UtopiaDriver {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AdminService as = new AdminService();
		Airport air = new Airport("LAX","Los Angeles");
		Airport air2 = new Airport("BOS","Boston");
//		as.addAirport(air2);
//		as.removeAirport(air2);
		List<Airport> list = as.readAirport();
//		for(Airport a : list) {
//			System.out.println(a.toString());
//		}
		AdminMenu am = new AdminMenu();
		am.AdminMenuRunner();
	}
}
