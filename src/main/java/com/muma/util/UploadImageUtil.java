package com.muma.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * 
 * @author Administrator
 *上传图片工具类
 */
public final class UploadImageUtil {

    private static  final List<String> IMAGE_EXTENSIONS = Lists.newArrayList(".png",".jpg",".jpeg",".gif",".bmp");
    public static  final String  UPLOAD_IMAGE_TYPE_USER_INFO = "userInfo";
    public static  final String  UPLOAD_IMAGE_TYPE_TASK = "taskInfo";
	
	private static String makeFileName(String suffix,String regPhone){
		         //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		         return regPhone+"_"+UUID.randomUUID().toString()+suffix;
		     }

    /**
     * 创建文件夹
     * @param type
     * @return
     */
    private static String  makeFilePath(String type){
        String newPath = "D:"+File.separator+"muma"+File.separator;
        if("userInfo".equals(type)){ //用户信息
            newPath += "userInfo";
        }else if("taskInfo".equals(type)){
            // 1. 读取系统时间
            Calendar calendar = Calendar.getInstance();
            Date time = calendar.getTime();
            // 2. 格式化系统时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fileName = format.format(time); //获取系统当前时间并将其转换为string类型,fileName即文件名
            // 3. 创建文件夹
            newPath += "task-"+fileName;
        }
        File file = new File(newPath);
        //如果文件目录不存在则创建目录
        if(!file.exists() && !file.isDirectory()){
            System.out.println(newPath+"目录不存在，需要创建");
            file.mkdirs();
        }
        System.out.println("创建成功" + newPath);
        return newPath;
    }
		
	
	 public static  JSONObject upImage(MultipartFile file, String type,String regPhone){
         // 判断文件是否为空
         JSONObject result = new JSONObject();
         result.put("success",false);
         result.put("message","");
         result.put("url","");
         if (!file.isEmpty()) {
             try {
                 // 源文件名称
                 final String originalFileName = file.getOriginalFilename();
                 // 文件后缀[.jpg]
                 final String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
                 //创建路径
                 String newPath = makeFilePath(type);
                 String newName = makeFileName(suffix,regPhone);
                 if (!IMAGE_EXTENSIONS.contains(suffix)) {
                     result.put("message", "图片格式错误！");
                     return result;
                 }
                 // 文件保存路径
                 String filePath = newPath+File.separator+newName;
                 // 转存文件
                 file.transferTo(new File(filePath));
                 result.put("success",true);
                 result.put("url",filePath);
                 return result;
             } catch (IllegalStateException e) {
                 e.printStackTrace();
                 result.put("message","上传失败！");
                 return result;
             } catch (IOException e) {
                 e.printStackTrace();
                 result.put("message","上传失败！");
                 return result;
             }catch (Exception e){
                 e.printStackTrace();
                 result.put("message","上传失败！");
                 return result;
             }
         }else {
             result.put("message","请上传图片！");
         }
         return result;
	 }
}
