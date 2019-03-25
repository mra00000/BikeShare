package com.fpt.prm.bikeshare.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.design.shape.RoundedCornerTreatment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fpt.prm.bikeshare.Controller.Activity.DetailActivity;
import com.fpt.prm.bikeshare.Entity.Post;
import com.fpt.prm.bikeshare.Helper.StringHelper;
import com.fpt.prm.bikeshare.Helper.TimeHelper;
import com.fpt.prm.bikeshare.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class ListPostAdapter extends BaseAdapter {
    private ImageView imgView;
    private Activity activity;
    private int layout;
    private List<Post> list;

    public ListPostAdapter(Activity activity, int layout, List<Post> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
    }

    public ListPostAdapter() {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = activity.getLayoutInflater().inflate(layout, null);
        }
        TextView txtTitle = view.findViewById(R.id.txtTitlePost);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        TextView txtTime = view.findViewById(R.id.txtTime);
        TextView txtDescription = view.findViewById(R.id.txtDescription);
        final Post p = list.get(position);

        txtTitle.setText(p.getTitle());
        txtDescription.setText(StringHelper.shorten(p.getDescription()));
        txtPrice.setText("Price: $"+String.valueOf(p.getPrice()));
        txtTime.setText(TimeHelper.getTime(p.getCreatedTime()));


        RelativeLayout relativeLayout = view.findViewById(R.id.relativeClickable);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Your code here
                Intent intent = new Intent(activity.getApplicationContext(), DetailActivity.class);
                intent.putExtra("post",p);
                activity.startActivityForResult(intent, 100);


            }
        });
        String image = p.getImages();
        List<String> images = StringHelper.toList(image, "|");
        imgView = view.findViewById(R.id.imageView);

//        new GetImageFromURL(img).execute(image.get(0));
        final int radius = 5;
        final int margin = 5;
//        final Transformation transformation = new RoundedC;

        if(images.size()>0) {
//            images.remove(0);
            Picasso.get().load(images.get(0)).resize(190, 150).transform(new RoundedTransformation(15, 0)).into(imgView);
        }

        return view;

    }

    public class RoundedTransformation implements com.squareup.picasso.Transformation {
        private final int radius;
        private final int margin;

        public RoundedTransformation(final int radius, final int margin) {
            this.radius = radius;
            this.margin = margin;
        }

        @Override
        public Bitmap transform(final Bitmap source) {
            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP));

            Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin,
                    source.getHeight() - margin), radius, radius, paint);

            if (source != output) {
                source.recycle();
            }
            return output;
        }

        @Override
        public String key() {
            return "rounded(r=" + radius + ", m=" + margin + ")";
        }
    }
}
