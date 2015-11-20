import java.io.*;
import java.util.ArrayList;

public class UdacityCourseSubtitleFixer {
    final static String lineSp = System.getProperty("line.separator");
    private static ArrayList<File> files;

    public static void main(String[] args) throws IOException {
        files = new ArrayList<>();
        File file = new File("E:\\Study\\_Career\\Trainings\\Mobile Application Launchpad\\Subs");
        listAllFiles(file.getAbsolutePath());
        for (File f : files) {
            if (f.getName().endsWith(".srt")) {
                fixSubtitle(f);
            }
        }
    }

    private static void fixSubtitle(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuilder sb = new StringBuilder("");
        int i = 1;
        String line = null;

        while ((line = br.readLine()) != null) {
            sb.append(i++ + lineSp);
            String[] parts = line.split(",");
            sb.append(parts[0]);
            sb.append(" --> ");
            sb.append(parts[1] + lineSp);
            sb.append(br.readLine() + lineSp);
            br.readLine();
            sb.append(lineSp);
        }
        br.close();

        PrintWriter print = new PrintWriter(f);
        print.write(String.valueOf(sb));
        print.close();
    }

    public static void listAllFiles(String directoryName) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listAllFiles(file.getAbsolutePath());
            }
        }
    }
}
