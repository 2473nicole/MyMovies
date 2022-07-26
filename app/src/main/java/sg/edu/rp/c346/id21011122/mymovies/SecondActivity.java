package sg.edu.rp.c346.id21011122.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Movie> movieList;
    ArrayAdapter<Movie> adapter;
    String moduleCode;
    Button btnShowPG13;

    ArrayList<String> years;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " + "Show Movies");

        lv = (ListView) this.findViewById(R.id.lv);
        btnShowPG13 = (Button) this.findViewById(R.id.btnShowPG13);
        spinner = (Spinner) this.findViewById(R.id.spinnerYear);
        ImageView ivRating = rowView.findViewById(R.id.imageViewRating);
        DBHelper dbh = new DBHelper(this);
        movieList = dbh.getAllMovies();
        years = dbh.getYears();
        dbh.close();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movieList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("movie", movieList.get(position));
                startActivity(i);
            }
        });

        btnShowPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                movieList.clear();
                movieList.addAll(dbh.getAllMoviesByRating(13));
                adapter.notifyDataSetChanged();
            }
        });
        Intent i = getIntent();
        final Movie currentMovie = (Movie) i.getSerializableExtra("movie");

        if (currentMovie.getRating() == "G") {
            ivRating.setImageResource(R.drawable.rating_g);
        }
        else if (currentMovie.getRating() == "M18"){
            ivRating.setImageResource(R.drawable.rating_m18);
        }
        else if (currentMovie.getRating() == "NC16"){
            ivRating.setImageResource(R.drawable.rating_nc16);
        }
        else if (currentMovie.getRating() == "PG"){
            ivRating.setImageResource(R.drawable.rating_pg);
        }
        else if (currentMovie.getRating() == "PG13"){
            ivRating.setImageResource(R.drawable.rating_pg13);
        }
        else{
            ivRating.setImageResource(R.drawable.rating_r21);
        }
    }
}