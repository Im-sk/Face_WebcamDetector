package pack1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.JPanel;
import org.opencv.core.Mat;

public class CameraPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public CameraPanel() {
		
	}

	public boolean convertMatToImage(Mat matBGR) {
		
		int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels();
		byte[] sourcePixels = new byte[width * height * channels];
		matBGR.get(0, 0, sourcePixels);
	
		image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
		
		return true;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (this.image == null){
			return;
		}
			
		g.drawImage(this.image, 10, 10, this.image.getWidth(),this.image.getHeight(), null);
	}
}
