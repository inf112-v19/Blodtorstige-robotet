package no.uib.inf112.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.google.gson.Gson;
import no.uib.inf112.core.map.MapHandler;
import no.uib.inf112.core.map.TiledMapHandler;
import no.uib.inf112.core.multiplayer.IClient;
import no.uib.inf112.core.multiplayer.Server;
import no.uib.inf112.core.multiplayer.dtos.NewGameDto;
import no.uib.inf112.core.player.MultiPlayerHandler;
import no.uib.inf112.core.player.PlayerHandler;
import no.uib.inf112.core.screens.menuscreens.TitleScreen;
import no.uib.inf112.core.testutils.HeadlessMapHandler;
import no.uib.inf112.core.ui.Sound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public class GameGraphics extends Game {

    private static RoboRally roboRally;
    public static boolean HEADLESS;

    public static final String ART_FOLDER = "art" + File.separatorChar;
    public static final String UI_FOLDER = ART_FOLDER + "ui" + File.separatorChar;
    public static final String BUTTON_FOLDER = UI_FOLDER + "buttons" + File.separatorChar;
    public static final String CARD_SKIN_FOLDER = UI_FOLDER + "cardSkins" + File.separatorChar;
    public static final String MAP_FOLDER = "maps" + File.separatorChar;
    public static final String MAP_EXTENSION = ".tmx";
    public static final String SCREEN_FONT = "screen_font.ttf";
    public static final String SCREEN_FONT_BOLD = "screen_font_bold.ttf";

    public static String mapFileName = "risky_exchange";

    public static Music backgroundMusic;
    public static boolean soundMuted;
    public static int players;

    public static Gson gson = new Gson();

    public static String mainPlayerName = "default name";

    public static final int MIN_PORT = 49152;
    public static final int MAX_PORT = 65535;
    public static int port = 55555; // Default port

    public SpriteBatch batch;

    private static Server server;
    private static IClient client;

    public static RoboRally createRoboRallyMultiplayer(@NotNull NewGameDto setup, IClient client) {
        String mapPath = (!HEADLESS ? MAP_FOLDER : "") + setup.map + MAP_EXTENSION;
        MapHandler mapHandler = !HEADLESS ? new TiledMapHandler(mapPath) : new HeadlessMapHandler(mapPath);
        roboRally = new RoboRally(mapHandler, new MultiPlayerHandler(setup, mapHandler, client));
        return roboRally;
    }

    @Override
    public void create() {
        players = 2;
        batch = new SpriteBatch();
        setScreen(new TitleScreen(this));

        backgroundMusic = Sound.getBackgroundMusic();
        backgroundMusic.setVolume(1f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundMusic.stop();
        backgroundMusic.dispose();
        batch.dispose();
        closeResources();
    }

    /**
     * Close the client and the server and serve it to the garbage collector
     */
    public void closeResources() {
        if (client != null) {
            client.closeConnection();
            client = null;
        }
        if (server != null) {
            server.close();
            server = null;
        }
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    /**
     * Method to change the map that will be used to create roborally. The parameter string comes from the selectbox in
     * AbstractSetupScreen and can therefore not have other values than the cases deal with.
     *
     * @param newMapFile The name of the map (not file name) that we want to use
     */
    public static void setMap(String newMapFile) {
        mapFileName = newMapFile;
    }

    public static RoboRally getRoboRally() {
        if (null == roboRally) {
            createRoboRally(MAP_FOLDER + mapFileName + MAP_EXTENSION, players);
        }
        return roboRally;
    }

    public static void resetRoborally() {
        RoboRally.SECOND_THREAD.cancelTasks();
        roboRally = null;
    }

    public static synchronized RoboRally createRoboRally(String map, int playerCount) {
        MapHandler mapHandler = !HEADLESS ? new TiledMapHandler(map) : new HeadlessMapHandler(map);
        roboRally = new RoboRally(mapHandler, new PlayerHandler(playerCount, mapHandler));
        return roboRally;
    }

    public static BitmapFont generateFont(String fontFile, int size) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        return fontGenerator.generateFont(parameter);
    }


    public Label createLabel(String text, float x, float y, int fontSize) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = generateFont(GameGraphics.SCREEN_FONT, fontSize);

        Label label = new Label(text, labelStyle);
        label.setColor(Color.BLACK);

        label.setPosition(x, y);
        return label;
    }

    /**
     * Set the given server for this instance
     *
     * @param newServer the server
     */
    public static void setServer(@Nullable Server newServer) {
        server = newServer;
    }

    /**
     * Set the given client for this instance
     *
     * @param newClient the client
     */
    public static void setClient(@Nullable IClient newClient) {
        client = newClient;
    }

    /**
     * @return the given client for this instance
     */
    @Nullable
    public static IClient getClient() {
        return client;
    }

}
