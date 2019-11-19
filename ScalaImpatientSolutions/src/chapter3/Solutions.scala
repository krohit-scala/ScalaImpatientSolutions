package chapter3

import java.net.MalformedURLException
import java.io.IOException
import scala.collection.mutable.ArrayBuffer

object Solutions {
	def main(args: Array[String]): Unit = {
			def exceptionTest() : Unit = {
					try{
						//						throw new IndexOutOfBoundsException
					}catch{
					case _ : MalformedURLException => println("Invalid URL")
					case _ : IOException => println("Invalid conditions")
					case t : IndexOutOfBoundsException => t.printStackTrace()
					}finally{
						println("I'm in finally!")
					}
			}
			exceptionTest()

			// 1. Write a code snippet that sets a to an array of n random integers between 0
			// (inclusive) and n (exclusive).
			def randomIntArray(num : Int) : Array[Int] = {
					val a = new Array[Int](num)
					for(i <- 0 until a.length){
								a(i) = scala.util.Random.nextInt(num)
					}
					a
			}
			println("Random Array: " + randomIntArray(10).mkString("<", ", ", ">") )

			// 2. Write a loop that swaps adjacent elements of an array of integers. For example,
			// Array(1, 2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).
			def swapArrayElements(a1 : Array[Int]) : Array[Int] = {
					for(i <- 0 until a1.length by 2){
						var temp = 0
						if(i + 1 < a1.length){
								temp = a1(i)
								a1(i) = a1(i+1)
								a1(i+1) = temp
						}
					}
					a1
			}
			
			var a1 = Array(1, 2, 3, 4, 5)
			var b1 : Array[Int] = swapArrayElements(a1)
			println("Random Array: " + b1.mkString("<", ", ", ">") )

			// 3. Repeat the preceding assignment, but produce a new array with the swapped
			// values. Use for/yield.
			def swapArrayElementsForYield(a1 : Array[Int]) = {
  			for(i <- a1.indices) yield a1(
  			  if(i%2 == 0){
  			    if(i + 1 == a1.length)
  			      i
  			    else
  			      i + 1
  			  }
  			  else{
  			    i-1
  			  }
  			)
			}
			println("Random Array: " + swapArrayElementsForYield(a1).mkString("<", ", ", ">") )

			// 4. Given an array of integers, produce a new array that contains all positive
			// values of the original array, in their original order, followed by all values that
			// are zero or negative, in their original order.
			def arrayArrangement(a2 : Array[Int]) : Array[Int] = {
  			val b : ArrayBuffer[Int] = new ArrayBuffer[Int]
			  val positiveIndices = for(i <- a2.indices if (a2(i) > 0) ) yield (i)
  			val nonPositiveIndices = for(i <- a2.indices if (a2(i) < 0) ) yield (i)

  			for(i <- positiveIndices) 
  			  b += a2(i)
  			
  			for(i <- nonPositiveIndices)
  			  b += a2(i)
  			  
			  b.toArray
			}
      val a2 : Array[Int] = Array(-1, 2, 3, -8, 6, -3, -7, 1, 2, 10, -5)
			println("Input Array: " + a2.mkString("<", ", ", ">") )
      println("Output Array: " + arrayArrangement(a2).mkString("<", ", ", ">") )
      
      // 5. How do you compute the average of an Array[Double]?
      val a3 : Array[Double] = Array[Double](1.5, 2,5, 5.0, 3.0, 5.2, 7.8)
      println("Average value for the Array[Double] is: " + a3.sum/a3.length)
      
			// 6. How do you rearrange the elements of an Array[Int] so that they appear in
			// reverse sorted order? How do you do the same with an ArrayBuffer[Int]?
      val b2 : ArrayBuffer[Int] = new ArrayBuffer[Int]
      b2 ++= a2.toBuffer
      
			scala.util.Sorting.quickSort(a2)
      println("Reverse sorted array: " + a2.reverse.mkString("<", ", ", ">"))
      
      // For ArrayBuffer
      def reverseArraybuffer(b : ArrayBuffer[Int]) : ArrayBuffer[Int] = {
        val resultBuffer : ArrayBuffer[Int] = new ArrayBuffer[Int]
        b.toArray.reverse.copyToBuffer(resultBuffer)
        resultBuffer
      }
      
      println("Reverse sorted ArrayBuffer: " + reverseArraybuffer(b2).mkString("<", ", ", ">"))
      
      
			// 7. Write a code snippet that produces all values from an array with duplicates
			// removed. (Hint: Look at Scaladoc.)
      val a4 = Array[Int](1, 4, 3, 4, 2, 3, 5, 5, 6)
      println("All unique elemets: " + a4.distinct.mkString("<", ", ", ">"))

			// 8. Rewrite the example at the end of Section 3.4, “Transforming Arrays,” on
			// page 34 using the drop method for dropping the index of the first match. Look
			// the method up in Scaladoc.
			def dropAllNegativesExceptFirst(a : ArrayBuffer[Int]) : ArrayBuffer[Int] = {
        val allNegativeValueIndices = for(i <- a.indices if(a(i) < 0)) yield (i)
        println("All negative indices: " + allNegativeValueIndices.mkString(", "))
        val ind = allNegativeValueIndices.drop(1)
        println("All negative indices but one: " + ind.mkString(", "))
        
        for(i <- ind.reverse) 
          a.remove(i)
        a
      }
      val a5 : ArrayBuffer[Int] = ArrayBuffer(-1, 2, 3, -8, 6, -3, -7, 1, 2, 10, -5)
			println("Result is: " + dropAllNegativesExceptFirst(a5).mkString("<" , ", ", ">"))

			// 9. Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs
			// that are in America. Strip off the "America/" prefix and sort the result.
			val collection = java.util.TimeZone.getAvailableIDs()
			collection.filter(_.contains("US/"))
			          .map(str => str.stripPrefix("US/"))
			          .map(f => println("timezones: " + f))

			// 10. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with
			// the call
			// val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
			// Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor
			// and get the return value as a Scala buffer. (Why this obscure class? It’s hard
			// to find uses of java.util.List in the standard Java library.)
			import java.awt.datatransfer._
			import scala.collection.JavaConverters._
			
			val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
      val imgFlavor = flavors.getNativesForFlavor(DataFlavor.imageFlavor) // Java List[String]
      val scalaBuffer = imgFlavor.asScala
      scalaBuffer.map(println(_))
	}
}