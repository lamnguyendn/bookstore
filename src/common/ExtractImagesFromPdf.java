package common;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class ExtractImagesFromPdf {
	public void extractImagesFromPdfFile(String isbn,String fileName) {
		final int IMG_WIDTH = /*1115*/ /*567*/ 2083 /*480*/;
		final int IMG_HEIGHT = /*1443*/ /*752*/ 2746 /*600*/;
		String pdfFilePath = "WebContent/pdf";
		String destinationFolder = "WebContent/image"+"/"+isbn;
		Path path = Paths.get(destinationFolder);
		if (Files.notExists(path, LinkOption.NOFOLLOW_LINKS)) {
			new File(destinationFolder.toString()).mkdir();
		}
		PDDocument document = null;
		try {
			document = PDDocument.load(new File(pdfFilePath + "/" + fileName+".pdf"));
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// Xử lý exception
			e.printStackTrace();
		}
		PDFRenderer pdfRenderer = new PDFRenderer(document);

		int pageCounter = 0;

		for (PDPage page : document.getPages()) {
			BufferedImage bim = null;
			try {
				bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);
			} catch (IOException e) {
				// Xử lý exception
				e.printStackTrace();
			}
			BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = resizedImage.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(bim, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
			g.dispose();
			try {
				ImageIOUtil.writeImage(resizedImage, destinationFolder + "/" + ((pageCounter++)+1) + "-large.png", 300);
			} catch (IOException e) {
				// Xử lý exception
				e.printStackTrace();
			}
		}
		try {
			document.close();
		} catch (IOException e) {
			// Xử lý exception
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		ExtractImagesFromPdf e = new ExtractImagesFromPdf();
		e.extractImagesFromPdfFile("E:/Data_Dat/libJava/","26", "Harry Potter and the Sorcerer's Stone");
	}
}
