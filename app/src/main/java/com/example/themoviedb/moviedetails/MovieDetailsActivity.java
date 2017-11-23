package com.example.themoviedb.moviedetails;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.themoviedb.R;

import static com.example.themoviedb.moviedetails.MovieDetailsFragment.MOVIE_ID;
import static com.example.themoviedb.moviedetails.MovieDetailsFragment.MOVIE_TITLE;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        final Bundle arguments = getIntent().getExtras();
        String movieTitle = "";
        int movieId = 0;

        if (arguments != null) {
            movieId = arguments.getInt(MOVIE_ID, 0);
            movieTitle = arguments.getString(MOVIE_TITLE);
        }

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit);
        fragmentTransaction.replace(R.id.content, MovieDetailsFragment.newInstance(movieId, movieTitle));
        fragmentTransaction.commit();
    }
}
