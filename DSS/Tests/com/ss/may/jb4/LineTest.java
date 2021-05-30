package com.ss.may.jb4;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class LineTest {
	private Line lTestPos = new Line(0,0,5,5);
	private Line lTestNeg = new Line(3,0,5,-7);
	private Line pTest = new Line(-2,-2,3,3);
	
	@Test
	public void slopeTest(){
		assertEquals(1,lTestPos.getSlope(),0.0001);
		assertEquals(-3.5,lTestNeg.getSlope(),0.0001);
		assertNotEquals(5,lTestNeg.getSlope(),0.0001);
	}
	
	@Test
	public void distanceTest() {
		assertEquals(7.071068,lTestPos.getDistance(),0.0001);
		assertEquals(7.28011,lTestNeg.getDistance(),0.0001);
		assertNotEquals(5,lTestNeg.getDistance(),0.0001);
	}
	
	@Test
	public void parallelTest() {
		assertEquals(true,lTestPos.parallelTo(pTest));
		assertNotEquals(true,lTestPos.parallelTo(lTestNeg));
	}
	
}
