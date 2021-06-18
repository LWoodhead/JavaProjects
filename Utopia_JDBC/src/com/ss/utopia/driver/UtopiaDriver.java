/**
 * 
 */
package com.ss.utopia.driver;

import java.sql.SQLException;
import com.ss.utopia.service.MainMenu;

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
		MainMenu menu = new MainMenu();
		menu.mainMenu();
	}
}
