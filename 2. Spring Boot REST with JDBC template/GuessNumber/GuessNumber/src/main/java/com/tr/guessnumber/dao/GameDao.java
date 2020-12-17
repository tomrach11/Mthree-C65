package com.tr.guessnumber.dao;

import com.tr.guessnumber.model.Game;

import java.util.List;

public interface GameDao {
    Game addGame(Game game);
    List<Game> getAllGame();
    Game getGameById(int id);
    void updateGame(Game game);
    void deleteGame(int id);
}
