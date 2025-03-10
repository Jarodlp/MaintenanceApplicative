
package trivia;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
//	@Test
//	public void caracterizationTest() {
//		// runs 10.000 "random" games to see the output of old and new code matches
//		for (int seed = 1; seed < 10_000; seed++) {
//			testSeed(seed, false);
//		}
//	}
//
//	private void testSeed(int seed, boolean printExpected) {
//		String expectedOutput = extractOutput(new Random(seed), new GameOld());
//		if (printExpected) {
//			System.out.println(expectedOutput);
//		}
//		String actualOutput = extractOutput(new Random(seed), new Game());
//		assertEquals(expectedOutput, actualOutput);
//	}
//
//	@Test
//	public void oneSeed() {
//		testSeed(1, false);
//	}
//
//	private String extractOutput(Random rand, IGame aGame) {
//		PrintStream old = System.out;
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		try (PrintStream inmemory = new PrintStream(baos)) {
//			// WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
//			System.setOut(inmemory);
//
//			aGame.add("Chet");
//			aGame.add("Pat");
//			aGame.add("Sue");
//
//			boolean notAWinner = false;
//			do {
//				aGame.roll(rand.nextInt(5) + 1);
//
//				if (rand.nextInt(9) == 7) {
//					notAWinner = aGame.wrongAnswer();
//				} else {
//					notAWinner = aGame.correctAnswer();
//				}
//
//			} while (notAWinner);
//		} finally {
//			System.setOut(old);
//		}
//
//		return new String(baos.toByteArray());
//	}

	@Test
	public void testAjoutCategorie() {
		Questions.CATEGORIES.add("Video Games");
		assertEquals("[Rock, Pop, Science, Sports, Géographie, Video Games]", Questions.CATEGORIES.toString());
		Game game = new Game();
		game.add("Crazy");
		game.add("Zack");
		// Les 6 premières cases sont donc : 1-Pop, 2-Science, 3-Sports, 4-Géographie, 5-Video Games, 6-Rock
		game.roll(4);
		assertEquals("Video Games", game.currentCategory());
		Questions.CATEGORIES.remove("Video Games");
	}

	@Test
	public void testFinPartie() {
		Game game = new Game();
		game.add("Winner");
		game.add("Looser");
		game.players.get(0).purse = 5;

		game.roll(1);
		boolean notAWinner = game.correctAnswer();

		assertFalse(notAWinner);
	}

	@Test
	public void testAjoutPieceBonneReponse() {
		Game game = new Game();
		game.add("1 piece");
		game.add("0 piece");
		game.roll(1);
		game.correctAnswer();
		game.roll(1);
		game.wrongAnswer();
		assertEquals(1, game.players.get(0).purse);
		assertEquals(0, game.players.get(1).purse);
	}

	@Test
	public void testSortiePrison() {
		Game game = new Game();
		game.add("Prisonnier 1");
		game.add("Prisonnier 2");
		game.players.get(0).inPenaltyBox = true;
		game.players.get(1).inPenaltyBox = true;
		game.roll(1);
		game.roll(2);
		assertFalse(game.players.get(0).inPenaltyBox);
		assertTrue(game.players.get(1).inPenaltyBox);
	}

	@Test
	public void testCaseSuperieurA12() {
		Game game = new Game();
		game.add("Joe");
		game.add("John");
		game.players.get(0).place = 12;
		game.players.get(1).place = 12;
		game.roll(2);
		assertEquals(2, game.players.get(0).place);
		assertEquals(12, game.players.get(1).place);
	}

	@Test
	public void testJeuJouable() {
		Game game = new Game();
		for (int i = 1; i < 8; i++) {
			game.add("Player " + i);
			if (i < 2 || i > 6) {
				assertFalse(game.isPlayable());
			} else {
				assertTrue(game.isPlayable());
			}
		}
	}

	@Test
	public void testLancementJeuInjouable() {
		Game game = new Game();
		game.add("Joe");
		assertThrows(IllegalStateException.class, () -> game.roll(1));
	}

	@Test
	public void testAjoutJoueurDejaExistant() {
		Game game = new Game();
		game.add("Joe");
		assertThrows(IllegalArgumentException.class, () -> game.add("Joe"));
	}

	@Test
	public void testAjoutJoueurApresDebutPartie() {
		Game game = new Game();
		game.add("Joe");
		game.add("John");
		game.roll(1);
		assertThrows(IllegalStateException.class, () -> game.add("Retardataire"));
	}

}
