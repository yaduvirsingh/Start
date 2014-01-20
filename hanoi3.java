import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
public class hanoi3 extends JFrame
{
	JButton start;
	static int NoD,Sep,HoT,WoT=20,dragingdisk,prevX,prevY,previostour,framX,framY,frameCenterX,frameCenterY,frameHeight;
	static boolean draw=false,tag=false,flag=false;
	TextField text;
	String str;
	Tour ftour,mtour,ltour;
	Disk []disks; 
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
	public void paint()
	{
			Graphics g=getGraphics();
			g.setColor(new Color(0,50,10));
			g.fillRect(0,0,this.getWidth(),this.getHeight()-100);
			if(ltour.diskNo==NoD)
			{
				g.setColor(Color.RED);
				g.setFont(new Font("Italic", Font.BOLD, 40));
    			g.drawString("COMPLETED!!!",this.getWidth()/2-200,this.getHeight()/2-100);
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
	void sleep(int s)
	{
			try 
			{
    			Thread.sleep(s);
			} 
			catch(InterruptedException ex) 
			{
    			Thread.currentThread().interrupt();
			}
	}
	public void going(int n,Tour t1,Tour t2,Tour t3)
	{
		if(n>0)
		{
			going(n-1,t1,t3,t2);
			dragingdisk=t1.contain[t1.diskNo-1];
			t1.diskNo-=1;
			float fx1,fx2,fy1,fy2,f1,f2;
			fx1=(float)((((t1.center+t3.center)/2)-disks[dragingdisk].x)/50);
			fy1=(float)((100-disks[dragingdisk].y)/50);
			f1=disks[dragingdisk].x;
			f2=disks[dragingdisk].y;
			for(int i=1;i<=50;i++)
			{
				f1+=fx1;
				f2+=fy1;
				disks[dragingdisk].x=(int)(f1);
				disks[dragingdisk].y=(int)(f2);
				paint();
				sleep(20);
			}
			fx2=(float)((t3.center-15*(dragingdisk+1)-((t1.center+t3.center)/2))/50);
			fy2=(float)((frameHeight-((t3.diskNo+1)*20)-100)/50);
			f1=((t1.center+t3.center)/2);
			f2=100;
			for(int i=1;i<=50;i++)
			{
				f1+=fx2;
				f2+=fy2;
				disks[dragingdisk].x=(int)(f1);
				disks[dragingdisk].y=(int)(f2);
				paint();
				sleep(20);
			}

			disks[dragingdisk].x=t3.center-15*(dragingdisk+1);
			disks[dragingdisk].y=frameHeight-((t3.diskNo+1)*20);
			disks[dragingdisk].place=t3.diskNo+1;
			disks[dragingdisk].center=t3.center;
			t3.contain[t3.diskNo]=dragingdisk;
			t3.diskNo+=1;
			paint();
			sleep(1000);
			going(n-1,t2,t1,t3);
		}
	}	
	public void setData()
	{	 
		Sep=(int)(this.getWidth()/4);
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
		going(NoD,ftour,mtour,ltour);
	}
	public hanoi3(String title)
	{
		super(title);
		JPanel south=new JPanel();
		south.add(new JLabel("NO. Of Disk :"));
		text = new TextField();
		south.add(text);
		start=new JButton("Start");
		south.add(start);
		this.add(south,BorderLayout.SOUTH);
		buttonListener listener=new buttonListener();
		start.addActionListener(listener);
        Container c=this.getContentPane();
        c.setBackground(new Color(0,50,10));
	}
	public static void main(String args[])
	{
		JFrame f=new hanoi3("HANOI TOWER");
		f.setSize(600,400);
		f.setVisible(true);
	}
}