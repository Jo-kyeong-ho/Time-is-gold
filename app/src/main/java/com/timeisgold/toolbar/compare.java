package com.timeisgold.toolbar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx
        .fragment
        .app
        .FragmentActivity;
import org
        .opencv
        .core
        .Core;
import org
        .opencv
        .core
        .Mat;
import org
        .opencv
        .core
        .MatOfFloat;
import org
        .opencv
        .core
        .MatOfInt;
import org
        .opencv
        .imgcodecs
        .Imgcodecs;
import org
        .opencv
        .imgproc
        .Imgproc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org
        .opencv
        .imgproc
        .Imgproc
        .COLOR_RGB2GRAY;
public class Compare extends FragmentActivity implements SurfaceHolder.Callback {
    @SuppressWarnings("deprecation")
    Camera camera;
    ImageView image;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    ImageButton button;
    String str;
    int tmptime;
    double money;
    int count = 0;
    @SuppressWarnings("deprecation")
    Camera.PictureCallback jpegCallback;
    private static int compareHistogram(String filename1, String filename2) {
        int retVal = 0;
        long startTime = System.currentTimeMillis();
        System.loadLibrary("opencv_java3");
        Mat img1 = Imgcodecs.imread(filename1, Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Mat img2 = Imgcodecs.imread(filename2, Imgcodecs.CV_LOAD_IMAGE_COLOR);
        if (img1.empty()) {}
        Mat hsvImg1 = new Mat();
        Mat hsvImg2 = new Mat();
        Imgproc.cvtColor(img1, hsvImg1, COLOR_RGB2GRAY);
        Imgproc.cvtColor(img2, hsvImg2, COLOR_RGB2GRAY);
        List < Mat > listImg1 = new ArrayList<Mat>();
        List < Mat > listImg2 = new ArrayList<Mat>();
        listImg1.add(hsvImg1);
        listImg2.add(hsvImg2);
        MatOfFloat ranges = new MatOfFloat(0, 255);
        MatOfInt histSize = new MatOfInt(50);
        MatOfInt channels = new MatOfInt(0);
        Mat histImg1 = new Mat();
        Mat histImg2 = new Mat();
        Imgproc.calcHist(listImg1, channels, new Mat(), histImg1, histSize, ranges);
        Imgproc.calcHist(listImg2, channels, new Mat(), histImg2, histSize, ranges);
        Core.normalize(histImg1, histImg1, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Core.normalize(histImg2, histImg2, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        double result0,
                result1,
                result2,
                result3;
        result0 = Imgproc.compareHist(histImg1, histImg2, 0);
        result1 = Imgproc.compareHist(histImg1, histImg2, 1);
        result2 = Imgproc.compareHist(histImg1, histImg2, 2);
        result3 = Imgproc.compareHist(histImg1, histImg2, 3);
        Log.d("confirm", "Method [0] " + result0);
        Log.d("confirm", "Method [1] " + result1);
        Log.d("confirm", "Method [2] " + result2);
        Log.d("confirm", "Method [3] " + result3);
        int count = 0;
        if (result0 > 0.7)
            count ++;

        if (result1 < 4)
            count ++;

        if (result2 > 5)
            count ++;

        if (result3 < 0.4)
            count ++;

        if (count >= 3)
            retVal = 1;

        long estimatedTime = System.currentTimeMillis() - startTime;
        return retVal;
    }
    @Override public void onBackPressed() {
        return;
    }
    @SuppressWarnings("deprecation")
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count = 0;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_compare);
        image = findViewById(R.id.compare);
        Intent intent = getIntent();
        tmptime = intent.getIntExtra("time", 1);
        money = intent.getDoubleExtra("money", 0.0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/compare1.png", options);
        int width = 120; // 축소시킬 너비
        int height = 160; // 축소시킬 높이
        float bmpWidth = bitmap.getWidth();
        float bmpHeight = bitmap.getHeight();
        if (bmpWidth > width) { // 원하는 너비보다 클 경우의 설정
            float mWidth = bmpWidth / 100;
            float scale = width / mWidth;
            bmpWidth *= (scale / 100);
            bmpHeight *= (scale / 100);
        } else if (bmpHeight > height) { // 원하는 높이보다 클 경우의 설정
            float mHeight = bmpHeight / 100;
            float scale = height / mHeight;
            bmpWidth *= (scale / 100);
            bmpHeight *= (scale / 100);
        }
        Bitmap resizedBmp = Bitmap.createScaledBitmap(bitmap, (int)bmpWidth, (int)bmpHeight, true);
        Matrix matrix = new Matrix();
        matrix.preRotate(90);
        Bitmap adjustedBitmap = Bitmap.createBitmap(resizedBmp, 0, 0, resizedBmp.getWidth(), resizedBmp.getHeight(), matrix, true);
        Bitmap resized = Bitmap.createScaledBitmap(adjustedBitmap, 100, 100, true);
        image.setImageBitmap(resized);
        button = findViewById(R.id.button2);
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
                    File file = new File("/sdcard/compare2.png");
                    if (file.exists() == true) {
                        file.delete();
                    }
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    str = String.format("/sdcard/compare2.png", System.currentTimeMillis());
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
                File root = Environment.getExternalStorageDirectory();
                File file1 = new File(root, "compare1.png");
                File file2 = new File(root, "compare2.png");
                String filename1 = file1.getAbsolutePath();
                String filename2 = file2.getAbsolutePath();
                int ret;
                ret = compareHistogram(filename1, filename2);
                if (ret > 0) {
                    Intent intent2 = new Intent(Compare.this, TimerValue.class);
                    intent2.putExtra("time", tmptime);
                    intent2.putExtra("money", money);
                    startActivity(intent2);
                } else {
                    count ++;
                    if (count == 5) {
                        Toast.makeText(getApplicationContext(), "모든 기회를 소비하였습니다.", Toast.LENGTH_LONG).show();
                        Intent failintent = new Intent(Compare.this, TimerValue.class);
                        failintent.putExtra("time", "-1");
                        startActivity(failintent);
                    }
                    Toast.makeText(getApplicationContext(), (5 - count) + "개의 기회가 남았습니다.", Toast.LENGTH_LONG).show();
                }
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