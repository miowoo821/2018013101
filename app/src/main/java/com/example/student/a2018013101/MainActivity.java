package com.example.student.a2018013101;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageView);
    }

    public void click1(View v){//照相到img
        Intent it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//呼叫相機唷
        startActivityForResult(it,65);//要抓東西回來的intent要用這個(參數1：INTENT，參數2；識別碼)
        //接下來新增傳回參數時要幹嘛(寫新方法)
    }
    public void click2(View v){//照相存起來再讀出來到img
        Intent it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//呼叫相機唷

        File f = new File(getExternalFilesDir("PHOTO"), "myphoto.jpg");//給一個路徑喔
        it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));//把照片放到這個路徑喔
        startActivityForResult(it,66);//要抓東西回來的intent要用這個(參數1：INTENT，參數2；識別碼)
        //接下來新增傳回參數時要幹嘛(寫新方法)



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==65){//處理上面那個識別碼

            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();//新增一個Bundle，裝?
                Bitmap bmp = (Bitmap) bundle.get("data");//以Bundle抓data放入bmp
                img.setImageBitmap(bmp);
            }
        }
        if (requestCode==66){//處理照相存起來讀書來的那個識別碼(click2)
            if (resultCode == RESULT_OK)
            {
                File f = new File(getExternalFilesDir("PHOTO"), "myphoto.jpg");
                Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());//這個方法會塞爆記憶體導致當機，所以不好(解析bmp太大了)
                img.setImageBitmap(bmp);
            }
        }

    }
}
