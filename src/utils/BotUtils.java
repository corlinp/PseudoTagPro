package utils;

import tagpro.Ball;
import TagProBot.SampleChaserBot;
import TagProBot.TagProBot;


/**
 * Provides some nice utilities for TagPro Bots
 * And other vector operations.
 */
public class BotUtils {

	public static void main(String[] args) {
		timeBot(new SampleChaserBot());
	}
	
	//Times how long it takes for your bot to make a move.
	//The max allowed time on a modern desktop is 4ms. 
	//Try to keep it under that for good measure. 4ms is a LOT of time.
	public static void timeBot(TagProBot bot){
		Ball me = new Ball(0,0);
		Ball other = new Ball(200,200);
		int numRuns = 10000;
		long startTime = System.nanoTime();
		for(int i = 0; i < numRuns; i++){
			bot.move(me, other);
		}
		long totalTime = System.nanoTime() - startTime;
		double avgTime = (Math.round((double)totalTime/numRuns)/1000.0);
		System.out.println("Your bot took an average of " +avgTime+" ms per move.");
		if(avgTime>4.0)
			System.out.println("This is too long under bot league rules. Needs optimization!");
		else if(avgTime>2.5)
			System.out.println("This is approaching the limit. Watch out!");
		else
			System.out.println("This is totally fine under bot league rules.");
	}
	
	
	public double distance(double[] one, double[] two){
		double out = 0;
		for(int i = 0; i < one.length && i < two.length; i++){
			double vecdist = (one[i] - two[i]);
			out += vecdist*vecdist;
		}
		return Math.sqrt(out);
	}
	
	public static double dot(double[] one, double[] two){
		double out = 0;
		for(int i = 0; i < one.length && i < two.length; i++){
			out += one[i] * two[i];
		}
		return out;
	}
	
	public static double magnitude(double[] one){
		double out = 0;
		for(int i = 0; i < one.length; i++){
			out += one[i] * one[i];
		}
		return Math.sqrt(out);
	}
	
	//returns the unit vector (magnitude 1) of the input.
	public static double[] unitVector(double[] one){
		return divide(one,magnitude(one));
	}

	//projects vector A onto vector B
	public static double[] project(double[] a, double[] b){
		double a1 = dot(a,divide(b,magnitude(b)));
		return multiply(b,a1);
	}
	
	public static double[] add(double[] one, double[] two){
		double[] out = new double[(int)Math.round(Math.min(one.length, two.length))];
		for(int i = 0; i < one.length && i < two.length; i++){
			out[i] = one[i] + two[i];
		}
		return out;
	}
	public static double[] subtract(double[] one, double[] two){
		double[] out = new double[(int)Math.round(Math.min(one.length, two.length))];
		for(int i = 0; i < one.length && i < two.length; i++){
			out[i] = one[i] - two[i];
		}
		return out;
	}
	public static double[] divide(double[] one, double[] two){
		double[] out = new double[(int)Math.round(Math.min(one.length, two.length))];
		for(int i = 0; i < one.length && i < two.length; i++){
			out[i] = one[i] / two[i];
		}
		return out;
	}
	public static double[] multiply(double[] one, double[] two){
		double[] out = new double[(int)Math.round(Math.min(one.length, two.length))];
		for(int i = 0; i < one.length && i < two.length; i++){
			out[i] = one[i] * two[i];
		}
		return out;
	}
	public static double[] add(double[] one, double two){
		double[] out = new double[one.length];
		for(int i = 0; i < one.length; i++){
			out[i] = one[i] + two;
		}
		return out;
	}
	public static double[] subtract(double[] one, double two){
		double[] out = new double[one.length];
		for(int i = 0; i < one.length; i++){
			out[i] = one[i] - two;
		}
		return out;
	}
	public static double[] multiply(double[] one, double two){
		double[] out = new double[one.length];
		for(int i = 0; i < one.length; i++){
			out[i] = one[i] * two;
		}
		return out;
	}
	public static double[] divide(double[] one, double two){
		double[] out = new double[one.length];
		for(int i = 0; i < one.length; i++){
			out[i] = one[i] / two;
		}
		return out;
	}
	public static double[] abs(double[] one){
		double[] out = new double[one.length];
		for(int i = 0; i < one.length; i++){
			out[i] = Math.abs(one[i]);
		}
		return out;
	}
	
	public static double[] pow(double[] one,double num){
		double[] out = new double[one.length];
		for(int i = 0; i < one.length; i++){
			out[i] = Math.pow(one[i],num);
		}
		return out;
	}

}
