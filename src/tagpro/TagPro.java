package tagpro;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import utils.Pr;
import TagProBot.SampleChaserBot;
import TagProBot.SampleRunnerBot;
import TagProBot.TagProBot;
import animate.AbstractAnimation;

/**
 * Extensible Specifications:
 * 
 * An abstract "AI" class
 * 
 * 
 */
public class TagPro {
	static final double timeStep = 0.75;
	static final double maxSpeed = 14.0;
	static final int ballDiameter = 75;
	static final int arenaDiameter = 800;
	static final double chaserSpeedMultiplier = 1.0;
	
	
	public static void main(String[] args) {
		TagProBot runner = null;
		runner = new SampleRunnerBot();
		TagProBot chaser = null;
		//chaser = new SampleChaserBot();
		new TagPro(runner, chaser);
	}

	private final Ellipse2D.Double arena = new Ellipse2D.Double(450-arenaDiameter/2, 450-arenaDiameter/2, arenaDiameter, arenaDiameter);
	public class TPAN extends AbstractAnimation{
		private static final long serialVersionUID = -5980740457482343647L;
		public TPAN(int w, int h) {
			super(w, h);
		}
		public void draw(Graphics2D g, long frame) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0,0,getWidth(),getHeight());
			g.setColor(Color.WHITE);
			g.fill(arena);
			if(runnerBot == null){
				if(wasd[0]){
					runnerBall.moveY(-1);
				}
				if(wasd[1]){
					runnerBall.moveX(-1);
				}
				if(wasd[2]){
					runnerBall.moveY(1);
				}
				if(wasd[3]){
					runnerBall.moveX(1);
				}
			}
			if(chaserBot == null){
				if(wasd[4]){
					chaserBall.moveY(-1);
				}
				if(wasd[5]){
					chaserBall.moveX(-1);
				}
				if(wasd[6]){
					chaserBall.moveY(1);
				}
				if(wasd[7]){
					chaserBall.moveX(1);
				}
			}

			if(runnerBot != null){
				double[] move = runnerBot.move(runnerBall, chaserBall);
				runnerBall.moveX(move[0]);
				runnerBall.moveY(move[1]);
			}
			if(chaserBot != null && frame > 0){
				double[] move = chaserBot.move(chaserBall, runnerBall);
				chaserBall.moveX(move[0]);
				chaserBall.moveY(move[1]);
			}

			chaserBall.calculate();
			runnerBall.calculate();
			if(collisions()){
				//TODO:collision!
				reset();
				TagPro.this.reset();
				return;
			}
			runnerBall.paint(g);
			chaserBall.paint(g);
		}
		public void spaceAction(){
			TagPro.this.reset();
		}
		public void mouseAction(MouseEvent e){
			if(e.getButton() == MouseEvent.BUTTON3){
				Pr.pr("Hella Diagnostics:");
				Pr.pr("Paused = " + pause);
				Pr.pr("Frame = " + frame);
				Pr.pr("Chaser x = " + chaserBall.x);
				Pr.pr("Chaser y = " + chaserBall.y);
				Pr.pr("Chaser vx = " + chaserBall.vx);
				Pr.pr("Chaser vy" + chaserBall.vy);

				Pr.pr("Runner x = " + runnerBall.x);
				Pr.pr("Runner y = " + runnerBall.y);
				Pr.pr("Runner vx = " + runnerBall.vx);
				Pr.pr("Runner vy" + runnerBall.vy);
				Pr.pr("---");
			}
		}
	};
	private TagProBot chaserBot=null, runnerBot=null;
	public TagPro(TagProBot runner, TagProBot chaser){
		this.chaserBot = chaser; this.runnerBot=runner;
		reset();
		TPAN aa = new TPAN(arenaDiameter+140,arenaDiameter+200);
		aa.setFPS(60);
		aa.runInFrame();
	}

	private void reset(){
		Random rand = new Random();
		//myBall = new Ball(rand.nextInt(windowSize-200)+100,rand.nextInt(windowSize-200)+100);
		runnerBall = new Ball(0,0);
		runnerBall.color = Color.RED;
		double deg = rand.nextInt(360)/(2*Math.PI);
		chaserBall = new Ball(Math.sin(deg)*(arenaDiameter/2-ballDiameter/2-5),Math.cos(deg)*(arenaDiameter/2-ballDiameter/2-5));
		chaserBall.color = Color.BLUE;
	}

	private boolean collisions(){
		double xd = (chaserBall.x-runnerBall.x);
		double yd = (chaserBall.y-runnerBall.y);
		return Math.sqrt(xd*xd+yd*yd) < ballDiameter;
	}

	public Ball chaserBall = null, runnerBall = null;
}










































