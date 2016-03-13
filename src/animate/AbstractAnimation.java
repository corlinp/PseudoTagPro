package animate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public abstract class AbstractAnimation extends JPanel{

	public static void main(String[] args) {
		AbstractAnimation aa = new AbstractAnimation(1000,1000){
			public void draw(Graphics2D g, long frame) {
				g.setColor(Color.black);
				int wid = (int)(Math.sin(frame/50.0) * 100.0) + 100;
				//System.out.println(wasd[0]);
				g.fillOval(500, 500, wid, 80);
			}
		};
		aa.runInFrame();
	}

	int w, h;
	public AbstractAnimation(int w, int h){
		this.w=w;this.h=h;
		this.setBackground(Color.white);
		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e) {
				mouseY = e.getY();
				mouseX = e.getX();
			}
			public void mouseMoved(MouseEvent arg0) {
			}
		});
		//this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"space");
		AbstractAction spaceAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				spaceAction();
			}
		};
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"space");
		this.getActionMap().put("space", spaceAction);

		for(int i = 0; i < wasd.length; i++){
			wasd[i] = false;
			int key = i;
			this.getInputMap().put(KeyStroke.getKeyStroke(wasdKeys[key], 0, false),i+"press");
			this.getActionMap().put(i+"press", new AbstractAction(){
				public void actionPerformed(ActionEvent e) {
					wasd[key] = true;
				}
			});
			this.getInputMap().put(KeyStroke.getKeyStroke(wasdKeys[key], 0, true),i+"release");
			this.getActionMap().put(i+"release", new AbstractAction(){
				public void actionPerformed(ActionEvent e) {
					wasd[key] = false;
				}
			});
		}


		this.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent e) {
				mouseAction(e);
			}
			public void mouseReleased(MouseEvent arg0) {}
		});
	}

	public void spaceAction(){
		System.err.println("You have to override spaceAction to get this to do anything");
	}
	public boolean[] wasd = new boolean[8];
	public int[] wasdKeys = new int[]{KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT};

	public void mouseAction(MouseEvent e){
		System.err.println("You have to override spaceAction to get this to do anything");
	}


	private long refresh = 17; //msec per frame
	public long setFPS(int fps){
		refresh = Math.round(1000.0/fps);
		return refresh;
	}
	public static long getRefresh(int fps){
		return Math.round(1000.0/fps);
	}
	private long curTime = 0;
	public int mouseY, mouseX;
	Thread thread = null;

	public void run(){
		thread = new Thread(new Runnable(){
			public void run() {
				while(true){
					curTime = System.currentTimeMillis();
					if(!pause){
						repaint();
						//paintComponent(getGraphics());
						//paintImmediately(0,0,getWidth(),getHeight());
					}
					long wait = refresh - (System.currentTimeMillis() - curTime);
					if(wait > 0){
						try {
							Thread.sleep(wait);
						} catch (InterruptedException e) {
							//this is normal, stops the thread when window closes
							//e.printStackTrace();
						}
					}
				}
			}
		});
		thread.start();
	}

	public void runInFrame(){
		JFrame jf = new JFrame();
		jf.setSize(w, h);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this);
		jf.setVisible(true);
		jf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent) {
				thread.interrupt();
			}
		});
		run();
	}

	protected boolean pause = false;
	public void pause(){
		pause = true;
	}
	public void play(){
		pause = false;
	}
	public void reset(){
		frame = 0;
	}

	boolean aa = true;
	public void disableAA(){
		aa=false;
	}

	protected long frame = 0;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		if(aa)
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		draw(g2,frame);
		frame++;
	}

	public abstract void draw(Graphics2D g, long frame);
}










