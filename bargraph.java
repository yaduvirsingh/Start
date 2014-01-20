import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import javax.swing.JFrame;
//import java.awt.Graphics;
//import javax.swing.JComponent;
//import java.awt.Rectangle;

public class bargraph extends JFrame
{
	JButton go,back;
	static int digit=0,lv=0,uv=0,lc=0,uc=0,other=0;
	String str=new String(" ");
	TextField txt;
	char[] chAr;
	static int draw=1;
	public class Display extends JComponent 
	{
		 
  	 	public void paintComponent(Graphics g)  
    	{
    		g.setColor(Color.RED);
    		g.setFont(new Font("Italic", Font.BOLD, 15));
    		g.drawString("digit",42, 420);
    		g.drawString("vowel", 122, 420);
    		g.drawString("VOWEL", 210, 420);
    		g.drawString("consonent",290, 420);
    		g.drawString("CONSONENT",375, 420);
    		g.drawString("Other",485, 420);
       		g.fillRect (40,400-10*digit,50,10*digit);    
        	g.fillRect (120,400-10*lv,50,10*lv);
        	g.fillRect (210,400-10*uv,50,10*uv);
        	g.fillRect (300,400-10*lc,50,10*lc);
        	g.fillRect (390,400-10*uc,50,10*uc);
        	g.fillRect (480,400-10*other,50,10*other); 
        	g.drawLine(10,400,590,400);
        	digit=0;lv=0;uv=0;uc=0;lc=0;other=0;
    	}
	}
	 Display display =new Display();
	public class buttonpress implements ActionListener 
	{
		private Container container;
		public buttonpress(JFrame c)
		{
			container=c.getContentPane();
		}
	 	public void actionPerformed(ActionEvent a)
	 	{
	 		String label = a.getActionCommand();
	 		if(label.equals("Go>>"))
	 		{
				//.setText("sanjay");
				str=txt.getText();
				chAr = str.toCharArray();
				for(int i=0;i<chAr.length;i++)
				{
					if(Character.isDigit(chAr[i]))
					{
						digit++;
					}
					else if(Character.isLowerCase(chAr[i]))
					{
						if(chAr[i]=='a'||chAr[i]=='e'||chAr[i]=='i'||chAr[i]=='o'||chAr[i]=='u')
							lv++;
						else
							lc++;
					}
					else if(Character.isUpperCase(chAr[i]))
					{
						if(chAr[i]=='A'||chAr[i]=='E'||chAr[i]=='I'||chAr[i]=='O'||chAr[i]=='U')
							uv++;
						else
							uc++;
					}
					else
						other++;
				}
	 		}
	 		if(label.equals("Quit!!"))
	 		{
	 			System.exit(0);
	 		}
	 		container.setBackground(Color.PINK);
	 		 rrpaint();
	 	}
	 		
	}
	public void rrpaint()
	{
		repaint();
	}
	public bargraph(String title)
	{
		super(title);
		JPanel northp=new JPanel();
		northp.add(new JLabel("BAR GRAPH"));
		this.add(northp,BorderLayout.NORTH);
		
		
		go=new JButton("Go>>");
		back=new JButton("Quit!!");
		buttonpress btp=new buttonpress(this);
		JPanel southp=new JPanel();
		southp.add(go);
		txt=new TextField(40);
		southp.add(txt);
		southp.add(back);
		go.addActionListener(btp);
		back.addActionListener(btp);
		this.add(southp,BorderLayout.SOUTH);
		this.add(display);
	}
	public static void main(String args[])
	{
		JFrame f=new bargraph("Bargraph");
		f.setSize(600,600);
		f.setVisible(true);
	}
}



/*Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
g2d.drawImage(photo, 0, 0, null);
g2d.setColor(Color.white);
Font font = new Font(fontName, Font.PLAIN, fontSize);
g2d.setFont(font);
g2d.drawString(text,x,y);

BufferedImage img = new BufferedImage(
    w, h, BufferedImage.TYPE_INT_ARGB);
Graphics2D g2d = img.createGraphics();
g2d.drawImage(photo, 0, 0, null);
g2d.setPaint(Color.red);
//example :     g2d.setFont(new Font("Serif"Calibri, Font.BOLD, 15));
g2d.setFont(new Font(fontName, Font.BOLD, size));
String s = "Hello, world!";
// assuming x & y is set using graphic's font metrics
g2d.drawString(s, x, y);
g2d.dispose();

*/