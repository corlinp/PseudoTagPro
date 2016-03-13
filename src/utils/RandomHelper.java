package utils;

import java.util.Random;

public class RandomHelper {
	public static void main(String[] args) {
		int[] w = new int[]{2,4,8,16,32};
		for(int i = 0; i < 100; i++){
			System.out.println(weightedRandom(w));
		}
	}


	public static int weightedRandom(int[] weights){
		int total = 0;
		for(int i = 0; i < weights.length; i++){
			total += weights[i];
		}
		int choice = new Random().nextInt(total);
		//System.err.println(choice);
		int pos = 0;
		while(choice > 0){
			choice -= weights[pos];
			pos++;
		}
		return pos-1;
	}

}
