import org.scalatest.funsuite.AnyFunSuite

class ExperienceTesting extends AnyFunSuite {
  test ("Testing XP gain"){
    val p1 : Character = new Character("p1")
    val amount = 200
    p1.gainXP(amount)
    assert(p1.xpGained == amount)
  }
  test ("Testing level up"){
    val p1 : Character = new Character("p1")
    val amount = 20
    p1.gainXP(amount)
    assert(p1.level == 2)
    assert(p1.currentHP == 600) // default = 500
    assert(p1.AD == 60) // default = 50
  }

  test ("Testing multiple level up"){
    val p1 : Character = new Character("p1")
    val amount = 290
    val xpForLevel4 = 280
    p1.gainXP(amount)
    assert(p1.level == 4)
    assert(p1.currentXp == amount - xpForLevel4)
  }
  test ("Testing defeat Enemy functionality"){
    val p1 : Character = new Character("p1")
    val p2 : Character = new Character("p2")

    p1.defeatEnemy(p2)
    assert(p1.xpGained == p2.level * 20)
    p2.defeatEnemy(p1)
    assert(p2.xpGained == p1.level * 20)
  }
}
