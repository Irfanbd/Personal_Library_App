package com.example.boipoks;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText name,WriterName,genre;
Button insert,update,delete,view;
DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        WriterName=findViewById(R.id.WriterName);
        genre=findViewById(R.id.genre);


        insert=findViewById(R.id.btnInsert);
        delete=findViewById(R.id.btnDelete);
        update=findViewById(R.id.btnUpdate);
        view=findViewById(R.id.btnView);
        DB=new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String nameTXT=name.getText().toString();
                String WriterNameTXT=WriterName.getText().toString();
                String genreTXT=genre.getText().toString();

                Boolean checkinsertdata=DB.insertuserdata(nameTXT,WriterNameTXT,genreTXT);
                if (checkinsertdata==true)
                    Toast.makeText(MainActivity.this,"New Book Inserted",Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this,"New Book Not Found",Toast.LENGTH_SHORT).show();
            }
        });



        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String nameTXT=name.getText().toString();
                String WriterNameTXT=WriterName.getText().toString();
                String genreTXT=genre.getText().toString();

                Boolean checkupdatedata=DB.Updateuserdata(nameTXT,WriterNameTXT,genreTXT);
                if (checkupdatedata==true)
                    Toast.makeText(MainActivity.this,"Book Updated",Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this,"Book Not Updated",Toast.LENGTH_SHORT).show();
            }
        });


        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String nameTXT=name.getText().toString();


                Boolean checkdeletedata=DB.deletedata(nameTXT);
                if (checkdeletedata==true)
                    Toast.makeText(MainActivity.this,"Book Delete",Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this,"Book Not Deleted",Toast.LENGTH_SHORT).show();
            }
        });

view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Cursor res=DB.getedata();
        if (res.getCount()==0)
        {
            Toast.makeText(MainActivity.this,"Book Does Not Found",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext())
        {
            buffer.append("Name :"+res.getString(0)+"\n");
            buffer.append("Writer Name:"+res.getString(1)+"\n");
            buffer.append("Book genre:"+res.getString(2)+"\n\n");
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Book List");
        builder.setMessage(buffer.toString());
        builder.show();
    }
});
    }
}