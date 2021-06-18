/**
 * 
 */
package com.ss.may.jb4;

/**
 * @author lukej
 *
 */
public class DoubleCheckLocking {

	/**
	 * @param args
	 */
	
	private static volatile DoubleCheckLocking instance = null;
	
	private DoubleCheckLocking() {
		
	}
	
	public static DoubleCheckLocking getInstance() {
		if(instance == null) {
			synchronized(instance){
				if(instance == null) {
					instance = new DoubleCheckLocking();
				}
			}
		}
		return instance;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
