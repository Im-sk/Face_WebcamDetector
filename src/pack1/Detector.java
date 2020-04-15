package pack1;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Detector {

	private CascadeClassifier cascadeClassifier;
	private MatOfRect detectedFaces;
	private Mat coloredImage;
	private Mat greyImage;
	
	public Detector() {
		this.detectedFaces = new MatOfRect();
		this.coloredImage = new Mat();
		this.greyImage = new Mat();
		this.cascadeClassifier = new CascadeClassifier("E:\\Workspaces\\Eclipse Combine Workspace\\haarcascade_frontalface_alt.xml");
	}

	public Mat detect(Mat inputframe) {
	
		inputframe.copyTo(coloredImage);
		inputframe.copyTo(greyImage);
		
		Imgproc.cvtColor(coloredImage, greyImage, Imgproc.COLOR_BGR2GRAY);
		Imgproc.equalizeHist(greyImage, greyImage);
		
		cascadeClassifier.detectMultiScale(greyImage, detectedFaces);
		
		showFacesOnScreen();
		
		return coloredImage;
	}
	
	private void showFacesOnScreen(){
		for (Rect rect : detectedFaces.toArray()) {
			Imgproc.rectangle(coloredImage, new Point(rect.x, rect.y), new Point(
					rect.x + rect.width, rect.y + rect.height), new Scalar(100,
					100, 250), 10);
		}
	}
}

