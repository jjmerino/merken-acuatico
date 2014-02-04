package cl.adepti.merkenacuatico.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cl.adepti.merkenacuatico.data.DocumentStorageService;
import cl.adepti.merkenacuatico.domain.entity.MerkenDocument;

@Controller
public class FileUploadController {

	@Autowired
	private DocumentStorageService storage;
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                MerkenDocument doc = storage.create(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
                return "You successfully uploaded "+file.getOriginalFilename()+ ". Renamed to: " + doc.getFile().getFilename() + " into mongo id " + doc.getFile().getId().toString();
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}
