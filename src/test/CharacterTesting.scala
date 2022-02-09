import org.scalatest.funsuite.AnyFunSuite


class CharacterTesting  extends AnyFunSuite {
  test ( "testing takeDamage method"){
    val p1: Character = new Character("p1")
    val damageDealt: Int = 200
    p1.takeDamage(damageDealt, "AD")
    assert( p1.currentHP == p1.maxHP - damageDealt + p1.Armor)
  }

  test("Testing non-lethal damage"){
    val p1: Character = new Character("p1")
    val damageDealt: Int = 500
    p1.takeDamage(damageDealt, "AP")
    assert(!p1.isDead)
  }

  test("Testing lethal damage"){
    val p1: Character = new Character("p1")
    val damageDealt: Int = 600
    p1.takeDamage(damageDealt, "AD")
    assert(p1.isDead)
  }

  test("Testing player attacks"){
    val p1: Character = new Character("p1")
    val p2: Character = new Character("p2")

    p1.attackAD(p2)
    assert(p2.currentHP == p2.maxHP - p1.AD + p2.Armor)
    p2.attackAP(p1)
    assert(p1.currentHP == p1.maxHP - p2.AP + p1.MR)

  }

  test("Testing mana reduction"){
    val p1: Character = new Character("p1")
    val p2: Character = new Character("p2")

    p1.attackAP(p2)
    assert(p1.currentMana == p1.maxMana - (p1.AP / 2.5))
    p1.attackAP(p2)
    assert(p1.currentMana == p1.maxMana - 2 * (p1.AP / 2.5))
  }

  test("Testing AP attack without any mana"){
    val p1: Character = new Character("p1")
    val p2: Character = new Character("p2")

    p1.AP = 260
    p1.attackAP(p2)

    assert(p2.currentHP == p2.maxHP)
  }
  test("testing assassin"){
    val a1: Assassin = new Assassin("a1")
    val t1: Tank = new Tank("t1")
    a1.gainXP(10000)
    println(a1.toString)
    println(t1.toString)

    a1.pierce(t1)

    println("-----------")
    println(a1.toString)
    println(t1.toString)
  }
}
