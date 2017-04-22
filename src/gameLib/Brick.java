package gameLib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import gameLib.Position;

public class Brick
{
	protected ArrayList<Position> _positions;
	protected ArrayList<IBrickMoveListener> _moveListeners;
	private Color _rectColor;
	private int _moveDistance = 1;
	private boolean _stopped;
	private boolean _alreadyStopped;
	
	// there are twiddle states from 0 to 3 (0 to 270°)
	// twiddle() updates the state automatically
	protected int _twiddleState = 0;
	
	protected static int _rectSize = 20;
	
	public static int getRectSize()
	{
		return _rectSize;
	}
	
	public Brick()
	{
		_positions = new ArrayList<Position>();
		_rectColor = Color.BLACK;
	}
	
	public Brick(ArrayList<Position> positions, Color color)
	{
		initialize( positions, color);
	}
	
	public void removePosition(Position deletePosition)
	{
		Iterator<Position> it = _positions.iterator();
		
		// find matching position and remove it from brick;
		while(it.hasNext())
		{
			Position removePosition = it.next();
			
			if(removePosition.x == deletePosition.x && 
			   removePosition.y == deletePosition.y)
			{
				it.remove();
				break;
			}
		}
		
		if(_positions.isEmpty())
		{
			for(IBrickMoveListener listener : _moveListeners)
			{
				listener.disappeared(this);
			}
		}
	}
	
	public boolean isStopped()
	{
		return _stopped;
	}
	
	public void moveAttempt()
	{
		boolean moveSucessfull = internalMoveAttempt(new Position(0, _moveDistance));
		
		// if moving was not possible, brick run against another brick
		if(moveSucessfull == false)
		{
			if(_stopped == false)
			{
				_stopped = true;
				for(IBrickMoveListener listener : _moveListeners)
				{
					listener.stopped(this);
				}
			}
			
			if(_alreadyStopped == false)
			{
				_alreadyStopped = true;
				for(IBrickMoveListener listener : _moveListeners)
				{
					listener.firstStop(this);
				}
			}
		}
		else
		{
			_stopped = false;
		}
	}
	
	public void moveLeftAttempt()
	{
		if(_alreadyStopped == false)
		{
			internalMoveAttempt(new Position(-_rectSize, 0));
		}
	}
	
	public void moveRightAttempt()
	{
		if(_alreadyStopped == false)
		{
			internalMoveAttempt(new Position(_rectSize, 0));
		}
	}
	
	public void draw(Graphics2D g2d)
	{
		g2d.setColor(_rectColor);
		
		for(Position pos : _positions)
		{
			g2d.drawRect(pos.x, pos.y + 1, _rectSize, _rectSize - 1);
			g2d.fillRect(pos.x + 3, pos.y + 3, _rectSize - 3, _rectSize - 3);
		}
	}
	
	public void registerMoveListener(IBrickMoveListener listener)
	{
		_moveListeners.add(listener);
		listener.appeared(this);
	}
	
	public ArrayList<Position> getPositions()
	{
		return _positions;
	}
	
	protected void initialize(ArrayList<Position> positions, Color color)
	{
		_positions = positions;
		_rectColor = color;
		_moveListeners = new ArrayList<IBrickMoveListener>();
		_stopped = false;
		_alreadyStopped = false;
	}
	
	protected void move(Position moveVector)
	{
		for(int i = 0; i < _positions.size(); ++i)
		{
			Position pos = _positions.get(i);
			pos.y += moveVector.y;
			pos.x += moveVector.x;
			_positions.set(i, pos);
		}
	}
	
	/**
	 * indicates if the move was successful or not
	 **/
	protected boolean internalMoveAttempt(Position moveVector)
	{
		boolean moveSucessFull = true;
		for(IBrickMoveListener listener : _moveListeners)
		{
			boolean sucess = listener.moveAttempt(this, moveVector);
			
			if(sucess == false)
			{
				 moveSucessFull = false;
			}
		}
		
		if(moveSucessFull == true)
		{
			move(moveVector);
		}
		
		return moveSucessFull;
	}
	
	public void twiddle()
	{
		switch(_twiddleState)
		{
		case 0:
			_twiddleState = 1;
			break;
			
		case 1:
			_twiddleState = 2;
			break;
			
		case 2:
			_twiddleState = 3;
			break;
			
		case 3:
			_twiddleState = 0;
			break;
		}
	}
	
	/**
	 * replace current _positions with newPositions -> if no collision appeared
	 **/
	protected void setNewPositionsSafe(ArrayList<Position> newPositions)
	{
		boolean moveValid = true;
		for(IBrickMoveListener listener : _moveListeners)
		{
			boolean returnValue = listener.checkPositions(this, newPositions);
			
			if(returnValue == false)
			{
				moveValid = false;
			}
			
		}
		
		if(moveValid == true)
		{
			_positions = newPositions;
		}
		
	}
}
