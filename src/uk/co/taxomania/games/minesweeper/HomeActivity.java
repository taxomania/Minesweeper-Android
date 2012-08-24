package uk.co.taxomania.games.minesweeper;

import android.app.Activity;
import android.os.Bundle;

public final class HomeActivity extends Activity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new Game();
    } // onCreate(Bundle)

} // class HomeActivity
