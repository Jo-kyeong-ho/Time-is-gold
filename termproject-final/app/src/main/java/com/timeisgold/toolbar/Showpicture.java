package com.timeisgold.toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

public class Showpicture extends FragmentActivity {

    Button button;
    String job;
    int time;
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.show_picture);

        Intent intent = getIntent();
        String photoPath = intent.getStringExtra("strParamName");
        time = intent.getIntExtra("time",0);
        job = intent.getStringExtra("job");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        final Bitmap bmp = BitmapFactory.decodeFile(photoPath, options);

        Matrix matrix = new Matrix();
        matrix.preRotate(90);
        Bitmap adjustedBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
                bmp.getHeight(), matrix, true);
        ImageView img = (ImageView) findViewById(R.id.imageView1);
        img.setImageBitmap(adjustedBitmap);

        button = findViewById(R.id.nexto);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Showpicture.this, TimerValue.class);
                intent2.putExtra("job",job);
                intent2.putExtra("time",time);
                startActivity(intent2);
            }
        });
    }
}
