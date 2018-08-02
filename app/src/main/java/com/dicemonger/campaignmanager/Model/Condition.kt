package com.dicemonger.campaignmanager.Model

data class Condition(val name: String, val description: String) {
    companion object {
        val Blinded: Condition = Condition("Blinded",
                "• A blinded creature can’t see and automatically fails any ability check that requires sight.\n" +
                        "• Attack rolls against the creature have advantage, and the creature’s Attack rolls have disadvantage.")
        val Deafened: Condition = Condition("Deafened",
                "• A deafened creature can’t hear and automatically fails any ability check that requires hearing.")
        val Fatigued: Condition = Condition("Fatigued",
                "1: Disadvantage on Ability Checks\n" +
                        "2: Speed halved\n" +
                        "3: Disadvantage on Attack rolls and Saving Throws\n" +
                        "4: Hit point maximum halved\n" +
                        "5: Speed reduced to 0\n" +
                        "6: Death")
        val Frightened: Condition = Condition("Frightened",
                "• A frightened creature has disadvantage on Ability Checks and Attack rolls while the source of its fear is within line of sight.\n" +
                        "• The creature can’t willingly move closer to the source of its fear.")
        val Grappled: Condition = Condition("Grappled",
                "• A grappled creature’s speed becomes 0, and it can’t benefit from any bonus to its speed.\n" +
                        "• The condition ends if the Grappler is incapacitated (see the condition).\n" +
                        "• The condition also ends if an effect removes the grappled creature from the reach of the Grappler or Grappling effect, such as when a creature is hurled away by the Thunderwave spell.")
        val Invisible: Condition = Condition("Invisible",
                "• An invisible creature is impossible to see without the aid of magic or a Special sense. For the purpose of Hiding, the creature is heavily obscured. The creature’s location can be detected by any noise it makes or any tracks it leaves.\n" +
                        "• Attack rolls against the creature have disadvantage, and the creature’s Attack rolls have advantage.")
        val Paralyzed: Condition = Condition("Paralyzed",
                "• A paralyzed creature is incapacitated (see the condition) and can’t move or speak.\n" +
                        "• The creature automatically fails Strength and Dexterity Saving Throws.\n" +
                        "• Attack rolls against the creature have advantage.\n" +
                        "• Any Attack that hits the creature is a critical hit if the attacker is within 5 feet of the creature.")
        val Petrified: Condition = Condition("Petrified",
                "• A petrified creature is transformed, along with any nonmagical object it is wearing or carrying, into a solid inanimate substance (usually stone). Its weight increases by a factor of ten, and it ceases aging.\n" +
                        "• The creature is incapacitated (see the condition), can’t move or speak, and is unaware of its surroundings.\n" +
                        "• Attack rolls against the creature have advantage.\n" +
                        "• The creature automatically fails Strength and Dexterity Saving Throws.\n" +
                        "• The creature has Resistance to all damage.\n" +
                        "• The creature is immune to poison and disease, although a poison or disease already in its system is suspended, not neutralized.")
        val Poisoned: Condition = Condition("Poisoned",
                "• A poisoned creature has disadvantage on Attack rolls and Ability Checks.")
        val Prone: Condition = Condition("Prone",
                "• A prone creature’s only Movement option is to crawl, unless it stands up and thereby ends the condition.\n" +
                        "• The creature has disadvantage on Attack rolls.\n" +
                        "• An Attack roll against the creature has advantage if the attacker is within 5 feet of the creature. Otherwise, the Attack roll has disadvantage.")
        val Restrained: Condition = Condition("Restrained",
                "• A restrained creature’s speed becomes 0, and it can’t benefit from any bonus to its speed.\n" +
                        "• Attack rolls against the creature have advantage, and the creature’s Attack rolls have disadvantage.\n" +
                        "• The creature has disadvantage on Dexterity Saving Throws.")
        val Stunned: Condition = Condition("Stunned",
                "• A stunned creature is incapacitated (see the condition), can’t move, and can speak only falteringly.\n" +
                        "• The creature automatically fails Strength and Dexterity Saving Throws.\n" +
                        "• Attack rolls against the creature have advantage.")
        val Unconscious: Condition = Condition("Unconscious",
                "• An unconscious creature is incapacitated (see the condition), can’t move or speak, and is unaware of its surroundings\n" +
                        "• The creature drops whatever it’s holding and falls prone.\n" +
                        "• The creature automatically fails Strength and Dexterity Saving Throws.\n" +
                        "• Attack rolls against the creature have advantage.\n" +
                        "• Any Attack that hits the creature is a critical hit if the attacker is within 5 feet of the creature.")
        val Unknown: Condition = Condition("Unknown",
                "There are no conditions with this name")

        fun getCondition(conditionName: String) : Condition {
            when(conditionName) {
                "Blinded" -> return Blinded
                "Deafened" -> return Deafened
                "Fatigued" -> return Fatigued
                "Frightened" -> return Frightened
                "Grappled" -> return Grappled
                "Invisible" -> return Invisible
                "Paralyzed" -> return Paralyzed
                "Petrified" -> return Petrified
                "Poisoned" -> return Poisoned
                "Prone" -> return Prone
                "Restrained" -> return Restrained
                "Stunned" -> return Stunned
                "Unconscious" -> return Unconscious
                else -> return Unknown
            }
        }
    }
}