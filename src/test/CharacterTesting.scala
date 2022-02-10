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
  test("testing assassin class"){
    val a1: Assassin = new Assassin("a1")
    val t1: Tank = new Tank("t1")

    assert(a1.AD == 75 && a1.AD != t1.AD)
    assert(a1.Armor == 15 && a1.MR == 15)
    assert(a1.maxMana == 0)
    assert(a1.battleOptions() == List("pierce"))

    a1.gainXP(280)
    assert(a1.level == 4 && a1.maxHP == 760 && a1.AD == 120)

    a1.gainXP(100000)
    assert(a1.battleOptions() == List("pierce", "deathMark", "critHit"))

    a1.takeAction("pierce", t1)
    assert(t1.currentHP != 1000 && t1.currentHP == (t1.maxHP - a1.AD))
    t1.currentHP = 1000

    a1.takeAction("deathMark", t1)
    assert(t1.currentHP != 1000 && t1.currentHP == t1.maxHP - a1.AD + t1.Armor)
    t1.currentHP = 1000

    a1.takeAction("critHit", t1)
    assert(t1.currentHP != 1000 && t1.currentHP == t1.maxHP - a1.AD * 2) // this may fail because the ability has a chance to miss (20%)
  }

  test("testing tank class"){
    val a1: Assassin = new Assassin("a1")
    val t1: Tank = new Tank("t1")

    assert(t1.maxHP == 1000 && t1.maxHP != a1.maxHP)
    assert(t1.maxMana == 100)
    assert(t1.Armor == 30 && t1.MR == 30)
    assert(t1.battleOptions() == List("smash"))

    t1.gainXP(280)
    assert(t1.level == 4 && t1.maxHP == 1900 && t1.AD == 50 && t1.AP == 50)

    t1.gainXP(100000)
    assert(t1.battleOptions() == List("smash", "feast", "deathSentence"))

    t1.takeAction("smash", a1)
    assert(a1.currentHP != a1.maxHP && a1.currentHP == 270)
    a1.currentHP = a1.maxHP

    t1.currentHP = 1000
    t1.takeAction("feast", t1)
    assert(t1.currentHP == 1000 + t1.level * 30)
    assert(t1.Armor == 271 && t1.MR == 271 && t1.AP == 150)

    t1.currentHP = t1.maxHP
    t1.takeAction("deathSentence", a1)
    assert(a1.currentHP == a1.maxHP - 2 * (t1.AP - a1.MR) - a1.MR )
    assert(t1.currentMana != t1.maxMana)
    assert(t1.currentHP == t1.maxHP - 40)
  }
}
