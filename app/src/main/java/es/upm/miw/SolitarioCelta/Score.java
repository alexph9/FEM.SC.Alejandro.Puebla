package es.upm.miw.SolitarioCelta;

import android.os.Parcel;
import android.os.Parcelable;

public class Score implements Parcelable {

    private int id;
    private String playerName;
    private int tokens;
    private String date;

    public Score(int id, String playerName, int tokens, String date) {
        this.id = id;
        this.playerName = playerName;
        this.tokens = tokens;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.playerName);
        dest.writeInt(this.tokens);
        dest.writeString(this.date);
    }

    protected Score(Parcel in){
        this.id = in.readInt();
        this.playerName = in.readString();
        this.tokens = in.readInt();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<Score> CREATOR = new Parcelable.Creator<Score>(){
        @Override
        public Score createFromParcel(Parcel source) {
            return new Score(source);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };
}
