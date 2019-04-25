package no.uib.inf112.core.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import no.uib.inf112.core.GameGraphics;

import java.io.File;


public class TitleScreen extends AbstractMenuScreen {

    private final String TITLE_SCREEN_FOLDER = "titlescreen" + File.separatorChar;

    private final Drawable HEADER = new TextureRegionDrawable(new Texture(TITLE_SCREEN_FOLDER + "header.png"));


    public TitleScreen(GameGraphics game) {
        super(game);
    }


    @Override
    public void show() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        TextButton play = createButton("SINGLE PLAYER", 0);
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SinglePlayerSetup(game));
            }
        });

        TextButton join = createButton("JOIN GAME", 2);
        join.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new JoinScreen(game));
            }
        });

        TextButton host = createButton("HOST GAME", 4);
        host.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HostSetup(game));
            }
        });

        TextButton options = createButton("OPTIONS", 6);
        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsScreen(game));
            }
        });

        TextButton quit = createButton("QUIT", 8);
        quit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });

        stage.addActor(play);
        stage.addActor(join);
        stage.addActor(host);
        stage.addActor(options);
        stage.addActor(quit);

    }

    @Override
    public void render(float v) {
        super.render(v);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        HEADER.draw(game.batch, 0, 2 * camera.viewportHeight / 3f - 20, camera.viewportWidth, camera.viewportHeight / 4f);
        game.batch.end();

    }
}
