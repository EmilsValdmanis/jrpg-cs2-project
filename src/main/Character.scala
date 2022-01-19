class Character(var nickname: String) {
  var characterName: String = this.nickname
  var isDead : Boolean = false

  val maxHP: Int = 500
  var currentHP: Int = this.maxHP

  val maxMana: Int = 100
  var currentMana: Int = this.maxMana

  var AD: Int = 100 // AKA AttackDamage
  var Armor: Int = 10

  var AP: Int = 50 // AKA AbilityPower
  var MR: Int = 5 // AKA Magic Resistance

  def attackAD(opponent: Character): Unit ={
    opponent.takeDamage(this.AD, "AD")
  }

  def attackAP(opponent: Character): Unit ={
    val manaReduced: Int = (this.AP / 2.5).toInt
    this.currentMana -= manaReduced
    if ( this.currentMana >= 0 ){
      opponent.takeDamage(this.AP, "AP")
    }else{
      this.currentMana += manaReduced
      println(this.nickname + " does not have enough mana for this attack, mana needed = " + manaReduced)
    }
  }

  def takeDamage(incomingDamage: Int, dmgType: String): Unit = {
    var damageMitigated: Int = 0
    if (dmgType == "AD"){
      damageMitigated = this.Armor
    }else if (dmgType == "AP"){
      damageMitigated = this.MR
    }
    val damageTaken: Int  = incomingDamage - damageMitigated
    this.currentHP -= damageTaken

    if (currentHP < 0){
      currentHP = 0
      isDead = true
    }
    println(this.characterName + " took " + damageTaken + " " + dmgType + " damage, now has " + this.currentHP + " (" + damageMitigated + " mitigated)")
  }
}

