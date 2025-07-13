import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;
import java.util.Comparator;

public class DirectoryList {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please give a relative path to a directory.");
            return;
        }

        String basePath = System.getProperty("user.dir");
        String fullPath = basePath + File.separator + args[0];

        File folder = new File(fullPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("The path is not a directory: " + args[0]);
            return;
        }

        StringBuilder listing = new StringBuilder();
        listFolder(folder, "", listing);

        try {
            FileOutputStream fos = new FileOutputStream("listing.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listing.toString());
            oos.close();
            fos.close();
            System.out.println("Saved listing to listing.ser");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }

        try {
            FileInputStream fis = new FileInputStream("listing.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            String content = (String) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Content from listing.ser:\n" + content);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void listFolder(File folder, String indent, StringBuilder listing) {
        File[] files = folder.listFiles();
        if (files == null) return;

        Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {
                return f1.getName().compareToIgnoreCase(f2.getName());
            }
        });

        for (File f : files) {
            String type = f.isDirectory() ? "[D]" : "[F]";
            String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(f.lastModified()));
            listing.append(indent)
                    .append(type)
                    .append(" ")
                    .append(f.getName())
                    .append(" - ")
                    .append(date)
                    .append("\n");

            if (f.isDirectory()) {
                listFolder(f, indent + "    ", listing);
            }
        }
    }
}

