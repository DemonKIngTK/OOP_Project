import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKey implements KeyListener{
	BomberData data;
	public MyKey(BomberData data) {
		// TODO Auto-generated constructor stub
		this.data=data;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			data.setUp(true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			data.setDown(true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			data.setLeft(true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			data.setRight(true);
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			data.setUp(false);
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			data.setDown(false);
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			data.setLeft(false);
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			data.setRight(false);
		}
		
	}

}
