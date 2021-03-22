package pollub.ism.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    char[] arr = {'O', 'X'};
    Button[] button = new Button[9];
    boolean hasWinner = false;
    boolean hasAnyEmptyField = false;
    TicTacToe ticTacToe = new TicTacToe(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public char[] checkButtons() {
        char[] getBoardSigns = new char[9];

        for (int i = 1; i <= 9; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            button[i - 1] = (Button) findViewById(id);
            if (!button[i - 1].getText().equals(""))
            getBoardSigns[i - 1] = button[i - 1].getText().charAt(0);
        }
        return getBoardSigns;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        char[] getSigns = (char[]) savedInstanceState.getSerializable("boardSigns");
        for (int i = 1; i <= 9; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            button[i - 1] = (Button) findViewById(id);
            if ((String.valueOf(getSigns[i - 1]).equals("X") || String.valueOf(getSigns[i - 1]).equals("O"))) {
                button[i - 1].setEnabled(false);
                button[i - 1].setText(String.valueOf(getSigns[i - 1]));
            } else {
                button[i - 1].setText("");
            }

        }

        TicTacToe.moves = savedInstanceState.getInt("numberOfMoves");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("boardSigns", checkButtons());
        outState.putInt("numberOfMoves", TicTacToe.moves);
    }


    public void clickButton(View view) throws InterruptedException {
        int whoseTurn = TicTacToe.moves % 2;
        Button button1 = findViewById(view.getId());
        button1.setEnabled(false);
        button1.setText(String.valueOf(arr[whoseTurn]));
        TicTacToe.moves++;


        for (int i = 1; i <= 9; i++) {
                int id = getResources().getIdentifier("button" + i, "id", getPackageName());
                 button[i - 1] = (Button) findViewById(id);
        }


        for (int i = 0; i < 3; i++) {
            if (!button[i].getText().equals("")) {
                if (button[i].getText().equals(button[i + 3].getText()) && button[i + 3].getText().equals(button[i + 6].getText())) {
                    hasWinner = true;
                    break;
                }
            }
        }

        for (int i = 0; i < 7; i += 3) {
            if (!button[i].getText().equals("")) {
                if (button[i].getText().equals(button[i + 1].getText()) && button[i + 1].getText().equals(button[i + 2].getText())) {
                    hasWinner = true;
                    break;
                }
            }
        }

        if (!button[0].getText().equals("")) {
            if (button[0].getText().equals(button[4].getText()) && button[0].getText().equals(button[8].getText())) {
                hasWinner = true;
            }
        }
         if (!button[2].getText().equals("")) {
             if (button[2].getText().equals(button[4].getText()) && button[4].getText().equals(button[6].getText())) {
                 hasWinner = true;
            }
        }


        if (hasWinner) {
            Toast.makeText(this,"Wygrały " + arr[whoseTurn], Toast.LENGTH_LONG).show();
            for (Button buttons: button) {
                buttons.setText("");
                buttons.setEnabled(true);
            }
            hasWinner = false;
        } else {
            for (Button buttons: button) {
                if(buttons.getText().equals("")) {
                    return;
                }
            }
            if (!(hasAnyEmptyField)) {
                Toast.makeText(this," MAMY REMIS!", Toast.LENGTH_LONG).show();
                for (Button buttons: button) {
                    buttons.setText("");
                    buttons.setEnabled(true);
                }
            }
        }


    }

}