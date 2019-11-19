package chapter4

import java.util.Scanner
import scala.io.Source
import scala.collection.JavaConverters
import scala.collection.JavaConversions.{mapAsScalaMap, propertiesAsScalaMap}
import javax.xml.transform.Result


object Solutions {
    def main(args: Array[String]): Unit = {
            // 1. Set up a map of prices for a number of gizmos that you covet. Then produce
            // a second map with the same keys and the prices at a 10 percent discount.
            val covets = Map[String, Double](
                    "Item1" -> 100.0, 
                    "Item2" -> 120.50, 
                    "Item3" -> 315.0, 
                    "Item4" -> 225.0
            )
            covets.mapValues(x => x * 0.9)
            
            
            // 2. Write a program that reads words from a file. Use a mutable map to count
            // how often each word appears. To read the words, simply use a java.util.Scanner:
            // val in = new java.util.Scanner(java.io.File("myfile.txt"))
            // while (in.hasNext()) process in.next()
            // Or look at Chapter 9 for a Scalaesque way.
            // At the end, print out all words and their counts.
            val filePath = "c://Users//kumar.rohit//Desktop//WordCountInput.txt"
            
            def wordCountProcess(filepath: String) : scala.collection.mutable.Map[String, Int]= {
                val wordCountMap : scala.collection.mutable.Map[String, Int] = scala.collection.mutable.Map[String, Int]()
                try{
                    for(line <- Source.fromFile(filePath).getLines()){
                        // println(line)
                        val words = line.split(" ")
                        for(word <- words){
                            wordCountMap += (word -> (wordCountMap.getOrElse(word, 0) + 1))
                        }
                    }
                    wordCountMap
                }
                catch{
                    case t : Exception => t.printStackTrace()
                    null
                }
            }
            
            val wordCountMap = wordCountProcess(filePath)
            // for((k, v) <- wordCountMap) println ("Word: " + k + "\tCount: " + v)

            // 3. Repeat the preceding exercise with an immutable map.
            def wordCountProcessImmutableMap(filepath: String) : scala.collection.immutable.Map[String, Int]= {
                var wordCountMapImmutable : scala.collection.immutable.Map[String, Int] = scala.collection.immutable.Map[String, Int]()
                try{
                    for(line <- Source.fromFile(filePath).getLines()){
                        // println(line)
                        val words = line.split(" ")
                        for(word <- words){
                            wordCountMapImmutable = wordCountMapImmutable + (word -> (wordCountMapImmutable.getOrElse(word, 0) + 1))
                        }
                    }
                    wordCountMapImmutable
                }
                catch{
                    case t : Exception => t.printStackTrace()
                    null
                }
            }
            
            val wordCountMapImmutable = wordCountProcessImmutableMap(filePath)
            // for((k, v) <- wordCountMapImmutable) println ("Word: " + k + "\tCount: " + v)
            
            // 4. Repeat the preceding exercise with a sorted map, so that the words are
            // printed in sorted order.
            def wordCountProcessSortedMap(filepath: String) : scala.collection.immutable.TreeMap[String, Int]= {
                var wordCountSortedMap : scala.collection.immutable.TreeMap[String, Int] = scala.collection.immutable.TreeMap[String, Int]()
                try{
                    for(line <- Source.fromFile(filePath).getLines()){
                        // println(line)
                        val words = line.split(" ")
                        for(word <- words){
                            wordCountSortedMap = wordCountSortedMap + (word -> (wordCountMap.getOrElse(word, 0) + 1))
                        }
                    }
                    wordCountSortedMap
                }
                catch{
                    case t : Exception => t.printStackTrace()
                    null
                }
            }
            
            val wordCountMapSorted = wordCountProcessSortedMap(filePath)
            // for((k, v) <- wordCountMapSorted) println ("Word: " + k + "\tCount: " + v)
            
            // 5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the
            // Scala API.
            def wordCountProcessTreeMap(filepath: String) : java.util.TreeMap[String, Int]= {
                var wordCountTreeMap = new java.util.TreeMap[String, Int]()
                try{
                    for(line <- Source.fromFile(filePath).getLines()){
                        // println(line)
                        val words = line.split(" ")
                        for(word <- words){
                            wordCountTreeMap.put(word, wordCountTreeMap.getOrDefault(word, 0) +  1)
                        }
                    }    
                    wordCountTreeMap
                }
                catch{
                    case t : Exception => t.printStackTrace()
                    null
                }
            }
            val wordCountTreeMap = wordCountProcessTreeMap(filePath)
            for((k, v) <- wordCountTreeMap) println ("Word: " + k + "\tCount: " + v)
                        
            // 6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY, and
            // similarly for the other weekdays. Demonstrate that the elements are visited
            // in insertion order.
            def weekdaysLinkedHashMap() : scala.collection.mutable.LinkedHashMap[String, Int] = {
                val linkedHashMap = scala.collection.mutable.LinkedHashMap[String, Int]()
                linkedHashMap.+=("MONDAY" -> java.util.Calendar.MONDAY)
                linkedHashMap.+=("TUESDAY" -> java.util.Calendar.TUESDAY)
                linkedHashMap.+=("WEDNESDAY" -> java.util.Calendar.WEDNESDAY)
                linkedHashMap.+=("THURSDAY" -> java.util.Calendar.THURSDAY)
                linkedHashMap.+=("FRIDAY" -> java.util.Calendar.FRIDAY)
                linkedHashMap.+=("SATURDAY" -> java.util.Calendar.SATURDAY)
                linkedHashMap.+=("SUNDAY" -> java.util.Calendar.SUNDAY)
                linkedHashMap
            }
            for((day, dayNum) <- weekdaysLinkedHashMap()) println("Day of week: " + day + ", Day number: " + dayNum)
            
            // 7. Print a table of all Java properties, like this:
            //    java.runtime.name     | Java(TM) SE Runtime Environment
            //    sun.boot.library.path | /home/apps/jdk1.6.0_21/jre/lib/i386
            //    java.vm.version       | 17.0-b16
            //    java.vm.vendor        | Sun Microsystems Inc.
            //    java.vendor.url       | http://java.sun.com/
            //    path.separator        | :
            //    java.vm.name          | Java HotSpot(TM) Server VM
            // You need to find the length of the longest key before you can print the table.
            def getJavaProperties() : scala.collection.immutable.List[String] = {
                val javaProperties = System.getProperties
                val result = scala.collection.mutable.ArrayBuffer[String]()
                var maxLength = 0
                // Get the max length
                javaProperties.map(x => {
                    if(x._1.length() > maxLength)
                        maxLength = x._1.length()
                    }
                )
                println("Max length is: " + maxLength)
                // Build the list of String required to be printed
                javaProperties.map(
                    x => result.+=(x._1.padTo(maxLength, " ").mkString + " | " + x._2)
                )
                result.toList
            }
            val result = getJavaProperties()
            result.map(println(_))

            // 8. Write a function minmax(values: Array[Int]) that returns a pair containing the
            // smallest and largest values in the array.
            def minmax(arr : Array[Int]) : scala.collection.mutable.HashMap[Int, Int] = {
                val minmax = scala.collection.mutable.HashMap[Int, Int]()
                val min = arr.min
                val max = arr.max
                minmax.+=(min -> max)
            }
            var minmaxValues = minmax(Array(4, 3, 5, 8, 2, 9))
            for((min, max) <- minmaxValues) println("Min: " + min + "\tMax: " + max)
            
            // 9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple containing
            // the counts of values less than v, equal to v, and greater than v.
            def lteqgt(values: Array[Int], v: Int) : (Int, Int, Int) = {
                var lt, eq, gt = 0
                values.map(
                        x => {
                            if (x < v) lt += 1
                            if (x == v) eq += 1
                            if (x > v) gt += 1
                        }
                )
                (lt, eq, gt)
            }
            val counts = lteqgt(Array(2, 4, 1, 6, 9, 3, 2, 7), 4)
            println("lt, eq, gt are: " + counts._1 + ", " + counts._2 + ", " + counts._3 )
            
            // 10. What happens when you zip together two strings, such as "Hello".zip("World")?
            // Come up with a plausible use case.
            var helloWorld = "Hello".zip("World").toMap
            println(helloWorld)
    }
}