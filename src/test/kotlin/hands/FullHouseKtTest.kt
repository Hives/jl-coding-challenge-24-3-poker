package hands

import Card
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsOnly
import assertk.assertions.hasSize
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class FullHouseKtTest {
    @Test
    fun `returns null if no full house exists`() {
        val hand = "2D 3D 4D 5D 7S 8S 9S".toCards().getFullHouse()
        assertThat(hand).isNull()
    }

    @Test
    fun `returns a full house if one exists`() {
        val hand = "2D 2H 2S 3D 3S JD QD".toCards().getFullHouse()!!
        assertThat(hand.pair).containsOnly(
            Card.fromString("3D"),
            Card.fromString("3S")
        )
        assertThat(hand.three).containsOnly(
            Card.fromString("2D"),
            Card.fromString("2H"),
            Card.fromString("2S")
        )
    }

    @Test
    fun `returns the best possible full house if there are two three-of-a-kinds`() {
        val hand = "2D 2H 2S 3D 3H 3S QD".toCards().getFullHouse()!!

        assertThat(hand.pair).hasSize(2)

        // TODO this is weird
        hand.pair.forEach {
            assertThat(
                listOf(
                    Card.fromString("2D"),
                    Card.fromString("2H"),
                    Card.fromString("2S")
                )
            ).contains(it)
        }

        assertThat(hand.three).containsOnly(
            Card.fromString("3D"),
            Card.fromString("3H"),
            Card.fromString("3S")
        )
    }

    @Test
    fun `returns the best possible full house if there are two pairs and one three-of-a-kind`() {
        val hand = "2D 2H 3D 3H 4D 4H 4S".toCards().getFullHouse()!!

        assertThat(hand.pair).containsOnly(
            Card.fromString("3D"),
            Card.fromString("3H")
        )

        assertThat(hand.three).containsOnly(
            Card.fromString("4D"),
            Card.fromString("4H"),
            Card.fromString("4S")
        )
    }

}