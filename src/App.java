import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String[] headers = {"Столбец 1", "Столбец 2", "Столбец 3", "Столбец 4", "Столбец 5"};
        int[][] data = {
                {505, 201, 706, 45, 31},
                {387, 65, 54, 748, 9},
                {837, 645, 28, 746, 11},
                {544, 391, 117, 365, 5}
        };
        write(headers, data, ';', new File("data.csv"));

        List<String[]> dataArray = read(new File("data.csv"), ';');
        print(dataArray);
    }

    public static void write(String[] headers, int[][] data, char separator, File file) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            writeStr(headers, separator, out);
            for (int[] intStr : data) {
                String[] str = Arrays.stream(intStr).mapToObj(Integer::toString).toArray(String[]::new);
                writeStr(str, separator, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeStr(String[] str, char separator, Writer writer) throws IOException {
        for (int i = 0; i < str.length; i++) {
            writer.write(str[i]);
            if (i != str.length - 1) {
                writer.write(separator);
            }
        }
        writer.write("\n");
    }

    public static List<String[]> read(File file, char separator) {
        ArrayList<String[]> dataArray = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = in.readLine()) != null) {
                dataArray.add(str.split(((Character) separator).toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataArray;
    }

    public static void print(List<String[]> array) {
        for (String[] str : array) {
            for (String cell : str) {
                System.out.printf(" [%s]", cell);
            }
            System.out.println();
        }
    }
}
