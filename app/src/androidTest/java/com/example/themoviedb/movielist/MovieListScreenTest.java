package com.example.themoviedb.movielist;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.themoviedb.MainActivity;
import com.example.themoviedb.R;
import com.example.themoviedb.data.model.Movie;

import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Tests for the movies screen, the main screen which contains a list of all movies.
 */
@RunWith(AndroidJUnit4.class)
public class MovieListScreenTest {

    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     * <p>
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addMovieToList() throws Exception {
        onView(withId(R.id.movie_list)).check(matches(isDisplayed()));

        final Movie movie = Movie.builder()
            .id(0)
            .voteAverage(7.5f)
            .voteCount(10)
            .title("Movie 0")
            .posterPath(null)
            .originalLanguage(null)
            .originalTitle(null)
            .genreIds(new ArrayList<Integer>() {{
                add(1);
                add(2);
            }})
            .adult(false)
            .overview("Movie0's overview")
            .releaseDate(DateTime.now())
            .build();
    }
}
