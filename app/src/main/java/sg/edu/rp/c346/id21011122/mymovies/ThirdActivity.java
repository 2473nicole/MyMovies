package sg.edu.rp.c346.id21011122.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    EditText etID, etMovieTitle, etGenre, etYear;
    Button btnCancel, btnUpdate, btnDelete;
    Spinner spinnerRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " + "Modify Movie");

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etMovieTitle = (EditText) findViewById(R.id.etMovieTitle);
        etGenre = (EditText) findViewById(R.id.etGenre);
        etYear = (EditText) findViewById(R.id.etYear);

        Intent i = getIntent();
        final Movie currentMovie = (Movie) i.getSerializableExtra("movie");

        etID.setText(currentMovie.getId()+"");
        etMovieTitle.setText(currentMovie.getTitle());
        etGenre.setText(currentMovie.getGenre());
        etYear.setText(currentMovie.getYear()+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentMovie.setTitle(etMovieTitle.getText().toString().trim());
                currentMovie.setGenre(etGenre.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentMovie.setYear(year);

                currentMovie.setRating(Integer.parseInt(spinnerRating.getText().toString()));
                int result = dbh.updateMovie(currentMovie);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                int result = dbh.deleteMovie(currentMovie.getId());
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Song deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
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
