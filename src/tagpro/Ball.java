package tagpro;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import utils.BotUtils;

public class Ball{
	double x, y, vx, vy;
	Ellipse2D.Double ellipse = null;
	Color color = Color.blue;
	double multiplier = 1.0;
	public Ball(double x, double y) {
		this.x = x;
		this.y = y;
	}
	//The magnitude/radius of the ball's position from the center
	public double getPosMagnitude(){
		return Math.sqrt((x*x)+(y*y));
	}
	//The position vector from the center for the ball
	public double[] getPosVector(){
		return new double[]{x,y};
	}
	//The velocity vector for the ball in pixels/timeStep
	public double[] getVelVector(){
		return new double[]{vx,vy};
	}
	//The speed of the ball
	public double getVelMagnitude(){
		return Math.sqrt((vx*vx)+(vy*vy));
	}
	//The X position of the ball. The center of the map is X = 0.
	public double getX(){
		return x;
	}
	//The Y position of the ball. The center of the map is Y = 0.
	public double getY(){
		return y;
	}
	//The X velocity of the ball.
	public double getXvel(){
		return vx;
	}
	//The Y velocity of the ball.
	public double getYvel(){
		return vy;
	}
	void calculate(){
		//Calculating the new position based on velocity
		x += vx * TagPro.timeStep;
		y += vy * TagPro.timeStep;
		//Applying friction
		vx *= 0.95;
		vy *= 0.95;
		//this code calculates if and how the ball bounces off a wall.
		//This is done by summing the velocity vector projected on the 
		//tangent plane to the wall with the negative of the velocity
		//vector projected onto the perpendicular plane.
		if(getPosMagnitude()>TagPro.arenaDiameter/2-TagPro.ballDiameter/2){
			double[] vv = getVelVector();
			double[] tanPlane = BotUtils.unitVector(new double[]{-y,x});
			double[] perPlane = BotUtils.unitVector(new double[]{-x,-y});
			double[] noo = BotUtils.subtract(BotUtils.project(vv, tanPlane),BotUtils.project(vv,perPlane));
			vx = noo[0]; vy = noo[1];
			double[] ppp = BotUtils.multiply(BotUtils.unitVector(getPosVector()),TagPro.arenaDiameter/2-TagPro.ballDiameter/2);
			x = ppp[0]; y = ppp[1];
		}
		ellipse = new Ellipse2D.Double(x-TagPro.ballDiameter/2+450, y-TagPro.ballDiameter/2+450, TagPro.ballDiameter, TagPro.ballDiameter);
	}
	void paint(Graphics2D g){
		g.setColor(color);
		g.fill(ellipse);
	}
	void moveX(double amount){
		amount *= TagPro.timeStep*multiplier;
		if(amount > TagPro.timeStep*multiplier)
			amount = TagPro.timeStep*multiplier;
		if(amount < -TagPro.timeStep*multiplier)
			amount = -TagPro.timeStep*multiplier;
		vx += amount;
		if(vx > TagPro.maxSpeed)
			vx = TagPro.maxSpeed;
		if(vx < -TagPro.maxSpeed)
			vx = -TagPro.maxSpeed;
	}
	void moveY(double amount){
		amount *= TagPro.timeStep*multiplier;
		if(amount > TagPro.timeStep*multiplier)
			amount = TagPro.timeStep*multiplier;
		if(amount < -TagPro.timeStep*multiplier)
			amount = -TagPro.timeStep*multiplier;
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
