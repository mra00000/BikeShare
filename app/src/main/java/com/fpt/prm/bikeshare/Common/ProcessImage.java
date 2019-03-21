package com.fpt.prm.bikeshare.Common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ProcessImage {
    private static Bitmap bitmap;

    public String ConvertImage2Base64(String filePath){

        Bitmap selectedImage =  BitmapFactory.decodeFile(filePath);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String base64 =Base64.encodeToString(byteArray, 0);
        return base64;
    }

    public static class GetImageFromURL extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public GetImageFromURL(ImageView imageView){
            this.imageView = imageView;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urlImage = url[0];
            bitmap = null;
            try{
                InputStream srt = new URL(urlImage).openStream();
                bitmap = BitmapFactory.decodeStream(srt);
            }catch (Exception e){
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}
