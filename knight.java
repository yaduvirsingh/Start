import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class knight extends JFrame
{
	TextField text;
	int mx,my,x,y,tag=0,value,px,py,pwidth,pheight;
	JButton start,next;
	mypanel [][]panel;
	int [][]arr=new int[][]
	{
		{1 ,16 ,27 ,22, 3, 18, 47, 56}, 
		{26, 23, 2, 17, 46, 57, 4, 19}, 
		{15, 28, 25, 62, 21, 48, 55, 58},
		{24, 35, 30, 45, 60, 63, 20, 5},
		{29, 14, 61, 34, 49, 44, 59, 54}, 
		{36, 31, 38, 41, 64, 53, 6, 9}, 
		{13, 40, 33, 50, 11, 8, 43, 52}, 
		{32, 37, 12, 39, 42, 51, 10, 7}
	};
	public class mypanel extends JPanel
	{
		boolean visited;
		int value;
		mypanel(boolean b1,int v)
		{
			this.visited=b1;
			this.value=v;
		}
	}
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
			my= evt.getY()-60;
			System.out.println(mx+" "+my);
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					px=panel[i][j].getX();
					py=panel[i][j].getY();
					pwidth=panel[i][j].getWidth();
					pheight=panel[i][j].getHeight();
					if(mx>=px&&mx<=px+pwidth&&my>=py&&my<=py+pheight)
					{
						panel[i][j].setBackground(Color.YELLOW);
						x=i;y=j;
						panel[i][j].visited=true;
						value=panel[i][j].value;
					}
				}
			}
      	}   //    and MouseMotionListener
      	public void mouseMoved(MouseEvent evt) { } 
	}

	public class buttonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			String s=a.getActionCommand();
			if(s.equals("start"))
			{

				for(int i=0;i<8;i++)
				{
					for(int j=0;j<8;j++)
					{
						panel[i][j].setBackground(Color.PINK);
						panel[i][j].visited=false;
					}
				}

			}
			if(s.equals("next"))
			{
				for(int i=0;i<8;i++)
				{
					tag=0;
					for(int j=0;j<8;j++)
					{
						if(panel[i][j].value==value+1)
						{
							value++;
							panel[x][y].setBackground(Color.RED);
							panel[i][j].visited=true;
							x=i;y=j;
							panel[i][j].setBackground(Color.YELLOW);
							tag=1;
							break;
						}
					}
					if(tag==1)
						break;
				}
				if(value==64)
					value=0;
			}
		}
	}
	public knight(String title)
	{
		super(title);
		//text.setBackground(Color.RED);
		JPanel north=new JPanel();
		north.add(new JLabel("Click on any squre then press next"));
		this.add(north,BorderLayout.NORTH);
		JPanel center =new JPanel();
		center.setLayout(new GridLayout(8,8,1,1));
		panel=new mypanel[8][];
		for(int i=0;i<8;i++)
		{
			panel[i]=new mypanel[8];
		}
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				panel[i][j]=new mypanel(false,arr[i][j]);
				panel[i][j].setBackground(Color.PINK);
				center.add(panel[i][j]);
			}
		}
		this.add(center,BorderLayout.CENTER);
		JPanel south=new JPanel();
		next=new JButton("next");
		start=new JButton("start");
		south.add(start);
		south.add(next);
		buttonHandler listener=new buttonHandler();
		start.addActionListener(listener);
		next.addActionListener(listener);
		this.add(south,BorderLayout.SOUTH);
		mouseDragHandler mouse=new  mouseDragHandler();
		this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
	}
	public static void main(String args[])
	{
		JFrame f=new knight("knight");
		f.setLocation(100,100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(420,440);
		f.setVisible(true);
	}
}