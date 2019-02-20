package no.uib.inf112.core.player;

import java.util.*;

public class PlayerHandler implements IPlayerHandler {

    private Deck deck;
    private int playerCount;
    private ArrayList<Player> players;

    /**
     * @param playerCount
     * @throws IllegalArgumentException if playercount is invalid
     */
    public PlayerHandler(int playerCount) {
        if (playerCount < 2) {
            throw new IllegalArgumentException("Not enough players");
        } else if (playerCount > 8) {
            throw new IllegalArgumentException("Too many players");
        }
        this.playerCount = playerCount;
        players = new ArrayList<>(playerCount);

        deck = new ProgramDeck();
    }

    @Override
    public void generatePlayers(boolean headless) {
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(5 + i, 2, Direction.NORTH, headless));
        }

        Stack<Integer> docks = new Stack<>();
        for (int i = 1; i <= playerCount; i++) {
            docks.push(i);
        }
        Collections.shuffle(docks);

        for (Player player : players) {
            player.setDock(docks.pop());
        }
    }

    @Override
    public void doTurn() {
        //TODO Issue #44 check if dead
        //TODO Issue #44 check if player is out side of map
        deck.shuffle();
        for (Player player : players) {
            if (player.isPoweredDown()) {
                //TODO Issue #24 check if is powered down (then heal)
                continue;
            } else {
                player.drawCards();
            }
        }

    }

    @Override
    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public int getPlayerCount() {
        return playerCount;
    }

    @Override
    public Deck getDeck() {
        return deck;
    }

    /**
     * Temporary mainplayer
     *
     * @return player
     */
    public Player mainPlayer() {
        return players.get(0);
    }
}