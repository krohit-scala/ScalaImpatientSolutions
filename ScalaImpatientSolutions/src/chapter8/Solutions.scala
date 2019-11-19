package chapter8

object Solutions {
  
  def main(args: Array[String]): Unit = {
    // Problem-1
    val ca1 = new CheckingAccount(100.0)
    ca1.deposit(100)
    println(ca1.currentBalance)
    ca1.withdraw(10)
    println(ca1.currentBalance)
    
    // Problem-2
    val sa1 = new SavingsAccount(125.0, 10.0)
    sa1.deposit(10)
    println("Current Balance: " + sa1.currentBalance)
    sa1.withdraw(15)
    println("Current Balance: " + sa1.currentBalance)
    sa1.deposit(30)
    println("Current Balance: " + sa1.currentBalance)
    sa1.withdraw(20)
    println("Current Balance: " + sa1.currentBalance)
    sa1.earnMonthlyInterest
    println("Current Balance: " + sa1.currentBalance)
    sa1.withdraw(20)
    println("Current Balance: " + sa1.currentBalance)
    
    // Problem-3
    // Skipped
    
    // Problem-4
    val item1 = new SimpleItem(10, "KitKat")
    
    val bundle = new Bundle
    bundle.items.append(item1)
    bundle.items.append(new SimpleItem(15, "Coke"))
    bundle.items.append(new SimpleItem(10, "Apple"))
    
    println(bundle.description)
    println(s"Price of the bundle is: ${bundle.price}")
    
    // Problem-5
    val label1 = new LabeledPoint("My Label 1", 10.5, 15)
    println(s"${label1.label}, ${label1.x}, ${label1.y}")
    
    // Problem-6
    val rect = new Rectangle(1,2,3,4)
    rect.centerPoint
    val circle = new Circle(4,6)
    circle.centerPoint
    
    // Problem-7
    val sq1 = new Square(1, 2, 3)
    val sq2 = new Square(4)
    val sq3 = new Square()
    
    // Problem-11
    val pt1 = Point1(10, 20)
    println(pt1.toString())
  }

  
}

// 1. Extend the following BankAccount class to a CheckingAccount class that charges $1
// for every deposit and withdrawal.
// class BankAccount(initialBalance: Double) {
//     private var balance = initialBalance
//     def currentBalance = balance
//     def deposit(amount: Double) = { balance += amount; balance }
//     def withdraw(amount: Double) = { balance -= amount; balance }
// }

// NOTE: Renamed to BankAccount1 due to conflict with previous exercise which also has BankAccount class
class BankAccount1 (initialBalance: Double) { 
  private var balance :Double = initialBalance
  def currentBalance = balance
  def deposit(amount: Double) = { balance += amount; balance }
  def withdraw(amount: Double) = { balance -= amount; balance }
}
 
class CheckingAccount (initialBalance : Double) extends BankAccount1 (initialBalance) {
  override def deposit(amount: Double) = super.deposit(amount - 1)
  override def withdraw(amount: Double) = super.withdraw(amount + 1)
  
}

// 2. Extend the BankAccount class of the preceding exercise into a class SavingsAccount
// that earns interest every month (when a method earnMonthlyInterest is called)
// and has three free deposits or withdrawals every month. Reset the transaction
// count in the earnMonthlyInterest method.

class SavingsAccount(initialBalance: Double, val interestRate: Double) extends BankAccount1(initialBalance) {
  var transactionCounter : Int = 0
  
  override def withdraw(amount: Double) : Double = { 
    transactionCounter +=1
    var charge = if(transactionCounter > 3)  1 else 0
    println(s"This is transaction number: ${transactionCounter}")
    super.withdraw(amount + charge) 
  }
  
  override def deposit (amount: Double) : Double = {
    transactionCounter +=1
    var charge = if(transactionCounter > 3) 1 else 0
    println(s"This is transaction number: ${transactionCounter}")
    super.deposit(amount - charge)
  }
  
  def earnMonthlyInterest : Double = {
    transactionCounter = 0
    super.deposit(super.currentBalance * interestRate/100 * 1/12)
    super.currentBalance
  }
}

// 3. Consult your favorite Java or C++ textbook which is sure to have an example
// of a toy inheritance hierarchy, perhaps involving employees, pets, graphical
// shapes, or the like. Implement the example in Scala.

// Skipped

// 4. Define an abstract class Item with methods price and description. A SimpleItem is
// an item whose price and description are specified in the constructor. Take
// advantage of the fact that a val can override a def. A Bundle is an item that
// contains other items. Its price is the sum of the prices in the bundle. Also
// provide a mechanism for adding items to the bundle and a suitable description
// method.

abstract class Item {
  def price : Double
  def description : String
}

class SimpleItem (override val price: Double, override val description: String) extends Item { 
  override def toString: String = s"Item name is ${description} and price is: ${price}"
}

class Bundle extends Item {
  val items = scala.collection.mutable.ArrayBuffer[Item]()
  
  override def price : Double = {
//    var sumOfPrice = 0.0
//    for(item <- items){
//      sumOfPrice += item.price
//    }
//    sumOfPrice
    
    items.map(item => item.price).sum
  }
  
  override def description : String = s"This bundle has ${items.length} item(s)."
}


// 5. Design a class Point whose x and y coordinate values can be provided in a
// constructor. Provide a subclass LabeledPoint whose constructor takes a label
// value and x and y coordinates, such as
// new LabeledPoint("Black Thursday", 1929, 230.07)
class Point(val x: Double, val y: Double) {}

class LabeledPoint(val label: String, x: Double, y: Double) extends Point(x, y){ }

// 6. Define an abstract class Shape with an abstract method centerPoint and subclasses
// Rectangle and Circle. Provide appropriate constructors for the subclasses and
// override the centerPoint method in each subclass.
abstract class Shape {
  def centerPoint : Unit
}

// Assuming diagonally opposite points are given
class Rectangle (val x1 : Double, val x2 : Double, val y1 : Double, val y2 : Double) extends Shape {
  def centerPoint : Unit = {
    println(s"Center point is: (${(x1 + x2) / 2}, ${(y1 + y2)/2})")
  }
}

class Circle (val x: Double, val y: Double) extends Shape {
  def centerPoint : Unit = { println(s"Center point is: (${x}, ${y})") }
}


// 7. Provide a class Square that extends java.awt.Rectangle and has three constructors:
// one that constructs a square with a given corner point and width, one
// that constructs a square with corner (0, 0) and a given width, and one that
// constructs a square with corner (0, 0) and width 0.
class Square (x: Int, y: Int, width: Int) extends java.awt.Rectangle (x, y, width, width) {
  def this(width: Int) = { this(0,0,width) }
  def this() = { this(0,0,0) }
}

// 8. Compile the Person and SecretAgent classes in Section 8.6, “Overriding Fields,”
// on page 95 and analyze the class files with javap. How many name fields are
// there? How many name getter methods are there? What do they get? (Hint:
// Use the -c and -private options.)
class Person(val name: String) {
    override def toString = s"${getClass.getName}[name=$name]"
}

class SecretAgent(codename: String) extends Person(codename) {
    override val name = "secret" // Don’t want to reveal name . . .
    override val toString = "secret" // . . . or class name
}

// Compiled from "SecretAgent.scala"
// public class SecretAgent extends Person {
//   private final java.lang.String name; --> Field
//   private final java.lang.String toString;
//   public java.lang.String name(); --> Getter Method
//   public java.lang.String toString();
//   public SecretAgent(java.lang.String); --> COnstructor
//}


// 9. In the Creature class of Section 8.10, “Construction Order and Early Definitions,”
// on page 98, replace val range with a def. What happens when you also use a def
// in the Ant subclass? What happens when you use a val in the subclass? Why?
class Creature {
  def range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class Ant extends Creature {
//  override val range = 2
//  override def range : Int = 2
}
// While 'def' in the subclass can override and 'def' of the superclass.
// A 'val' in the subclass can override both - 'def' as well as 'val' of the superclass.
// Therefore, if we use both 'def' and 'val' in the subclass, we get the error that - method is defined twice.
// [ERROR] method range is defined twice; the conflicting value range was defined at line 222:16


// 11. Define a value class Point that packs integer x and y coordinates into a Long
// (which you should make private).
class Point1 (private val pack: Long) extends AnyVal {
  def x = (pack >> 32).toInt
  def y = pack.toInt
  
  override def toString = s"Packed Object: $pack,\tx = $x,\ty = $y"
}

object Point1 {
  def apply(x: Int, y: Int) = new Point1((x.toLong << 32) | y)
}
