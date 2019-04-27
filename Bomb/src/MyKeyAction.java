import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyAction implements KeyListener{
	bmb game;
	BomberData bomdata;
	outClient Clout;
	public MyKeyAction(bmb game) {
		// TODO Auto-generated constructor stub
		this.game=game;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			
			
	}
	else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
		
			
		}
	else if(e.getKeyCode()==KeyEvent.VK_DOWN){
		
			
		}
	else if	(e.getKeyCode()==KeyEvent.VK_UP) {

			
		}
	else if(e.getKeyCode()==KeyEvent.VK_SPACE) {

			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stu
		
	}

}
