import org.scalatest.funsuite.AnyFunSuite

class PartyTesting extends AnyFunSuite {
  test ("testing XP per member"){
    val p1: Character = new Character("p1")
    val p2: Character = new Character("p2")
    val p3: Character = new Character("p3")
    p1.level = 2
    p2.level = 1
    p3.level = 4

    val d1: Character = new Character("d1")
    val d2: Character = new Character("d2")
    val d3: Character = new Character("d3")
    val d4: Character = new Character("d4")
    d1.isDead = true

    val party1: Party = new Party(List(p1,p2,p3))
    val party2: Party = new Party(List(d1,d2,d3,d4))

    party2.defeatEnemyParty(party1)
    assert(d1.xpGained == 0)
    assert(d2.xpGained == (20*7/3))
    assert(d3.xpGained == (20*7/3))
    assert(d4.xpGained == (20*7/3))
  }
}
