/**
 * 
 */
package com.ss.may.jb2;

/**
 * @author lukej
 *
 */
public class Rectangle implements iShape{
	
	private int height;
	private int width;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle r1 = new Rectangle(5,4);
		r1.calculateArea();
		r1.display();
	}
	
	public Rectangle(int rectHeight, int rectWidth) {
		height = rectHeight;
		width = rectWidth;
	}

	@Override
	public void calculateArea() {
		// TODO Auto-generated method stub
		double area = height * width;
		System.out.println("area is: " + area);
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Rectangle:");
		for(int i=0;i < height; i++) {
			for(int j=0;j < width; j++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
		
	}

}
