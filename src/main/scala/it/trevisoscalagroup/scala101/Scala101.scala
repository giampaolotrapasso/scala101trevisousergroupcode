package it.trevisoscalagroup.scala101

import scala.util.Random


object MutablePizza {

  class Pizza(var tomato: Boolean = true,
              var cheese: Boolean = true,
              var ham: Boolean = true,
              var size: String = "NORMAL")

  val pizza = new Pizza(true, false, true, "HUGE") // type inference


  // val pizza : Pizza = new Pizza(true, false, true, "HUGE")
  val smallSize = "SMALL"
  // type inference again
  val newTomato = false // and again
  pizza.size = smallSize
  pizza.tomato = newTomato

}

object NoCaseClass {

  class Pizza(val tomato: Boolean = true,
              val cheese: Boolean = true,
              val ham: Boolean = true,
              val size: String = "NORMAL") {

    def slice(numberOfPieces: Int): Unit = {
      println(s"Pizza is in ${numberOfPieces} pieces")
    }

  }


  val pizza1 = new Pizza(true, true, false, "NORMAL")
  val pizza2 = new Pizza(true, true)
  val pizza3 = new Pizza(tomato = true, ham = true, size = "HUGE", cheese = true)
  val pizza4 = new Pizza(size = "SMALL")


  var secondPizza = new Pizza(true, true, true, "NORMAL")
  secondPizza = new Pizza(false, false, true, "HUGE")
  // will compile
  val thirdPizza = new Pizza(true, false, true, "SMALL")

  // thirdPizza = secondPizza // won't compile


  object Oven {
    def cook(pizza: Pizza): Unit = {
      println(s"Pizza $pizza is cooking")
      Thread.sleep(1000)
      println("Pizza is ready")
    }
  }


  object Margherita extends Pizza(true, false, false, "NORMAL") {
    override def toString = "Pizza Margherita"
  }


}

object CaseClass {

  case class Pizza(val tomato: Boolean = true,
                   val cheese: Boolean = true,
                   val ham: Boolean = true,
                   val size: String = "NORMAL") {

    def slice(numberOfPieces: Int): Unit = {
      println(s"Pizza is in ${numberOfPieces} pieces")
    }

  }

  object Margherita extends Pizza(true, false, false, "NORMAL") {
    override def toString = "Pizza Margherita"
  }

  val p1 = Pizza.apply(cheese = false)
  // not idiomatic
  val p2 = Pizza(cheese = false)

  abstract class Worker(val name: String) {
    def greet: String // this is abstract
  }

  trait PizzaMaker {
    def preparePizza(pizza: Pizza) = println(s"I prepare ${pizza}")
  }

  trait Waiter {
    def servePizza(pizza: Pizza) = println(s"this is your ${pizza}")
  }

  class Handyman(override val name: String, val wage: Int) extends Worker(name) with PizzaMaker with Waiter {
    override def greet: String = s"Hello, I'm ${name}"
  }

  val vito = new Handyman("Vito", 1000)
  println(vito.greet)
  vito.preparePizza(Margherita)
  vito.preparePizza(Margherita)


}

object Linearization {

  case class Pizza(val tomato: Boolean = true,
                   val cheese: Boolean = true,
                   val ham: Boolean = true,
                   val size: String = "NORMAL") {

    def slice(numberOfPieces: Int): Unit = {
      println(s"Pizza is in ${numberOfPieces} pieces")
    }

  }

  object Margherita extends Pizza(true, false, false, "NORMAL") {
    override def toString = "Pizza Margherita"
  }

  val p1 = Pizza.apply(cheese = false)
  // not idiomatic
  val p2 = Pizza(cheese = false)

  abstract class Worker(val name: String) {
    def greet: String // this is abstract
  }

  trait PizzaMaker extends Worker {
    def preparePizza(pizza: Pizza) = println(s"I prepare ${pizza}")

    override def greet = "Hello"
  }

  trait Waiter extends Worker {
    def servePizza(pizza: Pizza) = println(s"this is your ${pizza}")

    override def greet = "How can I serve you?"
  }

  class Handyman(override val name: String, val wage: Int) extends Worker(name) with Waiter with PizzaMaker


  def execute = {
    val vito = new Handyman("Vito", 1000)
    println(vito.greet)
    vito.preparePizza(Margherita)
    vito.preparePizza(Margherita)
  }

}


object MoreOnTraits {

  case class Pizza(val tomato: Boolean = true,
                   val cheese: Boolean = true,
                   val ham: Boolean = true,
                   val size: String = "NORMAL") {

    def slice(numberOfPieces: Int): Unit = {
      println(s"Pizza is in ${numberOfPieces} pieces")
    }

  }

  object Margherita extends Pizza(true, false, false, "NORMAL") {
    override def toString = "Pizza Margherita"
  }

  val p1 = Pizza.apply(cheese = false)
  // not idiomatic
  val p2 = Pizza(cheese = false)

  abstract class Worker(val name: String) {
    def greet: String // this is abstract
  }

  trait PizzaMaker {
    def preparePizza(pizza: Pizza) = println(s"I prepare ${pizza}")

    def greet = "Hello"

  }

  trait Waiter {
    def servePizza(pizza: Pizza) = println(s"this is your ${pizza}")

    def greet = "How can I serve you?"

  }

  class Handyman(override val name: String, val wage: Int) extends Worker(name) with Waiter with PizzaMaker {
    override def greet = super[Waiter].greet
  }


  def runExample = {
    val vito = new Handyman("Vito", 1000)
    println(vito.greet)
    vito.preparePizza(Margherita)
    vito.preparePizza(Margherita)
  }

}

object WontCompile {
/*

  abstract class Worker(val name: String){
    def greet : String // this is abstract
  }

  class Intern

  trait PizzaMaker extends Intern {
    def greet = "Hello"
  }

  trait Waiter extends Worker{
    def greet = "How can I serve you?"
  }

  class Handyman(override val name: String, val wage: Int) extends Worker(name) with  Waiter with PizzaMaker


*/
}

object PatternMatching {

  case class Pizza(val tomato: Boolean = true,
                   val cheese: Boolean = true,
                   val ham: Boolean = true,
                   val size: String = "NORMAL") {

    def slice(numberOfPieces: Int): Unit = {
      println(s"Pizza is in ${numberOfPieces} pieces")
    }

  }


  object Margherita extends Pizza(true, false, false, "NORMAL") {
    override def toString = "Pizza Margherita"
  }

  object Pino {

    def comment(pizza: Pizza) =
      pizza match {
        case Pizza(tomato, cheese, ham, size) if size == "HUGE" => "Wow!"
        case Pizza(false, cheese, ham, size) =>
          s"No tomato on this ${size} pizza"
        case Pizza(_, _, _, "SMALL") =>
          "Little champion, your pizza is coming"
        case pizza@Margherita =>
          s"I like your ${pizza.size} Margherita"
        case _ => "OK"
      }

  }

  def runExample {
    val pizza1 = new Pizza(true, true, false, "NORMAL")
    val pizza2 = new Pizza(true, true)
    val pizza3 = new Pizza(tomato = true, ham = true, size = "HUGE", cheese = true)
    val pizza4 = new Pizza(size = "SMALL")

    val order: List[Pizza] = List(pizza1, pizza2, pizza3, pizza4)

    val noTomato: (Pizza => Boolean) = (p => p.tomato == false)


    order
      .filter(noTomato)
      .filter(p => p.ham == true)
      .map(pizza => Pino.comment(pizza))
      .map(println)


    val bigOrder: (Pizza, Int) = (Pizza(ham = true), 3)
    val howMany: Int = bigOrder._2
    //bigOrder._2 = 4 //don't compile, tuple is immutable
    val newBigOrder = bigOrder.copy(_2 = 4)

    val number = List(3, 4, 2, 1)
    val hugeOrder: List[(Pizza, Int)] = order.zip(number)
    val hugeOrder2 = hugeOrder.map(t => t.swap)

  }


}


object OperatorsAsMethods {


  case class Pizza(tomato: Boolean = true, cheese: Boolean = true,
                   ham: Boolean = true, size: String = "NORMAL") {

    def slice(numberOfPieces: Int) =
      s"Pizza is in ${numberOfPieces} pieces"

    def /(numberOfPieces: Int) = slice(numberOfPieces)
  }

  def runExamples = {
    val pizza = Pizza()
    println(pizza.slice(4))
    println(pizza./(4))
    println(pizza / 4)
  }
}


object Implicits {

  case class Pizza(val tomato: Boolean = true,
                   val cheese: Boolean = true,
                   val ham: Boolean = true,
                   val size: String = "NORMAL") {

    def slice(numberOfPieces: Int): Unit = {
      println(s"Pizza is in ${numberOfPieces} pieces")
    }

  }


  object Margherita extends Pizza(true, false, false, "NORMAL") {
    override def toString = "Pizza Margherita"
  }

  class MargheritaList(val n: Int) {

    def margherita: List[Pizza] = {
      var list: List[Pizza] = List()
      for (i <- 1 to n)
        list = list :+ Margherita
      list
    }

  }

  val order1 = new MargheritaList(4).margherita

  import scala.language.implicitConversions
  import scala.language.postfixOps

  implicit def fromIntToMargherita(n: Int) = new MargheritaList(n)

  val order2: List[Pizza] = 4.margherita

  val order3 : List[Pizza] = 4 margherita

  val p: Pizza = order3 head


}

object ForComprehension {

  case class Pizza(val tomato: Boolean = true,
                   val cheese: Boolean = true,
                   val ham: Boolean = true,
                   val size: String = "NORMAL") {

    def slice(n: Int): List[Slice] = List.fill(n)(Slice(this, n))


  }

  case class Slice(val p: Pizza, val fractions: Int) {
    override def toString = s"1/$fractions of $p"
  }


  def runExample = {
    val order = List(Pizza(), Pizza(ham = false))

    val l1: List[List[Slice]] = order.map(p => p.slice(4))
    val l2: List[Slice] = order.flatMap(p => p.slice(4))


    val l3: List[String] = order.flatMap(p => p.slice(4)).map(slice => slice.toString)

    val l4: List[String] = for {
      pizza: Pizza <- order
      slice: Slice <- pizza.slice(4)
    } yield slice.toString

    l4

  }

}

object Options {

  case class Pizza(tomato: Boolean = true,
                   cheese: Boolean = true,
                   ham: Boolean = true,
                   size: String = "NORMAL") {

  }

  object TheLazyPizzaMaker {
    def prepare(p: Pizza): Option[Pizza] =
      if (Random.nextInt(6) == 0) None
      else Some(p)
  }

  object Margherita extends Pizza(true, false, false, "NORMAL") {
    override def toString = "Pizza Margherita"
  }

  def runExample = {


    val option = TheLazyPizzaMaker.prepare(Margherita)

    option match {
      case Some(p) => "My pizza is ready"
      case None => "Fire that pizza maker!"
    }


    option.map(o => "My pizza is ready").getOrElse("Fire that pizza maker!")
  }


}

object NoImplicitParameter {

  case class Pizza(tomato: Boolean = true,
                   cheese: Boolean = true,
                   ham: Boolean = true,
                   size: String = "NORMAL") {

  }

  object Pasquale {
    def prepare(p: Pizza) = s"I prepare you pizza $p"
    def receiveOrder(p: Pizza) = s"You ordered me $p"
    def serve(p: Pizza) = s"Here is your pizza $p"
    def receivePayment(p: Pizza, tip: Int) = s"Thanks for paying $p and for the $tip $$"
  }

  def runExample = {
    val p = new Pizza(tomato = false)
    Pasquale.receiveOrder(p)
    Pasquale.prepare(p)
    Pasquale.serve(p)
    Pasquale.receivePayment(p, 3)
  }


}

object ImplicitParameter {

  case class Pizza(tomato: Boolean = true,
                   cheese: Boolean = true,
                   ham: Boolean = true,
                   size: String = "NORMAL") {

  }

  object Pasquale {

    def prepare(implicit p: Pizza) = s"I prepare you pizza $p"
    def receiveOrder(implicit p: Pizza) = s"You ordered me $p"
    def serve(implicit p: Pizza) = s"Here is your pizza $p"
    def receivePayment(tip: Int)(implicit p: Pizza) = s"Thanks for paying $p and for the $tip $$ "

  }

  def runExample = {

    implicit val p = new Pizza(tomato = false)

    Pasquale.receiveOrder
    Pasquale.prepare
    Pasquale.serve
    Pasquale.receivePayment(tip = 3)
  }


}



