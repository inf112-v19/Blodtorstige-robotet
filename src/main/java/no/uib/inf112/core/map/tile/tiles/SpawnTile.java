package no.uib.inf112.core.map.tile.tiles;

import no.uib.inf112.core.map.tile.TileGraphic;
import no.uib.inf112.core.map.tile.api.AbstractTile;
import no.uib.inf112.core.map.tile.api.DockableTile;
import no.uib.inf112.core.util.Vector2Int;
import org.jetbrains.annotations.NotNull;

public class SpawnTile extends AbstractTile implements DockableTile {

    private final int SPAWN_NR;
    private final TileGraphic tg;

    public SpawnTile(@NotNull Vector2Int pos, @NotNull TileGraphic tg) {
        super(pos, tg);
        this.tg = tg;
        SPAWN_NR = Integer.parseInt(tg.toString().replace("SPAWN", ""));
    }

    @Override
    public int getSpawnNumber() {
        return SPAWN_NR;
    }

    @Override
    public String toString() {
        return "SpawnTile{" + tg.toString() + "}";
    }
}
