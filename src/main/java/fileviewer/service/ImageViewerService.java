package fileviewer.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fileviewer.config.FileServiceConfig;

@Service("imageViewerService")
public class ImageViewerService {
	
	private final Logger servicelog = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileServiceConfig fileserviceconfig;
	
	/*Serviço que verifica se a imagem esta disponivel na pasta e retorna o caminho do diretorio*/
	public String getImageFilePath(String imageFileName) {
		for(String folder: fileserviceconfig.getFolders()) {
			if(new File(folder + imageFileName + ".jpg").exists() && !new File(folder + imageFileName + ".jpg").isDirectory()) {
				servicelog.info("Image Founded");
				return folder + imageFileName + ".jpg";
			}
		}
		return null;
	}
}
