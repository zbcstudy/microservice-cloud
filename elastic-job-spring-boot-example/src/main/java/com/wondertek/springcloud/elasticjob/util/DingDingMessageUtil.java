package com.wondertek.springcloud.elasticjob.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 钉钉机器人发送消息
 * https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.karFPe&treeId=257&articleId=105735&docType=1
 * Created by win on 2019/4/11.
 */
public class DingDingMessageUtil {

    public static String access_token = "填写你自己申请的token";

    public static void sendTextMessage(String msg) {

        try {
            //生成消息对象
            Message message = new Message();
            message.setMsgtype("text");
            message.setText(new MessageInfo(msg));

            URL url = new URL("https://oapi.dingtalk.com/robot/send?access_token=" + access_token);

            //建立http连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            String jsonMessage = JsonUtils.toJson(message);
            byte[] bytes = jsonMessage.getBytes();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            System.out.println(httpURLConnection.getResponseCode());
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            System.out.println(new String(data));
        } catch (Exception e) {

        }
    }
}

class Message {
    private String msgtype;
    private MessageInfo text;
    public String getMsgtype() {
        return msgtype;
    }
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
    public MessageInfo getText() {
        return text;
    }
    public void setText(MessageInfo text) {
        this.text = text;
    }
}

class MessageInfo {
    private String content;

    public MessageInfo(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
