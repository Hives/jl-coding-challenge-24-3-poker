data class Card(val face: Face, val suit: Suit) {
    val value = face.value

    companion object {
        fun from(str: String): Card =
            Card(
                Face.fromString(str.dropLast(1)),
                Suit.fromChar(str.last())
            )
    }
}

sealed class Suit {
    companion object {
        fun fromChar(char: Char) = when (char) {
            'S' -> SPADES
            'D' -> DIAMONDS
            'H' -> HEARTS
            'C' -> CLUBS
            else -> throw Exception("Could not create suit from '${char}'")
        }
    }
}

object HEARTS : Suit()
object DIAMONDS : Suit()
object SPADES : Suit()
object CLUBS : Suit()

sealed class Face {
    abstract val value: Int

    companion object {
        fun fromString(str: String) = when(str) {
            "J" -> JACK
            "Q" -> QUEEN
            "K" -> KING
            "A" -> ACE
            else -> Spots(str.toInt())
        }
    }
}

data class Spots(override val value: Int) : Face()

sealed class Picture(override val value: Int) : Face()
object JACK : Picture(11)
object QUEEN : Picture(12)
object KING : Picture(13)
object ACE : Picture(14)
object LOW_ACE : Picture(1)
