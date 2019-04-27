import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;


class bmb extends JFrame{    
	int xPixel1 = 70;   // Player1
	int yPixel1 = 90;
	int xPixel2 = 870; // Player2
	int yPixel2 = 90;
	int xPixel3 = 70; // Player3
	int yPixel3 = 540;
	int xPixel4 = 870; // Player4
	int yPixel4 = 540;
	//public JTextField Score;
	//private JPanel panel;
	MyAudio ad;
	int ok;
	int Player1=1,Player2=1,Player3=1,Player4=1,sh1=0,sh2=0,sh3=0,sh4=0; //Player life
	int x1=1,y1=1,x2=17,y2=1,x3=1,y3=10,x4=17,y4=10; //coord Player1 & PLayer2
	long time1,time2,t1,t2;   //time form bomb & explosion
	JFrame endgame=new JFrame("End");
	Button Endok=new Button("Ok");
	int v[][]= {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},  //map
				{1,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,1,},
				{1,0,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,0,1,},
				{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,},
				{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,},
				{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,},
				{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,},
				{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,},
				{1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,},
				{1,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,1,},
				{1,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,1,},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},};
	
	int i,j,ok1=0,ok2=0,cx,cy,cx1,cy1,c1,c2,C1,C2,bomb1,bomb2,Bomb1Level=1,Bomb2Level=1;
	Image Imagep1,Imagep2,Imagep3,Imagep4,Image2,Image3,Image4,Image5,ImageC,ImageL,ImageR,
	ImageSH,ImageU,ImageD,ImageP,ImageV,ImageO, offScreenImage,ImageBG;
	String die;
	Graphics offScreenGraphics; 

	public bmb() {
		
		try {
			
			Imagep1 = Toolkit.getDefaultToolkit().getImage("./ImagesRes/P1F.png");
			Imagep2 = Toolkit.getDefaultToolkit().getImage("./ImagesRes/P2F.png");
			Imagep3 = Toolkit.getDefaultToolkit().getImage("./ImagesRes/P3F.png");
			Imagep4 = Toolkit.getDefaultToolkit().getImage("./ImagesRes/P4F.png");
			ImageBG = Toolkit.getDefaultToolkit().getImage("./ImagesRes/BG.jpg");
			Image2 = Toolkit.getDefaultToolkit().getImage("./ImagesRes/BlockBreakable.jpg"); 
			Image3 = Toolkit.getDefaultToolkit().getImage("./ImagesRes/Block.jpg");
			Image4 = Toolkit.getDefaultToolkit().getImage("./ImagesRes/bomb6.gif");
			Image5 = Toolkit.getDefaultToolkit().getImage("./ImagesRes/bmb2.png");
			ImageC = Toolkit.getDefaultToolkit().getImage("./ImagesRes/center.png");
			ImageL = Toolkit.getDefaultToolkit().getImage("./ImagesRes/left.png");
			ImageR = Toolkit.getDefaultToolkit().getImage("./ImagesRes/right.png");
			ImageU = Toolkit.getDefaultToolkit().getImage("./ImagesRes/up.png");
			ImageD = Toolkit.getDefaultToolkit().getImage("./ImagesRes/down.png");
			ImageP = Toolkit.getDefaultToolkit().getImage("./ImagesRes/Poweritem.png");
			ImageV = Toolkit.getDefaultToolkit().getImage("./ImagesRes/vertical.png");
			ImageO = Toolkit.getDefaultToolkit().getImage("./ImagesRes/orizontal.png");
			ImageSH =  Toolkit.getDefaultToolkit().getImage("./ImagesRes/shielditem.png");
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		ad=new MyAudio(this);
		setBackground(new Color(17, 120, 49));
		setSize(1000,650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new MyKeyAction(this));
		ad.playSound();
	}
	public void update(Graphics g) {
		paint(g);
	}     
	public void paint(Graphics g) {

		int width  = getWidth();
		int height = getHeight();

		if (offScreenImage == null) {
			offScreenImage    = createImage(width, height);
			offScreenGraphics = offScreenImage.getGraphics();
		}
		offScreenGraphics.clearRect(0, 0, width, height);
		for(i=0;i<12;i++)
			for(j=0;j<19;j++)
			{
				if(v[i][j]==2) {
					offScreenGraphics.drawImage(Image2, 20+j*50, 38+i*50, this);}
				if(v[i][j]==1) {
					offScreenGraphics.drawImage(Image3, 20+j*50, 40+i*50, this);}
				if(v[i][j]==3) {
					offScreenGraphics.drawImage(ImageP, 20+j*50, 38+i*50, this);}
				if(v[i][j]==0) {
					offScreenGraphics.drawImage(ImageBG, 20+j*50, 38+i*50, this);}
				if(v[i][j]==4) {
					offScreenGraphics.drawImage(ImageSH, 20+j*50, 38+i*50, this);
				}
			}
		if(ok1!=0) //if bomb1 is placed
			{
				repaint();
				offScreenGraphics.drawImage(Image4, cx-20, cy-12,50,50, this);
				long Time1 = System.nanoTime() - time1;
				if(Time1/1000000>1500) //3 seconds explode time
					{
						ok1=0;
						boom(c2,c1,Bomb1Level);  //explode
						bomb1=1;
						v[c2][c1]=0;
						t1=System.nanoTime();
						repaint();
					}
			}
		if(ok2==1)
			{
				repaint();
				offScreenGraphics.drawImage(Image4, cx1-20, cy1-20, this);
				long Time2 = System.nanoTime() - time2;
				if(Time2/1000000>1500)
				{
					ok2=0;
					boom(C2,C1,Bomb2Level);
					v[C2][C1]=0;
					bomb2=1;
					t2=System.nanoTime();
					repaint();
				}
			}
		if(bomb1==1)
		{
			int bomblevel=Bomb1Level;
				repaint();
				offScreenGraphics.drawImage(ImageC, cx-20, cy-10, this);
				if(bomblevel==1)
				{
						offScreenGraphics.drawImage(ImageL, cx-70, cy-10, this);
						offScreenGraphics.drawImage(ImageR, cx+25, cy-10, this);
						offScreenGraphics.drawImage(ImageU, cx-20, cy-55, this);
						offScreenGraphics.drawImage(ImageD, cx-20, cy+35, this);
				}
				else
				{
					for(int i=1;i<bomblevel;i++)
						{
						offScreenGraphics.drawImage(ImageO, cx-20-50*i, cy-10, this);
						offScreenGraphics.drawImage(ImageO, cx-20+50*i, cy-10, this);
						offScreenGraphics.drawImage(ImageV, cx-20, cy-10-50*i, this);
						offScreenGraphics.drawImage(ImageV, cx-20, cy-10+50*i, this);
						}
					for(int i=0;i<bomblevel;i++) {
						offScreenGraphics.drawImage(ImageL, cx-20-50*Bomb1Level, cy-10, this);
						offScreenGraphics.drawImage(ImageR, cx-20+50*Bomb1Level, cy-10, this);
						offScreenGraphics.drawImage(ImageU, cx-20, cy-10-50*Bomb1Level, this);
						offScreenGraphics.drawImage(ImageD, cx-20, cy-10+50*Bomb1Level, this);
					}
					
						
				}
				long Time1 = System.nanoTime() - t1;
				if(Time1/1000000>1000) //1 second explode
				{
						bomb1=0;
						repaint();
				}
		}
		if(bomb2==1)
		{
				repaint();
				offScreenGraphics.drawImage(ImageC, cx1-20, cy1-20, this);
				if(Bomb2Level==1)
				{
					offScreenGraphics.drawImage(ImageL, cx1-70, cy1-10, this);
					offScreenGraphics.drawImage(ImageR, cx1+25, cy1-10, this);
					offScreenGraphics.drawImage(ImageU, cx1-20, cy1-55, this);
					offScreenGraphics.drawImage(ImageD, cx1-20, cy1+35, this);
				}
				else
				{
					for(int i=1;i<Bomb2Level;i++)
						{
						offScreenGraphics.drawImage(ImageO, cx1-20-50*i, cy1-10, this);
						offScreenGraphics.drawImage(ImageO, cx1-20+50*i, cy1-10, this);
						offScreenGraphics.drawImage(ImageV, cx1-20, cy1-10-50*i, this);
						offScreenGraphics.drawImage(ImageV, cx1-20, cy1-10+50*i, this);
						}
					offScreenGraphics.drawImage(ImageL, cx1-20-50*Bomb2Level, cy1-10, this);
					offScreenGraphics.drawImage(ImageR, cx1-20+50*Bomb2Level, cy1-10, this);
					offScreenGraphics.drawImage(ImageU, cx1-20, cy1-10-50*Bomb2Level, this);
					offScreenGraphics.drawImage(ImageD, cx1-20, cy1-10+50*Bomb2Level, this);
						
				}
				long Time2 = System.nanoTime() - t2;
				if(Time2/1000000>1000) //2 seconds explode
				{
						bomb2=0;
						repaint();
				}
		}
		if(Player1>0) {
		offScreenGraphics.drawImage(Imagep1, xPixel1, yPixel1,50,50, this);}
		if(Player2>0) {
		offScreenGraphics.drawImage(Imagep2, xPixel2, yPixel2,50,50, this);}
		if(Player3>0) {
			offScreenGraphics.drawImage(Imagep3, xPixel3, yPixel3,50,50, this);}
		if(Player4>0) {
			offScreenGraphics.drawImage(Imagep4, xPixel4, yPixel4,50,50, this);}
		g.drawImage(offScreenImage, 0, 0, this);
		
	}
	public void boom(int yy,int xx,int player) //clears barrels
	{
		die(yy,xx);
		int i=0;
		while(++i<=player)
			{
			if(v[yy-i][xx]==2)
				{
					v[yy-i][xx]=0;
					power(yy-i,xx);
				}
			else 
				{
				if(v[yy-i][xx]==1)
				i=player+1;
				else die(yy-i,xx);
				}
			}
		i=0;
		while(++i<=player)
			if(v[yy][xx-i]==2)
				{
					v[yy][xx-i]=0;
					power(yy,xx-i);
				}
			else 
			{
				if(v[yy][xx-i]==1)
					i=player+1;
				else die(yy,xx-i);
			}
		i=0;
		while(++i<=player)
			if(v[yy+i][xx]==2)
				{
					v[yy+i][xx]=0;
					power(yy+i,xx);
				}
			else 
				{
				if(v[yy+i][xx]==1)
				i=player+1;
				else die(yy+i,xx);
				}
		i=0;
		while(++i<=player)
			if(v[yy][xx+i]==2)
				{
					v[yy][xx+i]=0;
					power(yy,xx+i);
				}
			else 
			{
				if(v[yy][xx+i]==1)
				i=player+1;
				else die(yy,xx+i);
			}
	}
	public void die(int yy,int xx)
	{
		if(yy==y3&&xx==x3) {
			if(sh3==0) {
				Player3=0;
			}else if(sh3>0){
				sh3-=1;
			}
		}
		if(yy==y2&&xx==x2) {
			Player2=0;}
		if(yy==y1&&xx==x1) {
			Player1=0;}
		if(yy==y4&&xx==x4) {
			Player4=0;}
		end(Player1,Player2,Player3,Player4);
		
	}
	public void end(int p1,int p2,int p3,int p4) {
		
		endgame.setSize(300, 300);
		endgame.setLocationRelativeTo(null);
		Endok.setBounds(150, 100, 60, 40);
		endgame.add(Endok);
		if(p1==1&&p2==0&&p3==0&&p4==0) {	
			endgame.setVisible(true);
		}else if(p1==0&&p2==1&&p3==0&&p4==0) {
			endgame.setVisible(true);
		}else if(p1==0&&p2==0&&p3==1&&p4==0) {
			endgame.setVisible(true);
		}else if(p1==0&&p2==0&&p3==0&&p4==1) {
			endgame.setVisible(true);
		}
	}
	public void power(int a,int b)
	{
		int rand = ThreadLocalRandom.current().nextInt(1, 10 + 1);
		if(rand==3) {
			v[a][b]=3;}
		else if(rand==4) {
			v[a][b]=4;
		}
	}
	public void atrib(int a,int b,int player)
	{
		if(v[a][b]==3)
		{
			if(player==1)
				{
					if(Bomb1Level<4) {
						Bomb1Level++;
					}
					v[a][b]=0;
				}
			else {
					if(Bomb2Level<4) {
						Bomb2Level++;
					}
					v[a][b]=0;
				}
			
		}else if(v[a][b]==4) {
			if(player==1)
			{
				if(sh3<1) {
					sh3++;
				}
				v[a][b]=0;
			}
		else {
				v[a][b]=0;
			}
		}
	}
	public int valid (int i,int j) //if moving position is valid
	{
		if(v[i][j]!=1&&v[i][j]!=2&&v[i][j]!=5)
			return 1;
		return 0;
	}
	
		/*if(Player2>0)
		{switch (ke.getKeyChar()) {
		case 'd': {
			{
				if(valid(y2,x2+1)==1)
				{
					xPixel2+=50;
					x2++;
					atrib(y2,x2,2);
				}
			}
		}
		break;
		case 'a': {
			{
				if(valid(y2,x2-1)==1)
				{
					xPixel2-=50;
					x2--;
					atrib(y2,x2,2);
				}
			}
		}
		break;
		case 's': {
			{
				if(valid(y2+1,x2)==1)
				{
					yPixel2+=50;
					y2++;
					atrib(y2,x2,2);
				}
			};
		}
		break;
		case 'w': {
			{
				if(valid(y2-1,x2)==1)
				{
					yPixel2-=50;
					y2--;
					atrib(y2,x2,2);
				}
			}
		}
		break;
		case 'f': {
		{
			if(ok2==0&&bomb2==0)
			{
			ok2=1;
			cx1=xPixel2+20;
			C1=x2;
			cy1=yPixel2+10;
			C2=y2;
			time2=System.nanoTime();
			v[C2][C1]=5;
			}
		}
		}
		break;
		}
		}
		repaint();
	}*/
	
}