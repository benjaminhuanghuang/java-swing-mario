package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Map {
    public int[][] readMap() throws Exception {
        // Read file
        FileInputStream fis = new FileInputStream("map.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        List<String> list = new ArrayList<>();
        String value = br.readLine();
        // Read file line by line
        while (value != null) {
            list.add(value);
            value = br.readLine();
        }

        br.close();

        int row = list.size();
        int col = 0;

        for (int i = 0; i < 1; i++) {
            String str = list.get(i);
            String[] values = str.split(",");
            col = values.length;
        }

        int[][] map = new int[row][col];

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String[] values = str.split(",");
            for (int j = 0; j < values.length; j++) {
                map[i][j] = Integer.parseInt(values[j]);
            }
        }
        return map;
    }
}
