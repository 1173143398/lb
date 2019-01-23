package com.wechat;

import java.io.File;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Author xiuhong.chen@hand-china.com
 * created on 2018/1/9
 * 上传media素材, 获取media_id
 */
public class WxApiUtil {
    // token 接口(GET)
    private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    // 素材上传(POST)URL
    private static final String UPLOAD_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload";
    // 素材下载:不支持视频文件的下载(GET)
    private static final String DOWNLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";

    //创建菜单
    private static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    //查询菜单
    private static final String MENU_QUERY = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    
    private static String accessToken = null;
    
    public static String createMenu(String content){
    	return NetWorkUtil.getHttpsResponse(String.format(MENU_CREATE,getAccessToken()), "POST", content);
    }
    
    public static synchronized String getAccessToken(){
    	return accessToken;
    }
    
    public static synchronized void setAccessToken(String token){
    	accessToken = token;
    }
    
    public static String getTokenUrl(String appId, String appSecret) {
     return String.format(ACCESS_TOKEN, appId, appSecret);
    }

    public static String getNetAccessToken() {
        String Url = getTokenUrl(Constants1.APP_ID,Constants1.APP_SECRET);
        //此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        String result = NetWorkUtil.getHttpsResponse(Url, "","");
        System.out.println("获取到的access_token="+result);
        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSON.parseObject(result);
        setAccessToken(json.getString("access_token"));
        return result;
    }
    
    public static String getDownloadUrl(String token, String mediaId) {
     return String.format(DOWNLOAD_MEDIA, token, mediaId);
    }

    
    /**
     * 素材上传到微信服务器
     * @param file  File file = new File(filePath); // 获取本地文件
     * @param token access_token
     * @param type type只支持四种类型素材(video/image/voice/thumb)
     * @return
     */
    public static JSONObject uploadMedia(File file, String type) {
    	return null;
//        if(file == null || type == null){
//            return null;
//        }
//        if(!file.exists()){
//            System.out.println("上传文件不存在,请检查!");
//            return null;
//        }
//        JSONObject jsonObject = null;
//        PostMethod post = new PostMethod(UPLOAD_MEDIA);
//        post.setRequestHeader("Connection", "Keep-Alive");
//        post.setRequestHeader("Cache-Control", "no-cache");
//        FilePart media;
//        HttpClient httpClient = new HttpClient();
//        //信任任何类型的证书
//        Protocol myhttps = new Protocol("https", new SSLProtocolSocketFactory(), 443);
//        Protocol.registerProtocol("https", myhttps);
//
//        try {
//            media = new FilePart("media", file);
//            Part[] parts = new Part[]{new StringPart("access_token", getAccessToken()), new StringPart("type", type),media};
//            MultipartRequestEntity entity = new MultipartRequestEntity(parts,post.getParams());
//            post.setRequestEntity(entity);
//            int status = httpClient.executeMethod(post);
//            if (status == HttpStatus.SC_OK) {
//                String text = post.getResponseBodyAsString();
//                jsonObject = JSONObject.parseObject(text);
//            } else {
//                System.out.println("upload Media failure status is:" + status);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (HttpException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
    }
    
    public static File downloadMedia(String fileName, String token, String mediaId) {
    	return null;
//        String path = getDownloadUrl(token, mediaId);
//
//        if (fileName == null || path == null) {
//            return null;
//        }
//        File file = null;
//        HttpURLConnection conn = null;
//        InputStream inputStream = null;
//        FileOutputStream fileOut = null;
//        try {
//             URL url = new URL(path);
//             conn = (HttpURLConnection) url.openConnection();
//             conn.setDoOutput(true);
//             conn.setDoInput(true);
//             conn.setUseCaches(false);
//             conn.setRequestMethod("GET");
//
//             inputStream = conn.getInputStream();
//             if (inputStream != null) {
//                 file = new File(fileName);
//             } else {
//                 return file;
//             }
//
//             //写入到文件
//             fileOut = new FileOutputStream(file);
//             if (fileOut != null) {
//                 int c = inputStream.read();
//                 while (c != -1) {
//                     fileOut.write(c);
//                     c = inputStream.read();
//                 }
//             }
//        } catch (Exception e) {
//        } finally {
//             if (conn != null) {
//                 conn.disconnect();
//             }
//
//             try {
//                  inputStream.close();
//                  fileOut.close();
//               } catch (IOException execption) {
//             }
//        }
//    return file;
    }

}
