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
        //delete all data every time before start each test
        //delete all games
        List<Game> games = gameDao.getAllGame();
        for(Game game : games) {
            gameDao.deleteGame(game.getGameId());
        }
        //delete all rounds
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
        round.setResult("e1:p1");
        round.setTime(LocalDateTime.now());
        //add to database
        round = roundDao.addRound(round);
        //read from database
        Round fromDB = roundDao.getRoundById(round.getRoundId());

        assertEquals(round, fromDB);
    }

    @Test
    void testGetAllRound() {
        Game game = new Game();
        game.setFinished(true);
        game.setNumber("1234");
        game = gameDao.addGame(game);

        Round round1 = new Round();
        round1.setGameId(game.getGameId());
        round1.setTime(LocalDateTime.now());
        round1.setGuess("2546");
        round1.setResult("e1:p1");
        round1 = roundDao.addRound(round1);

        Round round2 = new Round();
        round2.setGameId(game.getGameId());
        round2.setTime(LocalDateTime.now());
        round2.setGuess("1234");
        round2.setResult("e1:p1");
        round2 = roundDao.addRound(round1);
        //get all rounds
        List<Round> rounds = roundDao.getAllRound();
        //check rounds size
        assertEquals(2, rounds.size());
        //check if rounds contain 2 round objects above
        assertTrue(rounds.contains(round1));
        assertTrue(rounds.contains(round2));
    }

    @Test
    void testUpdateRound() {
        Game game = new Game();
        game.setFinished(true);
        game.setNumber("1234");
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setTime(LocalDateTime.now());
        round.setGuess("2546");
        round.setResult("e1:p1");
        round = roundDao.addRound(round);
        //change local
        round.setGuess("1234");
        //change database
        roundDao.updateRound(round);

        Round fromDB = roundDao.getRoundById(round.getRoundId());
        assertEquals(round, fromDB);
    }

    @Test
    void testDeleteRound() {
        Game game = new Game();
        game.setFinished(true);
        game.setNumber("1234");
        game = gameDao.addGame(game);

        Round round1 = new Round();
        round1.setGameId(game.getGameId());
        round1.setTime(LocalDateTime.now());
        round1.setGuess("2546");
        round1.setResult("e1:p1");
        round1 = roundDao.addRound(round1);

        Round round2 = new Round();
        round2.setGameId(game.getGameId());
        round2.setTime(LocalDateTime.now());
        round2.setGuess("1234");
        round2.setResult("e1:p1");
        round2 = roundDao.addRound(round1);

        roundDao.deleteRound(round2.getRoundId());
        List<Round> rounds = roundDao.getAllRound();

        assertEquals(1, rounds.size());
        //make sure it delete the right one
        assertFalse(rounds.contains(round2));
    }

    @Test
    void testGetRoundsByGameId() {
        Game game = new Game();
        game.setFinished(true);
        game.setNumber("1234");
        game = gameDao.addGame(game);

        Round round1 = new Round();
        round1.setGameId(game.getGameId());
        round1.setTime(LocalDateTime.now());
        round1.setGuess("2546");
        round1.setResult("e1:p1");
        round1 = roundDao.addRound(round1);

        Round round2 = new Round();
        round2.setGameId(game.getGameId());
        round2.setTime(LocalDateTime.now());
        round2.setGuess("1234");
        round2.setResult("e1:p1");
        round2 = roundDao.addRound(round2);

        Game game2 = new Game();
        game2.setFinished(true);
        game2.setNumber("0123");
        game2 = gameDao.addGame(game2);

        Round round3 = new Round();
        round3.setGameId(game2.getGameId());
        round3.setTime(LocalDateTime.now());
        round3.setGuess("1234");
        round3.setResult("e1:p1");
        round3 = roundDao.addRound(round3);

        //create list of rounds for each game
        List<Round> roundsOfGame1 = roundDao.getRoundsByGameId(game.getGameId());
        List<Round> roundsOfGame2 = roundDao.getRoundsByGameId(game2.getGameId());

        assertEquals(2, roundsOfGame1.size());
        assertEquals(1, roundsOfGame2.size());
    }


}