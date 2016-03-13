package tagpro;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import utils.BotUtils;

public class Ball{
	double x, y, vx, vy;
	Ellipse2D.Double ellipse = null;
	Color color = Color.blue;
	public Ball(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getPosMagnitude(){
		return Math.sqrt((x*x)+(y*y));
	}
	public double[] getPosVector(){
		return new double[]{x,y};
	}
	public double[] getVelVector(){
		return new double[]{vx,vy};
	}
	public double getVelMagnitude(){
		return Math.sqrt((vx*vx)+(vy*vy));
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getXvel(){
		return vx;
	}
	public double getYvel(){
		return vy;
	}
	void calculate(){
		x += vx * TagPro.timeStep;
		y += vy * TagPro.timeStep;
		//friction
		vx *= 0.95;
		vy *= 0.95;
		
		if(getPosMagnitude()>TagPro.arenaDiameter/2-TagPro.ballDiameter/2){
			double[] vv = getVelVector();
			double[] tanPlane = BotUtils.unitVector(new double[]{-y,x});
			double[] perPlane = BotUtils.unitVector(new double[]{-x,-y});
			double[] noo = BotUtils.subtract(BotUtils.project(vv, tanPlane),BotUtils.project(vv,perPlane));
			vx = noo[0];
			vy = noo[1];
			double[] ppp = BotUtils.multiply(BotUtils.unitVector(getPosVector()),TagPro.arenaDiameter/2-TagPro.ballDiameter/2);
			x = ppp[0];
			y = ppp[1];
		}
		ellipse = new Ellipse2D.Double(x-TagPro.ballDiameter/2+450, y-TagPro.ballDiameter/2+450, TagPro.ballDiameter, TagPro.ballDiameter);
	}
	void paint(Graphics2D g){
		g.setColor(color);
		g.fill(ellipse);
	}
	void moveX(double amount){
		amount *= TagPro.timeStep;
		if(amount > TagPro.timeStep)
			amount = TagPro.timeStep;
		if(amount < -TagPro.timeStep)
			amount = -TagPro.timeStep;
		vx += amount;
		if(vx > TagPro.maxSpeed)
			vx = TagPro.maxSpeed;
		if(vx < -TagPro.maxSpeed)
			vx = -TagPro.maxSpeed;
	}
	void moveY(double amount){
		amount *= TagPro.timeStep;
		if(amount > TagPro.timeStep)
			amount = TagPro.timeStep;
		if(amount < -TagPro.timeStep)
			amount = -TagPro.timeStep;
		vy += amount;
		if(vy > TagPro.maxSpeed)
			vy = TagPro.maxSpeed;
		if(vy < -TagPro.maxSpeed)
			vy = -TagPro.maxSpeed;
	}

	public double distanceTo(Ball other){
		double xd = (x-other.x);
		double yd = (y-other.y);
		return Math.sqrt(xd*xd+yd*yd);
	}
}
