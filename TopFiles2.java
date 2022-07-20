
import java.util.*;

public class TopFiles2 {

/*
*
* file1.txt (size: 100)
file2.txt (size: 200) in collection "collection1"
file3.txt (size: 200) in collection "collection1"
file4.txt (size: 400) in collection "collection2”
file5.txt (size: 10)
*
*  */

    static int totalSize = 0;

    //<collection Name, size of files in collection>
    static Map<String, Integer> collectionToSizeMap = new HashMap<>();


    public static void main(String[] args) {
        getInput();

        System.out.println(getTotalFileSize());


        System.out.println(getCollectionBySize(1));
    }

    public static void getInput()
    {
//        for(String[] input : inputs) {
//            String a = "file2.txt (size: 200) in collection \"collection1\"";
//
//            String fileName = a.substring(0, a.indexOf("(")).trim();
//            System.out.println(fileName);
//
//            int fileSize = Integer.parseInt(a.substring(a.indexOf(":") + 1, a.indexOf(")")).trim());
//            System.out.println(fileSize);
//
//            String collectionName = null;
//            if (a.indexOf("\"") != -1 || a.lastIndexOf("\"") != -1) {
//                collectionName = a.substring(a.indexOf("\"") + 1, a.lastIndexOf("\"")).trim();
//            }
//            System.out.println(collectionName);
//            addRecord(fileName, fileSize, collectionName);
//        }

        addRecord("f1", 100, null);
        addRecord("f2", 700, "c1");
        addRecord("f3", 400, "c1");
        addRecord("f4", 100, "c2");
        addRecord("f4", 500, "c3");
        addRecord("f4", 400, "c2");
        addRecord("f5", 10, null);
    }

    public static void addRecord(String fileName, int size, String collectionName) {
        totalSize += size;
        if (collectionName == null) {
            return;
        }
        int oldSize = collectionToSizeMap.getOrDefault(collectionName, 0);
        collectionToSizeMap.put(collectionName, oldSize + size);
    }

    public static List<String> getCollectionBySize(int topK) {
        List<String> result = new LinkedList<>();
        collectionToSizeMap.entrySet().stream().sorted((k1,k2) -> k1.getValue().compareTo(k2.getValue())).forEach(k -> result.add(k.getKey()));
        Collections.reverse(result);
        if(topK > result.size()) topK = result.size();
        return result.subList(0,topK);
    }

    public static int getTotalFileSize() {
        return totalSize;
    }
}
