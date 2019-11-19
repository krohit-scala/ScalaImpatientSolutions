package chapter1

import scala.math.sqrt
import scala.math.pow
import scala.BigInt.probablePrime
import scala.util.Random

object Solutions {
	def main(args: Array[String]): Unit = {
			// 1. In the Scala REPL, type 3. followed by the Tab key. What methods can be
			// applied?
		
	    // Sol: Do it yourself. :)
	  
			// 2. In the Scala REPL, compute the square root of 3, and then square that value.
			// By how much does the result differ from 3? (Hint: The res variables are your
			// friend.)
			var x : Int = 3
			var y : Double = sqrt(x)
			println("x: " + x + "\n" + "y: " + y + "\n" + "y*y: " + pow(y, 2))
			println("Difference is: " + (x.toDouble - pow(y, 2)).round )
			
			// 3. Are the res variables val or var?
			// Sol: The res varables are val, as you can not reassign values to res variable
			
			// 4. Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL.
			// What does this operation do? Where can you find it in Scaladoc?
			var str : String = "crazy"
			println(str * 3)
			// You can find more on * operator in Scaladoc by searching for '*'. PFB, the result:
			// Trait StringLike[+Repr] extends IndexedSeqOptimized[Char, Repr] with Ordered[String]
			// Repr:  The type of the actual collection inheriting StringLike.
      //	def *(n: Int): String
      //    Return the current string concatenated n times.
			
			// 5. What does 10 max 2 mean? In which class is the max method defined?
			println(10.max(2))
			// abstract final class Int extends AnyVal
			// def max(that: Int): Int
      // Returns this if this > that or that otherwise.
      // Implicit
      // This member is added by an implicit conversion from Int to RichInt performed by method intWrapper in scala.LowPriorityImplicits.
      // Definition Classes
      //   RichInt → ScalaNumberProxy
			
			// 6. Using BigInt, compute 2^1024.
			println(BigInt(2).pow(1024))

			// 7. What do you need to import so that you can get a random prime as
			// probablePrime(100, Random), without any qualifiers before probablePrime and Random?
			// Sol: We need to import scala.BigInt.probablePrime, and scala.util.Random
			
			// 8. One way to create random file or directory names is to produce a random
			// BigInt and convert it to base 36, yielding a string such as "qsnvbevtomcj38o06kul".
			// Poke around Scaladoc to find a way of doing this in Scala.
			var randomName : String = probablePrime(100, Random).toString(36)
			println(randomName)
			
			// 9. How do you get the first character of a string in Scala? The last character?
			str = "Hello, World!"
			println("First: " + str.head + "\tLast: " + str.last) // Using 'head' and 'last'
			println("First: " + str(0) + "\tLast: " + str(str.length()-1)) // Using apply method - '()'
			println("First: " + str.charAt(0) + "\tLast: " + str.charAt(str.length()-1)) // Using 'charAt()' method
			
			// 10. What do the take, drop, takeRight, and dropRight string functions do? What
			// advantage or disadvantage do they have over using substring?
			println("str.take(2): " + str.take(2))
			println("str.drop(2): " + str.drop(2))
			println("str.takeRight(2): " + str.takeRight(2))
			println("str.dropRight(2): " + str.dropRight(2))
			// The advantage that I can think of is, the these operators are more intuitive and provide better readability & clarity.
	}
}