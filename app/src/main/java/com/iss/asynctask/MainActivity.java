package com.iss.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
    implements MyAsyncTask.ICallback {

    ImageView imgView = null;
    ProgressBar progBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //调用初始化UI方法
        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //MyAsyncTask 用下载图像，保存图片
        String url = "https://images.pexels.com/photos/1413683/pexels-photo-1413683.jpeg?cs=srgb&dl=4k-wallpaper-daylight-environment-1413683.jpg&fm=jpg";
        String saveToPath = getFilesDir() + "/relax.jpg";
        new MyAsyncTask(this).execute(url, saveToPath);
    }

    protected void initUI() {
        //获取imageView按钮
        imgView = findViewById(R.id.imageView);
        progBar = findViewById(R.id.progressBar);

        //设置imageView图像
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.camp);
        if (bitmap != null)
            if (imgView != null)
                imgView.setImageBitmap(bitmap);
    }

    //实现接口给的方法于service通信，更新进度栏
    public void onAsyncTaskProgress(int progress) {
        if (progBar != null)
            progBar.setProgress(Math.round(progress));
    }

    //显示下载的位图并隐藏进度栏
    public void onAsyncTaskDone(Bitmap bitmap) {
        if (imgView != null)
            imgView.setImageBitmap(bitmap);

        if (progBar != null)
            progBar.setVisibility(View.GONE);
    }
}
