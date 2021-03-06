package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Player extends Entity {
	private Input input;
	private boolean goingRight, goingUp;
	
	/**
	 * Should be power of 2 or divisible by grid size (8)
	 */
	private int speed;
	
	public Player(GameContainer gc) {
		input = gc.getInput();
		speed = 4;
	}

	@Override
	public void draw(GameContainer gc, Graphics g) {
		g.fillRect(getX(), getY(), 16, 16);
	}

	@Override
	public void update(GameContainer gc, int i) {
		if (input.isKeyDown(Input.KEY_UP)) {
			setY(getY() - speed);
			goingUp = true;
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			setY(getY() + speed);
			goingUp = false;
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			setX(getX() + speed);
			goingRight = true;
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			setX(getX() - speed);
			goingRight = false;
		}

		// Normalize to 8x8 grid
		// TODO: Update normalization to take direction into consideration for smoother movement
		if (!input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN)) {
			if (getY() % 16 != 0 && getY() % 16 != 8) {
				setY((int)(getY() + (goingUp ? -speed : speed)));
			}
		}
		
		if (!input.isKeyDown(Input.KEY_LEFT) && !input.isKeyDown(Input.KEY_RIGHT)) {
			if (getX() % 16 != 0 && getX() % 16 != 8) {
				setX((int)(getX() + (goingRight ? speed : -speed)));
			}
		}
	}
}
