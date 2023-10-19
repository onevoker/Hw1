package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GameSessionTest {
    private final Dictionary dictionary = new Dictionary();
    private final String answer = dictionary.getRandomWord();
    private final GameSession gameSession = new GameSession(answer);

    @Test
    public void testMaxAttempts() {
        assertThat(gameSession.getMaxAttempts()).isEqualTo(5);
    }

    @Test
    public void testAttempts() {
        assertThat(gameSession.getAttempts()).isEqualTo(0);
    }

    @Test
    public void testAnswer() {
        assertThat(gameSession.getAnswer()).isNotNull();
    }

    @Test
    public void testCurrentAnswer() {
        assertThat(gameSession.getCurrentAnswer()).isNotNull();
    }

    @Test
    public void testBadAttemptChangeAttempts() {
        gameSession.badAttempt();

        assertThat(gameSession.getAttempts()).isEqualTo(1);
    }

    @Test
    public void testGoodAttemptDontChangeAttempts() {
        gameSession.goodAttempt("a");

        assertThat(gameSession.getAttempts()).isEqualTo(0);
    }
}
