
package trivia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.Test;


public class GameTest {
	@Test
	public void caracterizationTest() {
		// runs 10.000 "random" games to see the output of old and new code matches
		for (int seed = 1; seed < 10_000; seed++) {
			testSeed(seed, false);
		}
	}

	private void testSeed(int seed, boolean printExpected) {
		String expectedOutput = extractOutput(new Random(seed), new GameOld());
		if (printExpected) {
			System.out.println(expectedOutput);
		}
		String actualOutput = extractOutput(new Random(seed), new Game());
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void oneSeed() {
		testSeed(1, true);
	}

	private String extractOutput(Random rand, IGame aGame) {
		PrintStream old = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (PrintStream inmemory = new PrintStream(baos)) {
			// WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
			System.setOut(inmemory);

			aGame.add("Chet");
			aGame.add("Pat");
			aGame.add("Sue");

			boolean notAWinner = false;
			do {
				aGame.roll(rand.nextInt(5) + 1);

				if (rand.nextInt(9) == 7) {
					notAWinner = aGame.wrongAnswer();
				} else {
					notAWinner = aGame.correctAnswer();
				}

			} while (notAWinner);
		} finally {
			System.setOut(old);
		}

		return new String(baos.toByteArray());
	}

	@Test
	public void testEnAjoutantUneCategorie() {
		// Je ne teste pas la différence entre Game et GameOld car GameOld ne prend pas en compte la classe Questions
		// On teste juste si la 4ème case sera une case "Video Games", pour ça on prend le seed 1 où
		// Pat tombe sur la case 4
		Questions.CATEGORIES.add("Video Games");
		assertEquals("[Rock, Pop, Science, Sports, Video Games]", Questions.CATEGORIES.toString());
		Game game = new Game();
		String output = extractOutput(new Random(1), game);
		Questions.CATEGORIES.remove("Video Games");
		assertTrue(output.contains("new location is 4\r\nThe category is Video Games"));
	}
}
