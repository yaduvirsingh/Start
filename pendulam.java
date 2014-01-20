import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class pendulam extends JFrame
{
	JButton start,speed,stop;
	TextField text;
	static int cx1=300,cy1=20,cx2=900,cy2=540;
	static int tag=0,r=250;
	static float diff1=-0.001F,diff2,initdiff1 , t1=(float)(Math.PI/2),t2=(float)(Math.PI/2);
	static int x1=300,y1=270,x2=900,y2=290;
	Display display=new Display();
	public class Display extends JComponent
	{
		public void paintComponent(Graphics g)
		{
			g.setColor(Color.GREEN);
			g.fillOval(cx1-10,cy1-10,20,20);
			g.drawOval(cx1-10,cy1-10,20,20);
			g.drawLine(cx1,cy1,x1,y1);
			g.setColor(Color.RED);
    		g.fillOval(x1-20,y1-20,40,40);
    		g.setColor(Color.BLUE);
    		g.drawOval(x1-20,y1-20,40,40);

    		g.setColor(Color.GREEN);
			g.fillOval(cx2-10,cy2-10,20,20);
			g.drawOval(cx2-10,cy2-10,20,20);
			g.drawLine(cx2,cy2,x2,y2);
			g.setColor(Color.RED);
    		g.fillOval(x2-20,y2-20,40,40);
    		g.setColor(Color.BLUE);
    		g.drawOval(x2-20,y2-20,40,40);
    		if(tag==1)
    		going();
		}
	}
	public class buttonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			String s=a.getActionCommand();
			if(s.equals("start"))
			{
					tag=1; 
			}			
			if(s.equals("speed"))
			{
				 
				String str=text.getText();
				initdiff1=(Integer.parseInt(str))*0.0001F;
				diff1=initdiff1;
				 
				 
			}
			if(s.equals("stop"))
			{
				tag=0;
			}
			going();
		}
		
	}
	public void going()
	{
		
		x1=300+(int )(r*Math.cos(t1));
		y1=20+(int)(r*Math.sin(t1));
		x2=900+(int )(r*Math.cos(t2));
		y2=540-(int)(r*Math.sin(t2));
		if(x1<=60||x1>=540)
		{
			initdiff1=-initdiff1;
		}
		 
		diff1=(float)(initdiff1*Math.sin(t1));
		t1=t1+diff1;
		t2=t2+diff1;
		repaint();
	}
	public pendulam(String title)
	{
		super(title);
		start=new JButton("start");
		start.setBackground(Color.GREEN);
		speed=new JButton("speed");
		speed.setBackground(Color.YELLOW);
		text=new TextField(4);
		stop=new JButton("stop");
		stop.setBackground(Color.RED);
		JPanel south=new JPanel();
		south.add(start);
		south.add(speed);
		south.add(text);
		south.add(stop);
		this.add(south,BorderLayout.SOUTH);
		buttonHandler listener=new buttonHandler();
		start.addActionListener(listener);
		speed.addActionListener(listener);
		stop.addActionListener(listener);
		Container c=this.getContentPane();
		c.setBackground(new Color(0,50,10));
		this.add(display);

	}
	public static void main(String args[])
	{
		JFrame f=new pendulam("PENDULAM");
		f.setSize(1300,700);
		f.setVisible(true);
	}
}