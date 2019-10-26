package mouseMusicInstrument;

//Computer program that converts mouse movements into 
//musical pitches. The instrument is silent until one of 
//the mouse buttons is held down. Move the mouse up, and down to 
//change the volume. Move the mouse side to side to change the 
//pitch. Let go of the mouse button, and the musical tone stops.


import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfugue.Player;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class mouseMusicInstrument extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L; //Suggested by Eclipse
	static final String NEWLINE = System.getProperty("line.separator");
	Player player;
	private Image img;
	public musicInstrument instrument;
	Thread threadInstrument;
	int width, height;

	public static void main(String[] args) {
		mouseMusicInstrument panel = new mouseMusicInstrument(new ImageIcon("images/FourDirections.png").getImage());
		JFrame frame = new JFrame();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		
	}

	// http://www.java2s.com/Code/Java/Swing-JFC/Panelwithbackgroundimage.htm
	public mouseMusicInstrument(String img) {
		this(new ImageIcon(img).getImage());
	}

	public mouseMusicInstrument(Image img) {
		this.img = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		Dimension size = new Dimension(this.width, this.height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	// https://docs.oracle.com/javase/tutorial/uiswing/examples/events/MouseEventDemoProject/src/events/MouseEventDemo.java
	void eventOutput(String eventDescription, MouseEvent e) {
		System.out.println(eventDescription + " detected on " + e.getComponent().getClass().getName() + "." + NEWLINE);
	}

	public void mouseMoved(MouseEvent e) {
		//eventOutput("Mouse moved (X: " + e.getXOnScreen() + "Y: " + e.getYOnScreen() + ")", e);
	}

	public void mouseDragged(MouseEvent e) {
		eventOutput("Mouse dragged (X: " + e.getXOnScreen() + "Y: " + e.getYOnScreen() + ")", e);
        createNewThread(e.getX(),e.getY());
	}

	public void createNewThread(int x, int y) {
        Runnable runnable = new musicInstrument(x,y,this.height,this.width);
        threadInstrument = new Thread(runnable);
        threadInstrument.start();
	}
	
    public void mousePressed(MouseEvent e) {
        eventOutput("Mouse pressed (# of clicks: "+ e.getClickCount() + ")", e);     
        createNewThread(e.getX(),e.getY());
    }
    
    public void mouseReleased(MouseEvent e) {
        eventOutput("Mouse released (# of clicks: "+ e.getClickCount() + ")", e);
    }
    
    public void mouseEntered(MouseEvent e) {
        //eventOutput("Mouse entered", e);
    }
    
    public void mouseExited(MouseEvent e) {
        //eventOutput("Mouse exited", e);
    }
    
    public void mouseClicked(MouseEvent e) {
        eventOutput("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);
    }

}
