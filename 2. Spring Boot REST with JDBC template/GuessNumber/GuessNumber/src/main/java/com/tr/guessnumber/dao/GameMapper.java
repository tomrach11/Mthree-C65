package com.tr.guessnumber.dao;

import com.tr.guessnumber.model.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<Game> {
    @Override
    public Game mapRow(ResultSet rs, int i) throws SQLException {
        Game game = new Game();
        game.setGameId(rs.getInt("GameId"));
        game.setNumber(rs.getString("Number"));
        game.setFinished(rs.getBoolean("Finished"));
        return game;
    }
}
