package cl.adepti.merkenacuatico.services;

import java.awt.image.BufferedImage;
import java.io.IOException;
import org.junit.Test;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;

import static org.junit.Assert.*;
import cl.adepti.merkenacuatico.data.StorageService;
import cl.adepti.merkenacuatico.domain.ScanService;
import cl.adepti.merkenacuatico.domain.entity.Page;
public class ScanServiceTest {

	StorageService fileService = new FakeStorageService();
	@Test
	public void canGetFileImage() {
		ScanService service = new ScanService(fileService);
		Page page = new Page();
		
		assertNotNull(fileService);
		
		page.setFileId("pagep1.png");
		
		BufferedImage image=null;
		try {
			image = service.getImageForPage(page);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		assertNotNull(image);
		assertTrue("Must be a real image", image.getHeight()>0);
	}
	@Test
	public void canGetPageMetadata() {
		ScanService service = new ScanService(fileService);
		Page page = new Page();
		
		assertNotNull(fileService);
		
		page.setFileId("pagep1.png");
		
		String[] parts = null;
		try {
			parts = service.getMetaDataForPage(page);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (NotFoundException e) {
			fail(e.getMessage());
		} catch (ChecksumException e) {
			fail(e.getMessage());
		} catch (FormatException e) {
			fail(e.getMessage());
		}
		assertNotNull(parts);
		assertTrue("Must have metadata",parts.length>0);
		
	}
	
}
