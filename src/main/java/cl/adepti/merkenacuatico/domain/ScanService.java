package cl.adepti.merkenacuatico.domain;



import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

import com.sun.pdfview.ImageInfo;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;
import com.sun.pdfview.RefImage;
import com.sun.pdfview.decode.PDFDecoder;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
import org.jpedal.DisplayOffsets;
import org.jpedal.PDFtoImageConvertor;
import org.jpedal.PdfDecoder;
import org.jpedal.exception.PdfException;
import org.jpedal.fonts.FontMappings;
import org.jpedal.io.ColorSpaceConvertor;
import org.jpedal.objects.PdfPageData;
import org.jpedal.render.output.OutputDisplay;
import org.jpedal.utils.LogWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import cl.adepti.merkenacuatico.data.StorageService;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;
import cl.adepti.merkenacuatico.domain.entity.Page;

public class ScanService{

	@Autowired
	StorageService fileService;
	
	QRCodeReader reader;
	public ScanService(StorageService fileService){
		this.fileService = fileService;
		this.reader = new QRCodeReader();
	}
	
	public BufferedImage getImageForPage(Page page) throws IOException {
		MerkenFile file = fileService.find(page.getFileId().toString());
		InputStream stream = file.getInputStream();
		BufferedImage imBuff = ImageIO.read(stream);
		return imBuff;
	}
	
	public String getMetaDataForPage(Page page) throws IOException, NotFoundException, ChecksumException, FormatException{
		
		BufferedImage imBuf = this.getImageForPage(page);
		
		return this.getMetaDataForImage(imBuf,true);
	}
	
	public String getMetaDataForImage(BufferedImage image,boolean clip) throws NotFoundException, ChecksumException, FormatException, IOException{
		BufferedImage subimage = null;
		if(clip){
			int subWidth = image.getWidth() / 4;
			int subHeight = image.getHeight()/8;
			subimage = image.getSubimage(
				image.getWidth() - subWidth, 0,
				subWidth, subHeight);
		}else{
			subimage = image;
		}
		LuminanceSource source = new BufferedImageLuminanceSource(subimage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result =null;
		//TODO: Remove this. Uncomment next line to write a test file to see actual qr.
		ImageIO.write(subimage, "png", new File("src/test/resources/output/testing.png"));
		result=	reader.decode(bitmap);
		
		
		return result.getText().replace(" ", "").trim();
	}

	public List<String> getMetaDataForPdf(MerkenFile storedFile) throws IOException {
		return this.processBigWithJPedal(storedFile,true);
		//return this.processBigWithPdfRendererJar(storedFile);
		
	}
	private List<String> processBigWithJPedal(MerkenFile storedFile,boolean bp) throws IOException {
			List<String> thelist = new LinkedList<String>();
			/**instance of PdfDecoder to convert PDF into image*/
			System.setProperty("org.jpedal.jai", "true");
			PdfDecoder decode_pdf = new PdfDecoder(true);
			/**set mappings for non-embedded fonts to use*/
			FontMappings.setFontReplacements();
			int qrcount=0;
			bp=true;
			boolean frontside = false;
			/**open the PDF file - can also be a URL or a byte array*/
			try {
			    	decode_pdf.openPdfFileFromInputStream(storedFile.getInputStream(),false);
					
			
				 decode_pdf.setExtractionMode(0,1f); //do not save images
				 
				 	/**get page 1 as an image*/
				    	//page range if you want to extract all pages with a loop
				    	//int start = 1,  end = decode_pdf.getPageCount();
				 for(int i = 1; i<=decode_pdf.getPageCount();i++){
					 if(bp){
			            	frontside=!frontside;
		            }
					if(qrcount>0&&!frontside){//if scanning a backside and already got qr. continue
						qrcount=0;
						String last = thelist.get(thelist.size()-1);
						thelist.add("b");
						System.out.println(i+ "LINE " + last+"b");
						continue;
					}
					if(frontside){
						System.out.println("ItsFrontPage");
						qrcount=0;
					}
					
				 	BufferedImage img=decode_pdf.getPageAsImage(i);
				    	
						
					String outstr = "";
		            try{
		            	outstr = this.getMetaDataForImage(img,true);
		            	qrcount++;
		            } catch (NotFoundException e) {
		            	outstr = ScanStatus.QRNOTFOUND.toString();
					} catch (ChecksumException e) {
						outstr = ScanStatus.QRBADCHECKSUM.toString();
					} catch (FormatException e) {
						outstr = ScanStatus.QRBADFORMAT.toString();
					}
		            System.out.println(i+" LINE " + outstr);
		            thelist.add(outstr); 
		            
				 }
				 /**close the pdf file*/
					decode_pdf.closePdfFile();
			} catch (PdfException e) {
			    e.printStackTrace();
			}

		return thelist;
	}

	//Requires maven dependency for pdf-renderer. For jbig2 requires a newer version.
	private List<String> processBigWithPdfRendererJar(MerkenFile storedFile) throws IOException {
		List<String> output = new LinkedList<String>();
		InputStream is = storedFile.getInputStream();
		
       ByteBuffer buf = ByteBuffer.wrap(IOUtils.toByteArray(is));
        PDFFile pdffile = new PDFFile(buf);
        int numPgs = pdffile.getNumPages();
        System.out.println("Processing "+ numPgs+ "pages");
        for (int i = 1; i <= numPgs; i++) {
            System.out.print("Processing page "+i);
            // draw the first page to an image
            PDFPage page = pdffile.getPage(i);
            System.out.println("PAGEW "+page.getWidth());
            // get the width and height for the doc at the default zoom
            Rectangle rect = new Rectangle(0,0, (int) page.getWidth()*2, (int) page.getHeight()*2);
            
            Rectangle2D box =  page.getBBox();
            box.setRect(0, 0, (int) page.getWidth()*2, (int) page.getWidth()*2);
            System.out.println("PAGE2 "+page.getWidth());
            // generate the image
            Image img = page.getImage(rect.width, rect.height, // width & height
                    rect, // clip rect
                    null, // null for the ImageObserver
                    false, // fill background with white
                    true // block until drawing is done
                    );
            
            // save it as a file
            BufferedImage bImg = toBufferedImage(img);
            ImageIO.write(bImg, "png", new File("test"+i+".png"));
            String outstr = "";
            try{
            	outstr = this.getMetaDataForImage(bImg,true);
           
            } catch (NotFoundException e) {
            	outstr = ScanStatus.QRNOTFOUND.toString();
			} catch (ChecksumException e) {
				outstr = ScanStatus.QRBADCHECKSUM.toString();
			} catch (FormatException e) {
				outstr = ScanStatus.QRBADFORMAT.toString();
			}
            output.add(outstr);
            System.out.println(". Data:"+outstr);
        }
		return output;
		
	}

	// This method returns a buffered image with the contents of an image
    private static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent
        // Pixels
        boolean hasAlpha = hasAlpha(image);
        // Create a buffered image with a format that's compatible with the
        // screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }
            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    private static boolean hasAlpha(Image image) {
    	
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage) image;
            return bimage.getColorModel().hasAlpha();
        }
        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }
        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }

}
