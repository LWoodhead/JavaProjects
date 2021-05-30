package com.ss.may.jb4;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class ProducerConsumer {
	private static int production_target = 10;
	public static Semaphore createdItems;
	public static Semaphore spaceLeft;
	public static Semaphore mutex;
	public static Vector<Integer> buffer = new Vector<Integer>();
	
	public static void main(String args[]){
		buffer = new Vector<Integer>();
//		final Semaphore createdItems;
//		final Semaphore spaceLeft;
//		final Semaphore mutex;
		createdItems = new Semaphore(0);
		spaceLeft = new Semaphore(10);	
		mutex = new Semaphore(1);
		Runnable producer = new Runnable(){
	
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Random rand = new Random();
				int count = 0;
				while(count < production_target) {
					count++;
					Integer target_num = rand.nextInt(101);
					try {
						spaceLeft.acquire();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						mutex.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					buffer.add(target_num);
					System.out.println("Produced: " + target_num);
					mutex.release();
					createdItems.release();
				}
				
			}
			
		};
		
		Runnable consumer = new Runnable() {
	
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				System.out.println("in consumer");
				Integer target_num = 0;
				int count = 0;
				while(count < production_target) {
					count++; 
					try {
						createdItems.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						mutex.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					target_num = buffer.lastElement();
					buffer.remove(buffer.size()-1);
					System.out.println("Consumed: " + target_num);
					mutex.release();
					spaceLeft.release();
				}
			}
			
		};
		Thread con = new Thread(consumer);
		Thread prod = new Thread(producer);
		con.start();
		prod.start();
	}
}
