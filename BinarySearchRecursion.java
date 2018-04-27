/****************************************************************************
* Created by: Heejo Suh
 * Created on: Mar 2018
 * Created for: learning
 * 
 * This program generate a random list of 10 values and present them to the user. 
 * Then the list is sorted and presented to the user again. 
 * Finally you enter a number to be found. 
 * If the number is in the list, the position will be returned. 
 * If it is not in the list, the user will be informed of this.
 * 
 * 
 * 
 * Search a sorted array by repeatedly dividing the search interval in half.
 * Compare values and narrow into halves
 * Repeatedly check until the value is found or the interval is empty.
 *     
 *
 ****************************************************************************/



//have to import the following:
import java.util.Scanner;
import java.util.Random;
import java.math.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


//****************************************************************************
public class BinarySearchRecursion{

	//------------------------
	public static int binarySearch(List<Integer> inputArray , int inputNumber, int start, int end, int valueIsAt){
	//Checks if input is in array and return answer

		int middlePoint = start + ((int) Math.round( (end-start)/2 ));
		int middleValue = inputArray.get(middlePoint);

		if ( (end-start) < 0 ) {
			//if reach here, no values are in the list
			//so return '-1'
			//valueIsAt = -1;
			return valueIsAt; 
		} else {
			if (inputNumber > middleValue) {
				//if input is greater than middleValue
				//ignore left half
				//all the values less will be useless
				valueIsAt = binarySearch(inputArray, inputNumber, middlePoint + 1, end, valueIsAt);
				return valueIsAt; 

			} else if (inputNumber < middleValue) {
				//if input is less than middleValue
				//ignore right half
				//all the values greater will be useless
				valueIsAt = binarySearch(inputArray, inputNumber, start, middlePoint - 1, valueIsAt);
				return valueIsAt; 
			} else {
				//equal
				valueIsAt = middlePoint;
				return valueIsAt; 
			}
		}

    }//closing for binarySearch
		
	
	
	//------------------------
	public static List<Integer> sortList(List<Integer> inputArray, List<Integer> sortedList){
			//Sorts the unsorted list and assigns the result to the sorted list
			int smallestValAt;
			if (inputArray.size()>0) {
				//check all values of input array 
				//search for the smallest value in input array and restate it as such
				//if the value is less than the smallest value, set it to new smallest value
				smallestValAt= smallestIntAt(inputArray, 0, 0);
				
				sortedList.add(inputArray.get(smallestValAt));
				inputArray.remove(smallestValAt); //remove the small value from input array
				
				sortedList = sortList(inputArray, sortedList);
			}
			
			return sortedList;
			
		
		}
	//------------------------
		public static int smallestIntAt(List<Integer> inputArray, int index, int smallestNumberIndex){
			//returns the smallest number
			int smallestNumber= inputArray.get(smallestNumberIndex);
			if (index < inputArray.size()-1) {
				index+=1;
				if (smallestNumber > inputArray.get(index)) {
					smallestNumberIndex= index;
				}
				smallestNumberIndex= smallestIntAt(inputArray, index, smallestNumberIndex);
			}
			return smallestNumberIndex;
		}
	
	//------------------------
	
	public static void main(String args[]){
	//uses the binarySearch function to search
		
		Scanner scanner = new Scanner(System.in);
		String input = "", choice = "";
		int inputNumber = 0, numberOfIntegersInList = 10;
		
		//create random list
		List<Integer> randomList = new ArrayList<>(), sortedList = new ArrayList<>();
		
		
		System.out.println("List:");
		//add 250 random numbers
		for ( int count=0; count<numberOfIntegersInList; count++ ) {  
    		//add random number from 0 to 100
	    	int randNumber= new Random().nextInt(100);
	    	System.out.print(randNumber+" "); //show to user
	    	randomList.add(randNumber);
    	}
		
		
		while (!choice.equals("E")) {
			//not have exited program
			
			//get choice
			while (!choice.equals("I") && !choice.equals("S")){
				//see if user wants to input a value or search
				System.out.println("\n-----------------------------------------------"
						+ "\nI : insert value\nS : search for value\nE : exit");

				choice = scanner.nextLine().toUpperCase();
				
				if (! choice.equals("I") && !choice.equals("S")){
					//error
					System.out.println("Input a valid answer! Try again!");
				}
			}//closing for get choice
			
			
			//get number
			while (input == ""){
				//see if input is a number
				System.out.print("\nWhat number would you like to ");
				if (choice.equals("I")){
					System.out.print("insert?\n");
				} else {
					System.out.print("search for?\n");
				}
				
				try {
					//check if response is an integer
					input = scanner.nextLine();
					inputNumber = Integer.parseInt(input);
				}catch (IllegalArgumentException x) { 
					System.out.println("Insert an integer!");
				}
			}//closing for get number
			
			
			
			//return
			if (choice.equals("I")){
				//insert value
				randomList.add(inputNumber);
			}
			
			//sort
			sortedList = sortList(randomList, sortedList); //sorts list from least to greatest
			
			
			System.out.print("\nSorted list:\n"+sortedList+"\n");
			
			if (choice.equals("S")){
				//search value
				int answer = binarySearch(sortedList, inputNumber, 0, sortedList.size()-1, -1);

				
				if (answer == -1) {
					//not in the list
		            System.out.println("\nNumber is not in the list!");
				} else {
					//in the list

		            System.out.println("\n"+inputNumber+" is at index "+answer+"!");
				}
			}
			

			//need to reset to default
			choice = ""; 
			input = "";
			inputNumber = 0;
			
		}//closing for while not have exited the program
		
			
    }//closing for main
}//closing for BinarySearch