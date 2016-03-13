package TagProBot;

import tagpro.Ball;



/*
 * This is an abstract TagPro bot.
 * You can extend this to make your bot.
 */
public interface TagProBot {
	public double[] move(Ball me, Ball foe);
}
