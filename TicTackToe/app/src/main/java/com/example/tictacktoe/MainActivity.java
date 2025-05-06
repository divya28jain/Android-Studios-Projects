package com.example.tictacktoe;

import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean isXturn = true; // Track turns
    private ImageView[][] cells = new ImageView[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tableLayout = findViewById(R.id.grid_layout);

        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                ImageView cell = (ImageView) row.getChildAt(j);
                final int x = i, y = j;
                cells[i][j] = cell;
                cell.setOnClickListener(v -> dropSymbol(x, y));
            }
        }
    }

    private void dropSymbol(int x, int y) {
        if (cells[x][y].getDrawable() != null) return; // Prevent overwriting

        ImageView piece = new ImageView(this);
        piece.setImageResource(isXturn ? R.drawable.x_image : R.drawable.o_image);
        piece.setLayoutParams(cells[x][y].getLayoutParams());

        TranslateAnimation animation = new TranslateAnimation(0, 0, -500, 0);
        animation.setDuration(500);
        piece.startAnimation(animation);

        cells[x][y].setImageDrawable(piece.getDrawable());
        isXturn = !isXturn; // Toggle turn
    }
}
