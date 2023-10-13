package com.springframework.samples.petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuntimeHtmlScriptUpdter {

    @Autowired
    Environment env;

    @PostConstruct
    public void appendScriptIntoHtml() {
        String rumScript = env.getProperty("EG_RUM_SCRIPT");
//        System.out.println("rumScript => " + rumScript);
        Path path = Paths.get("spring-petclinic\\src\\main\\resources\\templates\\fragments\\olderLayout.html");
        File updatedFile = new File("spring-petclinic\\src\\main\\resources\\templates\\fragments\\layout.html");
//        System.out.println("path => " + path);
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
