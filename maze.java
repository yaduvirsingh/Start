import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Display extends JPanel implements KeyListener,FocusListener
{

	int mouseX,mouseY,mouseWidth,mouseHeight,mouseValueX,mouseValueY;
	boolean [][]b=new boolean[][]{{false,true,true,true,true,true,true,true,true,false},
					{false,true,false,false,false,false,false,false,true,false},
					{false,true,false,true,true,true,false,true,true,false},
					{false,true,false,true,false,true,false,false,true,false},
					{false,true,false,true,false,true,false,true,true,false},
					{true,true,true,true,false,true,false,false,false,false},
					{false,true,false,false,false,true,true,true,true,true},
					{false,true,false,true,false,true,false,false,false,false},
					{false,true,false,true,false,true,false,false,true,false},
					{false,true,true,true,true,true,true,true,true,false}};
	boolean hori=true,vert=false;

	public class squre
	{
		int x,y,width,height,value;
		boolean isRoot;
		public squre(int x,int y,int w,int h,boolean r,int v)
		{
			this.x=x;
			this.y=y;
			this.width=w;
			this.height=h;
			this.isRoot=r;
			this.value=v;
		}
	}
	public static squre sq[][];

		public Display()
		{
			int index=0;
			addKeyListener(this);
			addFocusListener(this);
			sq=new squre[10][10];
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					sq[i][j]=new squre(j*50,i*50+50,50,50,b[i][j],index);
					index++;
				}
			}
			mouseX=sq[5][0].x;
			mouseY=sq[5][0].y;
			mouseWidth=50;
			mouseHeight=50;
			mouseValueX=0;
			mouseValueY=5;
		}

		public void paintComponent(Graphics g)
		{	
			requestFocus();
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					if(!sq[i][j].isRoot)
					g.setColor(new Color(250,10,60));
					else
					g.setColor(new Color(240,240,240));
					g.fillRect(sq[i][j].x,sq[i][j].y,sq[i][j].width,sq[i][j].height);
				}
			}
			g.setColor(new Color(250,10,60));
			g.fillRect(0,550,700,50);
			g.fillRect(0,0,700,50);
			Image img1 = Toolkit.getDefaultToolkit().getImage("mouse2.jpg");
    		g.drawImage(img1,mouseX,mouseY,mouseWidth,mouseHeight,this);
			if((mouseValueX==7&&mouseValueY==2)||(mouseValueX==7&&mouseValueY==4)||(mouseValueX==3&&mouseValueY==7)||(mouseValueX==8&&mouseValueY==8))
			{
				g.setColor(Color.BLUE);
				g.setFont(new Font("Italic", Font.BOLD, 20));
    			g.drawString("LOSS!!!",200,20);
			}
			else if(mouseValueX==9&&mouseValueY==6)
			{
				g.setColor(Color.BLUE);
				g.setFont(new Font("Italic", Font.BOLD, 20));
    			g.drawString("COMPLETED!!!",150,20);
			}
		}
		public void keyPressed(KeyEvent k)
		{
			int key = k.getKeyCode();
			if(key == KeyEvent.VK_LEFT)
			{
				if(hori&&mouseValueX>0&&sq[mouseValueY][mouseValueX-1].isRoot)
				{
					vert=false;
					mouseX-=2;
					if(mouseX==sq[mouseValueY][mouseValueX-1].x)
					{
						 vert=true;
						mouseValueX-=1;
					}
					if(mouseX==sq[mouseValueY][mouseValueX].x)
						 vert=true;
					repaint();
				}
			}
			else if(key == KeyEvent.VK_RIGHT)
			{
				if(hori&&mouseValueX<9&&sq[mouseValueY][mouseValueX+1].isRoot)
				{
					mouseX+=2;
					vert=false;
					if(mouseX==sq[mouseValueY][mouseValueX+1].x)
					{
						mouseValueX+=1;
						vert=true;
					} 
					if(mouseX==sq[mouseValueY][mouseValueX].x)
						 vert=true;
					repaint();
				}
			}
			else if(key == KeyEvent.VK_UP)
			{
				if(vert&&mouseValueY>0&&sq[mouseValueY-1][mouseValueX].isRoot)
				{
					mouseY-=2;
					hori=false;
					if(mouseY==sq[mouseValueY-1][mouseValueX].y)
					{
						 hori=true;
						mouseValueY-=1;
					} 
					if(mouseY==sq[mouseValueY][mouseValueX].y)
						 hori=true;
					repaint();
				}
			}
			else if(key == KeyEvent.VK_DOWN)
			{
				if(vert&& mouseValueY<9&&sq[mouseValueY+1][mouseValueX].isRoot)
				{
					hori=false;
					mouseY+=2;
					if(mouseY==sq[mouseValueY+1][mouseValueX].y)
					{
						hori=true;
						mouseValueY+=1;
					} 
					if(mouseY==sq[mouseValueY][mouseValueX].y)
						 hori=true;
					repaint();
				}
			}
			 
		}
		public void keyTyped(KeyEvent k){}
		public void keyReleased(KeyEvent k){}

		public void focusGained(FocusEvent f)
		{
			repaint();
		}
		public void focusLost(FocusEvent f)
		{
			repaint();
		}
	}

 
public class maze extends JFrame
{
	public maze(String s)
	{
		super(s);
		Display display=new Display();
		this.setContentPane(display);
	}
	public static void main(String [] args)
	{
		JFrame frame = new maze("Knight");
		frame.setBounds(100,100,515,600);
		frame.setVisible(true);
	}
}
 