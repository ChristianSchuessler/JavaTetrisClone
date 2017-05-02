package gameLib;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InGameManager implements KeyListener, IBrickMoveListener, IGameOverListener
{
	private ArrayList<Brick> _bricks;
	private ArrayList<Brick> _brickDeletionList;
	private BrickFactory _brickFactory;
	private BrickField _brickField;
	private Brick _selectedBrick;
	private Brick _newBrick;
	private BrickRowDeleter _brickDeleter;
	private boolean _runGame;
	
	public InGameManager()
	{
		_brickDeletionList = new ArrayList<Brick>();
    	_brickFactory = new BrickFactory(160, 20);
    	_bricks = new ArrayList<Brick>();
    	
    	_brickField = new BrickField(new Position(20, 20), 220, 500);
    	_brickField.registerGameOverListener(this);
    	
    	_brickDeleter = new BrickRowDeleter(220);
    	_newBrick = null;
    	
    	_runGame = true;
    	addNewBrick();
	}
	
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        
        if(_runGame)
        {
            updateBricks();
            g2d.drawRect(20, 20, 220, 500);
            for(Brick brick : _bricks)
            {
            	brick.draw(g2d);
            	brick.moveAttempt();
            }
        }
        else
        {
        	 g2d.drawString("Game Over", 100, 200);
        	 g2d.drawString("Score: " + (_brickDeleter.getNumberOfDeletedRows() * 100), 100, 250);
        }
    }
    /**
     * this method add/removes new or disappeared bricks
     **/
    private void updateBricks()
    {
        for(Brick brick : _brickDeletionList)
        {
        	_bricks.remove(brick);
        }
        _brickDeletionList.clear();
        
        if(_newBrick != null)
        {
        	_bricks.add(_newBrick);
        	_selectedBrick = _newBrick;
        	_newBrick = null;
        }
    }
    
    private void addNewBrick()
    {
	 	Brick newBrick;
		try 
		{
			newBrick = _brickFactory.getBrick();
			newBrick.registerMoveListener(_brickField);
			newBrick.registerMoveListener(this);
			newBrick.registerMoveListener(_brickDeleter);
		
			_newBrick = newBrick;
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
    }
    
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		
		switch( keyCode ) 
	    { 
	        case KeyEvent.VK_UP:
	        	_selectedBrick.twiddle();
	            break;
	        case KeyEvent.VK_DOWN:
	            break;
	        case KeyEvent.VK_LEFT:
	            _selectedBrick.moveLeftAttempt();
	            break;
	        case KeyEvent.VK_RIGHT :
	        	_selectedBrick.moveRightAttempt();
	            break;
	     }
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

	@Override
	public boolean moveAttempt(Brick brick, Position moveVector) 
	{
		return true;
	}

	@Override
	public void appeared(Brick brick)
	{
	}

	@Override
	public void stopped(Brick brick) 
	{	
	}

	@Override
	public void firstStop(Brick brick)
	{
		addNewBrick();	
	}

	@Override
	public void disappeared(Brick brick)
	{
		_brickDeletionList.add(brick);
	}

	@Override
	public boolean checkPositions(Brick brick, ArrayList<Position> positions)  
	{
		return true;
	}

	@Override
	public boolean moveAttempt(Brick brick, Position singlePosition, Position moveVector) 
	{
		return true;
	}

	@Override
	public void gameOver() 
	{
		_runGame = false;
	}

}
