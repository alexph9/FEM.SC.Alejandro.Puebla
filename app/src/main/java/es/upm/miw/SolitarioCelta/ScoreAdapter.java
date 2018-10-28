package es.upm.miw.SolitarioCelta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Score> scores;

    public ScoreAdapter(Context contexto, ArrayList<Score> scores) {
        super(contexto, R.layout.score, scores);
        this.context = contexto;
        this.scores = scores;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.score, parent, false);
        }

        Score score = this.scores.get(position);
        if (score != null) {

            TextView tvPlayerName = (TextView) convertView.findViewById(R.id.tvPlayerName);
            tvPlayerName.setText(score.getPlayerName());

            TextView tvTokens = (TextView) convertView.findViewById(R.id.tvTokens);
            tvTokens.setText(String.valueOf(score.getTokens()));

            TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            tvDate.setText(score.getDate());
        }

        return convertView;
    }
}
