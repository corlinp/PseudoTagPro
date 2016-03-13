package TagProBot;

import tagpro.Ball;

public class SampleRunnerBot implements TagProBot{
	//A runner bot. Will move perpendicular (and away from wall) to oncoming enemy.
	public double[] move(Ball me, Ball foe) {
		//gets the difference in position between the two bots
		double xvec = foe.getX() - me.getX();
		double yvec = foe.getY() - me.getY();
		xvec = yvec - xvec/5 - me.getX()/2;
		yvec = -xvec - yvec/5 - me.getY()/2;
		double mag = Math.sqrt(xvec*xvec + yvec*yvec);
		double uvx = xvec/mag;
		double uvy = yvec/mag;
		return new double[]{uvx,uvy};
	}
}
