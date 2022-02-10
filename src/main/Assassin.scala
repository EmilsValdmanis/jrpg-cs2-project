class Assassin(nickname: String) extends Character(nickname){
  maxHP = 400
  maxMana = 0
  AD = 75
  Armor = 15
  AP = 0
  MR = 15

  currentHP = this.maxHP
  currentMana = this.maxMana

  override def levelUp(overMax: Int = 0): Unit ={
    this.currentXp = 0 + overMax
    this.level += 1

    this.maxHP += this.level * 40
    this.currentHP = this.maxHP

    this.AD += 15
    this.Armor += 5
    this.MR += 5
  }

  override def attackAP(opponent: Character): Unit = {

  }

  def pierce(opponent: Character): Unit = {
    opponent.currentHP -= this.AD
    this.Armor -= 2
    if (this.Armor < 0){
      this.Armor = 0
    }
    if (opponent.currentHP <= 0){
      opponent.isDead = true
    }
  }

  def deathMark(opponent: Character): Unit = {
    if (this.level >= 10){
      this.attackAD(opponent)
      val opponentHealthPercentage: Double = opponent.currentHP.toDouble / opponent.maxHP.toDouble
      if (opponentHealthPercentage <= 0.1){
        opponent.currentHP = 0
        opponent.isDead = true
      }
      this.currentHP -= this.level * 5
      this.MR -= 3
      this.Armor -= 3
      if (this.Armor < 0){
        this.Armor = 0
      }
      if (this.MR < 0){
        this.MR = 0
      }
    }
  }

  def critHit(opponent: Character): Unit = {
    if (this.level >= 15){
      val random = scala.util.Random
      val chance: Int = random.nextInt(100)
      if (chance <= 80) {
        opponent.currentHP -= this.AD * 2
      }else {
        this.AD -= 2
        this.currentHP -= this.level * 10
      }
      if (opponent.currentHP <= 0){
        opponent.isDead = true
      }
    }
  }
  def battleOptions(): List[String] = {
    if (this.level >= 15) {
      List("pierce", "deathMark", "critHit")
    }else if (this.level >= 10) {
      List("pierce", "deathMark")
    }else{
      List("pierce")
    }
  }

  def takeAction(action: String, character: Character): Unit ={
    val options = this.battleOptions()
    for (possible <- options){
      if (action == possible){
        if (action == "pierce"){
          this.pierce(character)
        }else if (action == "deathMark"){
          this.deathMark(character)
        }else if (action == "critHit"){
          this.critHit(character)
        }
      }
    }
  }
}
