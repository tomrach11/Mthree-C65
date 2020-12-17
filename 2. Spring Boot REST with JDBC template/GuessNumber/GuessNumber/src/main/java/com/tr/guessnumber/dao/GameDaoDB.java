package com.tr.guessnumber.dao;

import com.tr.guessnumber.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameDaoDB implements GameDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Game addGame(Game game) {
        //add to database
        final String INSERT_GAME = "INSERT INTO Game (Number, Finished) VALUE (?, ?);";
        jdbc.update(INSERT_GAME, game.getNumber(), game.isFinished());

        //get the ID from database and save to game object
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(id);

        return game;
    }

    @Override
    public List<Game> getAllGame() {
        final String SELECT_ALL_GAME = "SELECT * FROM Game;";
        return jdbc.query(SELECT_ALL_GAME, new GameMapper());
    }

    @Override
    public Game getGameById(int id) {
        //use try catch to avoid getting exception but return null instead
        try {
            final String SELECT_BY_GAME_ID = "SELECT * FROM Game WHERE GameId = ?;";
            return jdbc.queryForObject(SELECT_BY_GAME_ID, new GameMapper(), id);
        } catch (DataAccessException e) {
            //if id is not found in database return null
            return null;
        }
    }

    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE Game SET" +
                "Number = ?," +
                "Finished = ?" +
                "WHERE GameId = ?;";
        jdbc.update(UPDATE_GAME, game.getNumber(), game.isFinished(), game.getGameId());
    }

    @Override
    public void deleteGame(int id) {
        //delete all rounds that associate with this GameId
        final String DELETE_ROUND = "DELETE FROM Round WHERE GAMEId = ?";
        jdbc.update(DELETE_ROUND, id);
        //delete game from Game table
        final String DELETE_GAME = "DELETE FROM Game WHERE GameId = ?;";
        jdbc.update(DELETE_GAME, id);
    }
}
