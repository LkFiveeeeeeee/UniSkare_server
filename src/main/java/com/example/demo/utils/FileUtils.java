package com.example.demo.utils;

import java.io.File;
import java.io.IOException;
import com.example.demo.model.Image;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static Image upload(MultipartFile file,String filePath,String fileName) throws Exception{
        Image image = new Image();
        String realName = FileNameUtils.getFileName(fileName);
        String realPath = filePath + "/" + realName;
        image.setRealPhotoName(realName);
        File dest = new File(realPath);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
            image.setResult(true);
        }catch (IllegalStateException e){
            e.printStackTrace();
            image.setResult(false);
        }catch (IOException e){
            e.printStackTrace();
            image.setResult(false);
        }
        return image;

    }
}
