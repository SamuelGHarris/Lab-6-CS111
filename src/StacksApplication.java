/**
* CS 111 Section No. 002
* Lab Assignment 6
* @author Samuel Harris 
**/

import java.util.Stack;
import java.util.Scanner;

public class StacksApplication {

	public static void main(String args[]) {
		int response = 0;
		String expression = null;
		Scanner input = new Scanner(System.in);

		System.out.println("Enter 1 to evaluate a prefix expression.");
		System.out.println("Enter 2 to evalueate a postfix expression.");
		response = input.nextInt();
		input.nextLine();

		//Determines if the user wants to evaluate a postfix or prefix expression.
		if (response == 1) {
			System.out.println("Please enter a prefix expression.");
			System.out.println("Make sure that you leave a space after each character.");
			expression = input.nextLine();
			System.out.println("Here is the result: ");
			evalPrefix(expression);
		} else if (response == 2) {
			System.out.println("Please enter a postfix expression.");
			System.out.println("Make sure that you leave a space after each character.");
			expression = input.nextLine();
			System.out.println("Here is the result: ");
			evalPostfix(expression);
		} else {
			System.out.println("You entered a number that was not suggested in the menu.");
		}
	}

	public static void evalPrefix(String expression) {
		int rightOpp = 0;
		int leftOpp = 0;
		Stack<Integer> operands = new Stack<Integer>();
		
		//The line of code below splits the users input based on the location of the spaces.
		String[] expArray = expression.split(" ");

		for (int i = expArray.length - 1; i > -1; i--) { //To avoid using multiple stacks, the expression is read from right to left (The operands are on the left side).
			if (expArray[i].matches("[0-9]+")) { //The method matches is used to determine if an element only contains digits 0-9.
				operands.push(Integer.parseInt(expArray[i])); //If an element contains only digits 0-9 (Could be more than one digit) then that element is pushed on to the stack.
			} else if (expArray[i].equals("+")) { //All of the operators are listed in individual if statements to perform their specific operations.
				leftOpp = operands.pop();	      //When an operator is identified the element on the top of the stack is popped and set as the left operand.
				rightOpp = operands.pop();		  //The next value of the stack is popped off and set as the right operand.
				operands.push(leftOpp + rightOpp); //The expression is evaluated and the result is pushed to the top of the stack.
			} else if (expArray[i].equals("-")) {
				leftOpp = operands.pop();
				rightOpp = operands.pop();
				operands.push(leftOpp - rightOpp);
			} else if (expArray[i].equals("*")) {
				leftOpp = operands.pop();
				rightOpp = operands.pop();
				operands.push(leftOpp * rightOpp);
			} else if (expArray[i].equals("/")) {
				leftOpp = operands.pop();
				rightOpp = operands.pop();
				operands.push(leftOpp / rightOpp);
			}
		}
		System.out.println(operands.peek()); //The final part of the method displays the top of the stack which is the answer (The element is not removed).
	}

	public static void evalPostfix(String expression) {
		int rightOpp = 0;
		int leftOpp = 0;
		Stack<Integer> operands = new Stack<Integer>();
		
		//The line of code below splits the users input based on the location of the spaces.
		String[] expArray = expression.split(" ");

		for (int i = 0; i < expArray.length; i++) { //With post fix the best way to evaluate is from left to right since the operands will be on the right side.
			if (expArray[i].matches("[0-9]+")) { //The method matches is used to determine if an element only contains digits 0-9.
				operands.push(Integer.parseInt(expArray[i])); //If an element contains only digits 0-9 (Could be more than one digit) then that element is pushed on to the stack.
			} else if (expArray[i].equals("+")) { //All of the operators are listed in individual if statements to perform their specific operations.
				rightOpp = operands.pop();		  //When an operator is identified the element on the top of the stack is popped and set as the right operand.
				leftOpp = operands.pop();		  //The next value of the stack is popped off and set as the left operand.
				operands.push(leftOpp + rightOpp); //The expression is evaluated and the result is pushed to the top of the stack.
			} else if (expArray[i].equals("-")) {
				rightOpp = operands.pop();
				leftOpp = operands.pop();
				operands.push(leftOpp - rightOpp);
			} else if (expArray[i].equals("*")) {
				rightOpp = operands.pop();
				leftOpp = operands.pop();
				operands.push(leftOpp * rightOpp);
			} else if (expArray[i].equals("/")) {
				rightOpp = operands.pop();
				leftOpp = operands.pop();
				operands.push(leftOpp / rightOpp);
			}
		}
		System.out.println(operands.peek()); //The final part of the method displays the top of the stack which is the answer (The element is not removed).
	}
}
