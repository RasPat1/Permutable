
public class Notes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		

	}

}

/** Take a list of digits from the number in question
Return false is any of the digits are a 2,4,5,6,8,0

Method 1 - will have to hold all in memory at once (# of permutations = n!)!!
	-- Can use recursive idea here
Generate an ArrayList of all Permutations
Check each element in the list for Primality
Method 2 - Can avoid any extra permutation calculations will stop once first composite perm is found
		-- how to make sure all permutations are found
generate a Permuation
check for Primality
check next Permutation



Generate a permutation
1)
	If using recursive definition then all permutations must be generated before checking
	recursive if 1 digit return the one digit
	if 2 digits return AB and BA
	if 3 digits return A (BC, CB) B (AC, CA) C (AB, BA)
	etc
	
	Perm fx returns a string
	if one digit return digit
	
	Index running from 0 to length.
	  
	Take the digit at that index and make it the first digit of the permutation.
	Run teh perm fx on the remaining digits  
	
	
2) What's the method for listing all of the permutations of a number without using a recursive definition?

	digits indexed at lets say 0-2  sitting in the array as [A,B,C] let's say
	
	we need to output ABC, ACB
					  BAC, BCA
					  CAB, CBA
	'ABCD'
	start with empty array
	write the first line with A 6 times (n-1)!
	if the length of the entry is zero add A up to 6 times
	if length of entry is 1 add B two times (n-2)!
	"""""C"""
	"""""D"""
	if length of entry is 2 add
		array[0][i] = 'A' while 
	[ABCD, ABDC, ACBD, ACDB, ADBC, ADCB]
	[BACD, BADC, BCAD, BCDA, BDAC, BDCA]
	[CABD, CADB, CBAD, CBDA, CDAB, CDBA]
	[DABC, DACB, DBAC, DBCA, DCAB, DCBA]
	
	00 01 02 03 04 05
	10 11 12 13 14 15
	20 21 22 23 24 25
	30 31 32 33 34 35
	
	IF ARR[I][J] I = 0 ADD A
	IF ADD[I][J] I = 1 ADD B
	IF ADD[I][J] I = 2 ADD C
	IF ADD[I][J] I = 3 ADD D
	
	
		
	CREATE A STRING ARRAY OF DIMENSIONS (N-1)! BY N
	LETS SAY INDICIES ARE I AND J [I][J]
	string[] array = new array[(digits.length - 1)!][digits.length];
	for(int i = 0; i < array.length; i++ ) {
		for(int j = 0; j < digit.length or array[i].length; j++) {
    		array[i] += digit[j];
    		
		}
	
	
	
	
	Recursive Definition:
	
	Take in n digit number
	create arrayList of calculated Permutations
	create array of digits that have been extracted from the number
	
	
	
	
	
	
	
**/
