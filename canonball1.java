import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class canonball1 extends JFrame
{
	JButton fire,set;
	TextField text,text2;
	static int x,y,angle=0,motion=0,count=0,tarx,score=0,tary,tarwidth=100,tarheight1=100;
	static float gv=.001F,vx=0.0F,vy=0.0F,v=0.0F,angle1=0.0F;
	static int tag=0,sleep=0;
	boolean flag=true;
	Display display=new Display();
	public class Display extends JComponent
	{
		public void paintComponent(Graphics g)
		{
			if(flag)
			{
				tarx=(int)(500+500*Math.random());
				tary=(int)(10+500*Math.random());
				flag=false;
			}
			g.setColor(Color.RED);
			if(tag==1)
			{
				g.fillOval(x-10,y-10,20,20);
				g.setColor(Color.GREEN);
				g.drawOval(x-10,y-10,20,20);
			}
			g.setColor(Color.RED);
			g.fillOval(tarx-50,tary-50,100,100);
			g.setColor(Color.BLUE);
			g.drawLine(0,500,1200,500);
			g.setColor(new Color(40,60,0));
			 
			g.fillRect(50-motion,466,40,30);
			g.fillOval(50-motion ,452,40,40);
			g.fillArc(60-motion,466,60,60,0,90);
			g.fillArc(20-motion,466,60,60,90,90);
			g.fillOval(28-motion,472,28,28);
			g.fillOval(82-motion,472,28,28);
			g.setColor(Color.RED);
			g.setFont(new Font("Italic", Font.BOLD, 40));
    		g.drawString("SCORE : "+score,500, 40);
    		if(((tarx-x)*(tarx-x)+(tary-y)*(tary-y))<=50*50)
    		{
    			g.setColor(new Color(0,200,250));
    			g.fillOval(tarx-50,tary-50,100,100);
    			flag=true;
    			score+=5;
    			tag=0; 
    			gv=0;
    			x=(int)(65+80*Math.cos(angle1));
				y=(int)(457-80*Math.sin(angle1));
    		}	
			Graphics2D g2d = (Graphics2D)g;
    		Rectangle rect1 = new Rectangle(100-motion, 100, 100, 20);
    		g2d.setColor(Color.WHITE);
    		g2d.translate(65-motion,462);
    		g2d.rotate(Math.toRadians(-angle));
    		g.setColor(new Color(40,60,0));
    		g2d.fillRect(0-motion,-5,80,10);
    		going();
		}
	}
	 
	public class buttonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{	
			String s=a.getActionCommand();
			if(s.equals("Fire"))
			{
				tag=1;
				motion=5;
				count=0;
			}
			if(s.equals("Set"))
			{
				String str=text.getText();
				angle=Integer.parseInt(str);
				angle1=(float)Math.toRadians(angle);
				String str2=text2.getText();
				v=Float.parseFloat(str2);
				vx=(float)(v*(Math.cos(angle1)));
				vy=(float)(v*(Math.sin(angle1)));
				x=(int)(65+80*Math.cos(angle1));
				y=(int)(457-80*Math.sin(angle1));
			}	
			going();
		}	
	}
	public void going()
	{
		if(tag==1)
		{
		if(sleep%25==0)
		{
			x=x+(int)vx;
			y=y+(int)(gv-vy);
			gv+=.5;
			sleep=0;
			
		}	
		if(count>250)
		{
			motion=0;
			count=0;
		} 
		count++;
		sleep++;
		if(x>=1101||y<=0||y>500)
		{
			x=(int)(65+80*Math.cos(angle1));
			y=(int)(457-80*Math.sin(angle1));
			gv=0;
			tag=0;
		}
		}			 
		repaint();
	}
	public canonball1(String title)
	{
		super(title);
		fire=new JButton("Fire");
		set=new JButton("Set");
		text=new TextField(4);
		text2=new TextField(4);
		JPanel south=new JPanel();
		Container c=this.getContentPane();
		c.setBackground(new Color(0,200,250));
		this.add(display);
		south.add(new JLabel("Angle : "));
		south.add(text);
		south.add(new JLabel("Speed : "));
		set.setBackground(new Color(0,250,0));
		fire.setBackground(new Color(250,0,0));
		text2.setBackground(Color.YELLOW);
		text.setBackground(Color.YELLOW);
		south.add(text2);
		south.add(set);
		south.add(fire);
		this.add(south,BorderLayout.SOUTH);
		buttonHandler listener=new buttonHandler();
		//mouseDragHandler mouse=new  mouseDragHandler();
		fire.addActionListener(listener);
		set.addActionListener(listener);
		//this.addMouseListener(mouse);
        //this.addMouseMotionListener(mouse);
		this.add(display);
	}
	public static void main(String args[])
	{
		JFrame f=new canonball1("CANONBALL");
		f.setSize(1200,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}









/*private class MyListener extends MouseInputAdapter {
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        currentRect = new Rectangle(x, y, 0, 0);
        updateDrawableRect(getWidth(), getHeight());
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
        updateSize(e);
    }

    public void mouseReleased(MouseEvent e) {
        updateSize(e);
    }

    void updateSize(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        currentRect.setSize(x - currentRect.x,
                            y - currentRect.y);
        updateDrawableRect(getWidth(), getHeight());
        Rectangle totalRepaint = rectToDraw.union(previouseRectDrawn); 
        repaint(totalRepaint.x, totalRepaint.y,
                totalRepaint.width, totalRepaint.height);
    }
}*/








/*Graphics2D g2d = (Graphics2D)g;
    Rectangle rect1 = new Rectangle(100, 100, 20, 20);
    g2d.setColor(Color.WHITE);
    g2d.translate(rect1.x+(rect1.width/2), rect1.y+(rect1.height/2));
    g2d.rotate(Math.toRadians(90));
    g2d.draw(rect1);
    g2d.fill(rect1);*/