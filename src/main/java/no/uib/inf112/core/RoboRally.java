package no.uib.inf112.core;

import no.uib.inf112.core.map.MapHandler;
import no.uib.inf112.core.map.TiledMapHandler;
import no.uib.inf112.core.map.cards.Deck;
import no.uib.inf112.core.map.cards.MovementDeck;
import no.uib.inf112.core.player.IPlayer;
import no.uib.inf112.core.player.PlayerHandler;
import no.uib.inf112.core.round.DefaultGameRule;
import no.uib.inf112.core.testUtils.HeadlessMapHandler;
import org.jetbrains.annotations.NotNull;

public class RoboRally {
    private MapHandler map;

    private PlayerHandler playerHandler;
    private Deck deck;

    public RoboRally(String mapPath, int playerCount) {
        if (GameGraphics.HEADLESS) {
            map = new HeadlessMapHandler(mapPath);
        } else {
            map = new TiledMapHandler(mapPath);
        }
        deck = new MovementDeck();
        playerHandler = new PlayerHandler(playerCount, map);
        for (IPlayer player : playerHandler.getPlayers()) {
            map.addEntity(player);
        }

    }

    public void round() {
        DefaultGameRule.generate().startRound();
    }

    /**
     * Get cards which is currently in the game
     *
     * @return cards
     */
    public Deck getDeck() {
        return deck;
    }


    /**
     * @return The current map in play
     */
    @NotNull
    public MapHandler getCurrentMap() {
        return map;
    }


    @NotNull
    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }
}
