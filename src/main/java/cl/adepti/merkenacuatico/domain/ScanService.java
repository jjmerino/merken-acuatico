package cl.adepti.merkenacuatico.domain;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

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
import com.mongodb.gridfs.GridFSDBFile;

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
	
	public String[] getMetaDataForPage(Page page) throws IOException, NotFoundException, ChecksumException, FormatException{
		
		BufferedImage imBuf = this.getImageForPage(page);
		
		return this.getMetaDataForImage(imBuf);
	}
	
	private String[] getMetaDataForImage(BufferedImage image) throws NotFoundException, ChecksumException, FormatException, IOException{
		int subWidth = image.getWidth() / 4;
		int subHeight = image.getHeight()/8;
		BufferedImage subimage = image.getSubimage(
				image.getWidth() - subWidth, 0,
				subWidth, subHeight);
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result =null;
		//TODO: Remove this. Uncomment next line to write a test file to see actual qr.
		ImageIO.write(subimage, "png", new File("src/test/resources/output/testing.png"));
		result=	reader.decode(bitmap);
		
		
		String filename = result.getText().replace(" ", "").trim();
		String[] parts = filename.split("-");
		return parts;
	}

}
