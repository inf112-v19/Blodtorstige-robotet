package no.uib.inf112.desktop;

import no.uib.inf112.core.GameGraphics;
import no.uib.inf112.core.RoboRally;
import no.uib.inf112.core.map.TileType;
import no.uib.inf112.core.player.Movement;
import no.uib.inf112.core.player.Player;
import no.uib.inf112.core.player.Robot;
import no.uib.inf112.core.util.Vector2Int;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ExampleTest extends TestGraphics{
    private RoboRally roboRally;

    @Before
    public void setUp() {
        Main.HEADLESS = true;
        roboRally = new RoboRally();
        GameGraphics.SetRoboRally(roboRally);
    }

    @Test
    public void MoveShouldMovePlayer() {
        Robot robot = roboRally.getPlayerHandler().mainPlayer().getRobot();
        Vector2Int pos = new Vector2Int(robot.getX(), robot.getY());

        robot.move(Movement.MOVE_1);
        assertNotEquals(pos, new Vector2Int(robot.getX(), robot.getY()));
    }

    @Test
    public void MapShouldGetLoaded() {
        Robot robot = roboRally.getPlayerHandler().mainPlayer().getRobot();
        Vector2Int pos = new Vector2Int(robot.getX(), robot.getY());
        assertEquals(TileType.DEFAULT_TILE,roboRally.getCurrentMap().getBoardLayerTile(pos.x, pos.y));
    }
}
