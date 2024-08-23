package com.premierleague.premier_zone.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pleague/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService=playerService;
    }

    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation) {

        if (team != null && position != null) {
            return playerService.getPlayersByTeamAndPosition(team, position);
        } else if (team != null) {
            return playerService.getPlayersFromTeam(team);
        } else if (name != null) {
            return playerService.getPlayersByName(name);
        } else if (position != null) {
            return playerService.getPlayersByPos(position);
        } else if (nation != null) {
            return playerService.getPlayersByNation(nation);
        } else {
            return playerService.getPlayers();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        return new ResponseEntity<>(playerService.addPlayer(player), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player updatedPlayer) {
        Player resultPlayer = playerService.updatePlayer(updatedPlayer);
        if (resultPlayer != null) {
            return new ResponseEntity<>(resultPlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName) {
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
    }
}
