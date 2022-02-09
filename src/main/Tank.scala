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
}
