/**
 * 
 */
package com.ss.may.jb2;

/**
 * @author lukej
 *
 */
public class Circle implements iShape{
	private int radius;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c1 = new Circle(6);
		c1.calculateArea();
		c1.display();
	}

	public Circle(int radius) {
		this.radius = radius;
	}
	@Override
	public void calculateArea() {
		// TODO Auto-generated method stub
		double area = radius * radius * Math.PI;
		System.out.println("Area: " + area);
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println(" ****\n******\n******\n ****");
	}

}
