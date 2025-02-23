/**
 * 
 * Application used to test the LinkedBag class.
 * 
 * 
 * @author Warren Maxwell
 */

public class LinkedBagTest {
    
    public static void main(String[] args) {
        // Tests on a bag that is empty
        System.out.println("Creating an empty bag");
        BagInterface<String> aBag = new LinkedBag<>();
        displayBag(aBag);
        testIsEmpty(aBag,true);
        String[] testStrings1 = {"","B"};
        testFrequency(aBag, testStrings1);
        testContains(aBag,testStrings1);
        testRemove(aBag,testStrings1);

        // Adding strings
        String[] contentsOfBag = {"A","D","B","A","C","A","D"};
        testAdd(aBag,contentsOfBag);

        // Tests on a bag that is not empty
        testIsEmpty(aBag,false);
        String[] testStrings2 = {"A","B","C","D","Z"};
        testFrequency(aBag,testStrings2);
        testContains(aBag,testStrings2);

        // Removing strings
        String[] testStrings3 = {"","B","A","C","Z"};
        testRemove(aBag,testStrings3);

        System.out.println("\nClearing the bag:");
        aBag.clear();
        testIsEmpty(aBag,true);
        displayBag(aBag);

    } // end main

    public static void testAdd(BagInterface<String> aBag, String[] content){
        System.out.print("Adding to the bag: ");
        for(int index = 0; index < content.length; index++){
            aBag.add(content[index]);
            System.out.print(content[index] + " ");
        }
        System.out.println();
        displayBag(aBag);
    }

    public static void testRemove(BagInterface<String> aBag, String[] tests) {
        
        for (int index = 0; index < tests.length; index++){
         String aString = tests[index];
         if (aString.equals("") || (aString == null))
         {
            // Test remove()
            System.out.println("\nRemoving a string from the bag:");
            String removedString = aBag.remove();
            System.out.println("remove() returns " + removedString);
         }
         else
         {
            // Test remove(aString)
            System.out.println("\nRemoving \"" + aString + "\" from the bag:");
            boolean result = aBag.remove(aString);
            System.out.println("remove(\"" + aString + "\") returns " + result);
         } // end if
         
         displayBag(aBag);
    }
    }

    public static void testIsEmpty(BagInterface<String> aBag, boolean correctResult){
        
        System.out.print("\nTesting the method isEmpty with ");
        if(correctResult){
            System.out.println("an empty bag:");
        }else{
            System.out.println("a bag that is not empty:");
        }

        System.out.print("isEmpty finds the bag ");
        if(correctResult && aBag.isEmpty())
            System.out.println("empty: OK.");
        else if(correctResult)
            System.out.println("not empty, but it is empty: ERROR.");
        else if(!correctResult && aBag.isEmpty())
            System.out.println("empty, but it is not empty: ERROR.");
        else
            System.out.println("not empty: OK.");
    }

    public static void testFrequency(BagInterface<String> aBag, String[] content){
        
        System.out.println("\nTesting the method getFrequencyOf:");

        for (int index = 0; index < content.length; index++){
        System.out.println("In this bag, the count of " + content[index] + 
                            " is " + aBag.getFrequencyOf(content[index]));
        }
    }   

    public static void testContains(BagInterface<String> aBag, String[] content){
        
        System.out.println("\nTesting the method contains:");
        
        for (int index = 0; index < content.length; index++){
        System.out.println("Does this bag contain " + content[index] + 
                            "? " + aBag.contains(content[index]));
        }
    }

    public static void displayBag(BagInterface<String> aBag){
        System.out.println("The bag contains the following string(s):");
        Object[] bagArray = aBag.toArray();
        for(int index = 0; index < bagArray.length; index++){
            System.out.print(bagArray[index] + " ");
        }

        System.out.println();
    }
}
