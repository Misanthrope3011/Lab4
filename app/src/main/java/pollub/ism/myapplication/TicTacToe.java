package pollub.ism.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TicTacToe {
    Button[] button;
    Activity activity;
    static int moves = 0;
    char[] arr = {'O', 'X'};
    boolean hasWinner = false;
    boolean hasAnyEmptyField = false;

    public TicTacToe(Activity activity) {
        this.activity = activity;
        this.button = new Button[9];

        for (int i = 1; i <= 9; i++) {
            int id = this.activity.getResources().getIdentifier("button" + i, "id", this.activity.getPackageName());
            button[i - 1] = activity.findViewById(id);
        }

    }

    public char[] checkButtons() {
        char[] getBoardSigns = new char[9];

        for (int i = 1; i <= 9; i++) {
            int id = this.activity.getResources().getIdentifier("button" + i, "id", this.activity.getPackageName());
            button[i - 1] = (Button) this.activity.findViewById(id);
            if (!button[i - 1].getText().equals(""))
                getBoardSigns[i - 1] = button[i - 1].getText().charAt(0);
        }
        return getBoardSigns;
    }

    public void checkWin() {

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
    }

    public void drawSign(View view, int whoseTurn) {
        Button button1 = this.activity.findViewById(view.getId());
        button1.setEnabled(false);
        button1.setText(String.valueOf(this.arr[whoseTurn]));
    }

    public void detectedWinOrDrawAction() {

        if (hasWinner) {
           Toast.makeText(this.activity, "Wygral " + arr[TicTacToe.moves % 2], Toast.LENGTH_LONG).show();
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
                Toast.makeText(this.activity," MAMY REMIS!" , Toast.LENGTH_LONG).show();
                for (Button buttons: button) {
                    buttons.setText("");
                    buttons.setEnabled(true);
                }
            }
        }

    }




}
