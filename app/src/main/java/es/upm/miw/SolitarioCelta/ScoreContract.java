package es.upm.miw.SolitarioCelta;

import android.provider.BaseColumns;


public class ScoreContract {

    private ScoreContract(){}

    public static abstract class tablaScore implements BaseColumns{

        static final String TABLE_NAME = "score";
        static final String COL_ID = _ID;
        static final String COL_PLAYER_NAME = "playerName";
        static final String COL_TOKENS = "tokens";
        static final String COL_DATE = "date";
    }

}
