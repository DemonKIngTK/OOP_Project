import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;


class bmb extends JFrame{    
	static int xPixel1 = 70;   // Player1
	static int yPixel1 = 90;
	static int xPixel2 = 870; // Player2
	static int yPixel2 = 90;
	int xPixel3 = 70; // Player3
	int yPixel3 = 540;
	static int xPixel4 = 870; // Player4
	static int yPixel4 = 540;
	//public JTextField Score;
	//private JPanel panel;
	MyAudio ad=new MyAudio();
	int ok;
	int Player1=1,Player2=1,Player3=1,Player4=1; //Player life
	int x1=1,y1=1,x2=17,y2=1,x3=1,y3=10,x4=17,y4=10; //coord Player1 & PLayer2
	long time1,time2,time3,time4,t1,t2,t3,t4;   //time form bomb & explosion
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
	
	int i,j,ok1=0,ok2=0,ok3=0,ok4=0,cx1,cy1,cx2,cy2,cx3,cy3,cx4,cy4,c1,c2,C1,C2,a3,a23,A14,A24,bomb1,bomb2,bomb3,bomb4
			,Bomb1Level=1,Bomb2Level=1,Bomb3Level=1,Bomb4Level=1,sh1=0,sh2=0,sh3=0,sh4=0;
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
		setBackground(new Color(17, 120, 49));
		setSize(1000,650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new Client(this,new BomberData()));
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
			offScreenGraphics.drawImage(Image4, cx1-20, cy1-12,50,50, this);
			long Time1 = System.nanoTime() - time1;
			if(Time1/1000000>1000) //3 seconds explode time
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
				offScreenGraphics.drawImage(Image4, cx2-20, cy2-10, this);
				long Time2 = System.nanoTime() - time2;
				if(Time2/1000000>1000)
				{
					ok2=0;
					boom(C2,C1,Bomb2Level);
					bomb2=1;
					v[C2][C1]=0;
					t2=System.nanoTime();
					repaint();
				}
			}
		if(ok3==1)
		{
			repaint();
			offScreenGraphics.drawImage(Image4, cx3-20, cy3-20, this);
			long Time3 = System.nanoTime() - time3;
			if(Time3/1000000>1000)
			{
				ok3=0;
				boom(a23,a3,Bomb3Level);
				bomb3=1;
				v[a23][a3]=0;
				t3=System.nanoTime();
				repaint();
			}
		}
		if(ok4==1)
		{
			repaint();
			offScreenGraphics.drawImage(Image4, cx4-20, cy4-20, this);
			long Time4 = System.nanoTime() - time4;
			if(Time4/1000000>1000)
			{
				ok4=0;
				boom(A24,A14,Bomb3Level);
				bomb4=1;
				v[A24][A14]=0;
				t4=System.nanoTime();
				repaint();
			}
		}
		
		//à¹€à¸§à¸¥à¸²à¸£à¸°à¹€à¸šà¸´à¸”
		if(bomb1==1)
		{
			int bomblevel=Bomb1Level;
				repaint();
				offScreenGraphics.drawImage(ImageC, cx1-20, cy1-10, this);
				if(bomblevel==1)
				{
						offScreenGraphics.drawImage(ImageL, cx1-70, cy1-10, this);
						offScreenGraphics.drawImage(ImageR, cx1+25, cy1-10, this);
						offScreenGraphics.drawImage(ImageU, cx1-20, cy1-55, this);
						offScreenGraphics.drawImage(ImageD, cx1-20, cy1+35, this);
				}
				else
				{
					for(int i=1;i<bomblevel;i++)
						{
						offScreenGraphics.drawImage(ImageO, cx1-20-50*i, cy1-10, this);
						offScreenGraphics.drawImage(ImageO, cx1-20+50*i, cy1-10, this);
						offScreenGraphics.drawImage(ImageV, cx1-20, cy1-10-50*i, this);
						offScreenGraphics.drawImage(ImageV, cx1-20, cy1-10+50*i, this);
						}
					for(int i=0;i<bomblevel;i++) {
						offScreenGraphics.drawImage(ImageL, cx1-20-50*Bomb1Level, cy1-10, this);
						offScreenGraphics.drawImage(ImageR, cx1-20+50*Bomb1Level, cy1-10, this);
						offScreenGraphics.drawImage(ImageU, cx1-20, cy1-10-50*Bomb1Level, this);
						offScreenGraphics.drawImage(ImageD, cx1-20, cy1-10+50*Bomb1Level, this);
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
				offScreenGraphics.drawImage(ImageC, cx2-20, cy2-10, this);
				if(Bomb2Level==1)
				{
					offScreenGraphics.drawImage(ImageL, cx2-70, cy2-10, this);
					offScreenGraphics.drawImage(ImageR, cx2+25, cy2-10, this);
					offScreenGraphics.drawImage(ImageU, cx2-20, cy2-55, this);
					offScreenGraphics.drawImage(ImageD, cx2-20, cy2+35, this);
				}
				else
				{
					for(int i=1;i<Bomb2Level;i++)
						{
						offScreenGraphics.drawImage(ImageO, cx2-20-50*i, cx2-10, this);
						offScreenGraphics.drawImage(ImageO, cx2-20+50*i, cx2-10, this);
						offScreenGraphics.drawImage(ImageV, cx2-20, cx2-10-50*i, this);
						offScreenGraphics.drawImage(ImageV, cx2-20, cx2-10+50*i, this);
						}
					offScreenGraphics.drawImage(ImageL, cx2-20-50*Bomb2Level, cx2-10, this);
					offScreenGraphics.drawImage(ImageR, cx2-20+50*Bomb2Level, cx2-10, this);
					offScreenGraphics.drawImage(ImageU, cx2-20, cx2-10-50*Bomb2Level, this);
					offScreenGraphics.drawImage(ImageD, cx2-20, cx2-10+50*Bomb2Level, this);
						
				}
				long Time2 = System.nanoTime() - t2;
				if(Time2/1000000>1000) //2 seconds explode
				{
						bomb2=0;
						repaint();
				}
		}
		if(bomb3==1) {
			repaint();
			offScreenGraphics.drawImage(ImageC, cx3-20, cy3-10, this);
			if(Bomb4Level==1)
			{
				offScreenGraphics.drawImage(ImageL, cx3-70, cy3-10, this);
				offScreenGraphics.drawImage(ImageR, cx3+25, cy3-10, this);
				offScreenGraphics.drawImage(ImageU, cx3-20, cy3-55, this);
				offScreenGraphics.drawImage(ImageD, cx3-20, cy3+35, this);
			}
			else
			{
				for(int i=1;i<Bomb3Level;i++)
					{
					offScreenGraphics.drawImage(ImageO, cx3-20-50*i, cx3-10, this);
					offScreenGraphics.drawImage(ImageO, cx3-20+50*i, cx3-10, this);
					offScreenGraphics.drawImage(ImageV, cx3-20, cx3-10-50*i, this);
					offScreenGraphics.drawImage(ImageV, cx3-20, cx3-10+50*i, this);
					}
				offScreenGraphics.drawImage(ImageL, cx3-20-50*Bomb3Level, cx3-10, this);
				offScreenGraphics.drawImage(ImageR, cx3-20+50*Bomb3Level, cx3-10, this);
				offScreenGraphics.drawImage(ImageU, cx3-20, cx3-10-50*Bomb3Level, this);
				offScreenGraphics.drawImage(ImageD, cx3-20, cx3-10+50*Bomb3Level, this);
					
			}
			long Time3 = System.nanoTime() - t3;
			if(Time3/1000000>1000) //2 seconds explode
			{
					bomb3=0;
					repaint();
			}
		}
		if(bomb4==1) {
			repaint();
			offScreenGraphics.drawImage(ImageC, cx4-20, cy4-20, this);
			if(Bomb4Level==1)
			{
				offScreenGraphics.drawImage(ImageL, cx4-70, cy4-10, this);
				offScreenGraphics.drawImage(ImageR, cx4+25, cy4-10, this);
				offScreenGraphics.drawImage(ImageU, cx4-20, cy4-55, this);
				offScreenGraphics.drawImage(ImageD, cx4-20, cy4+35, this);
			}
			else
			{
				for(int i=1;i<Bomb4Level;i++)
					{
					offScreenGraphics.drawImage(ImageO, cx4-20-50*i, cx4-10, this);
					offScreenGraphics.drawImage(ImageO, cx4-20+50*i, cx4-10, this);
					offScreenGraphics.drawImage(ImageV, cx4-20, cx4-10-50*i, this);
					offScreenGraphics.drawImage(ImageV, cx4-20, cx4-10+50*i, this);
					}
				offScreenGraphics.drawImage(ImageL, cx4-20-50*Bomb4Level, cx4-10, this);
				offScreenGraphics.drawImage(ImageR, cx4-20+50*Bomb4Level, cx4-10, this);
				offScreenGraphics.drawImage(ImageU, cx4-20, cx4-10-50*Bomb4Level, this);
				offScreenGraphics.drawImage(ImageD, cx4-20, cx4-10+50*Bomb4Level, this);
					
			}
			long Time4 = System.nanoTime() - t4;
			if(Time4/1000000>1000) //2 seconds explode
			{
					bomb4=0;
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
			}else {
				sh3--;
			}}
		if(yy==y2&&xx==x2) {
			if(sh2==0) {
				Player2=0;
			}else {
				sh2--;
			}}
		if(yy==y1&&xx==x1) {
			if(sh1==0) {
				Player1=0;
			}else {
				sh1--;
			}
			}
		if(yy==y4&&xx==x4) {
			if(sh4==0) {
				Player4=0;
			}else {
				sh4--;
			}}
	}
	public void end(int p1,int p2,int p3,int p4) {
		if(p1==1&&p2==0&&p3==0&&p4==0) {
			
		}else if(p1==0&&p2==1&&p3==0&&p4==0) {
			
		}else if(p1==0&&p2==0&&p3==1&&p4==0) {
			
		}else if(p1==0&&p2==0&&p3==0&&p4==1) {
			
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
					if(Bomb1Level<6) {
						Bomb1Level++;
					}
					v[a][b]=0;
				}
			else if(player==2){
					if(Bomb2Level<6) {
						Bomb2Level++;
					}
					v[a][b]=0;
				}
			else if(player==3) {
				if(Bomb3Level<6) {
					Bomb3Level++;
				}
				v[a][b]=0;
			}else if(player==4) {
				if(Bomb4Level<6) {
					Bomb4Level++;
				}
				v[a][b]=0;
			}
			
		}else if(v[a][b]==4) {
			if(player==1)
			{
				sh1=1;
				v[a][b]=0;
			}
		else if(player==2){
				sh2=1;
				v[a][b]=0;
			}
		else if(player==3) {
			sh3=1;
			v[a][b]=0;
		}
		else if(player==4) {
			sh4=1;
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
	public static void main(String[] args) {
		bmb game=new bmb();
	}
}