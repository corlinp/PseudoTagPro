package utils;


/**
 * Provides some nice utilities for TagPro Bots
 */
public class BotUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public double distance(){
		return 0;
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
	
	public static double[] unitVector(double[] one){
		return divide(one,magnitude(one));
	}
	
	public static double[] perpendicular(double[] one){
		if(one.length != 2)
			return null;
		return new double[]{-one[1],one[0]};
	}
	
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
