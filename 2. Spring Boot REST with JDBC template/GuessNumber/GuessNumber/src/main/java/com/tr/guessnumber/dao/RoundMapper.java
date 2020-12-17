package com.tr.guessnumber.dao;

import com.tr.guessnumber.model.Game;
import com.tr.guessnumber.model.Round;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RoundMapper implements RowMapper<Round> {
    @Override
    public Round mapRow(ResultSet rs, int i) throws SQLException {
        Round round = new Round();
        round.setRoundId(rs.getInt("RoundId"));
        round.setGuess(rs.getString("Guess"));
        LocalDateTime time = rs.getTimestamp("Time").toLocalDateTime();
        round.setTime(time);
        round.setGameId(rs.getInt("GameId"));

        return round;
    }
}
