package tagProBot;

import tagpro.Ball;
import utils.BotUtils;

public class SampleRunnerBot implements TagProBot{
	//A runner bot. Will move perpendicular to and away from oncoming enemy.
	public double[] move(Ball me, Ball foe) {
		//gets the difference in position between the two bots
		double xvec = foe.getX() - me.getX();
		double yvec = foe.getY() - me.getY();
		//Only ever moves clockwise due to how the perpendicular vector is calculated.
		xvec = yvec - xvec/5 - me.getX()/2;
		yvec = -xvec - yvec/5 - me.getY()/2;
		double[] move = new double[]{xvec,yvec};
		//We're using BotUtils instead of finding the unit vector ourselves.
		return BotUtils.unitVector(move);
	}
}
