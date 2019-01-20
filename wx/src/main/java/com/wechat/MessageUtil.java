package com.wechat;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil {
    /**
     * 解析微信发来的请求（XML）
     * @param request
     * @return map
     * @throws Exception
     */
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception {
    	Enumeration<String> headerNames = request.getHeaderNames();
    	while(headerNames.hasMoreElements()){
    		String nextElement = headerNames.nextElement();
    		System.out.println(nextElement+" : "+request.getHeader(nextElement));
    	}
        // 将解析结果存储在HashMap中
        Map<String,String> map = new HashMap<String,String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        System.out.println("获取输入流");
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        System.out.println("接收报文："+document.asXML());
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 根据消息类型 构造返回消息
     */
    public static String buildXml(Map<String,String> map) {
        String result;
        String msgType = map.get("MsgType").toString();
        System.out.println("MsgType:" + msgType);
        if(msgType.toUpperCase().equals("TEXT")){
        	 String content = map.get("Content");
        	 System.out.println("============filterEmoji===========");
             EmojiUtil emojiUtil = new EmojiUtil();
             String unicodeEmoji = emojiUtil.filterEmoji(content); //unicode编码的Emoji
             System.out.println("unicodeEmoji:"+unicodeEmoji);
             if(content.contains("/:")  || content.contains("\\:")  || content.contains("[") && content.contains("]") || unicodeEmoji.contains("\\")){
                 result = buildTextMessage(map,"客官发送的内容很特别哟~/:heart    " + content);
             }else{
            	 result = buildTextMessage(map, "Cherry的小小窝, 请问客官想要点啥?");
             }
        }else if(msgType.toUpperCase().equals("IMAGE")){
        	return buildImageMessage(map,"");
        }
        else{
            String fromUserName = map.get("FromUserName");
            // 开发者微信号
            String toUserName = map.get("ToUserName");
            result = String
                    .format(
                            "<xml>" +
                                    "<ToUserName><![CDATA[%s]]></ToUserName>" +
                                    "<FromUserName><![CDATA[%s]]></FromUserName>" +
                                    "<CreateTime>%s</CreateTime>" +
                                    "<MsgType><![CDATA[text]]></MsgType>" +
                                    "<Content><![CDATA[%s]]></Content>" +
                                    "</xml>",
                            fromUserName, toUserName, getUtcTime(),
                            "请回复如下关键词：\n文本\n图片\n语音\n视频\n音乐\n图文");
        }

        return result;
    }

    /**
     * 构造文本消息
     *
     * @param map
     * @param content
     * @return
     */
    private static String buildTextMessage(Map<String,String> map, String content) {
        //发送方帐号
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");

        
        /**
         * 文本消息XML数据格式
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" + "</xml>",
                fromUserName, toUserName, getUtcTime(), content);
    }

    /**
     *  构建图片消息
     * @param map
     * @param picUrl
     * @return
     */
    private static String buildImageMessage(Map<String, String> map, String picUrl) {
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        /*返回指定的图片(该图片是上传为素材的,获得其media_id)*/
        String media_id = "WWKDN0vrCPJWkn1lr-lZpyz_CQFWV2hHFrE_m3m6BJGllaWAQkMJZ4q4l_1EyCjQ";

        /*返回用户发过来的图片*/
//        String media_id = map.get("MediaId");
        return String.format(
                "<xml>" +
                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                "<CreateTime>%s</CreateTime>" +
                "<MsgType><![CDATA[image]]></MsgType>" +
                "<Image>" +
                "   <MediaId><![CDATA[%s]]></MediaId>" +
                "</Image>" +
                "</xml>",
                fromUserName,toUserName, getUtcTime(),media_id
        );
    }

    private static String getUtcTime() {
        Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");// 设置显示格式
        String nowTime = df.format(dt);
        long dd = (long) 0;
        try {
            dd = df.parse(nowTime).getTime();
        } catch (Exception e) {

        }
        return String.valueOf(dd);
    }

}
