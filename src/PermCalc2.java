import java.math.*;
import java.lang.System;
/**
 * This code is used to calculate Permutable Primes within a boundary
 * A permutable Prime is a prime number which is Prime under any permutation of its digits
 * 
 *   --Questions -- 
 *   Exactly how much faster is calculating primes using sqrt(n) then n/2?  
 *   	Answer practically and theoretically extend question to other methods (Sieves)
 *   What are other implementations of the permutation algorithm that are not recursive?
 *   In what real world scenarios would a permutation algorithm be necessary?
 *   What is the highest theoretical efficiency of a recursive permutation alg?
 *   	How do the pros implement this?
 * 
 *   --Analysis--
 *   See excel workbook and notebook
 *   
 *   --POOR IMPLEMENTATIONS--
 *  
 *  There is no redundancy check.  i.e. (117 and 117 are both checked) This is a minor concern since the other factors listed 
 *  are more pertinent to runtime.   
 *  
 *  The first permutation generated is the original number which has already been checked for primality.  This redundancy
 *  is minor. Address later.  
 *  
 *  The bottleneck is checking the primes.  While the Permutable primes slow down considerably the primes 
 *  far out paces (find actual numbers on rate of Primes there was a theory with logs that i've forgotten)
 *  them as N gets on the order of 10^6 and larger the program spends 99.9% of its time finding the 
 *  primes
 *  
 *  Better method of finding primes.  We are going up to n/2 in the first version.  That is stupid and slow.
 *  At least go to square root of n.  That is also stupid and slow but much faster. Revised 1-21-13.
 *  
 *  Then use fast prime methods.
 *  
 *  ultimately though this program goes through each number to find a prime and then through each prime to find a permutable prime
 *  Will that be slower or faster then trying all combinations of 1,3,7,9?????  Second version of program must test this practically
 *  and do an on paper analysis of testing this.  Find the break even point. What must be the efficiency of the permutation algorithm
 *  to make that method faster then the 0 - N prime search (given a certain efficiency of Prime search alg)
 * 
 *  What if this were to simply access a list of known primes and calculate that way.  This woudl give a much better indicator
 *  of the actual efficiency of the recursive permutor. 
 *  
 *  
 *   --Revisions--
 * Add user input asking for max Then update lines
 * 'for(int j = 0; j < max; j++) {' // loop up to what boundary
 * '//System.out.println("There are " + primeCount + " primes less that" + max);'  uncomment
 * 
 * After max is added write way of automatically generating statistics on run time averages 
 * and sending these to external data files to generate the statistics that you're interested in.
 * Several runs are preferred at designated max values (high maxes have low variation at 
 * it is unnecessary to run multiple times)  Just have it churn out a stat sheet.  Save some time etc.
 * 
 * See comment below about going through the main loop two at a time.  Skipping the even number rather 
 * than even checking them.  Probably useless.  Think about it anyways.
 * 
 *   --General Revisions--
 *   Polish
 *   Put variable outputs in a way that they can all be 
 *   	triggered easily.  (i.e. centralize the location to choose different debug print line)
 *   	Look into how pros do this
 *   Use parseInt and valueOf better they seem kinda messsy in this code
 *   Find a better way of making the comments.  As they are now the in code comments are barely intelligible and take up a lot fo space.
 *   
 *   ---- Tips ----
 *		Try to develop more incrementally
 *		Standardize your comment formatting.  Use Javadoc notation for each method properly
 *		Try to use GIT for better Version Control
 *
 *
 * @author RasPat
 *
 */

public class PermCalc2 {
	
	//This string holds the prime that is currently being built by the permutation algorithm
	//It is static and therefore accessible by the different layers of the recursive function
	static String curNum = "";
	//Once the main permutaion function discovers a permutation which is not prime 
	//it changes isPerm to false and exits each layer of the recursive algorithm
	//It is reset to true for each subsequent number which 
	static boolean isPerm = true;
	
	//For metrics
	static int primeCount = 0;
	static double runTime = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//metrics
		long start = System.currentTimeMillis();

		//Goes through every number form 0 to max
		for(int j = 0; j < 10000000; j++) {
			
		//Prime numbers are examined more throughly
		//Checks if it is prime in the if statement conditional
			if(isPrime(j)) {
//				System.out.println(j + " is Prime");
				
				//If the candidate has only good digits or is a single digit continue (2,5)
				if(!hasBadDigit(j) || j < 10 ) {
					//workhorse permutation calculator takes a string
					perm(Integer.toString(j));
					//Perm while it does not return anythign has has made a change to isPerm
					//at this point isPerm = true if and only if j is a permutable prime
					if(isPerm) {
						System.out.println(j + " is a permutable prime");
					}
					//Resets the exit condition and the string holding the prime in progress
					curNum = "";
					isPerm = true;
				}
			}
			//end of loop for current j moves on to j+1
			//what if we moved on to j+2? would that save any measurable amount of time? even number are taken
			//out at the first cycle of the is prime check so it would save one division at least... 
			//probably not worth it would sacrifice simplicity
		}
		//metrics
		runTime = System.currentTimeMillis() - start;
		System.out.println("Run Time was " + runTime + " milliseconds");
//		System.out.println("There are " + primeCount + " primes less that" + max);
	}
	
	//pretty standard primality check
	public static boolean isPrime(int n) {
		
		if(n == 1 || n == 0) return false;
		if(n == 2) return true;
		
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0) {
				return false;
			}
		}
		//metric
		primeCount++;
		return true;		
	}
	
	/** This method generates the permutations given a number of any digits
	 *  and checks each permutation for primality as it is created.
	 *  This method is recursive
	 *  
	 *  CurNum starts with a blank string.  As the method progresses it is filled with the digits of num.
	 *  Once it is a permutation of the input number it will be checked for primality.
	 *  
	 *  If a non prime permutation is found the value of isPerm is changed to false.  This signals the method
	 *  to exit and return control to the main function.  The main function uses the false value of isPerm to 
	 *  report whether the number in question is a permutable prime or not.
	 *  
	 *  If every permutation of the original number has been found prime the method ends and returns control
	 *  to the main function.  It has not changed the value of isPerm to false and therefore reports the number
	 *  as a permutable prime.
	 *  
	 *  
	 * @param num
	 */
	public static void perm(String num) {
		
		if(num.length() == 1) {
			curNum = curNum + num;
//			System.out.print(curNum + " ");
			int curIntNum = Integer.parseInt(curNum);
			
			if(isPrime(curIntNum)) {
				//	Trim one now and trim the second one once returning to the layer above
				curNum = curNum.substring(0, curNum.length() - 1);
			}
			else { //its not prime break out of the whole thing
				isPerm = false;
//				System.out.println();
			}
			
		} else {
			for(int i = 0; i < num.length(); i++) {
				//Use this to break out of all the layers of recursive alg
				if(!isPerm) {break;}
				//add the next selected digit to the curNum
				curNum = curNum + num.charAt(i);
				//GO ONE DEEPER
				//append all the permutations of the input of this fx minus the letter we have just appended
				//to the curNum string
				perm(num.substring(0, i) + num.substring(i + 1, num.length()));
				//If we are here we have completed the layer listed above and need to trim off the number we 
				//added on this layer in oreder to add the next candidate digit from this loop.
				curNum = curNum.substring(0, curNum.length() - 1);
			}
		}
		//if this loop completes we return control to the higher level
		//If this is the highest level we return control to main
	}
	
	
	
	public static boolean hasBadDigit(int num) {
		String numString = Integer.toString(num);
		if(numString.contains("2") || 
				numString.contains("4") ||
				numString.contains("6") ||
				numString.contains("8") ||
				numString.contains("0") ||
				numString.contains("5")) {
			return true;
		}
		else return false;
	}
	
}

