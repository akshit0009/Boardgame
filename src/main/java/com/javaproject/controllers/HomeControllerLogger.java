package com.javaproject.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    DatabaseAccess da;

    // Other methods...

    @GetMapping("/")
    public String goHome(Model model) {
        logger.info("Accessing home page.");
        List<BoardGame> boardgames = da.getBoardGames();
        model.addAttribute("boardgames", boardgames);
        return "index";
    }

    @GetMapping("/{id}")
    public String getBoardgameDetail(@PathVariable Long id, Model model) {
        logger.info("Fetching details for board game with ID: {}", id);
        model.addAttribute("boardgame", da.getBoardGame(id));
        return "boardgame";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String userName, @RequestParam String password,
                          @RequestParam String[] authorities, Model model, RedirectAttributes redirectAttrs) {
        logger.info("Adding new user: {}", userName);
        // Existing logic...
    }

    // Other methods...
}
