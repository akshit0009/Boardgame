package com.javaproject.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javaproject.beans.BoardGame;
import com.javaproject.database.DatabaseAccess;

@RestController
@RequestMapping("/boardgames")
public class BoardGameController {

    private static final Logger logger = LoggerFactory.getLogger(BoardGameController.class);
    private final DatabaseAccess da;

    public BoardGameController(DatabaseAccess da) {
        this.da = da;
    }

    @GetMapping
    public List<BoardGame> getBoardGames() {
        logger.info("Retrieving all board games.");
        return da.getBoardGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoardGame(@PathVariable Long id) {
        logger.info("Retrieving board game with ID: {}", id);
        BoardGame boardGame = da.getBoardGame(id);
        if (boardGame != null) {
            return ResponseEntity.ok(boardGame);
        } else {
            logger.warn("Board game with ID {} not found.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("No such record"));
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> postBoardGame(@RequestBody BoardGame boardGame) {
        logger.info("Adding new board game: {}", boardGame.getName());
        try {
            Long id = da.addBoardGame(boardGame);
            boardGame.setId(id);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).body(boardGame);
        } catch (Exception e) {
            logger.error("Error adding board game: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("Name already exists."));
        }
    }
}
