package es.upm.miw.SolitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SaveScoreDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(View.inflate(main, R.layout.save_score, null))
                .setTitle(R.string.txtDialogoFinalTitulo)
                .setPositiveButton(
                        R.string.txtSaveScoreSaveOption,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String playerName = ((EditText) SaveScoreDialogFragment.this
                                        .getDialog().findViewById(R.id.userName))
                                        .getText().toString();

                                if (playerName.length() > 0) {
                                    main.mJuego.saveScore(main, playerName);
                                    new AlertDialogFragment().show(getFragmentManager(),
                                            "ALERT DIALOG");
                                }
                            }
                        }
                )
                .setNegativeButton(
                        R.string.txtSaveScoreCancelOption,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SaveScoreDialogFragment.this.getDialog().cancel();
                            }
                        }
                );

        return builder.create();
    }
}
