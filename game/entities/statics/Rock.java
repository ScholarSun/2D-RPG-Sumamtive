package game.entities.statics;
import java.awt.Graphics;
import game.Handler;
import game.gfx.Assets;
import game.tiles.Tile;

public class Rock extends StaticEntity {

	//constructor
	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		//set bounds for the rock object
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 2f);
	}

	@Override
	public void tick() {
	}
	
	@Override
	public void die(){
	}

	//render rock image, draw on the map
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
