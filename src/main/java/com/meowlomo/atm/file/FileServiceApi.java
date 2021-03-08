package com.meowlomo.atm.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meowlomo.atm.file.impl.SMBFileService;
import com.meowlomo.atm.file.service.FileService;

@Component
public class FileServiceApi {

    private final Logger logger = LoggerFactory.getLogger(FileServiceApi.class);

    // check the type of the file server
    @Value("${meowlomo.atm.file.type:smb}")
    private String serviceType;
    @Value("${meowlomo.atm.file.username}")
    private String username;
    @Value("${meowlomo.atm.file.password}")
    private String password;
    @Value("${meowlomo.atm.file.hostname:localhost}")
    private String hostname;
    @Value("${meowlomo.atm.file.domain:\\}")
    private String serverDomain;
    @Value("${meowlomo.atm.file.sharename:share}")
    private String sharename;

    public FileService createService() {
        if (this.serviceType.equalsIgnoreCase("smb") || this.serviceType.equalsIgnoreCase("cifs")) {
            logger.info("create cifs file service by config");
            SMBFileService  fileService = new SMBFileService();
            fileService.setHostname(hostname);
            fileService.setPassword(password);
            fileService.setUsername(username);
            fileService.setServerDomain(serverDomain);
            fileService.setShareName(sharename);
            fileService.init();
            return fileService;
        }
        else {
            logger.info("create cifs file service by default");
            SMBFileService  fileService = new SMBFileService();
            fileService.setHostname(hostname);
            fileService.setPassword(password);
            fileService.setUsername(username);
            fileService.setServerDomain(serverDomain);
            fileService.setShareName(sharename);
            fileService.init();
            return fileService;
        }
    }

    public FileService createService(String username, String password) {
        if (this.serviceType.equalsIgnoreCase("smb") || this.serviceType.equalsIgnoreCase("cifs")) {
            logger.info("create cifs file service by config");
            SMBFileService  fileService = new SMBFileService();
            fileService.setHostname(hostname);
            fileService.setPassword(password);
            fileService.setUsername(username);
            fileService.setServerDomain(serverDomain);
            fileService.setShareName(sharename);
            fileService.init();
            return fileService;
        }
        else {
            logger.info("create cifs file service by default");
            SMBFileService  fileService = new SMBFileService();
            fileService.setHostname(hostname);
            fileService.setPassword(password);
            fileService.setUsername(username);
            fileService.setServerDomain(serverDomain);
            fileService.setShareName(sharename);
            fileService.init();
            return fileService;
        }
    }
}
