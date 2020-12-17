package com.tr.guessnumber.model;

import java.util.ArrayList;

public class Game {
    private int gameId;
    private String number;
    private boolean finished;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        if (getGameId() != game.getGameId()) return false;
        if (isFinished() != game.isFinished()) return false;
        return getNumber().equals(game.getNumber());
    }

    @Override
    public int hashCode() {
        int result = getGameId();
        result = 31 * result + getNumber().hashCode();
        result = 31 * result + (isFinished() ? 1 : 0);
        return result;
    }
}
