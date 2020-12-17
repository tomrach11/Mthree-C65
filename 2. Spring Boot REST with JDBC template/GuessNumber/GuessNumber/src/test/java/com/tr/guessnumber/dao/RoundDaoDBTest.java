package com.tr.guessnumber.dao;

import com.tr.guessnumber.TestApplicationConfiguration;
import com.tr.guessnumber.model.Game;
import com.tr.guessnumber.model.Round;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class RoundDaoDBTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    @BeforeEach
    void setUp() {
        List<Game> games = gameDao.getAllGame();
        for(Game game : games) {
            gameDao.deleteGame(game.getGameId());
        }

        List<Round> rounds = roundDao.getAllRound();
        for(Round round : rounds) {
            roundDao.deleteRound(round.getRoundId());
        }

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAddRoundAndGetById() {
        Game game = new Game();
        game.setFinished(true);
        game.setNumber("1234");
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("0123");
        round.setTime(LocalDateTime.now());
        round = roundDao.addRound(round);

       Round fromDB = roundDao.getRoundById(round.getRoundId());

        assertEquals(round, fromDB);
    }

    @Test
    void getAllRound() {
        Round round1 = new Round();
        round1.setGameId(1);
        round1.setTime(LocalDateTime.now());
        round1.setGuess("2546");
        roundDao.addRound(round1);

        Round round2 = new Round();
        round2.setGameId(1);
        round2.setTime(LocalDateTime.now());
        round2.setGuess("1234");
        roundDao.addRound(round1);

        List<Round> rounds = roundDao.getAllRound();
        assertEquals(2, rounds.size());
    }

    @Test
    void getRoundById() {
    }

    @Test
    void updateRound() {
    }

    @Test
    void deleteRound() {
    }

    private void addSampleData() {

    }

}