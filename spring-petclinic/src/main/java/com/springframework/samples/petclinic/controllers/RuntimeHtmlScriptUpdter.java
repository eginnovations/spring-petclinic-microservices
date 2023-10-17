package com.springframework.samples.petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuntimeHtmlScriptUpdter {

    @Autowired
    Environment env;

    @Autowired
    ResourceLoader resourceLoader;

    @PostConstruct
    public void appendScriptIntoHtml() throws IOException {
		if(true){
			return;
		}
        String rumScript = env.getProperty("EG_RUM_SCRIPT");
        System.out.println("rumScript => " + rumScript);
        Resource urlResource = resourceLoader.getResource("classpath:templates/fragments/olderLayout.html");
        System.out.println("pathLoc => " + urlResource.getFile().getAbsolutePath());
        Path path = urlResource.getFile().toPath();
        Resource urlResource1 = resourceLoader.getResource("classpath:templates/fragments/layout.html");
        System.out.println("pathLoc1 => " + urlResource1.getFile().getAbsolutePath());
        File updatedFile = urlResource1.getFile();
        try {
            updatedFile.createNewFile();
            List<String> ls = Files.readAllLines(path);
//            System.out.println("ls => " + ls);
            List<String> newList = ls.stream().map(i-> i.trim()).collect(Collectors.toList());
//            System.out.println("newList => " + newList);
            int index = newList.indexOf("</head>");
//            System.out.println("index => " + index);
//            System.out.println("Size => " + newList.size());
            newList.add(index, rumScript);
//            System.out.println("newList => " + newList);
//            System.out.println("Size => " + newList.size());
            Files.write(updatedFile.toPath(), "".getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
            for(int i=0; i<newList.size(); i++) {
                String str = newList.get(i) + "\n";
                Files.write(updatedFile.toPath(), str.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
