import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class ResizableArrayBagTest {
   
   //Test for the add and to array methods
   @Test
   void testAddAndToArray() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Populate bags
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      // Check items
      assertEquals(Arrays.toString(testBag.toArray()), "[A, B, C, D]");
   }

   //Test for the remove and to array methods
   @Test
   void testRemoveAndToArray() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Populate bags
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      // Remove items
      testBag.remove("A");
      testBag.remove("B");
      testBag.remove("Z");
      // Check remaining items
      assertEquals(Arrays.toString(testBag.toArray()), "[D, C]");
   }

   //Test for the get current size method
   @Test
   void testGetCurrentSize() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Check empty bag
      assertEquals(testBag.getCurrentSize(), 0);
      // Populate bags
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      // Check filled bag
      assertEquals(testBag.getCurrentSize(), 4);
   }

   //Test for the is empty method
   @Test
   void testIsEmpty() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Check if empty
      assertEquals(testBag.isEmpty(), true);
      // Populate bags
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      // Check if empty
      assertEquals(testBag.isEmpty(), false);
   }

   //Test for the clear method
   @Test
   void testClear() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Populate bags
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      // Clear the bag
      testBag.clear();
      // Check if empty
      assertEquals(testBag.isEmpty(), true);
   }

   //Test for the get frequency of method
   @Test
   void testGetFrequencyOf() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Populate bags
      testBag.add("A");
      testBag.add("A");
      testBag.add("A");
      testBag.add("B");
      // Check frequencies
      assertEquals(testBag.getFrequencyOf("A"), 3);
      assertEquals(testBag.getFrequencyOf("B"), 1);
      assertEquals(testBag.getFrequencyOf("C"), 0);
   }

   //Test for the contains method
   @Test
   void testContains() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Populate bags
      testBag.add("A");
      // Check for contents
      assertEquals(testBag.contains("A"), true);
      assertEquals(testBag.contains("B"), false);
   }

   //Test for the union method
   @Test
   void testUnion() {
      // Create bags
      BagInterface<String> testBag1 = new ResizableArrayBag<String>();
      BagInterface<String> testBag2 = new ResizableArrayBag<String>();
      BagInterface<String> testBag3 = new ResizableArrayBag<String>();
      // Populate bags
      testBag1.add("A");
      testBag1.add("B");
      testBag1.add("C");
      testBag1.add("D");
      testBag2.add("E");
      testBag2.add("F");
      // Check variations of union
      assertEquals(Arrays.toString(testBag1.union(testBag2).toArray()), "[A, B, C, D, E, F]");
      assertEquals(Arrays.toString(testBag1.union(testBag3).toArray()), "[A, B, C, D]");
      assertEquals(Arrays.toString(testBag2.union(testBag3).toArray()), "[E, F]");
   }

   //Test for the intersection method
   @Test
   void testIntersection() {
      // Create bags
      BagInterface<String> testBag1 = new ResizableArrayBag<String>();
      BagInterface<String> testBag2 = new ResizableArrayBag<String>();
      BagInterface<String> testBag3 = new ResizableArrayBag<String>();
      // Populate bags
      testBag1.add("A");
      testBag1.add("B");
      testBag1.add("C");
      testBag1.add("D");
      testBag2.add("A");
      testBag2.add("B");
      testBag2.add("F");
      // Check variations of intersection
      assertEquals(Arrays.toString(testBag1.intersection(testBag2).toArray()), "[A, B]");
      assertEquals(Arrays.toString(testBag2.intersection(testBag1).toArray()), "[A, B]");
      assertEquals(Arrays.toString(testBag1.intersection(testBag3).toArray()), "[]");
      assertEquals(Arrays.toString(testBag2.intersection(testBag3).toArray()), "[]");
      assertEquals(Arrays.toString(testBag3.intersection(testBag3).toArray()), "[]");
   }

   //Test for the difference method
   @Test
   void testDifference() {
      // Create bags
      BagInterface<String> testBag1 = new ResizableArrayBag<String>();
      BagInterface<String> testBag2 = new ResizableArrayBag<String>();
      BagInterface<String> testBag3 = new ResizableArrayBag<String>();
      // Populate bags
      testBag1.add("A");
      testBag1.add("B");
      testBag1.add("C");
      testBag1.add("D");
      testBag2.add("A");
      testBag2.add("B");
      testBag2.add("F");
      // Check variations of difference
      assertEquals(Arrays.toString(testBag1.difference(testBag2).toArray()), "[D, C]");
      assertEquals(Arrays.toString(testBag2.difference(testBag1).toArray()), "[F]");
      assertEquals(Arrays.toString(testBag1.difference(testBag3).toArray()), "[A, B, C, D]");
      assertEquals(Arrays.toString(testBag2.difference(testBag3).toArray()), "[A, B, F]");
      assertEquals(Arrays.toString(testBag3.difference(testBag2).toArray()), "[]");
      assertEquals(Arrays.toString(testBag3.difference(testBag3).toArray()), "[]");
   }
}