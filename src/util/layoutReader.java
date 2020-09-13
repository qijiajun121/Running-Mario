package util;

import java.io.*;
import java.util.ArrayList;

public class layoutReader {
    public int[][] layout = null;
    public ArrayList<String> lines = new ArrayList<>();

    public int[][] readFile() throws IOException {
        FileInputStream a = new FileInputStream("layOut.txt");
        InputStreamReader b = new InputStreamReader(a);
        BufferedReader c = new BufferedReader(b);

        String line = c.readLine();

        while (line != null) {
            lines.add(line);
            line = c.readLine();
        }

        int row, column;
        row = lines.size();
        column = 0;

        String tmp = lines.get(0);
        String[] pix = tmp.split(",");
        column = pix.length;

        layout = new int[row][column];

        for(int i=0; i<row; i++){
            String tmp1 = lines.get(i);
            String[] pixS = tmp1.split(",");
            for(int j=0; j<pixS.length; j++){
                layout[i][j] = Integer.parseInt(pixS[j]);
            }
        }

        return layout;
    }
}
