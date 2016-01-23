package io.hapi.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final int REQUEST_TAKE_PHOTO = 420;
  private String mCurrentPhotoPath;

  private RecyclerView mRecyclerView;
  private RecyclerView.LayoutManager mLayoutManager;
  private RecyclerView.Adapter mRecyclerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Uri imgUri = Uri.parse("android.resource://io.hapi.android/" + R.drawable.nic_cage);
    List<Uri> images = new ArrayList<Uri>();

    for (int i = 0; i < 4; i++) {
      images.add(imgUri);
    }

    mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
    mRecyclerView.setHasFixedSize(true);
    mLayoutManager = new GridLayoutManager(this, 3);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerAdapter = new GridImageAdapter(images);
    mRecyclerView.setAdapter(mRecyclerAdapter);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(view -> dispatchTakePictureIntent());
  }

  private void dispatchTakePictureIntent() {
    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (cameraIntent.resolveActivity(getPackageManager()) != null) {
      File photoFile = null;
      try {
        photoFile = createImageFile();
      } catch (IOException ex) {
        App.toast("Failed to read photo");
      }
      if (photoFile != null) {
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO);
      }
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
      Uri image = Uri.parse(mCurrentPhotoPath);
      IndicoApi.getEmotions(image)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(emotions -> {

            // Do something with the emotions
          }, error -> App.toast("Network error"));
    }
  }

  private File createImageFile() throws IOException {
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" + timeStamp + "_";
    File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(imageFileName, ".jpg", storageDir);
    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
    return image;
  }
}
