package com.example.admin.datastoragedemo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Displays images from an SD card.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnSelectImage).setOnClickListener(this);
    }
    /* Choose an image from Gallery */
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    Log.i(TAG, "Image Path : " + path);
                    // Set the image in ImageView
                    ((ImageView) findViewById(R.id.imgView)).setImageURI(selectedImageUri);
                }
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    @Override
    public void onClick(View v) {
      openImageChooser();
    }
}

















  /**
     * Cursor used to access the results from querying for images on the SD card.
     */
    /*  private Cursor cursor;
    /*
   * Column index for the Thumbnails Image IDs.
   */
   /* private int columnIndex;*/

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up an array of the Thumbnail Image ID column we want
        String[] projection = { MediaStore.Images.Thumbnails._ID};
        //create the cursor pointing to the SD Card
        cursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection ,
                null,null,MediaStore.Images.Thumbnails.IMAGE_ID);
        // Get the column index of the Thumbnails Image ID
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);

        GridView gridView= findViewById(R.id.sdcardgallery);
        gridView.setAdapter(new ImageAdapter(this));

        //setting up a click listener
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the data location of the image
                String projection[] = {MediaStore.Images.Media.DATA};
                cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projection,null,null,null);
                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToPosition(position);
                //Get Image Filename
                String imagePath = cursor.getString(columnIndex);

            }
        });
    }*/
 /* private class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context localContext) {
            context = localContext;
        }

        @Override
        public int getCount() {
            return cursor.getCount();
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
            ImageView imageView;
            if(convertView == null){
                imageView = new ImageView(context);
                // Move cursor to current position
                cursor.moveToPosition(position);
                // Get the current value for the requested column
                int imageId = cursor.getInt(columnIndex);
                // Set the content of the image based on the provided URI
                imageView.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,""+imageId));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(8, 8, 8, 8);
                imageView.setLayoutParams(new GridView.LayoutParams(100,100));
            }
            else{
                imageView = (ImageView) convertView;
            }
            return imageView;
        }
    }

}*/
