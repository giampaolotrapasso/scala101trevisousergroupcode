case class Pizza(val tomato: Boolean = true,
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

val order: List[Pizza] = List(pizza1, pizza2, pizza3, pizza4)

val noTomato: (Pizza => Boolean) = (p => p.tomato == false)
val bigOrder: (Pizza, Int) = (Pizza(ham = true), 3)
val howMany: Int = bigOrder._2
//bigOrder._2 = 4 //don't compile, tuple is immutable
val newBigOrder = bigOrder.copy(_2 = 4)


val number = List(3,4,2,1)
val hugeOrder: List[(Pizza, Int)] = order.zip(number)
val hugeOrder2 = hugeOrder.map( t => t.swap)

