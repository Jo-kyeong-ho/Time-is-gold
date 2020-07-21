package com.timeisgold.toolbar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class TakePicture extends Activity implements SurfaceHolder.Callback {
    @SuppressWarnings("deprecation")
    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    ImageButton button;
    String str;
    String job;
    int time;
    @SuppressWarnings("deprecation")
    Camera.PictureCallback jpegCallback;
    @SuppressWarnings("deprecation")
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_take_picture);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, jpegCallback);
            }
        });
        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        jpegCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                try {
                    File file = new File("/sdcard/compare1.png");
                    if (file.exists() == true) {
                        file.delete();
                    }
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    str = String.format("/sdcard/compare1.png", System.currentTimeMillis());
                    String imageSaveUri = MediaStore
                            .Images
                            .Media
                            .insertImage(getContentResolver(), bitmap, "사진 저장", "저장되었습니다");
                    Uri uri = Uri.parse(imageSaveUri);
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                    outStream = new FileOutputStream(str);
                    outStream.write(data);
                    outStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {} refreshCamera();
                Intent intent2 = new Intent(TakePicture.this, ShowPicture.class);
                startActivity(intent2);
            }
        };
    }
    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        try {
            camera.stopPreview();
        } catch (Exception e) {}
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {}
    }
    @Override protected void onDestroy() {
        super.onDestroy();
    }
    @SuppressWarnings("deprecation")
    @Override public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        camera.stopPreview();
        Camera.Parameters param = camera.getParameters();
        camera.setDisplayOrientation(90);
        camera.setParameters(param);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
    }
    @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        refreshCamera();
    }
    @Override public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }
}