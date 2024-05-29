package com.example.medico;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class insertdata extends AppCompatActivity {
    Button btninsert, btnhomepage;
    EditText medname, meddate, medtime;
    TextView displayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertdata);

        medname = findViewById(R.id.editText);
        meddate = findViewById(R.id.editText1);
        medtime = findViewById(R.id.editText2);
        btninsert = findViewById(R.id.button);
        btnhomepage = findViewById(R.id.buttonhome);
        displayData = findViewById(R.id.displayData);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medicinename = medname.getText().toString();
                String medicinedate = meddate.getText().toString();
                String medicinetime = medtime.getText().toString();

                MedicineHelper helper = new MedicineHelper(getBaseContext(),
                        MedicineHelper.DATABASE_NAME, null, 1);
                SQLiteDatabase database = helper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("name",medicinename);
                cv.put("date",medicinedate);
                cv.put("time",medicinetime);
                database.insert("Medicine",null,cv);

                Toast.makeText(getBaseContext(),"Record inserted successfully",Toast.LENGTH_LONG).show();
                displayInsertedData();
            }
        });

        btnhomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(insertdata.this,MainActivity.class);
                startActivity(home);
            }
        });
    }

    private void displayInsertedData() {
        MedicineHelper helper = new MedicineHelper(getBaseContext(),
                MedicineHelper.DATABASE_NAME, null, 1);
        SQLiteDatabase database = helper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM Medicine", null);
        if (cursor.moveToFirst()) {
            StringBuilder data = new StringBuilder();
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                data.append("Medicine Name: ").append(name).append("\n");
                data.append("Medicine Date: ").append(date).append("\n");
                data.append("Medicine Time: ").append(time).append("\n\n");
            } while (cursor.moveToNext());
            displayData.setText(data.toString());
        } else {
            displayData.setText("No data found.");
        }

        cursor.close();
        database.close();
    }
}
