package sg.edu.rp.c346.id19014750.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    RadioGroup rgStars;
    RadioButton r1, r2, r3, r4, r5;
    Button btnUpdate, btnDel, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        r1 = findViewById(R.id.rBtnStar1);
        r2 = findViewById(R.id.rBtnStar2);
        r3 = findViewById(R.id.rBtnStar3);
        r4 = findViewById(R.id.rBtnStar4);
        r5 = findViewById(R.id.rBtnStar5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDel = findViewById(R.id.btnDel);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(String.valueOf(data.get_id()));
        etID.setEnabled(false);
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));

        int stars = data.getStars();

        if(stars == 1){
            r1.setChecked(true);
        } else if(stars == 2){
            r2.setChecked(true);
        } else if(stars == 3){
            r3.setChecked(true);
        } else if(stars == 4){
            r4.setChecked(true);
        } else {
            r5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.set_id(Integer.parseInt(etID.getText().toString()));
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

                int stars;

                if(rgStars.getCheckedRadioButtonId() == R.id.rBtnStar1){
                    stars = 1;
                } else if(rgStars.getCheckedRadioButtonId() == R.id.rBtnStar2){
                    stars = 2;
                } else if(rgStars.getCheckedRadioButtonId() == R.id.rBtnStar3){
                    stars = 3;
                } else if(rgStars.getCheckedRadioButtonId() == R.id.rBtnStar4){
                    stars = 4;
                } else {
                    stars = 5;
                }

                data.setStars(stars);
                dbh.updateSong(data);
                dbh.close();

                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.get_id());

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}