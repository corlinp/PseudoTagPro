package tagProBot;

import tagpro.Ball;

public class CleanPredChaserBot implements TagProBot{
	static private double mapSize = 800;
	static private double inertConst = .03; //percent of mapsize to consider movement negligable
	static private double farAwayConst = .65;  //percent of mapsize
	private double  selfX, selfY, foeX, foeY, foePrevX, foePrevY;
	private boolean firstRun = true;
	private int counterMin = 350, counterMax = 400;
	private int altMoveCounter = 0;
	public double[] move(Ball me, Ball foe) {
		getPositions(me, foe);
		altMoveCounter++;
		if (firstRun){
			firstRun = false;
			foePrevX = foeX;
			foePrevY = foeY;
		}
		double[] targetPos = velocityCalculations(foe);
		if (altMoveCounter > counterMin && altMoveCounter < counterMax) {
			targetPos[0] = 0;
			targetPos[1] = 0;
		} else if (altMoveCounter >= counterMax){
			altMoveCounter = 0;
		}
		double xvec = targetPos[0] - selfX;
		double yvec = targetPos[1] - selfY;
		double mag = Math.sqrt(xvec*xvec + yvec*yvec);
		double uvx = xvec/mag;
		double uvy = yvec/mag;
		return new double[]{uvx,uvy};
	}
	void getPositions(Ball me, Ball foe){
		selfX = me.getX();
		selfY = me.getY();
		foeX = foe.getX();
		foeY = foe.getY();
	}
	private double[] velocityCalculations(Ball foe){
		//XXX Do calculations if foe.getVelVector() becomes hidden.
		double[] foeVelo = foe.getVelVector();
		return new double[] {foeVelo[0]+foeX,foeVelo[1]+foeY};
	}
	private boolean foeIsInert(){
		double xvec = foeX - foePrevX;
		double yvec = foeY - foePrevY;
		double mag = Math.sqrt(xvec*xvec + yvec*yvec);
		return (mag <= (inertConst*mapSize));
	}
	private boolean foeIsFar(){
		double xvec = foeX - selfX;
		double yvec = foeY - selfY;
		double mag = Math.sqrt(xvec*xvec + yvec*yvec);
		return (mag >= (farAwayConst*mapSize));
	}
	public double[] makeGenericMove(Ball me, Ball foe) {
		//gets the difference in position between the two bots
		double xvec = foe.getX() - me.getX();
		double yvec = foe.getY() - me.getY();
		//finds the magnitude of the position between the two bots
		double mag = Math.sqrt(xvec*xvec + yvec*yvec);
		//normalizes the vector to the other bot's position.
		//note that the max x and y values are 1.
		double uvx = xvec/mag;
		double uvy = yvec/mag;
		//returns the desired velocity vector.
		return new double[]{uvx,uvy};
	}
}