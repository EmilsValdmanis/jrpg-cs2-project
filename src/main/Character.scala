class Character(var nickname: String) {
  var characterName: String = this.nickname
  var isDead : Boolean = false

  var maxHP: Int = 500
  var currentHP: Int = this.maxHP

  var maxMana: Int = 200
  var currentMana: Int = this.maxMana

  var AD: Int = 50
  var Armor: Int = 10

  var AP: Int = 50
  var magicResist: Int = 10

  def attack(opponent: Character): Unit ={
    opponent.TakingDamage(this.AD)
  }

  def TakingDamage(damageTaken: Int): Unit = {
    var Dodged: Boolean = false
    val random = scala.util.Random
    val randomInt : Int = random.nextInt(101)
    val dodgeChance: Int = ( this.Armor * 100 ) / damageTaken
//    println("Random = " + randomInt, "Chance = " + dodgeChance)
    if (dodgeChance > 100) {
      Dodged = true
    }else if (dodgeChance < 100 && dodgeChance >= 0){
      if (randomInt > dodgeChance){
        Dodged = false
      }else {
        Dodged = true
      }
    }

    if (Dodged == true){
      println(this.characterName + " dodged the attack")
      this.currentHP = this.currentHP
    }else if (Dodged == false){
      println(this.characterName + " did not dodge the attack")
      val damageMitigated: Double = 1 - this.Armor.toDouble/damageTaken
//      println("mitigated % " + damageMitigated)
      val actualTaken: Double  = damageMitigated * damageTaken
//      println("actual " + actualTaken)
      this.currentHP -= actualTaken.toInt
      println(this.characterName + " took " + actualTaken + " damage, now has " + this.currentHP + " (" + (damageTaken - actualTaken) + " mitigated)")
    }
  }
}

