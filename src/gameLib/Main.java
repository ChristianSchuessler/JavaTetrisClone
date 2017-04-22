package gameLib;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel
{
	private InGameManager _gameManager;
	
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        
        _gameManager.paint(g);
    }
    
    Main() throws Exception
    {
    	_gameManager = new InGameManager();
    	this.addKeyListener(_gameManager);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public static void main(String[] args) throws Exception 
    {
        JFrame frame = new JFrame("Chris-Tetris");
        Main game = new Main();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        while (true) 
        {
            game.repaint();
            Thread.sleep(10);
        }
    }
}
