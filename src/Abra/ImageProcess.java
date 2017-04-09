package Abra;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageProcess {
	
	public static BufferedImage crop(BufferedImage src, int x, int y) {
		BufferedImage image = src.getSubimage(x, y, src.getWidth()-x, src.getHeight()-y);
		BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = copy.getGraphics();
		g.drawImage(image, 0, 0, null);
		
		return copy;
	}
	
}
