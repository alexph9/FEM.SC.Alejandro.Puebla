package es.upm.miw.SolitarioCelta;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreRankingActivity extends Activity {

    private ListView lvScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_ranking);
        this.lvScores = findViewById(R.id.lvScores);

        ArrayList<Score> scores = this.getIntent().getParcelableArrayListExtra("score");
        this.setDataList(scores);

    }

    public void setDataList(ArrayList<Score> scores){
        ScoreAdapter adapter = new ScoreAdapter(
                this,
                scores
        );

        this.lvScores.setAdapter(adapter);
    }


}
