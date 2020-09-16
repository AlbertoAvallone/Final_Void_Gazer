package execution;

import java.awt.Graphics;

/**
 * interface to be implemented in objects that know where to draw themselves thus needing to know their specific graphic coordinates
 * different from their world coordinate system
 * @author frenc
 *
 */

public interface GraphicGameObject{
	void draw(Graphics g);
}
