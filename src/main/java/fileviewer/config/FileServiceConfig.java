package fileviewer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/*Classe de configugração do caminho de pastas*/
@Configuration
public class FileServiceConfig {
	
	@Value("${file.paths}")    
    private String[] searchFolders;
    
	public String[] getFolders() {
		return searchFolders;
	}
}
