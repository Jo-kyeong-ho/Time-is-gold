package com.timeisgold.toolbar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx
        .viewpager
        .widget
        .PagerAdapter;
public class Adapter extends PagerAdapter {
    private int[] images = {
            R.drawable.main,
            R.drawable.toolbar,
            R.drawable.timer,
            R.drawable.takepicture,
            R.drawable.timergo,
            R.drawable.compare,
            R.drawable.setting,
            R.drawable.book
    };
    private LayoutInflater inflater;
    private Context context;
    public Adapter(Context context) {
        this.context = context;
    }
    @Override public int getCount() {
        return images.length;
    }
    @Override public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout)object);
    }
    @Override public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.slider, container, false);
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
        TextView textView = (TextView)v.findViewById(R.id.textView);
        imageView.setImageResource(images[position]);
        String text = "사용법 " + (
                position + 1
        );
        textView.setText(text);
        container.addView(v);
        return v;
    }
    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}