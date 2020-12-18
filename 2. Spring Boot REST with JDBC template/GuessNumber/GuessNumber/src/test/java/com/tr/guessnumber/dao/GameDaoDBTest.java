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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class GameDaoDBTest {

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
    void testAddGameGetRoundsByGameId() {
        Game game = new Game();
        game.setFinished(false);
        game.setNumber("1234");
        //add
        game = gameDao.addGame(game);
        //get by id
        Game fromDao = gameDao.getGameById(game.getGameId());
        assertEquals(game, fromDao);
    }

    @Test
    void getAllGame() {
        Game game = new Game();
        game.setFinished(false);
        game.setNumber("1234");
        game = gameDao.addGame(game);

        Game game2 = new Game();
        game2.setFinished(false);
        game2.setNumber("0123");
        game2 = gameDao.addGame(game2);

        List<Game> games = gameDao.getAllGame();
        assertEquals(2, games.size());
        //make sure games contain both game objects
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    @Test
    void updateGame() {
        Game game = new Game();
        game.setFinished(false);
        game.setNumber("1234");
        game = gameDao.addGame(game);
        //update local data
        game.setFinished(true);
        //update through dao
        gameDao.updateGame(game);
        Game formDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, formDao);
        assertTrue(formDao.isFinished());
    }

    @Test
    void deleteGame() {
        Game game = new Game();
        game.setFinished(false);
        game.setNumber("1234");
        game = gameDao.addGame(game);

        Game game2 = new Game();
        game2.setFinished(false);
        game2.setNumber("0123");
        game2 = gameDao.addGame(game2);

        gameDao.deleteGame(game2.getGameId());
        List<Game> games = gameDao.getAllGame();

        assertEquals(1, games.size());
        //make sure it delete the right one
        assertFalse(games.contains(game2));
    }
}