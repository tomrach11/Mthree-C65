package com.tr.guessnumber.service;

import com.tr.guessnumber.model.Game;
import com.tr.guessnumber.model.Round;

import java.util.List;

public interface ServiceLayer {
    Game createGame();
    Round createRound(Round round) throws InValidGuessException, InvalidGameIdException;
    List<Game> getAllGames();
    Game getGameByGameId(int gameId);
    List<Round> getRoundByGameId(int gameId) throws InvalidGameIdException;
}
