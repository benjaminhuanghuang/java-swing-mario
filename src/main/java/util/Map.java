package util;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//��ͼ������
public class Map {

    //��������
    public List<String> list = new ArrayList<>();

    public int[][] map = null;


    public int[][] readMap() throws Exception {
        // �����ļ�������
        FileInputStream fis = new FileInputStream("map.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        //ֱ�Ӷ�ȡһ������
        String value = br.readLine();

        while (value != null) {
            //����ȡ����һ�����ݼ��뵽������
            list.add(value);
            value = br.readLine();
        }

        br.close();

        //�õ������ж�����
        int row = list.size();
        int cloum = 0;

        for (int i = 0; i < 1; i++) {
            String str = list.get(i);
            String[] values = str.split(",");
            cloum = values.length;
        }


        map = new int[row][cloum];

        //���������ַ���ת��������������ֵ����λ����map
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
