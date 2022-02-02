import org.scalatest.funsuite.AnyFunSuite

class ExperienceTesting extends AnyFunSuite {
  test ("Testing XP gain"){
    val p1 : Character = new Character("p1")
    var amount = 200
    p1.gainXP(amount)
    assert(p1.xpGained == amount)
  }
  test ("Testing level up"){
    val p1 : Character = new Character("p1")
    var amount = 20
    p1.gainXP(amount)
    p1.tryLevel()
    assert(p1.level == 2)
  }

  test ("Testing multiple level up"){
    val p1 : Character = new Character("p1")
    var amount = 290
    p1.gainXP(amount)
    p1.tryLevel()
    assert(p1.level == 4)
    assert(p1.xpGained == amount - 280)
  }
  test ("Testing defeat Enemy functionality"){
    val p1 : Character = new Character("p1")
    val p2 : Character = new Character("p2")

    p1.defeatEnemy(p2)
    assert(p1.xpGained == p2.level * 20)
    p1.tryLevel()
    p2.defeatEnemy(p1)
    assert(p2.xpGained == p1.level * 20)
  }
}
