class Character(var nickname: String, var maxHP: Int = 500, var maxMana: Int = 100, var AD: Int = 50,var Armor: Int = 10,var AP: Int = 50,var MR: Int = 10,var xpGained: Int = 0,var level: Int = 1 ) {
  var characterName: String = this.nickname
  var isDead : Boolean = false
  var overMax: Int = 0

  var currentHP: Int = this.maxHP
  var currentMana: Int = this.maxMana

  def gainXP(amount: Int): Unit ={
    this.xpGained += amount
  }
  def defeatEnemy(enemy: Character): Unit ={
    this.gainXP(enemy.level * 20)
  }

  def tryLevel(): Unit ={
    if (this.xpGained == this.level * this.level * 20 ){
      this.levelUp()
    }else if (this.xpGained > this.level * this.level * 20 ){
      do{
        overMax = this.xpGained - this.level * this.level * 20
        this.levelUp(overMax)
      }while (overMax >= this.level * this.level * 20)
    }else{
      println(this.characterName + " does not have enough XP to level up")
    }
  }


  def levelUp(overMax: Int = 0): Unit ={
    this.xpGained = 0 + overMax
    this.level += 1


    this.maxHP += this.level * 50
    this.currentHP = this.maxHP

    this.maxMana += this.level * 10
    this.currentMana = this.maxMana

    this.AD += 10
    this.AP += 10
    this.Armor += 5
    this.MR += 5

    println(this.characterName + " leveled to LVL-" + this.level )
  }

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

  override def toString: String ={
    "|" + this.nickname + " LVL-" + this.level + "| XP:" + this.xpGained + "/" + (this.level * this.level * 20) +  "| HP: " + this.currentHP + "/" + this.maxHP + "| Mana: " + this.currentMana + "/" + this.maxMana + "| AD/AP: " + this.AD + "/" + this.AP + "| Armor/MR: " + this.Armor + "/" + this.MR + "|"
  }
}
