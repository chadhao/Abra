package Abra;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageProcess {
	
	public static BufferedImage crop(BufferedImage src, int x, int y) {
		BufferedImage image = src.getSubimage(x, y, src.getWidth()-x, src.getHeight()-y);
		BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = copy.getGraphics();
		g.drawImage(image, 0, 0, null);
		
		return copy;
	}
	
	
	public static BufferedImage resize(BufferedImage src, int w, int h) {
		BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(src, 0, 0, w, h, null);
		g.dispose();
		
		return newImage;
	}
}
