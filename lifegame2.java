import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
public class lifegame2 extends JFrame
{
	int mx,my,px,py,pwidth,pheight,max,count;
	boolean flag=false;
	public class squre
	{
		int x,y,width,height;
		boolean preLife;
		boolean newLife;
		public squre(int x,int y,int w,int h)
		{
			this.x=x;
			this.y=y;
			this.width=w;
			this.height=h;
			this.preLife=false;
			this.newLife=false;
		}
	}
	squre sq[][];

	public class mouseDragHandler implements MouseListener, MouseMotionListener 
	{
		private int startX, startY; // Point where the original mousePress occurred. 
		private int prevX, prevY;// Most recently processed mouse coords. 
		private boolean dragging; // Set to true when dragging is in process. 
		public void mousePressed(MouseEvent evt) {}
		public void mouseDragged(MouseEvent evt) {}
		public void mouseReleased(MouseEvent evt) {}
		public void mouseEntered(MouseEvent evt) {}
      	public void mouseExited(MouseEvent evt) { }    //    (Required by the MouseListener
      	public void mouseClicked(MouseEvent evt) 
      	{

      		mx= evt.getX();
			my= evt.getY();
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					px=sq[i][j].x;
					py=sq[i][j].y;
					pwidth=sq[i][j].width;
					pheight=sq[i][j].height;
					if(mx>=px&&mx<=px+pwidth&&my>=py&&my<=py+pheight)
					{
						sq[i][j].newLife=true;
					}
				}
			}
			paint();
      	}   //    and MouseMotionListener
      	public void mouseMoved(MouseEvent evt) { }

	}

	public void paint()
	{
		 Graphics g=getGraphics();
		for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					g.setColor(new Color(100,100,100));
					if(sq[i][j].newLife)
					g.setColor(Color.RED);			
					g.fillRect(sq[i][j].x,sq[i][j].y,sq[i][j].width,sq[i][j].height);
					g.setColor(Color.BLUE);
					g.drawRect(sq[i][j].x,sq[i][j].y,sq[i][j].width,sq[i][j].height);
				}
			}
	}
	public void change()
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				sq[i][j].preLife=sq[i][j].newLife;
			}
		}
		for(int i=0;i<10;i++)
		{
			max=0;
			count=0;
			for(int j=0;j<10;j++)
			{
				count=0;
				if(i!=0 && j!=0 && sq[i-1][j-1].preLife==true)
					count++;
				if(i!=0 && sq[i-1][j].preLife)
					count++;
				if(i!=0 && j!=9 && sq[i-1][j+1].preLife==true)
					count++;
				if(j!=0 && sq[i][j-1].preLife==true)
					count++;
				if(j!=9 && sq[i][j+1].preLife==true)
					count++;
				if(i!=9 && j!=0 && sq[i+1][j-1].preLife==true)
					count++;
				if(i!=9 && sq[i+1][j].preLife==true)
					count++;
				if(i!=9&& j!=9 && sq[i+1][j+1].preLife==true)
					count++;
				if(max<count)
					max=count;
				if(count==3)
				{
					sq[i][j].newLife=true;
				}	
				else if(count>=4||count<2)
				{
					sq[i][j].newLife=false;
				}
			}	
		}
		paint();
	}
	public void going()
	{
		
		change();
		try 
			{
    			Thread.sleep(500);
			} 
			catch(InterruptedException ex) 
			{
    			Thread.currentThread().interrupt();
			}
		
		count=0;
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(sq[i][j].newLife)
					count++;
			}
		}
		
		if(count!=0&&flag);
			going();

	}


	public class buttonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{	
			String s=a.getActionCommand();
			if(s.equals("Set"))
			{
				paint();
			}
			if(s.equals("start"))
			{
				flag=true;
				 going();
			}	 
		}	
	}

	public lifegame2(String s)
	{
		super(s);
		sq=new squre[10][10];
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				sq[i][j]=new squre(i*50+50,j*50+50,50,50);
			}
		}
		mouseDragHandler mouse=new  mouseDragHandler();
		this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        buttonHandler listener=new buttonHandler();
        JButton start=new JButton("start");
        JButton set=new JButton("Set");
        JPanel south=new JPanel();
        south.add(set);
        south.add(start);
        this.add(south,BorderLayout.SOUTH);
        start.addActionListener(listener);
        set.addActionListener(listener);
	}
	public static void main(String args[])
	{
		lifegame2 f=new lifegame2("Life Game");
		f.setSize(600,600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}