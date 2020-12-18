package com.tr.guessnumber.dao;

import com.tr.guessnumber.model.Round;

import java.util.List;

public interface RoundDao {
    Round addRound(Round round);
    List<Round> getAllRound();
    Round getRoundById(int id);
    void updateRound(Round round);
    void deleteRound(int id);
    List<Round> getRoundsByGameId(int gameID);
}
