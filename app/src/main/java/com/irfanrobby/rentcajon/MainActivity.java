package com.irfanrobby.rentcajon;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.Math;

import static android.R.attr.button;
import static android.R.attr.end;
import static android.R.attr.left;
import static android.R.attr.startYear;
import static android.provider.Telephony.Mms.Part.FILENAME;
import static com.irfanrobby.rentcajon.R.id.editText;


public class MainActivity extends AppCompatActivity {

    Date date = new Date();
    Button tanggal1Button;
    Button tanggal2Button;
    Button simpan;
    TextView jmlhari;
    TextView jmlbiaya;
    EditText namaView;
    EditText kontakView;

    String endDateFromText;
    String startDateFromText;

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/RentCajon";

    private DatePicker dpResult;
    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentDateOnView();

        jmlhari = (TextView) findViewById(R.id.textView6);
        jmlbiaya = (TextView) findViewById(R.id.textView8);
        simpan = (Button)findViewById(R.id.button13);
        namaView = (EditText) findViewById(R.id.editText);
        kontakView = (EditText) findViewById(R.id.editText3);

        simpan.setEnabled(false);

        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "Rent Cajon");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }


        namaView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    simpan.setEnabled(false);
                } else {
                    simpan.setEnabled(true);
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        kontakView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    simpan.setEnabled(false);
                } else {
                    simpan.setEnabled(true);
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        jmlhari.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    simpan.setEnabled(false);
                } else {
                    simpan.setEnabled(true);
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        jmlbiaya.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    simpan.setEnabled(false);
                } else {
                    simpan.setEnabled(true);
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        tanggal2Button.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==15){
                    simpan.setEnabled(false);
                } else {
                    simpan.setEnabled(true);
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });


        simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BufferedWriter bw = null;
                FileWriter fw = null;

                try {

                    String data = (namaView.getText().toString() + "          " +
                            kontakView.getText().toString() + "          " +
                            tanggal1Button.getText().toString() + "     " +
                            tanggal2Button.getText().toString() + "     " +
                            jmlhari.getText().toString() + "     " +
                            jmlbiaya.getText().toString() + "\n");

                    File file = new File(Environment.getExternalStorageDirectory() +
                            File.separator + "Rent Cajon" + File.separator + "Penyewa.txt");

                    // if file doesnt exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    // true = append file
                    fw = new FileWriter(file.getAbsoluteFile(), true);
                    bw = new BufferedWriter(fw);

                    bw.write(data);

                    Toast.makeText(getBaseContext(),
                            "Berhasil Disimpan!",
                            Toast.LENGTH_SHORT).show();

                } catch (IOException e) {

                    e.printStackTrace();

                } finally {

                    try {

                        if (bw != null)
                            bw.close();

                        if (fw != null)
                            fw.close();

                    } catch (IOException ex) {

                        ex.printStackTrace();

                    }
                }
                namaView.setText("");
                kontakView.setText("");
                jmlhari.setText("");
                jmlbiaya.setText("");
                tanggal2Button.setText("ATUR TANGGAL...");

            }




        });

        tanggal1Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



            }
        });

        tanggal2Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }
        });
    }

    // display current date
    public void setCurrentDateOnView() {

        tanggal1Button = (Button)findViewById(R.id.button11);
        tanggal2Button = (Button)findViewById(R.id.button12);
        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);



        // set current date into textview
        tanggal1Button.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("/").append(month + 1).append("/")
                .append(year).append(" "));


        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tanggal2Button.setText(new StringBuilder().append(day)
                    .append("/").append(month + 1).append("/").append(year)
                    .append(" "));

            jmlhari = (TextView) findViewById(R.id.textView6);
            jmlbiaya = (TextView) findViewById(R.id.textView8);

            startDateFromText = ((TextView) findViewById(R.id.button11)).getText().toString();
            endDateFromText = ((TextView) findViewById(R.id.button12)).getText().toString();

            try {

                //Dates to compare
                String CurrentDate =  startDateFromText;
                String FinalDate =  endDateFromText;

                Date date1;
                Date date2;

                SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");

                //Setting dates
                date1 = dates.parse(CurrentDate);
                date2 = dates.parse(FinalDate);

                //Comparing dates
                long difference = Math.abs(date1.getTime() - date2.getTime());
                long differenceDates = difference / (24 * 60 * 60 * 1000);

                //Convert long to String
                String dayDifference = Long.toString(differenceDates);
                jmlhari.setText(dayDifference);
                long l = Long.parseLong(dayDifference);
                jmlbiaya.setText("Rp " + l * 5000);


                Log.e("HERE","HERE: " + dayDifference);

            } catch (Exception exception) {
                Log.e("DIDN'T WORK", "exception " + exception);
            }



        }
    };


}
