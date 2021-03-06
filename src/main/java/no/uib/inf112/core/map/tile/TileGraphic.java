package no.uib.inf112.core.map.tile;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import no.uib.inf112.core.GameGraphics;
import no.uib.inf112.core.map.tile.api.Tile;
import no.uib.inf112.core.util.Vector2Int;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static no.uib.inf112.core.map.tile.Attribute.*;

/**
 * All textures and how to render them
 */
@SuppressWarnings("unused")
public enum TileGraphic {


    /**
     * THE ROBOT
     * (TileType ROBOT)
     */
    ROBOT_TILE_NORTH(137, "player-robot", TileType.ROBOT, DIR_NORTH),
    ROBOT_TILE_EAST(138, "player-robot", TileType.ROBOT, DIR_EAST),
    ROBOT_TILE_SOUTH(139, "player-robot", TileType.ROBOT, DIR_WEST),
    ROBOT_TILE_WEST(140, "player-robot", TileType.ROBOT, DIR_SOUTH),

    /**
     * THE TILES THAT ROBOTS CAN FALL THROUGH
     * (TileType Void)
     */
    VOID_TILE(92, TileType.VOID),
    VOID_WITH_WARNING_ALL_AROUND(91, TileType.VOID),
    VOID_WITH_WARNING_NORTH_AND_WEST(105, TileType.VOID),
    VOID_WITH_WARNING_NORTH(106, TileType.VOID),
    VOID_WITH_WARNING_NORTH_AND_EAST(107, TileType.VOID),
    VOID_WITH_WARNING_WEST_AND_SOUTH(113, TileType.VOID),
    VOID_WITH_WARNING_EAST(108, TileType.VOID),
    VOID_WITH_WARNING_WEST(116, TileType.VOID),
    VOID_WITH_WARNING_SOUTH_AND_EAST(115, TileType.VOID),
    VOID_WITH_WARNING_NOT_WEST(109, TileType.VOID),
    VOID_WITH_WARNING_NOT_EAST(117, TileType.VOID),
    VOID_WITH_WARNING_NOT_NORTH(110, TileType.VOID),
    VOID_WITH_WARNING_NOT_SOUTH(118, TileType.VOID),


    /**
     * CONSIDERED TO BE THE TILE A PLAYER CAN STAND ON WITHOUT FALLING THROUGH
     * (TileType Default)
     */
    DEFAULT_TILE(5, TileType.DEFAULT),

    /**
     * FLAG TILES
     * (TileType Flag)
     */
    FLAG1(55, TileType.FLAG),
    FLAG2(63, TileType.FLAG),
    FLAG3(71, TileType.FLAG),
    FLAG4(79, TileType.FLAG),

    /**
     * SPAWN TILES
     * (Robots starting tiles)
     * (TileType Spawn)
     */
    SPAWN1(121, TileType.SPAWN),
    SPAWN2(122, TileType.SPAWN),
    SPAWN3(123, TileType.SPAWN),
    SPAWN4(124, TileType.SPAWN),
    SPAWN5(129, TileType.SPAWN),
    SPAWN6(130, TileType.SPAWN),
    SPAWN7(131, TileType.SPAWN),
    SPAWN8(132, TileType.SPAWN),

    /**
     * WALL TILES
     * (TileType Wall)
     */
    WALL_NORTH(31, TileType.WALL, DIR_NORTH),
    WALL_NORTH_WITH_LASER(45, TileType.WALL, DIR_NORTH, SHOOTS_LASER),
    WALL_NORTH_WITH_DOUBLE_LASER(94, TileType.WALL, DIR_NORTH, SHOOTS_LASER, HIGH_PRIORITY),

    WALL_EAST(23, TileType.WALL, DIR_EAST),
    WALL_EAST_WITH_LASER(46, TileType.WALL, DIR_EAST, SHOOTS_LASER),
    WALL_EAST_WITH_DOUBLE_LASER(95, TileType.WALL, DIR_EAST, SHOOTS_LASER, HIGH_PRIORITY),

    WALL_WEST(30, TileType.WALL, DIR_WEST),
    WALL_WEST_WITH_LASER(38, TileType.WALL, DIR_WEST, SHOOTS_LASER),
    WALL_WEST_WITH_DOUBLE_LASER(93, TileType.WALL, DIR_WEST, SHOOTS_LASER, HIGH_PRIORITY),

    WALL_SOUTH(29, TileType.WALL, DIR_SOUTH),
    WALL_SOUTH_WITH_LASER(37, TileType.WALL, DIR_SOUTH, SHOOTS_LASER),
    WALL_SOUTH_WITH_DOUBLE_LASER(87, TileType.WALL, DIR_SOUTH, SHOOTS_LASER, HIGH_PRIORITY),

    WALL_NORTH_PUSH_SOUTH_2_4(1, TileType.PUSHER, DIR_NORTH, PUSH_EVEN),
    WALL_EAST_PUSH_WEST_2_4(2, TileType.PUSHER, DIR_EAST, PUSH_EVEN),
    WALL_SOUTH_PUSH_NORTH_2_4(3, TileType.PUSHER, DIR_SOUTH, PUSH_EVEN),
    WALL_WEST_PUSH_EAST_2_4(4, TileType.PUSHER, DIR_WEST, PUSH_EVEN),
    WALL_NORTH_PUSH_SOUTH_1_3_5(9, TileType.PUSHER, DIR_NORTH, PUSH_ODD),
    WALL_EAST_PUSH_WEST_1_3_5(10, TileType.PUSHER, DIR_EAST, PUSH_ODD),
    WALL_SOUTH_PUSH_NORTH_1_3_5(11, TileType.PUSHER, DIR_SOUTH, PUSH_ODD),
    WALL_WEST_PUSH_EAST_1_3_5(12, TileType.PUSHER, DIR_WEST, PUSH_ODD),

    WALL_SOUTH_EAST_CORNER(8, TileType.WALL, DIR_EAST, DIR_SOUTH),
    WALL_NORTH_EAST_CORNER(16, TileType.WALL, DIR_NORTH, DIR_EAST),
    WALL_NORTH_WEST_CORNER(24, TileType.WALL, DIR_NORTH, DIR_WEST),
    WALL_SOUTH_WEST_CORNER(32, TileType.WALL, DIR_WEST, DIR_SOUTH),


    /**
     * STANDALONE LASERS
     * (TileType Laser)
     */
    LASER_VERTICAL(47, TileType.LASER, DIR_SOUTH, DIR_NORTH),
    LASER_HORIZONTAL(39, TileType.LASER, DIR_WEST, DIR_EAST),
    LASER_CROSS(40, TileType.LASER, DIR_WEST, DIR_EAST, DIR_SOUTH, DIR_NORTH),
    DOUBLE_LASER_VERTICAL(102, TileType.LASER, DIR_SOUTH, DIR_NORTH),
    DOUBLE_LASER_HORIZONTAL(103, TileType.LASER, DIR_WEST, DIR_EAST),
    DOUBLE_LASER_CROSS(101, TileType.LASER, DIR_WEST, DIR_EAST, DIR_SOUTH, DIR_NORTH),

    /**
     * OPTION TILES
     * (TileType Option)
     */
    HAMMER_AND_WRENCH(7, TileType.HAMMER_AND_WRENCH),
    WRENCH(15, TileType.WRENCH),

    /**
     * GEAR TILES
     * (TileType GEAR)
     */
    ROTATE_CLOCKWISE(54, TileType.GEAR, RIGHT),
    ROTATE_COUNTERCLOCKWISE(53, TileType.GEAR, LEFT),

    /**
     * CONVEYOR TILES
     * (TileType Conveyor or ROTATION_CONVEYOR)
     * GO_"Direction" is the conveyors that turn into an already straight CONVEYOR
     * ROTATE_"direction" rotate the player in given direction
     * EXPRESS prefix is the express (blue) conveyors
     */
    CONVEYOR_EAST(52, TileType.CONVEYOR, DIR_EAST),
    CONVEYOR_WEST(51, TileType.CONVEYOR, DIR_WEST),
    CONVEYOR_NORTH(49, TileType.CONVEYOR, DIR_NORTH),
    CONVEYOR_SOUTH(50, TileType.CONVEYOR, DIR_SOUTH),
    CONVEYOR_FROM_WEST_GO_NORTH(57, TileType.ROTATION_CONVEYOR, DIR_NORTH, WEST_NORTH),
    CONVEYOR_FROM_NORTH_GO_EAST(58, TileType.ROTATION_CONVEYOR, DIR_EAST, NORTH_EAST),
    CONVEYOR_FROM_EAST_GO_SOUTH(59, TileType.ROTATION_CONVEYOR, DIR_SOUTH, EAST_SOUTH),
    CONVEYOR_FROM_SOUTH_GO_WEST(60, TileType.ROTATION_CONVEYOR, DIR_WEST, SOUTH_WEST),
    CONVEYOR_FROM_NORTH_AND_SOUTH_GO_EAST(61, TileType.ROTATION_CONVEYOR, DIR_EAST, NORTH_EAST, SOUTH_EAST),
    CONVEYOR_FROM_WEST_AND_EAST_GO_SOUTH(62, TileType.ROTATION_CONVEYOR, DIR_SOUTH, EAST_SOUTH, WEST_SOUTH),
    CONVEYOR_FROM_EAST_GO_NORTH(65, TileType.ROTATION_CONVEYOR, DIR_NORTH, EAST_NORTH),
    CONVEYOR_FROM_SOUTH_GO_EAST(66, TileType.ROTATION_CONVEYOR, DIR_EAST, SOUTH_EAST),
    CONVEYOR_FROM_WEST_GO_SOUTH(67, TileType.ROTATION_CONVEYOR, DIR_SOUTH, WEST_SOUTH),
    CONVEYOR_FROM_NORTH_GO_WEST(68, TileType.ROTATION_CONVEYOR, DIR_WEST, NORTH_WEST),
    CONVEYOR_FROM_EAST_AND_WEST_GO_NORTH(69, TileType.ROTATION_CONVEYOR, DIR_NORTH, WEST_NORTH, EAST_NORTH),
    CONVEYOR_FROM_NORTH_AND_SOUTH_GO_WEST(70, TileType.ROTATION_CONVEYOR, DIR_WEST, SOUTH_WEST, NORTH_WEST),
    CONVEYOR_FROM_EAST_ROTATE_SOUTH(33, TileType.ROTATION_CONVEYOR, DIR_SOUTH, LEFT),
    CONVEYOR_FROM_NORTH_ROTATE_EAST(41, TileType.ROTATION_CONVEYOR, DIR_EAST, LEFT),
    CONVEYOR_FROM_WEST_ROTATE_NORTH(42, TileType.ROTATION_CONVEYOR, DIR_NORTH, LEFT),
    CONVEYOR_FROM_SOUTH_ROTATE_WEST(34, TileType.ROTATION_CONVEYOR, DIR_WEST, LEFT),
    CONVEYOR_FROM_SOUTH_ROTATE_EAST(35, TileType.ROTATION_CONVEYOR, DIR_EAST, RIGHT),
    CONVEYOR_FROM_WEST_ROTATE_SOUTH(36, TileType.ROTATION_CONVEYOR, DIR_SOUTH, RIGHT),
    CONVEYOR_FROM_NORTH_ROTATE_WEST(44, TileType.ROTATION_CONVEYOR, DIR_WEST, RIGHT),
    CONVEYOR_FROM_EAST_ROTATE_NORTH(43, TileType.ROTATION_CONVEYOR, DIR_NORTH, RIGHT),

    //express
    EXPRESS_CONVEYOR_EAST(14, TileType.CONVEYOR, HIGH_PRIORITY, DIR_EAST),
    EXPRESS_CONVEYOR_WEST(22, TileType.CONVEYOR, HIGH_PRIORITY, DIR_WEST),
    EXPRESS_CONVEYOR_NORTH(13, TileType.CONVEYOR, HIGH_PRIORITY, DIR_NORTH),
    EXPRESS_CONVEYOR_SOUTH(21, TileType.CONVEYOR, HIGH_PRIORITY, DIR_SOUTH),
    EXPRESS_CONVEYOR_FROM_WEST_GO_NORTH(73, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_NORTH, WEST_NORTH),
    EXPRESS_CONVEYOR_FROM_NORTH_GO_EAST(74, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_EAST, NORTH_EAST),
    EXPRESS_CONVEYOR_FROM_EAST_GO_SOUTH(75, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_SOUTH, EAST_SOUTH),
    EXPRESS_CONVEYOR_FROM_SOUTH_GO_WEST(76, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_WEST, SOUTH_WEST),
    EXPRESS_CONVEYOR_FROM_NORTH_AND_SOUTH_GO_EAST(81, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_EAST, NORTH_EAST, SOUTH_EAST),
    EXPRESS_CONVEYOR_FROM_WEST_AND_EAST_GO_SOUTH(82, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_SOUTH, EAST_SOUTH, WEST_SOUTH),
    EXPRESS_CONVEYOR_FROM_EAST_GO_NORTH(77, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_NORTH, EAST_NORTH),
    EXPRESS_CONVEYOR_FROM_SOUTH_GO_EAST(78, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_EAST, SOUTH_EAST),
    EXPRESS_CONVEYOR_FROM_WEST_GO_SOUTH(86, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_SOUTH, WEST_SOUTH),
    EXPRESS_CONVEYOR_FROM_NORTH_GO_WEST(85, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_WEST, NORTH_WEST),
    EXPRESS_CONVEYOR_FROM_EAST_AND_WEST_GO_NORTH(84, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_NORTH, WEST_NORTH, EAST_NORTH),
    EXPRESS_CONVEYOR_FROM_NORTH_AND_SOUTH_GO_WEST(83, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_WEST, SOUTH_WEST, NORTH_WEST),
    EXPRESS_CONVEYOR_FROM_EAST_ROTATE_SOUTH(17, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_SOUTH, LEFT),
    EXPRESS_CONVEYOR_FROM_NORTH_ROTATE_EAST(25, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_EAST, LEFT),
    EXPRESS_CONVEYOR_FROM_WEST_ROTATE_NORTH(26, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_NORTH, LEFT),
    EXPRESS_CONVEYOR_FROM_SOUTH_ROTATE_WEST(18, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_WEST, LEFT),
    EXPRESS_CONVEYOR_FROM_SOUTH_ROTATE_EAST(19, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_EAST, RIGHT),
    EXPRESS_CONVEYOR_FROM_WEST_ROTATE_SOUTH(20, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_SOUTH, RIGHT),
    EXPRESS_CONVEYOR_FROM_NORTH_ROTATE_WEST(28, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_WEST, RIGHT),
    EXPRESS_CONVEYOR_FROM_EAST_ROTATE_NORTH(27, TileType.ROTATION_CONVEYOR, HIGH_PRIORITY, DIR_NORTH, RIGHT),
    ;


    private final int id;
    @NotNull
    private String tilesetName;
    @NotNull
    private final TileType tileType;
    @NotNull
    private Set<Attribute> attributes;

    private static HashMap<Integer, TileGraphic> TileIdMap = new HashMap<>();

    static {
        for (TileGraphic value : values()) {
            TileIdMap.put(value.id, value);
        }
    }

    /**
     * @param id The tiled id of the wanted TileGraphic
     * @return The TileGraphic with the given id. Will be {@code null} if the given id is not known
     */
    @Nullable
    public static TileGraphic fromTiledId(int id) {
        return TileIdMap.get(id);
    }

    TileGraphic(int id, @NotNull TileType tileType, Attribute... attributes) {
        this(id, "tiles", tileType, attributes);
    }

    TileGraphic(int id, @NotNull String tilesetName, @NotNull TileType tileType, Attribute... attributes) {
        this.id = id;
        this.tilesetName = tilesetName;
        this.tileType = tileType;

        HashSet<Attribute> tempSet = new HashSet<>();
        Collections.addAll(tempSet, attributes);
        tempSet.addAll(tileType.getAttributes());
        this.attributes = Collections.unmodifiableSet(tempSet);
    }

    /**
     * @return The graphic part of this tile
     */
    @NotNull
    public TiledMapTile getTile() {
        return GameGraphics.getRoboRally().getCurrentMap().getMapTileSets().getTileSet(tilesetName).getTile(id);
    }

    /**
     * @return List of all attributes from both the type and graphic
     */
    @NotNull
    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public int getId() {
        return id;
    }

    /**
     * @return The more general type of tile this is
     */
    @NotNull
    public TileType getTileType() {
        return tileType;
    }

    /**
     * @param x The x-coordinate of the tile
     * @param y The y-coordinate of the tile
     * @return Create a new instance of this TileGraphic at the given coordinates, or {@code null} if there is no implantation
     * @throws IllegalStateException If the implementation does not fulfill the attributes criteria
     */
    @Nullable
    public Tile createInstance(int x, int y) {
        if (tileType.getImplClass() == null) {
            return null;
        }

        if (!getAttributes().stream().allMatch(att -> att.verifyInterfaces(tileType.getImplClass()))) {
            throw new IllegalStateException("TileType class (" + tileType.getImplClass() + ") does not have the required interface " + tileType.getAttributes());
        }
        try {
            Constructor<? extends Tile> constructor = tileType.getImplClass().getDeclaredConstructor(Vector2Int.class, TileGraphic.class);
            return constructor.newInstance(new Vector2Int(x, y), this);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}

