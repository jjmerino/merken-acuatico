package cl.adepti.merkenacuatico.rest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

import cl.adepti.merkenacuatico.data.FileStorageService;

@Controller
public class FileController {
	
	@Autowired
	private FileStorageService storage;
	@Autowired
	private GridFsOperations  gridOperations;
	
	@RequestMapping(value = "/files/{fileid}/download", method = RequestMethod.GET)
	public void download(@PathVariable String fileid,HttpServletResponse response) {
		GridFSDBFile document = gridOperations.findOne(
	               new Query().addCriteria(Criteria.where("_id").is(new ObjectId(fileid))));
		 try {
		      // get your file as InputStream
		      InputStream is = document.getInputStream();

		      
		      response.setContentType(document.getContentType());
		      response.setHeader("Content-Disposition","attachment; filename=\"" + document.getFilename() +"\"");
		      // copy it to response's OutputStream
		      IOUtils.copy(is, response.getOutputStream());
		      
		      response.flushBuffer();
		    } catch (IOException ex) {
		      throw new RuntimeException("IOError writing file to output stream");
		    }
	}
	@RequestMapping(value = "/files/{fileid}", method = RequestMethod.GET)
	@ResponseBody
	public DBObject document(@PathVariable String fileid,HttpServletResponse response) {
		GridFSDBFile document = gridOperations.findOne(
				new Query().addCriteria(Criteria.where("_id").is(new ObjectId(fileid))));
		return document.getMetaData();
	}
    @RequestMapping(value="/files", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    @RequestMapping(value="/upload/{area}", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@PathVariable("area") String area,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                GridFSFile doc = storage.create(file.getInputStream(), file.getOriginalFilename(), file.getContentType(),area);
                
                return "You successfully uploaded "+file.getOriginalFilename()+ ". Renamed to: " + doc.getFilename() + " into mongo id " + doc.getId().toString();
                
            } catch (Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
        }
    }
}
