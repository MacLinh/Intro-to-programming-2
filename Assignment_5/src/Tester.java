public class Tester {
    public static void main(String[] args) {
        LinearFrequencyTable n = new LinearFrequencyTable();
        n.init("key1");
        n.init("key2");
        n.init("key3");
        System.out.println(n);
    }
}