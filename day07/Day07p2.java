import java.io.*;
import java.util.*;

public class Day07p2 {
    public static final long DISK_SIZE = 70_000_000L;

    public static boolean isCommand(String line) {
        return line.startsWith("$");
    }

    public static void main(String[] args) throws Exception {
        long requiredSpace = 30_000_000L;
        Directory root = new Directory(null, "/");
        Directory workingDirectory = root;
        String sample = (args.length > 0) ? args[0] : "";
        String filename = "input" + sample + ".txt";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            List<String> lines = br.lines().toList();
            for (int i = 0; i < lines.size();) {
                String[] line = lines.get(i).split(" ");
                if (line[0].equals("$")) {
                    switch (line[1]) {
                        case "cd":
                            switch (line[2]) {
                                case "/":
                                    workingDirectory = root;
                                    break;
                                case "..":
                                    workingDirectory = workingDirectory.getParent();
                                    break;
                                default:
                                    workingDirectory = workingDirectory.get(line[2]);
                            }
                            i++;
                            break;
                        case "ls":
                            while (true) {
                                i++;
                                if (i < lines.size() && !isCommand(lines.get(i))) {
                                    String[] nextLine = lines.get(i).split(" ");
                                    FileSystemItem item;
                                    if (nextLine[0].equals("dir")) {
                                        item = new Directory(workingDirectory, nextLine[1]);
                                    } else {
                                        item = new File(nextLine[1], nextLine[0]);
                                    }
                                    workingDirectory.add(item);
                                } else {
                                    break;
                                }
                            }
                            break;
                        default:
                            System.out.println("Error! Not Supported Command: " + line[1]);
                    }
                }
            }
        }
        System.out.println(root);
        System.out.println();

        long usedSpace = root.size();

        Optional<Long> result = root.getAllDirectories().stream()
                .filter(d -> d.size() >= requiredSpace - (DISK_SIZE - usedSpace)).map(Directory::size)
                .min(Long::compare);
        System.out.println("Result: " + result);

    }
}

abstract class FileSystemItem {
    String name;

    protected FileSystemItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract long size();

    @Override
    public String toString() {
        return "- " + getName();
    }
}

class File extends FileSystemItem {
    long size;

    public File(String name, long size) {
        super(name);
        this.size = size;
    }

    public File(String name, String size) {
        this(name, Long.parseLong(size));
    }

    public long size() {
        return size;
    }

    @Override
    public String toString() {
        return super.toString() + " (file, size=" + size() + ")";
    }
}

class Directory extends FileSystemItem {
    Directory parent;
    Map<String, FileSystemItem> children;

    public Directory(Directory parent, String name) {
        super(name);
        this.parent = parent;
        this.children = new HashMap<>();
    }

    public Directory getParent() {
        return this.parent;
    }

    public void add(FileSystemItem item) {
        children.put(item.getName(), item);
    }

    public Directory get(String name) {
        return (Directory) this.children.get(name);
    }

    public List<Directory> getAllDirectories() {
        List<Directory> dirs = new ArrayList<>();
        List<Directory> baseDirs = children.values().stream().filter(Directory.class::isInstance)
                .map(Directory.class::cast).toList();
        dirs.addAll(baseDirs);
        for (Directory d : baseDirs) {
            dirs.addAll(d.getAllDirectories());
        }
        return dirs;
    }

    public long size() {
        return children.values().stream().map(FileSystemItem::size).reduce(0L, (x, y) -> x + y);
    }

    public String toString(int indentationLevel) {
        StringBuilder sb = new StringBuilder();

        String indentation = "";
        for (int i = 0; i < indentationLevel; i++) {
            indentation += "  ";
        }
        sb.append(indentation.substring(2));
        sb.append(super.toString() + " (dir, size=" + size() + ")\n");

        for (FileSystemItem item : children.values()) {
            if (item instanceof Directory dir) {
                sb.append(dir.toString(indentationLevel + 2));
            } else {
                sb.append(indentation + item.toString() + "\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(2);
    }
}