package ex4;

public class Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        // Put some key values.
        for (int i = 0; i < 30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        System.out.println("before");
        // Print the HashTable structure
        log("****   HashTable  ***");
        log(hashTable.toString());
//        log("\nValue for key(22) : " + hashTable.get("22"));

//        System.out.println("after");
//        // Print the HashTable structure
//        log("****   HashTable  ***");
//        hashTable.drop("11");
//        log(hashTable.toString());

    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}