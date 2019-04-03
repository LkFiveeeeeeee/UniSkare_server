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
    @Autowired
    private skillService skillService;
    @Autowired
    private momentService momentService;


    public String upLoadUserPhoto(MultipartFile file,String userId){
        String avatarName = upLoad(file);
        if(avatarName == null){
            return ConstValue.OPERATION_FAIL;
        }else{
            String imageName = userService.updateAvatar(ConstValue.IMAGE_GET_PATH+ "/" + avatarName,userId);
            deleteUrl(imageName);
            return ConstValue.UPDATE_SUCCESS + "&" + ConstValue.IMAGE_GET_PATH + "/" + avatarName;
        }
    }

    public String upLoadSkillPhoto(MultipartFile file,int skillId){
        String imageName = upLoad(file);
        if(imageName == null){
            return ConstValue.OPERATION_FAIL;
        }else{
            imageName = ConstValue.IMAGE_GET_PATH+"/"+imageName+"&";
            int result = skillService.updateDisplayPic(imageName,skillId);
            if(result == 1){
                return ConstValue.UPDATE_SUCCESS;
            }else{
                return ConstValue.OPERATION_FAIL;
            }
        }
    }

    public String upLoadSkillCover(MultipartFile file,int skillId){
        String imageName = upLoad(file);
        if(imageName == null){
            return ConstValue.OPERATION_FAIL;
        }else{
            imageName = ConstValue.IMAGE_GET_PATH+"/"+imageName+"&";
            String deleteUrl = skillService.updateCover(imageName,skillId);
            if(deleteUrl(deleteUrl)){
                return ConstValue.UPDATE_SUCCESS;
            }else{
                return ConstValue.OPERATION_FAIL;
            }
        }
    }


    public String upLoadMomentPhoto(MultipartFile file,int momentId){
        String imageName = upLoad(file);
        if(imageName == null){
            return ConstValue.OPERATION_FAIL;
        }else{
            imageName = ConstValue.IMAGE_GET_PATH+"/"+imageName+"&";
            int result = momentService.updateDisplayPic(imageName,momentId);
            if(result == 1){
                return ConstValue.UPDATE_SUCCESS;
            }else{
                return ConstValue.OPERATION_FAIL;
            }
        }
    }


    private boolean deleteUrl(String deleteUrl) {
        if(deleteUrl != null){
            try{
                int index = deleteUrl.lastIndexOf('/');
                deleteUrl = deleteUrl.substring(index+1);
                deleteImage(deleteUrl);
                return true;
            }catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    public String deleteSkillDisPic(String picUrl,int skillId){
        String currentUrl = skillService.getDisUrl(skillId);

        String fileUrl = picUrl.replaceAll(ConstValue.IMAGE_GET_PATH + "/","");
        try{
            deleteImage(fileUrl);
        }catch (IOException e){
            e.printStackTrace();
        }
        currentUrl = currentUrl.replaceAll(picUrl+"&","");

        int result = skillService.updateDeletePic(currentUrl,skillId);
        if(result == 1){
            return ConstValue.UPDATE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
        }
    }


    public String deleteMomentDisPic(String picUrl,int momentId){
        String currentUrl = momentService.getDisUrl(momentId);

        String fileUrl = picUrl.replaceAll(ConstValue.IMAGE_GET_PATH + "/","");
        try{
            deleteImage(fileUrl);
        }catch (IOException e){
            e.printStackTrace();
        }
        currentUrl = currentUrl.replaceAll(picUrl+"&","");

        int result = momentService.updateDeletePic(currentUrl,momentId);
        if(result == 1){
            return ConstValue.UPDATE_SUCCESS;
        }else{
            return ConstValue.OPERATION_FAIL;
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
