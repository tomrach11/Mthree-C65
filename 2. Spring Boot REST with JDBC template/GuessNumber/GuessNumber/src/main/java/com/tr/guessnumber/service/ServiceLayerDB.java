package com.tr.guessnumber.service;

import com.tr.guessnumber.dao.GameDao;
import com.tr.guessnumber.dao.RoundDao;
import com.tr.guessnumber.model.Game;
import com.tr.guessnumber.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Repository
public class ServiceLayerDB implements ServiceLayer {
    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    @Override
    public Game createGame() {
        Game game = new Game();
        game.setNumber(generateNumber());
        game.setFinished(false);
        return gameDao.addGame(game);
    }

    @Override
    public Round createRound(Round round) {
        //get game to check number with
        Game game = gameDao.getGameById(round.getGameId());
        //get the result and save to round object
        String result = getResult(round, game);
        round.setResult(result);
        round.setTime(LocalDateTime.now());

        //change game status to finish if guess is correct
        checkAndUpdateGame(round, game);
        return roundDao.addRound(round);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGame();
        for(Game game : games) {
            hideNumber(game);
        }
        return games;
    }

    @Override
    public Game getGameByGameId(int gameId) {
        Game game = gameDao.getGameById(gameId);
        hideNumber(game);
        return game;
    }

    @Override
    public List<Round> getRoundByGameId(int gameId) {
        return roundDao.getRoundsByGameId(gameId);
    }

    private void checkAndUpdateGame(Round round, Game game) {
        if(round.getGuess().equals(game.getNumber())) {
            game.setFinished(true);
            gameDao.updateGame(game);
        }
    }

    private Game hideNumber(Game game) {
        if (!game.isFinished()) {
            game.setNumber("In-progress: Number is Hidden");
        }
        return game;
    }

    private String generateNumber() {
        String number = "";
        while(number.length() < 4) {
            Random random = new Random();
            int num = random.nextInt(10);
            String str = Integer.toString(num);
            if(!number.contains(str)) {
                number += str;
            }
        }
        return number;
    }

    private String getResult(Round round, Game game) {
        String result;
        String guess = round.getGuess();
        String number = game.getNumber();

        int partial = 0;
        int exact = 0;

        for(int i = 0; i < guess.length(); i++) {
            String c = guess.substring(i, i+1);
            if (number.contains(c) && (guess.indexOf(c) == number.indexOf(c))) {
                    exact++;
            } else if (number.contains(c)) {
                partial++;
            }
        }
        result = "e" + exact + ":" + "p" + partial;
        return result;
    }
}
