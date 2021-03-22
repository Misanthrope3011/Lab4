package pollub.ism.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TicTacToe ticTacToe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ticTacToe = new TicTacToe(this);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        char[] getSigns = (char[]) savedInstanceState.getSerializable("boardSigns");
        for (int i = 1; i <= 9; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            ticTacToe.button[i - 1] = (Button) findViewById(id);
            if ((String.valueOf(getSigns[i - 1]).equals("X") || String.valueOf(getSigns[i - 1]).equals("O"))) {
                ticTacToe.button[i - 1].setEnabled(false);
                ticTacToe.button[i - 1].setText(String.valueOf(getSigns[i - 1]));
            } else {
                ticTacToe.button[i - 1].setText("");
            }

        }

        TicTacToe.moves = savedInstanceState.getInt("numberOfMoves");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("boardSigns", ticTacToe.checkButtons());
        outState.putInt("numberOfMoves", TicTacToe.moves);
    }


    public void clickButton(View view) throws InterruptedException {
        int whoseTurn = TicTacToe.moves % 2;
        ticTacToe.drawSign(view, whoseTurn);
        ticTacToe.checkWin();
        ticTacToe.detectedWinOrDrawAction();
        TicTacToe.moves++;

    }

}