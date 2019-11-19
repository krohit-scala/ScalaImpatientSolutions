package chapter6

object Solutions {
  def main(args: Array[String]): Unit = {
    // Problem-1
    println(Conversions.inchesToCentimeters(10))
    println(Conversions.gallonsToLiters(4))
    println(Conversions.milesToKilometers(2.4))
    
    // Problem-2
    println(InchesToCentimeters.apply(10))
    println(GallonsToLeters(2))
    println(MilesToKilometers(5))
    
    // Problem-3
    // Certainly not a good idea to extend java.awt.Point class as it contains pointers (for lack of a better word)
    // to the reserved keywords like app, main, try, catch, etc. Overriding these would cause instability in compilor.
    
    // Problem-4
    val point1 = Point(10, 15)
    val point2 = Point(3, 6)
    
    // Problem-5 
    Reverse.main(Array("Hello", "India"))
    
    // Problem-6-7
    import Deck._
    println(DIAMONDS)
    println("Is DIAMOND Red: " + Deck.isRed(HEARTS))
    println("Is SPADES Red: " + Deck.isRed(SPADES))
    
    // Problem-8
    import Cube._
    Cube.values.map(color => println(s"Color is ${color}, Value is: ${color.id}"))
  }
}

// 1. Write an object Conversions with methods inchesToCentimeters, gallonsToLiters, and
// milesToKilometers.
object Conversions {
  def inchesToCentimeters(inches : Double) : Double = {
    inches * 2.54
  }
  
  def gallonsToLiters(gallons : Double): Double = {
    gallons * 3.785
  }
  
  def milesToKilometers(miles : Double) : Double = {
    miles * 1.6
  }
}

// 2. The preceding problem wasn’t very object-oriented. Provide a general superclass
// UnitConversion and define objects InchesToCentimeters, GallonsToLiters, and
// MilesToKilometers that extend it.
abstract class UnitConversion {
  def apply(input : Double) : Double
}

object InchesToCentimeters extends UnitConversion {
  def apply(input : Double) : Double = {
    input * 2.54
  }
}

object GallonsToLeters extends UnitConversion {
  def apply(input : Double) : Double = {
    input * 3.785
  }
}

object MilesToKilometers extends UnitConversion {
  def apply(input : Double) : Double = {
    input * 1.6
  }
}


// 3. Define an Origin object that extends java.awt.Point. Why is this not actually a
// good idea? (Have a close look at the methods of the Point class.)
object Origin extends java.awt.Point { }


// 4. Define a Point class with a companion object so that you can construct Point
// instances as Point(3, 4), without using new.
class Point private(val x : Int, val y : Int) { }

object Point {
  def apply(x : Int, y : Int) = {
    new Point(x, y)
    println(s"(x, y) is ($x, $y)")
  }
}

// 5. Write a Scala application, using the App trait, that prints its command-line
// arguments in reverse order, separated by spaces. For example, scala Reverse
// Hello World should print World Hello.
object Reverse extends App {
  for(i <- args.indices.reverse) {println(args(i)) }
}


// 6. Write an enumeration describing the four playing card suits so that the toString
// method returns Clubs, Diamonds, Hearts and Spades.
// 7. Implement a function that checks whether a card suit value from the preceding
// exercise is red.
object Deck extends Enumeration {
  val CLUBS, DIAMONDS, HEARTS, SPADES = Value
  
  def isRed(suit : Value) : Boolean = {
    suit == HEARTS || suit == DIAMONDS
  }
}

// 8. Write an enumeration describing the eight corners of the RGB color cube. As
// IDs, use the color values (for example, 0xff0000 for Red).
object Cube extends Enumeration {
  type Cube = Value
  val BLACK = Value(0x000000)
  val RED = Value(0xff0000)
  val GREEN = Value(0x00ff00)
  val BLUE = Value(0x0000ff)
  val YELLOW = Value(0xffff00)
  val MAGENTA = Value(0xff00ff)
  val CYAN = Value(0x00ffff)
  val WHITE = Value(0xffffff)
}