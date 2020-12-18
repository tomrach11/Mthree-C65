package com.tr.guessnumber.controller;

import com.tr.guessnumber.model.Game;
import com.tr.guessnumber.model.Round;
import com.tr.guessnumber.service.InValidGuessException;
import com.tr.guessnumber.service.InvalidGameIdException;
import com.tr.guessnumber.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class GuessNumberController {
    @Autowired
    ServiceLayer service;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame() {
        return service.createGame();
    }

    @PostMapping("/guess")
    public Round createRound(@RequestBody Round round) throws InValidGuessException, InvalidGameIdException {
        return service.createRound(round);
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }

    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable int id) {
        return service.getGameByGameId(id);
    }

    @GetMapping("/rounds/{id}")
    public List<Round> getRoundsByGameId(@PathVariable int id) throws InvalidGameIdException {
        return service.getRoundByGameId(id);
    }


}
