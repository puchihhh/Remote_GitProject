package com.libraryreservation.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @PostMapping("/relgoods/video")
    @ResponseBody
    public JSONObject relgoodsvideo(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();
        String filename = UUID.randomUUID().toString().replaceAll("-", "");
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        String filenames = filename + "." + ext;
        String pathname = "D:\\uploadimgtest\\" + filenames;
        file.transferTo(new File(pathname));
        resUrl.put("src", "/pic/"+filenames);
        res.put("msg", "");
        res.put("code", 0);
        res.put("data", resUrl);
        return res;
    }
    @PostMapping(value="/relgoods/images")
    @ResponseBody
    public JSONObject relgoodsimages(@RequestParam(value = "file", required = false) MultipartFile[] file) throws IOException {
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();
        List<String> imageurls=new ArrayList<>();
        for (MultipartFile files:file){
            String filename = UUID.randomUUID().toString().replaceAll("-", "");
            String ext = FilenameUtils.getExtension(files.getOriginalFilename());
            String filenames = filename + "." + ext;
            String pathname = "D:\\uploadimgtest\\" + filenames;
            files.transferTo(new File(pathname));
            imageurls.add("/pic/"+filenames);
            res.put("msg", "");
            res.put("code", 0);
        }
        resUrl.put("src", imageurls);
        res.put("data", resUrl);
        return res;
    }
}
