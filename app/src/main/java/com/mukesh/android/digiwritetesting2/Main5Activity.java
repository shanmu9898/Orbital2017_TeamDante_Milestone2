package com.mukesh.android.digiwritetesting2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;





public class Main5Activity extends AppCompatActivity {
    EditText textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        textView = (EditText) findViewById(R.id.textView);
        String string = getIntent().getStringExtra("string");
        textView.setText(string);

    }
    public void saveButton(View view) {
        //reference to EditText
        EditText et = (EditText) findViewById(R.id.textView);
        //create document object
        Document doc = new Document();
        //output file path
        String outpath = Environment.getExternalStorageDirectory() + "/mypdf.pdf";
        try {
            //create pdf writer instance
            PdfWriter.getInstance(doc, new FileOutputStream(outpath));
            //open the document for writing
            doc.open();
            //add paragraph to the document
            doc.add(new Paragraph(et.getText().toString()));
            //close the document
            doc.close();
            Toast.makeText(this, "PDF Created", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void emailPDF(View view){
        String filename="mypdf.pdf";
        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
        Uri path = Uri.fromFile(filelocation);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // set the type to 'email'
        emailIntent .setType("vnd.android.cursor.dir/email");
        String to[] = {""};
        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
        // the attachment
        emailIntent .putExtra(Intent.EXTRA_STREAM, path);
        // the mail subject
        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");
        startActivity(Intent.createChooser(emailIntent , "Send email..."));

    }


}
