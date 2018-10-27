package es.upm.miw.SolitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class LoadGameDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.txtLoadGameTitle))
                .setMessage(getString(R.string.txtLoadGameMessage))
                .setPositiveButton(
                        getString(R.string.txtLoadGameYesOption),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                main.mJuego.loadGame(main);
                                main.mostrarTablero();
                                Toast.makeText(main, main.getString(R.string.txtLoadGame),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.txtLoadGameNoOption),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }
                );

        return builder.create();
    }
}
