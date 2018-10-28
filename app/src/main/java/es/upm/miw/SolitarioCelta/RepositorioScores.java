package es.upm.miw.SolitarioCelta;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import es.upm.miw.SolitarioCelta.ScoreContract.tablaScore;

public class RepositorioScores extends SQLiteOpenHelper {

    private static final String DB_NAME = tablaScore.TABLE_NAME + ".db";
    private static final int DB_VERSION = 1;

    public RepositorioScores(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSQL = "CREATE TABLE " + tablaScore.TABLE_NAME + " ("
                + tablaScore.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaScore.COL_PLAYER_NAME  + " TEXT, "
                + tablaScore.COL_TOKENS     + " INTEGER, "
                + tablaScore.COL_DATE   + " TEXT)";
        db.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE IF EXISTS " + tablaScore.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    public long count() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, tablaScore.TABLE_NAME);
    }

    public long add(String playerName, int tokens, String date){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(tablaScore.COL_PLAYER_NAME, playerName);
        valores.put(tablaScore.COL_TOKENS, tokens);
        valores.put(tablaScore.COL_DATE, date);

        return db.insert(tablaScore.TABLE_NAME, null, valores);
    }

    public ArrayList<Score> getAll(){
        String consultaSQL = "SELECT * FROM " + tablaScore.TABLE_NAME;
        ArrayList<Score> listaScores = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Score score = new Score(
                        cursor.getInt(cursor.getColumnIndex(tablaScore.COL_ID)),
                        cursor.getString(cursor.getColumnIndex(tablaScore.COL_PLAYER_NAME)),
                        cursor.getInt(cursor.getColumnIndex(tablaScore.COL_TOKENS)),
                        cursor.getString(cursor.getColumnIndex(tablaScore.COL_DATE))
                );

                listaScores.add(score);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return listaScores;
    }
}
