package cl.adepti.merkenacuatico.domain;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import cl.adepti.merkenacuatico.domain.entity.Exam;
import cl.adepti.merkenacuatico.domain.entity.MerkenFile;

public class QRPdfService implements PDFService {
	
	QRMetaRules rules;
	public QRPdfService(QRMetaRules rules){
		this.rules = rules;
	}
	@Override
	public MerkenFile create(Exam exam) throws Exception{
		QRCodeWriter writer = new QRCodeWriter();
		String content = rules.makeQRDataForExam(exam);
		BitMatrix matrix=null;
		List<BitMatrix> codes = new LinkedList<BitMatrix>();
		exam.getSourceDocument();
		try {
			matrix = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200);
		} catch (WriterException e) {
			System.err.println("GRAVE: couldnt create QR. Exception was: ");
			e.printStackTrace();
			return null;
		}
		BufferedImage bfImage = MatrixToImageWriter.toBufferedImage(matrix);
		
		
		
		
		return null;
	}

}
