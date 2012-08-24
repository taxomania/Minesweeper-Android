package uk.co.taxomania.games.minesweeper;

import uk.co.taxomania.games.minesweeper.Game.Cell;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MineView extends LinearLayout {
    private TextView tv;
    private ImageView image;
    private Cell cell;
    private Callback mCallback;

    static interface Callback {
        void mineClicked();

        void mineMissed();
    }

    public MineView(final Context context, final Cell c, final Callback callback) {
        super(context);
        cell = c;
        mCallback = callback;
        image = new ImageView(context);
        image.setDuplicateParentStateEnabled(true);
        addView(image);
        tv = new TextView(context);
        tv.setVisibility(View.GONE);
        tv.setText(cell.noSurroundingMines+"");
        addView(tv);

        if (cell.isMine) {
            image.setImageResource(R.drawable.mine);
        } else {
            image.setImageResource(R.drawable.no_mine);
        } // else
    } // MineView(Context)

    @Override
    public void setOnClickListener(final OnClickListener l) {
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                l.onClick(v);
                v.setEnabled(false);
                if (cell.isMine) {
                    mCallback.mineClicked();
                } else {
                    image.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);
                    mCallback.mineMissed();
                }
            }
        });
    }
} // class MineView
