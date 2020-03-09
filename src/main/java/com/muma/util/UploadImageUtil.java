package com.muma.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
    public static  final String  IMAGE_TYPE_USER_INFO = "userInfo";
    public static  final String  IMAGE_TYPE_PLATFORM_INFO = "platformInfo";
    public static  final String  IMAGE_TYPE_TASK = "taskInfo";
	
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
        if(IMAGE_TYPE_USER_INFO.equals(type)){ //用户信息
            newPath += IMAGE_TYPE_USER_INFO;
        }else if(IMAGE_TYPE_PLATFORM_INFO.equals(type)){
            newPath += IMAGE_TYPE_PLATFORM_INFO;
        } else if(IMAGE_TYPE_TASK.equals(type)){
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
        return newPath;
    }

    /**
     * 获取图片转base64
     * @param imageUrl
     * @return
     */
    public static String base64image(String imageUrl) {
        String imgStr = "";
        try {
            File file = new File(imageUrl);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length && (numRead = fis.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }
            fis.close();
            BASE64Encoder encoder = new BASE64Encoder();
            imgStr = encoder.encode(buffer);
            System.out.println("base64iamge:" + imgStr);
            return imgStr;
        } catch (Exception e) {
            e.printStackTrace();
            return imgStr;
        }

    }

    /**
     * 保存照片
     * @param file
     * @param type
     * @param regPhone
     * @return
     */
    public static String saveImage(MultipartFile file, String type,String regPhone,String imageName){
        JSONObject result = upImage(file,type,regPhone);
        Precondition.checkState(result.getBoolean("success"), imageName+result.getString("message"));
        String url = result.getString("url");
        return url;
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
                     result.put("message", "格式错误！");
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

    public static void main(String[] args) {
       base64image("D:\\muma\\userInfo\\15705938769_738beb59-eeee-4488-90f6-974908fdac44.png");
    }
}
