package com.tr.guessnumber.model;

import java.time.LocalDateTime;

public class Round {
    private int RoundId;
    private LocalDateTime time;
    private String guess;
    private int gameId;

    public int getRoundId() {
        return RoundId;
    }

    public void setRoundId(int roundId) {
        RoundId = roundId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
