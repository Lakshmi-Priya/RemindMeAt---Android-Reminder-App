package com.asu.remind.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.asu.remind.R;
import com.asu.remind.model.EventModelDB;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddEvent extends AppCompatActivity {

    private EditText event, date, time;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        event = (EditText)findViewById(R.id.event);
        time = (EditText)findViewById(R.id.time);
        date = (EditText)findViewById(R.id.date);
        button = (Button)findViewById(R.id.button);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(AddEvent.this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                       public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);

                timePickerDialog.show();
        }

      });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEvent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Save Entered data to Realm dataabse
                String temp1= event.getText().toString();
                String temp2= time.getText().toString();
                String temp3= date.getText().toString();


                Realm myRealm = Realm.getInstance(getBaseContext());
                myRealm.beginTransaction();

                // Create an object
                EventModelDB eventDetails = myRealm.createObject(EventModelDB.class);

                // Set its fields
                eventDetails.setEvent(temp1);
                eventDetails.setTime(temp2);
                eventDetails.setDate(temp3);

                myRealm.commitTransaction();


                // see the saved values in database
                RealmResults<EventModelDB> results1 =
                        myRealm.where(EventModelDB.class).findAll();

                for(EventModelDB c:results1) {
                    Log.d("results1", c.getEvent());
                }


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.go_to_home) {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
