package game.gfx;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage dirt, grass, stone, tree, rock;
	public static BufferedImage wood;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] player_adown, player_aup, player_aleft, player_aright;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;

	//set all the animation frames
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("textures/sheet.png"));
		
		wood = sheet.crop(width, height, width, height);
		
		//get picture from texture sheet
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		//get picture from texture sheet
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_adown = new BufferedImage[2];
		player_aup = new BufferedImage[2];
		player_aleft = new BufferedImage[2];
		player_aright = new BufferedImage[2];
		
		//get picture from texture sheet
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		
		player_adown[0] = sheet.crop(width * 4, height * 5, width, height);
		player_adown[1] = player_down[0];
		player_aup[0] = sheet.crop(width * 5, height * 5, width, height);
		player_aup[1] = player_up[0];
		player_aright[0] = sheet.crop(width * 4, height * 4, width, height);
		player_aright[1] = player_right[0];
		player_aleft[0] = sheet.crop(width * 5, height * 4, width, height);
		player_aleft[1] = player_left[0];
		
		//get picture from texture sheet
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		//get picture from texture sheet
		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
		
		//get picture from texture sheet
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
	}
	
}
