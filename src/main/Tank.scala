class Tank(nickname: String) extends Character(nickname){
  maxHP = 1000
  maxMana = 100
  AD = 35
  Armor = 30
  AP = 35
  MR = 30

  currentHP = this.maxHP
  currentMana = this.maxMana

  override def levelUp(overMax: Int = 0): Unit ={
    this.currentXp = 0 + overMax
    this.level += 1

    this.maxHP += (this.level * 50) * 2
    this.currentHP = this.maxHP

    this.maxMana += (this.level * 10) / 2
    this.currentMana = this.maxMana

    this.AD += 5
    this.AP += 5
    this.Armor += 10
    this.MR += 10
  }

  def smash(opponent: Character): Unit= {
    this.attackAD(opponent)
    opponent.currentHP += 10
    opponent.Armor -= 3
    opponent.MR -= 3
    this.currentHP -= this.level * 10
    this.Armor -= 4
    this.MR -= 4
  }

  def feast(): Unit= {
    if (this.level >=10){
      this.currentHP += this.level * 30
      this.Armor += 5
      this.MR += 5
      this.AP -= 5
    }
  }

  def deathSentence(opponent: Character): Unit= {
    if (this.level >= 15){
      opponent.currentHP -= opponent.MR
      this.attackAP(opponent)
      this.attackAP(opponent)
      this.currentHP -= 40
      this.currentMana -= 5
    }
  }

  def battleOptions(): List[String] = {
    if (this.level >= 15) {
      List("smash", "feast", "deathSentence")
    }else if (this.level >= 10) {
      List("smash", "feast")
    }else{
      List("smash")
    }
  }

  def takeAction(action: String, character: Character): Unit ={
    val options = this.battleOptions()
    for (possible <- options){
      if (action == possible){
        if (action == "smash"){
          this.smash(character)
        }else if (action == "feast"){
          this.feast()
        }else if (action == "deathSentence"){
          this.deathSentence(character)
        }
      }
    }
  }
}
