public class Tester {
    public static void main(String[] args) {
        TreeFrequencyTable n = new TreeFrequencyTable();
        n.init("key1");
        n.init("key2");
        n.init("key2");
        System.out.println(n);
    }
}