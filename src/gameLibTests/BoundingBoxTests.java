package gameLibTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameLib.*;

public class BoundingBoxTests
{
	@Test
	public void testConstructorCorrectValues() 
	{
		BoundingBox box = new BoundingBox(4, 4, 10, 10);
		
		assertEquals(4, box.getX());
		assertEquals(4, box.getY());
		assertEquals(10, box.getWidth());
		assertEquals(10, box.getHeight());
	}
	
	@Test
	public void testConstructorPositionIsZero()
	{
		BoundingBox box = new BoundingBox(0, 0, 10, 10);
		
		assertEquals(0, box.getX());
		assertEquals(0, box.getY());
		assertEquals(10, box.getWidth());
		assertEquals(10, box.getHeight());
	}

	@Test(expected=RuntimeException.class)
	public void testConstructorXSmallerZero()
	{
		new BoundingBox(-1, 4, 10, 10);
	}
	
	@Test(expected=RuntimeException.class)
	public void testConstructorYSmallerZero()
	{
		new BoundingBox(4, -1, 10, 10);
	}
	
	@Test(expected=RuntimeException.class)
	public void testConstructorWidthIsZero()
	{
		new BoundingBox(4, 4, 0, 10);
	}
	
	@Test(expected=RuntimeException.class)
	public void testConstructorHeightIsZero()
	{
		new BoundingBox(4, 4, 10, 0);
	}
	
	@Test
	public void testSetAndGetX()
	{
		BoundingBox box = new BoundingBox(4, 4, 20, 20);
		box.setX(10);
		assertEquals(10, box.getX());		
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetXSmallerZero()
	{
		BoundingBox box = new BoundingBox(4, 4, 10, 10);
		box.setX(-1);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetYSmallerZero()
	{
		BoundingBox box = new BoundingBox(4, 4, 10, 10);
		box.setY(-1);
	}
	
	@Test
	public void testSetAndGetY()
	{
		BoundingBox box = new BoundingBox(4, 4, 20, 20);
		box.setY(10);
		assertEquals(10, box.getY());		
	}
	
	@Test
	public void testSetAndGetWidth()
	{
		BoundingBox box = new BoundingBox(4, 4, 20, 20);
		box.setWidth(10);
		assertEquals(10, box.getWidth());		
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetWidthSmallerZero()
	{
		BoundingBox box = new BoundingBox(4, 4, 10, 10);
		box.setWidth(-1);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetWidthEqualZero()
	{
		BoundingBox box = new BoundingBox(4, 4, 10, 10);
		box.setWidth(0);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetHeightSmallerZero()
	{
		BoundingBox box = new BoundingBox(4, 4, 10, 10);
		box.setHeight(-1);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetHeightEqualZero()
	{
		BoundingBox box = new BoundingBox(4, 4, 10, 10);
		box.setHeight(0);
	}
	
	@Test
	public void testSetAndGetHeight()
	{
		BoundingBox box = new BoundingBox(4, 4, 20, 20);
		box.setHeight(10);
		assertEquals(10, box.getHeight());		
	}
	
	@Test
	public void testCheckCollisionNoCollision()
	{
		BoundingBox firstBox = new BoundingBox(0, 0, 2, 2);
		BoundingBox secondBox = new BoundingBox(3, 3, 2, 2);
		
		boolean firstWithSecondBox = firstBox.checkCollision(secondBox);
		boolean secondWithFirstBox = secondBox.checkCollision(firstBox);
		
		assertFalse(firstWithSecondBox);
		assertFalse(secondWithFirstBox);
	}
	
	@Test
	public void testCheckCollisionCollision()
	{
		BoundingBox firstBox = new BoundingBox(0, 0, 2, 2);
		BoundingBox secondBox = new BoundingBox(1, 1, 2, 2);
		
		boolean firstWithSecondBox = firstBox.checkCollision(secondBox);
		boolean secondWithFirstBox = secondBox.checkCollision(firstBox);
		
		assertTrue(firstWithSecondBox);
		assertTrue(secondWithFirstBox);
	}
	
	@Test
	public void testCheckCollisionNoCollisionVerticalEdgeCase()
	{
		BoundingBox firstBox = new BoundingBox(0, 0, 2, 2);
		BoundingBox secondBox = new BoundingBox(2, 0, 2, 2);
		
		boolean firstWithSecondBox = firstBox.checkCollision(secondBox);
		boolean secondWithFirstBox = secondBox.checkCollision(firstBox);
		
		assertFalse(firstWithSecondBox);
		assertFalse(secondWithFirstBox);
	}
	
	@Test
	public void testCheckCollisionNoCollisionHorizontalEdgeCase()
	{
		BoundingBox firstBox = new BoundingBox(0, 0, 2, 2);
		BoundingBox secondBox = new BoundingBox(0, 2, 2, 2);
		
		boolean firstWithSecondBox = firstBox.checkCollision(secondBox);
		boolean secondWithFirstBox = secondBox.checkCollision(firstBox);
		
		assertFalse(firstWithSecondBox);
		assertFalse(secondWithFirstBox);
	}
	
	@Test
	public void testCheckCollisionNoCollisionMixedEdgeCase()
	{
		BoundingBox firstBox = new BoundingBox(0, 0, 2, 2);
		BoundingBox secondBox = new BoundingBox(1, 0, 2, 2);
		
		boolean firstWithSecondBox = firstBox.checkCollision(secondBox);
		boolean secondWithFirstBox = secondBox.checkCollision(firstBox);
		
		assertTrue(firstWithSecondBox);
		assertTrue(secondWithFirstBox);
	}
	
	@Test
	public void testLeftToRightEdgeCase()
	{
		BoundingBox firstBox = new BoundingBox(0, 20, 20, 20);
		BoundingBox secondBox = new BoundingBox(20, 40, 20, 20);
		
		boolean firstWithSecondBox = firstBox.checkCollision(secondBox);
		boolean secondWithFirstBox = secondBox.checkCollision(firstBox);
		
		assertFalse(firstWithSecondBox);
		assertFalse(secondWithFirstBox);
	}
}
