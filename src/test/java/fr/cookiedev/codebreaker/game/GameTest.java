package fr.cookiedev.codebreaker.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.cookiedev.codebreaker.core.Code;
import fr.cookiedev.codebreaker.core.Tile;

@ExtendWith(MockitoExtension.class)
class GameTest {

	private Game game;

	@Mock
	private Player player1;

	@Mock
	private Player player2;

	@Test
	void whenTwoRegistredPlayers_thenStartedEventWithCorrectCodeIsEmitted() {
		// GIVEN
		final ArgumentCaptor<Code> codeCaptor = ArgumentCaptor.forClass(Code.class);

		// WHEN
		game = new Game(player1, player2);

		// THEN
		verify(player1).provideCode(eq(game), codeCaptor.capture());
		final Code player1Code = Objects.requireNonNull(codeCaptor.getValue());
		verify(player2).provideCode(eq(game), codeCaptor.capture());
		final Code player2Code = Objects.requireNonNull(codeCaptor.getValue());
		final Set<Tile> tiles = EnumSet.of(player1Code.a(), player1Code.b(), player1Code.c(), player1Code.d(),
				player1Code.e());
		assertThat(tiles.removeAll(Arrays.asList(player2Code.getTiles()))).isFalse();
	}

}
