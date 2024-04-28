import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
*/
public class CarPanel extends JComponent
{  
	private Car car1;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;
	
	public CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
	}
	public void startAnimation() {
	    Runnable r = new AnimationRunnable();
	    Thread t = new Thread(r);
	    t.start();
	}

	class AnimationRunnable implements Runnable {
	    public void run() {
	        try {
	            while (true) {
	                direction = carQueue.deleteQueue();
	                switch (direction) {
	                    case 0: y = Math.max(0, y - 16); break; 
	                    case 1: y = Math.min(getHeight() - 30, y + 17); break; 
	                    case 2: x = Math.min(getWidth() - 60, x + 19); break; 
	                    case 3: x = Math.max(0, x - 20); break; 
	                }
	                repaint();
	                Thread.sleep(delay * 1000);
	            }
	        } catch (InterruptedException exception) {
	            Thread.currentThread().interrupt(); // Restore the interrupted status
	        }
	    }
	}
	
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;

      car1.draw(g2,x,y);    
   }
}