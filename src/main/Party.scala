class Party(var party: List[Character]){
  def defeatEnemyParty(defeatedParty: Party): Unit ={
    var totalXP = 0
    var aliveCount = 0
    for (playerLost <- defeatedParty.party){
      totalXP += playerLost.level * 20
    }
    for (playerWon <- this.party){
      if (!playerWon.isDead){
        aliveCount += 1
      }
    }
    val xpPerWinner: Int = totalXP / aliveCount
    for (playerWon <- this.party){
      if (!playerWon.isDead){
        playerWon.gainXP(xpPerWinner)
      }
    }
  }
}

