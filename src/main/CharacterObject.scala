object CharacterObject {
  def main(args: Array[String]): Unit = {
    val player1 : Character = new Character("p1")
    val player2 : Character = new Character("p2")
    val player3 : Character = new Character("p3")

    println(player1.toString)
    player1.takeDamage(50, "AD")
    println(player1.toString)
  }
}
