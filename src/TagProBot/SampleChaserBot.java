package TagProBot;

import tagpro.Ball;
import tagpro.TagPro;

/*
 * This is a sample chaser bot. It simply moves toward the foe
 */
public class SampleChaserBot implements TagProBot{
	public double[] move(Ball me, Ball foe) {
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
