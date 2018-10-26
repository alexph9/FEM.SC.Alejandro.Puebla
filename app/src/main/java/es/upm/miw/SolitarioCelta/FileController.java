package es.upm.miw.SolitarioCelta;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileController {

    public static final String FILE_GAME = "solitarioCelta.txt";

    private String name;

    public FileController(String name) {
        this.setName(name);
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void writeFile(Context context, String fileData, int openMode) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(this.getName(),
                    openMode);
            fileOutputStream.write(fileData.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFile(Context context) throws IOException {
        String text = "";
        String line;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                context.openFileInput(this.getName())));
        line = bufferedReader.readLine();
        while (line != null) {
            text += line;
            line = bufferedReader.readLine();
        }

        return text;
    }

}