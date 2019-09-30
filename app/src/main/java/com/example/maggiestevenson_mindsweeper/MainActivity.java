package com.example.maggiestevenson_mindsweeper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;
interface Constants
{
    String TAG = "MindSweeper";
    int NB_ROWS = 10;
    int NB_COLUMNS = 10;

    int EXPOSED = 1;    // 1 << 0 == 001
    int MINE = 2;       // 1 << 1 ==
    int FLAG = 4;       // 1 << 2 ==
}

public class MainActivity extends AppCompatActivity implements Constants
{

    private int[] grid = new int [NB_ROWS * NB_COLUMNS];

    // for bit wise operation
    public static  boolean HasBit(int value, int bit)
    {
        return (value & bit) == bit;
    }

    // for bit wise operation
    public static int TurnOn(int value, int bit)
    {
        return value | bit;
    }

    // for bit wise operation
    public static int TurnOff(int value, int bit)
    {
        // only flag can be turned off
        return value & ~bit;
    }

    private int getIndex(int column, int row)
   {
       return row * NB_COLUMNS + column;
   }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = findViewById(R.id.grid);

        for (int i = 0; i < NB_COLUMNS; i++)
        {
            for(int j = 0; j < NB_ROWS; j++)
            {
                int x = i;
                int y = j;
                Button button = (Button) grid.getChildAt(getIndex(x, y));
                button.setOnClickListener(v -> onClick(x, y));
                //long click listener
            }
        }
    }

    private void revealCell(int x, int y)
    {
        int value = getIndex(x, y );
        int value = grid[index];
        value = TurnOn(value, EXPOSED);
        grid[index] = value;

        //grid[getIndex(x, y )]= 1;

        //TODO: code the game!!!

    }

    private void removeFlag(int x, int y)
    {
        int index = getIndex(x, y);
        int value = grid[index];
        value = TurnOff(value, FLAG);
        grid[index] = value;
    }

    private void onClick(int x, int y)
    {
        Log.d(TAG, "onClick( "+ x + ", "+ y+ ")");

        revealCell(x, y);
        refresh();
    }

    private void refresh()
    {
        GridLayout grid = findViewById(R.id.grid);
        for (int i = 0; i < NB_COLUMNS; i++)
        {
            for(int j = 0; j < NB_ROWS; j++)
            {
                int x = i;
                int y = j;
                int index = getIndex(x,y);
                Button button = (Button) grid.getChildAt(getIndex(x, y));


                switch (this.grid[index])
                {
                    case 1: //exposed if mine around afficher le chiffre
                        // TODO show numbers of neighbor mines
                        button.setBackgroundResource((R.drawable.bnt_down));
                        break;
                    default:
                        button.setBackgroundResource((R.drawable.bnt_idel));
                        break;

                }
            }
        }
    }
}
