package rach.android_swzom;

import java.util.List;

import rach.android_swzom.framework.Game;
import rach.android_swzom.framework.Graphics;
import rach.android_swzom.framework.Input.TouchEvent;
import rach.android_swzom.framework.Pixmap;
import rach.android_swzom.framework.Screen;
import android.graphics.Color;
import android.util.Log;


public class GameScreen extends Screen {
	enum GameState {
		Ready,
		Running,
		Paused,
		GameOver
	}

	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";

	public GameScreen(Game game) {
		super(game);
		world = new World();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		if(state == GameState.Ready)
			updateReady(touchEvents);
		if(state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if(state == GameState.Paused)
			updatePaused(touchEvents);
		if(state == GameState.GameOver)
			updateGameOver(touchEvents);        
	}

	private void updateReady(List<TouchEvent> touchEvents) {
		if(touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {        
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x < 64 && event.y < 64) {
					if(Settings.soundEnabled)
						Assets.click.play(1);
					state = GameState.Paused;
					return;
				}
			}
			if(event.type == TouchEvent.TOUCH_DOWN) {
				if(event.x < 64 && event.y > 416) {
					world.snake.turnLeft();
					Log.d(">>>>>>>>>>>>>","TOUCHDOWN");
				}
				if(event.x > 256 && event.y > 416) {
					world.snake.turnRight();
					Log.d(">>>>>>>>>>>>>","TOUCHDOWN");

				}
			}
		}

		world.update(deltaTime);
		if(world.gameOver) {
			//if(Settings.soundEnabled)
			//Assets.bitten.play(1);
			//state = GameState.GameOver;
		}
		if(oldScore != world.score) {
			oldScore = world.score;
			score = "" + oldScore;
			//            if(Settings.soundEnabled)
			//                Assets.eat.play(1);
		}
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x > 80 && event.x <= 240) {
					if(event.y > 100 && event.y <= 148) {
						if(Settings.soundEnabled)
							Assets.click.play(1);
						state = GameState.Running;
						return;
					}                    
					if(event.y > 148 && event.y < 196) {
						if(Settings.soundEnabled)
							Assets.click.play(1);
						game.setScreen(new MainMenuScreen(game));                        
						return;
					}
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x >= 128 && event.x <= 192 &&
						event.y >= 200 && event.y <= 264) {
					if(Settings.soundEnabled)
						Assets.click.play(1);
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
		
	}


	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.background, 0, 0);
		drawWorld(world);
		if(state == GameState.Ready) 
			drawReadyUI();
		if(state == GameState.Running)
			drawRunningUI();
		if(state == GameState.Paused)
			drawPausedUI();
		if(state == GameState.GameOver)
			drawGameOverUI();

		drawText(g, score, g.getWidth() / 2 - score.length()*20 / 2, g.getHeight() - 42);                
	}

	private void drawWorld(World world) {
		Graphics g = game.getGraphics();
		Snake snake = world.snake;

		Pixmap batMap = null;
		if(snake.direction == Snake.UP) 
			batMap = Assets.batskullup;
		if(snake.direction == Snake.LEFT) 
			batMap = Assets.batskullleft;
		if(snake.direction == Snake.DOWN) 
			batMap = Assets.batskulldown;
		if(snake.direction == Snake.RIGHT) 
			batMap = Assets.batskullright;      
		int x = snake.x * 32 + 16;
		int y = snake.y * 32 + 16;
		g.drawPixmap(batMap, x - batMap.getWidth() / 2, y - batMap.getHeight() / 2);

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.ready, 47, 100);
		//g.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
		//g.drawLine(0, 416, 480, 416, Color.BLACK);
		g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
		g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.pause, 80, 100);
		//g.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();

		//        g.drawPixmap(Assets.gameOver, 62, 100);
		//        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
		//        g.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	public void drawText(Graphics g, String line, int x, int y) {
		int len = line.length();
		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);

			if (character == ' ') {
				x += 20;
				continue;
			}

			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
				srcX = 200;
				srcWidth = 10;
			} else {
				srcX = (character - '0') * 20;
				srcWidth = 20;
			}

			// g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
	}

	@Override
	public void pause() {
		if(state == GameState.Running)
			state = GameState.Paused;

		if(world.gameOver) {
			Settings.addScore(world.score);
			Settings.save(game.getFileIO());
		}
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}