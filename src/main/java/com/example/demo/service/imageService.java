package com.example.demo.service;


import com.example.demo.model.ConstantValue.ConstValue;
import com.example.demo.model.Image;
import com.example.demo.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class imageService {
    @Autowired
    private UserService userService;


    public String upLoadUserPhoto(MultipartFile file,String userId){
        String avatarName = upLoad(file);
        if(avatarName == null){
            return ConstValue.OPERATION_FAIL;
        }else{
            String imageName = userService.updateAvatar(ConstValue.IMAGE_GET_PATH+ "/" + avatarName,userId);
            if(imageName != null){
                try{
                    int index = imageName.lastIndexOf('/');
                    imageName = imageName.substring(index+1);
                    deleteImage(imageName);//TODO check if the fix is true
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return ConstValue.UPDATE_SUCCESS + "&" + ConstValue.IMAGE_GET_PATH + "/" + avatarName;
        }
    }


    public String upLoad(MultipartFile file){
        Image image = new Image();
        image.setResult(false);
        String filePath = ConstValue.IMAGE_PATH;
        try{
            Image temp = FileUtils.upload(file,filePath,file.getOriginalFilename());
            image.setResult(temp.isResult());
            image.setRealPhotoName(temp.getRealPhotoName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(image.isResult()){
            return image.getRealPhotoName();
        }else{
            return null;
        }
    }

    public void deleteImage(String fileName)throws IOException{
        File folder = new File(ConstValue.IMAGE_PATH);
        File[] files = folder.listFiles();
        for(File file:files){
            if(file.getName().equals(fileName)){
                file.delete();
                break;
            }
        }
    }

}
