package chapter2

import java.math.BigInteger
import scala.math.pow

object Solutions {
	def main(args: Array[String]): Unit = {
		// 1. The signum of a number is 1 if the number is positive, –1 if it is negative, and
		// 0 if it is zero. Write a function that computes this value.
	  def signum(num : Int): Int = {
	    if(num > 0)
	      1
	    else if(num == 0)
	      0
	    else
	      -1
	  }
	  println("Signum is: " + signum(10))
	  println("Signum is: " + signum(0))
	  println("Signum is: " + signum(-10))
	  
		// 2. What is the value of an empty block expression {}? What is its type?
	  println("Printing {} gives: \"" + {} + "\", and its type is: \"" + {}.getClass().getName() + "\" and this is equivalent to \"scala.Unit\"") 
	  
	  // 3. Come up with one situation where the assignment x = y = 1 is valid in Scala.
		// (Hint: Pick a suitable type for x.)
	  var y : Int = 0
	  var x : Unit = y = 1 // Assignment (y = 1) has return type 'Unit' (void id Java)
	  println("Printing x gives: \"" + x + "\", and y gives: \"" + y + "\" and the datatype of x is: \"" + x.getClass().getName() + "\""
	          + "\" and this is equivalent to \"scala.Unit\"")
	  
	  // 4. Write a Scala equivalent for the Java loop
		// for (int i = 10; i >= 0; i--) System.out.println(i);
		for(i <- 10 to 0 by -1){
		  println(i)
		}
	  
	  // 5. Write a procedure countdown(n: Int) that prints the numbers from n to 0.
	  def countdown(num: Int) {
	    for(i <- num until 0 by -1){
	      println("Countdown: " + i)
	    }
	  }
	  countdown(5)
	  countdown(-10)
		
	  // 6. Write a for loop for computing the product of the Unicode codes of all letters
		// in a string. For example, the product of the characters in "Hello" is 825152896.
	  def productLoop = {
			  var prod = 1
					  for(char <- "Hello"){
						  prod = prod * char.toInt
					  }
			  println(prod)
	  }
	  productLoop // This is the procedure call
	  
	  // 7. Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps
		// Scaladoc.)
	  println("Hello".map(_.toInt).product)
		
	  // 8. Write a function product(s : String) that computes the product, as described
		// in the preceding exercises.
	  def getStringProduct(s : String): Long = {
			  var prod : Long= 1
					  for(char <- s){
						  prod = prod * char.toInt
						  // println("Char value: " + char.toInt)
					  }
			  prod
	  }
	  println(getStringProduct("Hello"))
 	  println(getStringProduct("World"))

	  // 9. Make the function of the preceding exercise a recursive function.
	  var prod : Long = 1
 	  def productRecursive(s : String) : Long = {
	    if(s.length() > 0){
//	      println("Char is: " + s.head)
	      prod = s.head.toInt * productRecursive(s.tail)
	    }
	    prod
	    var result = prod
	    prod = 1
	    result
	  }
 	  println(productRecursive("Hello"))
 	  println(productRecursive("World"))
 	  
    // 10. Write a function that computes x^n, where n is an integer. Use the following
    // recursive definition:
    // • x^n = y^2 if n is even and positive, where y = x^(n/2)
    // • x^n = x * x^(n – 1) if n is odd and positive.
    // • x^0 = 1
    // • x^n = 1 / x^(–n) if n is negative.
    // Don’t use a return statement.
 	  def computePow(x: Int, n : Int) : Any = {
 	    var result : Any = 0
 	    if(n == 0)
 	      result = 1
 	    if(n < 0)
 	      result = 1 / (pow(x, -1 * n))
 	    if(n > 0 && n%2 == 0)
 	      result = x * x
 	    if(n > 0 && n%2 != 0)
 	      result = x * pow(x, n-1)
 	      
 	    result 
 	  }
 	  println("Result for computePow(3, 0) is: " + computePow(3, 0))
	  println("Result for computePow(3, -2) is: " + computePow(3, -2))
	  println("Result for computePow(3, 2) is: " + computePow(3, 2))
	  println("Result for computePow(3, 3) is: " + computePow(3, 3))
	}
}