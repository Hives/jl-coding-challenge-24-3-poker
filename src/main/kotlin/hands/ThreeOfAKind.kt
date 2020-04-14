package hands

import Card

fun List<Card>.getThreeOfAKind(): ThreeOfAKind? {
    val threes = this
        .asSequence()
        .groupBy { it.face }
        .map { it.value }
        .filter { it.size == 3 }
        .sortedByDescending {
            it.first().value
        }

    return if (threes.size == 0) {
        null
    } else {
        ThreeOfAKind(threes.first())
    }
}