public class LinkedBagTest {
    
    public static void main(String[] args) {
        System.out.println("Creating an empty bag");
        BagInterface<String> aBag = new LinkedBag<>();
        aBag.toArray();
        aBag.isEmpty();
        String[] testStrings1 = {"","B"};
        aBag.getFrequencyOf(testStrings1);
        aBag.contains(testStrings1);
        aBag.remove(testStrings1);

        String[] contentsOfBag = {"A","D","B","A","C","A","D"};
        aBag.add(contentsOfBag);

        aBag.isEmpty();
        String[] testStrings2 = {"A","B","C","D","Z"};
        aBag.getFrequencyOf(testStrings2);
        aBag.contains(testStrings2);

        String[] testStrings3 = {"","B","A","C","Z"};
        aBag.remove(testStrings3);

        System.out.println("\nClearing the bag:");
        aBag.clear();
        aBag.isEmpty();
        aBag.toArray();

    }
}
