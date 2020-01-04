package fr.cookiedev.codebreaker.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CodeTest {

	@Test
	void givenACode_whenCreateSameCodeAnotherOrder_thenCodesAreEquals() {
		// GIVEN
		final Code baseCode = new Code(Tile.B1, Tile.W2, Tile.B3, Tile.W4, Tile.G5_1);

		// WHEN
		final Code equivCode = new Code(Tile.W2, Tile.B3, Tile.W4, Tile.B1, Tile.G5_1);

		// THEN
		assertThat(equivCode).isEqualTo(baseCode);
	}

	@Test
	void whenCreateACodeWithANullTile_ThenThrowNPE() {
		// WHEN
		assertThrows(NullPointerException.class, () -> new Code(Tile.B1, Tile.W2, null, Tile.W4, Tile.G5_1));
	}

	@Test
	void whenCreateACodeWithTwoSameTiles_ThenThrowIllegalArgsException() {
		// WHEN
		assertThrows(IllegalArgumentException.class, () -> new Code(Tile.B1, Tile.B1, Tile.W3, Tile.W4, Tile.G5_2));
	}

}
