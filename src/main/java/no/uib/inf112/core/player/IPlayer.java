package no.uib.inf112.core.player;

import no.uib.inf112.core.map.cards.Card;
import no.uib.inf112.core.map.cards.Movement;
import no.uib.inf112.core.util.ComparableTuple;
import no.uib.inf112.core.util.Vector2Int;
import org.jetbrains.annotations.NotNull;

public interface IPlayer extends Comparable<IPlayer>, Entity {
    /**
     * damage the player by the given amount and handles death if health is less than or equal to 0
     *
     * @param damageAmount How much to damage the player
     * @throws IllegalArgumentException If the damage amount is not positive
     */
    @Override
    void damage(int damageAmount);

    /**
     * Kill the player, decreasing their lives and depending on Main.headless permanently remove from map if there are
     * no lives left
     */
    @Override
    void kill();

    void destroy();

    /**
     * @return If the player is dead. A player is dead if their lives are 0 or less
     */
    boolean isDestroyed();

    /**
     * @param id
     * @return the next player card in queue
     */
    ComparableTuple<Card, IPlayer> getNextCard(int id);

    /**
     * Moves the robot with the movement corresponding to the cardAction
     * <p>
     * If the robot moves outside the map, {@link AbstractPlayer#kill()} method is called
     *
     * @param cardAction movement to do with the robot
     */
    void move(Movement cardAction, int maxTime);

    default void move(Movement cardAction) {
        move(cardAction, 0);
    }

    void registerFlagVisits(int n);

    /**
     * @return lives
     */
    int getLives();

    /**
     * @return health
     */
    int getHealth();

    void setName(String name);

    String getName();

    /**
     * @return if powered down or not
     */
    boolean isPoweredDown();

    /**
     * @return if robot will power down next round
     */
    boolean willPowerDown();

    /**
     * @return amount of damage tokens
     */
    int getDamageTokens();

    /**
     * @return backup position
     */
    @Override
    Vector2Int getBackup();

    /**
     * Sets the backup for the player
     *
     * @param x The new x coordinate of the backup
     * @param y The new y coordinate of the backup
     * @throws IllegalArgumentException if {@code x} or {@code y} is outside the current map
     */
    void setBackup(int x, int y);

    /**
     * @return assigned dock
     */
    int getDock();

    /**
     * @param dock
     */
    void setDock(int dock);

    /**
     * @return Amount of flags visited
     */
    int getFlags();

    /**
     * @param flagNr The flags number
     * @return If player can get given FLAG or not
     */
    boolean canGetFlag(int flagNr);

    /**
     * Update number of flags visited by one
     */
    void registerFlagVisit();

    @Override
    int compareTo(@NotNull IPlayer o);
}
