package fileviewer.rest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fileviewer.service.ImageViewerService;

@RestController
public class ImageViewerRequest {
	
	@Autowired
	private ImageViewerService imageFileService;
	
	/*Requisição para buscar imagem pelo nome do arquivo*/
	@RequestMapping(path = "/getImage/{imageFileName}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getImage(@PathVariable("imageFileName") String imageFileName) throws IOException{

	    ResponseEntity<?> response = null;
	    String imageFilePath = imageFileService.getImageFilePath(imageFileName);
	    
	    if(imageFilePath != null){
	        InputStream inputStream = new FileInputStream(imageFilePath);

	        byte[] image = IOUtils.toByteArray(inputStream);

	        HttpHeaders responseHeader = new HttpHeaders();
	        
	        ContentDisposition content = ContentDisposition.builder("inline")
	        		.filename(imageFileName).
	        		build();
	        
	        responseHeader.setContentDisposition(content);
	        responseHeader.setContentType(MediaType.IMAGE_JPEG);
	        responseHeader.setContentLength(image.length);

	        response = new ResponseEntity<> (image, responseHeader, HttpStatus.OK);
	    }else{
	    	response = new ResponseEntity<> ("File Not Found", HttpStatus.OK);
	    }
	    return response;
	}
}
