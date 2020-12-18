package com.tr.guessnumber.service;

import com.tr.guessnumber.model.Game;
import com.tr.guessnumber.model.Round;

import java.util.List;

public interface ServiceLayer {
    //crate service base on 5 questions from
    Game createGame();
    Round createRound(Round round);
    List<Game> getAllGames();
    Game getGameByGameId(int gameId);
    List<Round> getRoundByGameId(int gameId);
}
