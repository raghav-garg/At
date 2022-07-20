
import java.util.*;

    public class TopFiles {

    /*
    * file1.txt (size: 100)
file2.txt (size: 200) in collection "collection1"
file3.txt (size: 200) in collection "collection1"
file4.txt (size: 400) in collection "collection2”
file5.txt (size: 10)
*
*
* map : c1 : 400
* c2 : 400
*
* f3 100 c1
* map : c1 500, c2 400
*
* treeset : 500 -> c1 400 -> c2
*
* order map : treeset (RB tree logn): 400 -> {c1,c2}
*
* 100 c1
* 200 -> c2
* 100 -> {c1}
* 200 -> {c2}
*
*
* 100 c1
* 200 -> {c1, c2}
*

    *
    *
    * file1.txt (size: 100)
file2.txt (size: 200) in collection "collection1"
file3.txt (size: 200) in collection "collection1"
file4.txt (size: 400) in collection "collection2”
file5.txt (size: 10)
    * */

        static int totalSize = 0;

        //<collection Name, size of files in collection>
        static Map<String, Integer> collectionToSizeMap = new HashMap<>();

        //size, collectionNames
        static Map<Integer, HashSet<String>> sizeToCollectionsMap = new TreeMap<>(Collections.reverseOrder());


        public static void main(String[] args) {
//          String a = "file2.txt (size: 200) in collection \"collection1\"";
//
//          String fileName = a.substring(0, a.indexOf("(")).trim();
//          System.out.println(fileName);
//
//          int fileSize = Integer.parseInt(a.substring(a.indexOf(":")+1, a.indexOf(")")).trim());
//          System.out.println(fileSize);
//
//          String collectionName = null;
//          if(a.indexOf("\"") != -1 || a.lastIndexOf("\"") != -1)
//          {
//              collectionName =  a.substring(a.indexOf("\"")+1, a.lastIndexOf("\"") ).trim();
//          }
//          System.out.println(collectionName);

        update("f1", 100, null);
        update("f2", 700, "c1");
        update("f3", 400, "c1");
        update("f4", 400, "c2");
        update("f4", 500, "c3");
        update("f4", 400, "c2");
        update("f5", 10, null);
        System.out.println(getTotalFileSize());
        System.out.println(getCollectionBySize(2));
        }


        // TODO parse input
        public static void update(String fileName, int size, String collectionName) {
            totalSize += size;
            if (collectionName == null) {
                return;
            }
            int oldSize = collectionToSizeMap.getOrDefault(collectionName, 0);
            int newSize = oldSize + size;
            collectionToSizeMap.put(collectionName, oldSize + size);

            if (sizeToCollectionsMap.containsKey(oldSize) && sizeToCollectionsMap.get(oldSize).contains(collectionName)) {
                sizeToCollectionsMap.get(oldSize).remove(collectionName);
                if (sizeToCollectionsMap.get(oldSize).size() == 0) {
                    sizeToCollectionsMap.remove(oldSize);
                }
            }
            if (!sizeToCollectionsMap.containsKey(newSize)) {
                sizeToCollectionsMap.put(newSize, new HashSet<>());
            }
            sizeToCollectionsMap.get(newSize).add(collectionName);

//        System.out.println("total Size " + totalSize);
//        System.out.println("Size map " + sizeMap);
//        System.out.println("Tree map " + treeSet);
//        System.out.println("********************");
        }

        public static HashSet<String> getCollectionBySize(int topK) {
            HashSet<String> result = new HashSet<>();
//        System.out.println("Collecting top " + topK + " collections");
            int quota = topK;
            for (int key : sizeToCollectionsMap.keySet()) {
                if (quota > sizeToCollectionsMap.get(key).size()) {
//                System.out.println(treeSet.get(key));
                    result.addAll(sizeToCollectionsMap.get(key));
                    quota -= sizeToCollectionsMap.get(key).size();
                } else if (quota != 0) {
                    HashSet<String> collectionOfSize = sizeToCollectionsMap.get(key);
                    Iterator<String> collectionIterator = collectionOfSize.iterator();
                    while (quota > 0) {
                        result.add(collectionIterator.next());
                        --quota;
                    }
                } else if (quota == 0) {
                    break;
                }
            }
            return result;
        }

        public static int getTotalFileSize() {
            return totalSize;
        }
    }
