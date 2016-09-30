package com.example.student.asynctask;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        //若要在button中執行的話寫下此兩行
        MyTask task = new MyTask();
        task.execute(5);
    }

    class MyTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {
            for (int i=0;i<params[0];i++)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return "OK";
        }

        @Override//回傳
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
           dialog = ProgressDialog.show(MainActivity.this,
                    "讀取中", "請等待5秒...",true);
        }

        @Override//取得結果
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);

        }
    }


}
