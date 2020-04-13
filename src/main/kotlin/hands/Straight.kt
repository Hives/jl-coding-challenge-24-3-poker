package hands

import ACE
import Card
import LOW_ACE

fun List<Card>.getStraight(): Straight? = this
    .duplicateHighAcesToLowAces()
    .removeCardsOfDuplicateValue()
    .sortedByDescending { it.value }
    .let {
        it.drop(1).fold(listOf(it.first())) { acc, card ->
            when {
                card.value == acc.last().value - 1 -> acc + card
                acc.size >= 5 -> acc
                else -> listOf(card)
            }
        }
    }.let {
        if (it.size >= 5) Straight(it.first().face) else null
    }

private fun List<Card>.duplicateHighAcesToLowAces() = this
    .flatMap {
        if (it.face == ACE) {
            listOf(it, Card(LOW_ACE, it.suit))
        } else {
            listOf(it)
        }
    }

private fun List<Card>.removeCardsOfDuplicateValue() = this
    .distinctBy { it.value }