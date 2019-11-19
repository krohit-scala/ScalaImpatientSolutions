package chapter5

import scala.beans.BeanProperty

object Solutions {
    def main(args: Array[String]): Unit = {
        // 1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless
        // Methods,” on page 51 so that it doesn’t turn negative at Int.MaxValue.
        class Counter1 private(var counter : Int = 0){
            // Increment the counter
            def increment() : Unit = {
                if(this.counter + 1 == Int.MaxValue)
                    throw new Exception("Error: Cannot increase further. Reached Max Value!")
                counter += 1
            }
            
            // Returns the current counter
            def current = this.counter
        }

        // 2. Write a class BankAccount with methods deposit and withdraw, and a read-only
        // property balance.
        class BankAccount (private var balance : Double = 0){
            // Deposit
            def deposit(credit : Double) : Unit = {
                balance += credit
            }
            
            // Withdraw 
            def withdraw(debit : Double) : Unit = {
                balance -= debit
            }
            
            // Balance
            def checkBalance : Double = this.balance
        }

        // 3. Write a class Time with read-only properties hours and minutes and a method
        // before(other: Time): Boolean that checks whether this time comes before the
        // other. A Time object should be constructed as new Time(hrs, min), where hrs is in
        // military time format (between 0 and 23).
        class Time private (var hours : Int, var minutes : Int){
            def before(other : Time) : Boolean = {
                if(this.hours < other.hours) true
                else if(this.hours == other.hours && this.minutes <= other.minutes) true
                else false
            }
        }
        
        // 4. Reimplement the Time class from the preceding exercise so that the internal
        // representation is the number of minutes since midnight (between 0 and
        // 24 × 60 – 1). Do not change the public interface. That is, client code should be
        // unaffected by your change.
        class Time1 private(var hours: Int, var minutes : Int){
            def before(other : Time1) : Boolean = {
                val time1 = this.hours * 60 + this.minutes
                val time2 = other.hours * 60 + other.minutes
                
                if(time1 < time2) true else false
            }
        }
        
        // 5. Make a class Student with read-write JavaBeans properties name (of type String)
        // and id (of type Long). What methods are generated? (Use javap to check.) Can
        // you call the JavaBeans getters and setters in Scala? Should you?
        class Student{
            @BeanProperty var id : Int = 0
            @BeanProperty var name : String = ""
        }
        
        // 6. In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,”
        // on page 51, provide a primary constructor that turns negative ages to 0.
        class Person (newAge : Int){
            var age = if(newAge < 0) 0 else newAge
        }

        // 7. Write a class Person with a primary constructor that accepts a string containing
        // a first name, a space, and a last name, such as new Person("Fred Smith"). Supply
        // read-only properties firstName and lastName. Should the primary constructor
        // parameter be a var, a val, or a plain parameter? Why?
        class Person1 (name : String){
            val firtName = name.split(" ")(0)
            val lastName = name.split(" ")(1)
        }
        
        // 8. Make a class Car with read-only properties for manufacturer, model name,
        // and model year, and a read-write property for the license plate. Supply four
        // constructors. All require the manufacturer and model name. Optionally,
        // model year and license plate can also be specified in the constructor. If not,
        // the model year is set to -1 and the license plate to the empty string. Which
        // constructor are you choosing as the primary constructor? Why?
        class Car (
            val manufacturer : String,
            val modelName : String,
            val modelYear : Int = -1,
            var licensePlate : String = "" ) {
            
            def this(manufacturer: String, modelName : String, licensePlate : String){
                this(manufacturer, modelName, -1, licensePlate)
            }
            
        }
        
        // 9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your
        // choice). How much shorter is the Scala class?

        // 10. Consider the class
        // class Employee(val name: String, var salary: Double) {
        //     def this() { this("John Q. Public", 0.0) }
        // }
        // Rewrite it to use explicit fields and a default primary constructor. Which form
        // do you prefer? Why?
        class Employee{
            val name : String = "John Q. Public"
            val salary : Double = 0.0
        }
    }
}