package uk.co.taxomania.games.minesweeper;

import uk.co.taxomania.games.minesweeper.Game.Cell;
import uk.co.taxomania.games.minesweeper.MineView.Callback;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public final class HomeActivity extends Activity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final GridView grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(new GridAdapter(this, new Game()));
    } // onCreate(Bundle)

    static class GridAdapter extends ArrayAdapter<Cell> {
        final Game g;

        public GridAdapter(final Context con, Game game) {
            super(con, 0, game.game);
            g = game;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MineView v = new MineView(getContext(), getItem(position), new Callback() {

                @Override
                public void mineClicked() {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mineMissed() {
                    g.player1Turn = !g.player1Turn;
                }
            });
            v.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (g.player1Turn) {
                        v.setActivated(true);
                    }
                }
            });

            return v;
        }

    }
} // class HomeActivity
