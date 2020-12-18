package com.tr.guessnumber.model;

import java.time.LocalDateTime;

public class Round {
    private int RoundId;
    private LocalDateTime time;
    private String guess;
    private int gameId;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Round)) return false;

        Round round = (Round) o;

        if (getRoundId() != round.getRoundId()) return false;
        if (getGameId() != round.getGameId()) return false;
        if (!getTime().equals(round.getTime())) return false;
        if (!getGuess().equals(round.getGuess())) return false;
        return getResult().equals(round.getResult());
    }

    @Override
    public int hashCode() {
        int result1 = getRoundId();
        result1 = 31 * result1 + getTime().hashCode();
        result1 = 31 * result1 + getGuess().hashCode();
        result1 = 31 * result1 + getGameId();
        result1 = 31 * result1 + getResult().hashCode();
        return result1;
    }
}
