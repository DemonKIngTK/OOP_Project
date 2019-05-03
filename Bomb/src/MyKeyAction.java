import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class MyKeyAction extends KeyAdapter{
	bmb bmb;
	public MyKeyAction(bmb bmb) {
		this.bmb = bmb;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		bmb.keyPressed(e);
		System.out.println("true");
	}
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		bmb.keyReleased(e);
		System.out.println("flaot");
	}
}
