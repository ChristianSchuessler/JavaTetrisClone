package gameLib;

import java.lang.Math;

public class BoundingBox 
{
	private int _x;
	private int _y;
	private int _width;
	private int _height;
	
	public BoundingBox(int x, int y, int width, int height)
	{
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	private boolean checkCollisionSingleBox(BoundingBox other)
	{
		// create all four points to check
		int x1 = _x;
		int x2 = other.getX();
		int w1 = _width;
		int w2 = other.getWidth();
		
		int xSum = Math.abs(x2 - x1) + Math.abs(x2 - x1 - w1) + 
				Math.abs(x2 + w2 - x1) + Math.abs(x2 + w2 - x1 - w1);
		
		xSum -= (2 * w1 + 2*w2);
		
		int y1 = _y;
		int y2 = other.getY();
		int h1 = _height;
		int h2 = other.getHeight();
		
		int ySum = Math.abs(y2 - y1) + Math.abs(y2 - y1 - h1) + 
				Math.abs(y2 + h2 - y1) + Math.abs(y2 + h2 - y1 - h1);
		
		ySum -= (2 * h1 + 2*h2);
		
		if(xSum < 0 && ySum < 0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	public boolean checkCollision(BoundingBox other)
	{
		boolean checkOther = checkCollisionSingleBox(other);
		boolean checkMySelf = other.checkCollisionSingleBox(this);
		
		if(checkOther == true || checkMySelf == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// below you will find all get and set methods
	public void setX(int x)
	{
		if(x < 0)
		{
			System.out.println(x);
			throw new RuntimeException("x must be greater equal than zero"); 
		}
		else
		{
			_x = x;
		}
	}
	
	public int getX()
	{
		return _x;
	}
	
	public void setY(int y)
	{
		if(y < 0)
		{
			throw new RuntimeException("x must be greater equal than zero"); 
		}
		else
		{
			_y = y;
		}
	}
	
	public int getY()
	{
		return _y;
	}
	
	public void setWidth(int width)
	{
		if(width <= 0)
		{
			throw new RuntimeException("width must be greater than zero"); 
		}
		else
		{
			_width = width;
		}
	}
	
	public int getWidth() 
	{
		return _width;
	}
	
	public void setHeight(int height)
	{
		if(height <= 0)
		{
			throw new RuntimeException("x must be greater than zero"); 
		}
		else
		{
			_height = height;
		}
	}
	
	public int getHeight()
	{
		return _height;
	}
}
