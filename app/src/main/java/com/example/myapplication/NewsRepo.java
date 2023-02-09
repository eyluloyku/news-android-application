package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewsRepo {
    public void getAllNews(ExecutorService srv, Handler uiHandler)
    {
        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getall");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }
                JSONObject curr = new JSONObject(buffer.toString());
                JSONArray arr = curr.getJSONArray("items");
                List<News> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    News news = new News(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("categoryName"),
                            current.getString("image"),
                            current.getString("date").substring(0,10));
                    data.add(news);

                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
    public void getNewsByCategoryId(ExecutorService srv, Handler uiHandler, int id)
    {
        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getbycategoryid/" + String.valueOf(id));
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }
                JSONObject curr = new JSONObject(buffer.toString());
                JSONArray arr = curr.getJSONArray("items");
                List<News> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);
                    News news = new News(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("categoryName"),
                            current.getString("image"),
                            current.getString("date").substring(0,10));
                    data.add(news);

                }
                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
    public void getNewsById(ExecutorService srv, Handler uiHandler, int id)
    {
        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getnewsbyid/" + String.valueOf(id));
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }
                JSONObject curr = new JSONObject(buffer.toString());
                JSONArray arr = curr.getJSONArray("items");
                JSONObject current = arr.getJSONObject(0);
                News news = new News(current.getInt("id"),
                        current.getString("title"),
                        current.getString("text"),
                        current.getString("categoryName"),
                        current.getString("image"),
                        current.getString("date").substring(0,10));


                Message msg = new Message();
                msg.obj = news;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
    public void GetAllNewsCategories(ExecutorService srv, Handler uiHandler)
    {
        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getallnewscategories");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                JSONObject curr = new JSONObject(buffer.toString());
                JSONArray arr = curr.getJSONArray("items");
                List<NewsCategories> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    NewsCategories ctg = new NewsCategories(current.getInt("id"),
                            current.getString("name"));
                    data.add(ctg);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void GetCommentsByNewsId(ExecutorService srv, Handler uiHandler, int news_id)
    {
        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getcommentsbynewsid/" + String.valueOf(news_id));
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }
                JSONObject curr = new JSONObject(buffer.toString());
                JSONArray arr = curr.getJSONArray("items");
                List<Comments> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    Comments com = new Comments(current.getInt("id"),
                            current.getInt("news_id"),
                            current.getString("name"),
                            current.getString("text"));
                    data.add(com);

                }
                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
    public void downloadImage(ExecutorService srv, Handler uiHandler,String path)
    {
        srv.execute(()->{
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                Bitmap bitmap =  BitmapFactory.decodeStream(conn.getInputStream());

                Message msg = new Message();
                msg.obj = bitmap;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
    public void PostComment(ExecutorService srv, Handler uiHandler,String name, String text, int news_id)
    {

        srv.execute(()->{

            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/savecomment");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");


                JSONObject outputData  = new JSONObject();

                outputData.put("name",name);
                outputData.put("text",text);
                outputData.put("news_id", news_id);

                BufferedOutputStream writer =
                        new BufferedOutputStream(conn.getOutputStream());


                writer.write(outputData.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();

                String line ="";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject retVal = new JSONObject(buffer.toString());

                conn.disconnect();

                int srvMsg = retVal.getInt("serviceMessageCode");


                Message msg = new Message();
                msg.obj = srvMsg;

                uiHandler.sendMessage(msg);

                conn.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });



    }

}
