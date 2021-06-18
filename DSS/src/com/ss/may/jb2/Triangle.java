package com.ss.may.jb2;

public class Triangle implements iShape {

	private int height;
	private int base;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Triangle t1 = new Triangle(5,4);
		t1.calculateArea();
		t1.display();
	}
	
	public Triangle(int height, int base){
		this.height = height;
		this.base = base;
	}
	
	@Override
	public void calculateArea() {
		// TODO Auto-generated method stub
		double area = height * base / 2;
		System.out.println("area is: " + area);
	}

	@Override
	public void display() {
		for(int i=0;i<height;i++) {
			for (int j=0;j < i;j++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
		
	}

}
