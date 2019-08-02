package com.cc.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cc.util.DateChange;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MeetingApi {


    /**
     * 获取sessionId
     * @param url
     * @return
     */
    public static String getRequest(String url) {
        String response = "";
        HttpClient httpClient = new HttpClient();
        HttpMethod method = new GetMethod(url);
        try {
            httpClient.executeMethod(method);
            response = method.getResponseBodyAsString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 预订视频会议
     * @param url  url后面拼接sessionID   http://10.18.88.54:8080/syvcm/meeting/add/1?sessionId=CB3C69B9C10E5568C344AEB76CB0689B
     * @param json
     * @return
     */
    public static String HttpPostWithJson(String url, String json) {
        String returnValue = "这是默认返回值，接口调用失败";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try{
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json,"utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            //第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost,responseHandler); //调接口获取返回值时，必须用此方法

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        System.out.println(returnValue);
        return returnValue;
    }

    public static void main(String[] args) {
//        String request = MeetingApi.getRequest("http://10.18.88.54:8080/syvcm/api/getsession");
//        System.out.println(request);
        Map<String,Object> map = new HashMap<>();
        try {

            map.put("name", "XXXX31333");
            String s = "2019-07-04 17:00";
            String s1 = DateChange.changeTime(s, "yyyy-MM-dd HH:mm");
            String time = s1.replace(" ", "T") + ":00.000Z";
            map.put("startTime", time);
            map.put("durationSeconds", 30);
            map.put("type", 0);//会议类型  0  视频会议
            //map.put("password", "1");
            map.put("description", "1");
            map.put("label", "1");
            map.put("repetition", 0);//重复会议
//            JSONObject json = JSONObject.fromObject(map);
//            HttpPostWithJson("http://10.18.88.54:8080/syvcm/meeting/add/1?sessionId=CB3C69B9C10E5568C344AEB76CB0689B",json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        addMeeting(map);
//        String session1 = getSession1();
//        System.out.println(session1);
//        String session = getSession();
//        System.out.println(session);

    }

    public  static String getSession(){
        String request = MeetingApi.getRequest("http://10.18.88.54:8080/syvcm/api/getsession");
        if(request.indexOf("data")<0){
            throw new RuntimeException("获取Session异常...");
        }
        JSONObject jo = JSON.parseObject(request);
        String session = jo.getString("data");
        if(session == null || session.length() == 0){
            throw new RuntimeException("获取Session异常...");
        }
        return  session;
    }

    public static String addMeeting(Map map){
        String session = getSession1();
        String url = "http://10.18.88.54:8080/syvcm/meeting/add/1?sessionId=" + session;
        String paramStr = JSON.toJSONString(map);
        System.out.println(map);
        String result = HttpPostWithJson(url, paramStr);
       /* if(result.indexOf("status") < 0){
            throw new RuntimeException("预定会议室异常...");
        }
        JSONObject jo = JSON.parseObject(result);
        if(200 != jo.getInteger("status")){
            throw new RuntimeException(result);
        }
        JSONArray data = jo.getJSONArray("data");
        if(data == null || data.size() < 2){
            throw new RuntimeException(result);
        }
        String mId = data.getString(1);*/
        return result;
    }

    public  static String getSession1(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("username","YWRtaW4=");
        map.put("password","YWRtaW4=");
        String s = JSON.toJSONString(map);
        String request = HttpPostWithJson("http://10.18.88.54:8080/syvcm/loggin", s);
        if(request.indexOf("data")<0){
            throw new RuntimeException("获取Session异常...");
        }
        JSONObject jo = JSON.parseObject(request);
        String session = jo.getString("data");
        if(session == null || session.length() == 0){
            throw new RuntimeException("获取Session异常...");
        }
        return session;
    }



}
