package com.example.nz160.javaconnection;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button connect=(Button)findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        url = "http://192.168.2.79:8080/AndroidConnect/ServletConnect";
                        HttpParams httpParameters = new BasicHttpParams();
                        // Set the timeout in milliseconds until a connection is established.
                        int timeoutConnection = 62000;
                        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                        DefaultHttpClient client = new DefaultHttpClient(httpParameters);
                        HttpPost post = new HttpPost(url);
                        try {
                            HttpResponse response = client.execute(post);
                            System.out.println("RESPONSE IS "+response.toString());

                            HttpEntity rp = response.getEntity();
                            System.out.println("RESPONSE IS " + rp.toString());

                            String origresponseText = EntityUtils.toString(rp);

                            System.out.println("RESPONSE IS " + origresponseText);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("RESPONSE IS " + e.toString() + " url is " + url);
                        } catch (Exception e) {
                            System.out.println("RESPONSE IS " + e.toString() + " url is " + url);
                        }
                    }
                });
                t.start();
            }
        });
    }



}
