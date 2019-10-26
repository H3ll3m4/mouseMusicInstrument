package mouseMusicInstrument;

import org.jfugue.Player;

public class musicInstrument implements Runnable{
	Player player;
	int x,y,height, width;

	public musicInstrument(int x, int y,int height, int width) {
		player = new Player();
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}
	
	public void run() {
		int pitch = (int) (this.x*127/this.width);
		//BANK_SELECT
		//Suppose  you  would  like  to set  the  volume  to  10200,  out  of  a  possible range of 0 through 16383.Just use:CE(Volume,10200)
		int volume = 16383 - (int) (this.y*16383/this.height); 
		player.play("X[Volume]=" + volume + " [" + pitch + "]"); 
		
        if (Thread.interrupted()) {
            System.out.println("Instrument is about to stop");
            return;
        }
	}

}
