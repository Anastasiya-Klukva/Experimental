import jdk.jfr.Experimental;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public void printToFile(List<String> content, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");

        for(String row: content) {
            writer.println(row);
        }
        writer.close();
    }

    public List<String> readFileStructure(String filePath, String preString) {
        List<String> rows = new ArrayList<>();
        readStructure(filePath, preString, rows);
        return rows;
    }

    private void readStructure(String filePath, String preString, List<String> rows) {
        File f = new File(filePath);
        String fileName = f.getName();
        rows.add(preString + fileName);

        if (f.isDirectory()) {
            String[] filenames = f.list();
            for (int i = 0; i < filenames.length; i++) {
                String prefix = preString + "   ";
                readStructure(filePath + "\\" + filenames[i], prefix, rows);
            }
        }
    }


    public void printFile(String filePath, String preString) {
        File f = new File(filePath);
        String fileName = f.getName();
        System.out.println(preString + fileName);

        if (f.isDirectory()) {
            String[] filenames = f.list();
            for (int i = 0; i < filenames.length; i++) {
                String prefix = preString + "   ";
                printFile(filePath + "\\" + filenames[i], prefix);
            }
//            JFileChooser c = new JFileChooser();
//            c.showOpenDialog(c);
//            File writeFile = c.getSelectedFile();
//            String content = "TestFolder";

//            try {
//                FileWriter fw = new FileWriter(writeFile);
//                BufferedWriter bw = new BufferedWriter(fw);
//                bw.append(content);
//                bw.append("TestFolder");
//                bw.close();
//                fw.close();
//            }
//            catch (Exception exc) {
//                System.out.println(exc);
//            }
//        }
        }
    }

    public List<String> readFile(String filePath) {
        Path path = Paths.get(filePath);
        List<String> rows = new ArrayList<>();
        try {
            rows = Files.lines(path).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String row: rows) {
            System.out.println(row);
        }
        return rows;
    }

    public int countFoldersQuantity(List<String> rows) {
        int quantity = 0;
        for (String row : rows) {
            if (!row.contains(".")) {
                quantity++;
            }
        }
        return quantity;
    }
    public int countFilesQuantity(List<String> rows) {
        int quantity = 0;
        for (String row : rows) {
            if (row.contains(".")) {
                quantity++;
            }
        }
        return quantity;
    }


    public List<String> filterFiles(List<String> rows) {
        List<String> results = new ArrayList<>();


        return results;
    }

    public static void main(String[] args) throws IOException {
//        (new Main()).printFile(args[0], "");
//        System.out.println();

        Main main = new Main();
//        List<String> rows = main.readFileStructure(args[0], "");
//        main.printToFile(rows, "C:\\Users\\kluko\\TestFolder\\petrova.txt");
        List<String> rows = main.readFile("C:\\Users\\kluko\\TestFolder\\petrova.txt");
        int foldersQuantity = main.countFoldersQuantity(rows);
        int filesQuantity = main.countFilesQuantity(rows);
        System.out.println("Files quantity is : " + filesQuantity);
        System.out.println("Folders quantity is : " + foldersQuantity);


        System.out.println();

        /*Path path = Paths.get(args[0]);
        System.out.println(path);

        File f = new File(args[0]);

        String[] filenames = f.list();
        for (int i = 0; i < filenames.length; i++) {
            System.out.println(filenames[i]);

        }


       /* if (Files.isDirectory(path)) {
            System.out.println(" first if ");
            MyFileVisitor fileVisitor = new MyFileVisitor();
            Files.walkFileTree(path, fileVisitor);
        } else if (path.toString().endsWith(".txt")) {
            System.out.println("second if");
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
                String string;
                while ((string = reader.readLine()) != null) {
                    System.out.println(string);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
        }

       /* try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("C:\\Users\\kluko\\OneDrive\\Documents\\IOTask\\output.txt"));
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        } */
    }
}