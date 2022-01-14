import org.scalatest.funsuite.AnyFunSuite

class CharacterTesting  extends AnyFunSuite {
  test ( "testing damage taken"){
    val test1: Character = new Character("TheOnlyEmy")
    test1.TakingDamage(50)
    assert(test1 .currentHP < test1.maxHP)
  }

  test("testing attack"){
    val p1: Character = new Character("p1")
    val p2: Character = new Character("p2")

    p1.attack(p2)
    assert (p2.currentHP < 500)
  }
}
