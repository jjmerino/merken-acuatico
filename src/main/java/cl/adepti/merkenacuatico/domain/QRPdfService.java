package cl.adepti.merkenacuatico.domain;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.SimpleRenderer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import cl.adepti.merkenacuatico.data.ExamRepository;
import cl.adepti.merkenacuatico.data.StorageService;
import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;
import cl.adepti.merkenacuatico.domain.entity.StandardMerkenFile;

public class QRPdfService implements PDFService {

	StorageService storageService;
	QRMetaRules rules;
	ExamRepository examRepo;
	public QRPdfService(QRMetaRules rules,StorageService storageService,ExamRepository examRepo){
		this.rules = rules;
		this.storageService = storageService;
		this.examRepo = examRepo;
	}
	@Override
	public MerkenFile create(Exam exam) throws Exception{
		QRCodeWriter writer = new QRCodeWriter();
		String content = rules.makeQRDataForExam(exam);
		BitMatrix matrix=null;
		exam.getSourceDocument();
		PdfReader reader = new PdfReader(exam.getBluePrint().getSourceFile().getInputStream());

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(reader,out);
		Integer numPages = reader.getNumberOfPages();
		try {
			for(int i=1; i<= numPages; i++){
				matrix = writer.encode(content+"-"+i, BarcodeFormat.QR_CODE, 200, 200);
				ByteArrayOutputStream imgOut = new ByteArrayOutputStream();
				MatrixToImageWriter.writeToStream(matrix,"png", imgOut);
				
				PdfContentByte cont = stamper.getUnderContent(i);
				
				
		          Image image = Image.getInstance(imgOut.toByteArray());
		          image.setAbsolutePosition(reader.getPageSize(i).getWidth()-200-20, 20);
		          cont.addImage(image);
		      }
			stamper.close();	
		} catch (WriterException e) {
			System.err.println("GRAVE: couldnt create QR. Exception was: ");
			e.printStackTrace();
			return null;
		}
		
		InputStream is = new ByteArrayInputStream(out.toByteArray());
		String filename = exam.getStudent().getUsername()+".pdf";

		String id = storageService.create(is, filename, "application/pdf", "examsources");
		MerkenFile ret = new StandardMerkenFile(id,is,filename);
		exam.setSourceDocument(ret);
		exam.setOriginalNumPages(numPages);
		examRepo.save(exam);
		return ret;
	}

}
