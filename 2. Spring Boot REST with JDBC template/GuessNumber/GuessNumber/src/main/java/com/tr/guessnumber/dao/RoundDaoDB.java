package com.tr.guessnumber.dao;

import com.tr.guessnumber.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class RoundDaoDB implements RoundDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Round addRound(Round round) {
        //insert round to database
        final String INSERT_ROUND = "INSERT INTO Round (GameId, Guess, Time) VALUES (?,?,?);";
        jdbc.update(INSERT_ROUND, round.getGameId(), round.getGuess(), round.getTime());

        //get the id and set to round object
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(id);

        return round;
    }

    @Override
    public List<Round> getAllRound() {
        final String SELECT_ALL_ROUNDS = "SELECT * FROM Round;";
        return jdbc.query(SELECT_ALL_ROUNDS, new RoundMapper());
    }

    @Override
    public Round getRoundById(int id) {
        //use try catch to avoid exception if cannot find the id in database
        try {
            //get round from database
            final String SELECT_ROUND_BY_ID = "SELECT * FROM Round WHERE RoundId = ?;";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(),id);
        } catch (DataAccessException e) {
            //if cannot find the id in database return null
            return null;
        }
    }

    @Override
    public void updateRound(Round round) {
        final String UPDATE_ROUND = "UPDATE Round SET "
                + "GameId = ?,"
                + "Guess = ?,"
                + "Time = ?"
                + "WHERE RoundId = ?";
        jdbc.update(UPDATE_ROUND, round.getGameId(), round.getGuess(), round.getTime(), round.getRoundId());
    }

    @Override
    public void deleteRound(int id) {
        final String DELETE_ROUND = "DELETE FROM Round WHERE RoundId = ?;";
        jdbc.update(DELETE_ROUND, id);
    }
}
