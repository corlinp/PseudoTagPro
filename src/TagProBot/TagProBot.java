package tagProBot;

import tagpro.Ball;



/*
 * This is an abstract TagPro bot.
 * You can extend this in another class to make your bot.
 */
public interface TagProBot {
	/*
	 * This is the main method that tells your bot how to move.
	 * You are passed two variables, your ball and the enemy's ball.
	 * The ball class will tell you your/your enemy's position and velocity
	 * @return the [x,y] vector that will be added to your ball's velocity.
	 * Note that if either component exceeds +/- 1 it will be capped. 
	 */
	public double[] move(Ball me, Ball foe);
}
