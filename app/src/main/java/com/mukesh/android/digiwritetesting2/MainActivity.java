package com.mukesh.android.digiwritetesting2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button scandocumentbutton;
    Button opengallerybutton;
    int REQUEST_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scandocumentbutton = (Button)findViewById(R.id.scan_document_button);
        opengallerybutton = (Button)findViewById(R.id.open_gallery_button);
        scandocumentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        opengallerybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Intent.ACTION_PICK,
                  //      android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                //final int ACTIVITY_SELECT_IMAGE = 1234;
                //startActivityForResult(i, ACTIVITY_SELECT_IMAGE);


                int preference = ScanConstants.OPEN_MEDIA;
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

       /* switch(requestCode) {
            case 1234:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    Toast.makeText(this, filePath, Toast.LENGTH_LONG).show();

                     Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                     intent.putExtra("Imagefilepath",filePath);
                     startActivity(intent);

                } */
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            String stringuri = uri.toString();

                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                intent.putExtra("Imagefilepath",stringuri);
                startActivity(intent);


        }

    }
}
