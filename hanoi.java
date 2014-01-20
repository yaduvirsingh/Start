import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class hanoi extends JFrame
{
	static int NoD,Sep,HoT,WoT=20,dragingdisk,prevX,prevY,previostour,framX,framY,frameCenterX,frameCenterY,frameHeight;
	static boolean draw=false,tag=false,flag=false;
	TextField text;
	JButton start;
	String str;
	/* Hot height of tour
	* NoD no of disk
	* sep sepration between tour
	* WoT width of tour 
	* CoT center of tour*/
	public class Tour
	{
		private int x,y,width,height,center,diskNo,contain[];
		public Tour(int x,int y,int c,int w,int h,int n)
		{
			this.x=x;
			this.y=y;
			this.width=w;
			this.height=h;
			this.center=c;
			this.diskNo=n;
			this.contain=new int[NoD];
		}
	}
	public class Disk
	{
		private int x,y,center,width,height,place,value;
		public Disk(int x,int y,int c,int w,int h,int p,int v)
		{
			this.x=x;
			this.y=y;
			this.width=w;
			this.height=h;
			this.place=p;
			this.center=c;
			this.value=v;
		}
	}

	public class Display extends JComponent 
	{
		public void paintComponent(Graphics g)
		{
			if(draw)
			{
				if(ltour.diskNo==NoD)
				{
					g.setColor(Color.RED);
					g.setFont(new Font("Italic", Font.BOLD, 40));
    				g.drawString("COMPLETED!!!",150, 200);
    				draw=false;
				}
				g.setColor(Color.BLUE);
				g.drawLine(0,frameHeight,2*frameCenterX,frameHeight);
				g.fillRect(ftour.x,ftour.y,ftour.width,ftour.height);
				g.fillRect(mtour.x,mtour.y,mtour.width,mtour.height);
				g.fillRect(ltour.x,ltour.y,ltour.width,ltour.height);
				
				for(int i=0;i<NoD;i++)
				{
					g.setColor (Color.RED);
					g.fillRect(disks[i].x,disks[i].y,disks[i].width,disks[i].height);
					g.setColor(Color.GREEN);
					g.drawRect(disks[i].x,disks[i].y,disks[i].width,disks[i].height);
				}	
			}		
		}
	}
	Display display=new Display();
	Tour ftour,mtour,ltour;
	Disk []disks; 
	public class buttonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			String s=a.getActionCommand();
			if(s.equals("Start"))
			{
				draw=true;
				str=text.getText();
				NoD=Integer.parseInt(str);
				HoT=NoD*20;
				Sep=30*NoD+20;
				setData();	
			}
		}
	}
	public class mouseDragHandler implements MouseListener, MouseMotionListener 
	{
		private int startX, startY; // Point where the original mousePress occurred. 
		private int prevX, prevY;// Most recently processed mouse coords. 
		private boolean dragging; // Set to true when dragging is in process. 
		public void mousePressed(MouseEvent evt) 
		{ 
			int mx= evt.getX();
			int my=evt.getY();
			for(int i=0;i<NoD;i++)
			{
				if(mx>=disks[i].x&&mx<=disks[i].x+disks[i].width&&my>=disks[i].y+20&&my<=disks[i].y+disks[i].height+20)
				{
					 
					if(disks[i].center==ftour.center&&ftour.diskNo==disks[i].place)
					{
						dragging = true;
						dragingdisk=i;
						previostour=1;
						prevX=disks[i].x;
						prevY=disks[i].y;
						flag=true;
						break;
					}
					else if(disks[i].center==mtour.center&&mtour.diskNo==disks[i].place)
					{	 
						dragging = true;
						dragingdisk=i;
						previostour=2;
						prevX=disks[i].x;
						prevY=disks[i].y;
						flag=true;
						break;
					}
					else if(disks[i].center==ltour.center&&ltour.diskNo==disks[i].place)
					{ 
						dragging = true;
						dragingdisk=i;
						previostour=3;
						prevX=disks[i].x;
						prevY=disks[i].y;
						flag=true;
						break;
					} 
				}
			}
		}
		public void mouseDragged(MouseEvent evt) 
		{
			if ( dragging == false ) // First, check if we are 
			return;
			int mx = evt.getX(); 
			int my = evt.getY();
			disks[dragingdisk].x=mx-20;
			disks[dragingdisk].y=my-20; 
			going();
		}
		public void mouseReleased(MouseEvent evt) 
		{
			if(disks[dragingdisk].x>=ftour.center-Sep/2&&disks[dragingdisk].x<=ftour.center+Sep/2)
			{	
				if(ftour.diskNo==0||ftour.contain[ftour.diskNo-1]>dragingdisk)
				{	
					disks[dragingdisk].x=ftour.center-15*(dragingdisk+1);
					disks[dragingdisk].y=frameHeight-((ftour.diskNo+1)*20);
					disks[dragingdisk].place=ftour.diskNo+1;
					disks[dragingdisk].center=ftour.center;
					ftour.contain[ftour.diskNo]=dragingdisk;
					ftour.diskNo+=1;
					tag=true;
				}
			}
			else if(disks[dragingdisk].x>=mtour.center-Sep/2&&disks[dragingdisk].x<=mtour.center+Sep/2)
			{	
				if(mtour.diskNo==0||mtour.contain[mtour.diskNo-1]>dragingdisk)
				{	
					disks[dragingdisk].x=mtour.center-15*(dragingdisk+1);
					disks[dragingdisk].y=frameHeight-((mtour.diskNo+1)*20);
					disks[dragingdisk].place=mtour.diskNo+1;
					disks[dragingdisk].center=mtour.center;
					mtour.contain[mtour.diskNo]=dragingdisk;
					mtour.diskNo+=1;
					tag=true;
				}
			}
			else if(disks[dragingdisk].x>=ltour.center-Sep/2&&disks[dragingdisk].x<=ltour.center+Sep/2)
			{	
				if(ltour.diskNo==0||ltour.contain[ltour.diskNo-1]>dragingdisk)
				{	
					disks[dragingdisk].x=ltour.center-15*(dragingdisk+1);
					disks[dragingdisk].y=frameHeight-((ltour.diskNo+1)*20);
					disks[dragingdisk].place=ltour.diskNo+1;
					disks[dragingdisk].center=ltour.center;
					ltour.contain[ltour.diskNo]=dragingdisk;
					ltour.diskNo+=1;
					tag=true;
				}
			}
			if(tag)
			{
				
				tag=false;
				flag=false;
				if(previostour==1)
					ftour.diskNo-=1;
				else if(previostour==2)
					mtour.diskNo-=1;
				else if(previostour==3)
					ltour.diskNo-=1;	
			}
			else if(flag)
			{
				flag=false;
				disks[dragingdisk].x=prevX;
				disks[dragingdisk].y=prevY;
			}
			going();
			if ( dragging == false ) // First, check if we are
			 return;
			 dragging = false;
		}
		public void mouseEntered(MouseEvent evt) { }   // Some empty routines.
      	public void mouseExited(MouseEvent evt) { }    //    (Required by the MouseListener
      	public void mouseClicked(MouseEvent evt) { }   //    and MouseMotionListener
      	public void mouseMoved(MouseEvent evt) { } 
	}
	public void going()
	{
		repaint();
	}
	public void setData()
	{	 
		frameHeight=this.getHeight()-100;
		frameCenterX=(int)(this.getWidth()/2);
		frameCenterY=(int)(this.getHeight()/2);
		mtour=new Tour(frameCenterX,frameHeight-HoT,frameCenterX+10,20,HoT,0);
		ftour=new Tour(frameCenterX-Sep,frameHeight-HoT,frameCenterX-Sep+10,20,HoT,NoD);
		for(int i=0;i<NoD;i++)
		{
			ftour.contain[i]=NoD-i-1;
		}
		ltour=new Tour(frameCenterX+Sep,frameHeight-HoT,frameCenterX+Sep+10,20,HoT,0);
		disks =new Disk[NoD];
		for(int i=0;i<NoD;i++)
		{
			disks[i]=new Disk(ftour.center-15*(i+1),frameHeight-(HoT-20*i),ftour.center, 30*(i+1),20,NoD-i,i);
		}
		repaint();
	}
	public hanoi(String title)
	{
		super(title);
		//JPanel north=new JPanel();
		this.add(display);
		//this.add(north,BorderLayout.NORTH);
		JPanel south=new JPanel();
		south.add(new JLabel("NO. Of Disk :"));
		text = new TextField();
		south.add(text);
		start=new JButton("Start");
		south.add(start);
		this.add(south,BorderLayout.SOUTH);
		buttonListener listener=new buttonListener();
		start.addActionListener(listener);
		mouseDragHandler mouse=new  mouseDragHandler();
		this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        Container c=this.getContentPane();
        c.setBackground(new Color(0,50,10));
	}
	public static void main(String[] args) 
	{
		JFrame f=new hanoi("Hanoi");
		f.setSize(600,400);
		f.setVisible(true);	
	}
}