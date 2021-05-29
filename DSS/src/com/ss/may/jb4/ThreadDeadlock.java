/**
 * 
 */
package com.ss.may.jb4;

import com.ss.may.jb3.DirectoryNames;

/**
 * @author lukej
 *
 */
public class ThreadDeadlock {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DirectoryNames a = new DirectoryNames();
		DirectoryNames b = new DirectoryNames();
		Runnable threadA = new Runnable(){
			Thread t = Thread.currentThread();
			public void run(){
				synchronized(a){
					System.out.println("Thread ID: " + t.getId() + " locked resource a");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized(b){
						System.out.println("Thread ID: " + t.getId() + " locked resource b");
					}
				}
			}
		};
		
		Runnable threadB = new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Thread t = Thread.currentThread();
				synchronized(b){
					System.out.println("Thread ID: " + t.getId() + " locked resource b");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized(a){
						System.out.println("Thread ID: " + t.getId() + " locked resource a");
					}
				}
			}
			
		};
		
		Thread t1 = new Thread(threadA);
		Thread t2 = new Thread(threadB);
		t1.start();
		t2.start();
		System.out.println("Main Exiting");
	}


	
}
