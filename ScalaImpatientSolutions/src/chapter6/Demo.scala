package chapter6

object Demo {
  def main(args: Array[String]): Unit = {
    println("Account Number: " + Account.getNewAaccountNumber)
    println("Account Number: " + Account.getNewAaccountNumber)
    println("Account Number: " + Account.getNewAaccountNumber)
  }
}

object Account {
  private var lastAccountNumber : Int = 0
  def getNewAaccountNumber : Int = {
    lastAccountNumber += 1
    lastAccountNumber
  }
}