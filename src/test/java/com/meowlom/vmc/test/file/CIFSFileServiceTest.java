package com.meowlom.vmc.test.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meowlomo.atm.file.impl.SMBFileService;

class CIFSFileServiceTest {

    private final Logger logger = LoggerFactory.getLogger(CIFSFileServiceTest.class);

    static SMBFileService fileService;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        SMBFileService fileService = new SMBFileService();
        fileService.setHostname("10.0.30.52");
        fileService.setPassword("vmcadmin666");
        fileService.setUsername("vmcadmin");
        fileService.setServerDomain(null);
        fileService.setShareName("vmc");
        fileService.init();
    }

    @Test
    void readWriteFile() {
        File temp = new File("test.txt");
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(temp));
            out.write("sdjfalsjfdlasjdflasjdflasjkdflajslfdjl");
            out.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        java.nio.file.Path path = Paths.get("test/folder/sdfasdfasdf/test.txt");
        fileService.create(path, "sdjfalsjfdlasjdflasjdflasjkdflajslfdjl".getBytes(Charset.forName("UTF-8")), false);

        byte[] content = fileService.readFile(path);
        logger.info("read back     " + new String(content));

        assertEquals("sdjfalsjfdlasjdflasjdflasjkdflajslfdjl", new String(content));
    }

}
