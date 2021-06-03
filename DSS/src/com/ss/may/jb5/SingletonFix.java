/**
 * 
 */
package com.ss.may.jb5;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * @author lukej
 *
 */

public class SingletonFix {

	private static Connection conn = null;
	
	private static SingletonFix instance = null;
	
	public static SingletonFix getInstance() {
		if(instance == null) {
			synchronized(instance){
				if(instance == null) {
					instance = new SingletonFix();
				}
			}
		}
		return instance;
	}
	
}

