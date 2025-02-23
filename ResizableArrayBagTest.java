import org.junit.*;
import java.util.Arrays;

/**
 * 
 * Application used to test the ResizableArrayBag class.
 * 
 * 
 * @author Josiah Degeneffe
 */

public class ResizableArrayBagTest {
   
   /** Test for the add and to array methods */
   @Test
   public void testAddAndToArray() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Populate bags
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      // Check items
      Assert.assertEquals(Arrays.toString(testBag.toArray()), "[A, B, C, D]");
   }

   /** Test for the remove and to array methods */
   @Test
   public void testRemoveAndToArray() {
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
      Assert.assertEquals(Arrays.toString(testBag.toArray()), "[D, C]");
   }

   /** Test for the get current size method */
   @Test
   public void testGetCurrentSize() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Check empty bag
      Assert.assertEquals(testBag.getCurrentSize(), 0);
      // Populate bags
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      // Check filled bag
      Assert.assertEquals(testBag.getCurrentSize(), 4);
   }

   /** Test for the is empty method */
   @Test
   public void testIsEmpty() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Check if empty
      Assert.assertEquals(testBag.isEmpty(), true);
      // Populate bags
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      // Check if empty
      Assert.assertEquals(testBag.isEmpty(), false);
   }

   /** Test for the clear method */
   @Test
   public void testClear() {
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
      Assert.assertEquals(testBag.isEmpty(), true);
   }

   /** Test for the get frequency of method */
   @Test
   public void testGetFrequencyOf() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Populate bags
      testBag.add("A");
      testBag.add("A");
      testBag.add("A");
      testBag.add("B");
      // Check frequencies
      Assert.assertEquals(testBag.getFrequencyOf("A"), 3);
      Assert.assertEquals(testBag.getFrequencyOf("B"), 1);
      Assert.assertEquals(testBag.getFrequencyOf("C"), 0);
   }

   /** Test for the contains method */
   @Test
   public void testContains() {
      // Create bags
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      // Populate bags
      testBag.add("A");
      // Check for contents
      Assert.assertEquals(testBag.contains("A"), true);
      Assert.assertEquals(testBag.contains("B"), false);
   }

   /** Test for the union method */
   @Test
   public void testUnion() {
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
      Assert.assertEquals(Arrays.toString(testBag1.union(testBag2).toArray()), "[A, B, C, D, E, F]");
      Assert.assertEquals(Arrays.toString(testBag1.union(testBag3).toArray()), "[A, B, C, D]");
      Assert.assertEquals(Arrays.toString(testBag2.union(testBag3).toArray()), "[E, F]");
   }

   /** Test for the intersection method */
   @Test
   public void testIntersection() {
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
      Assert.assertEquals(Arrays.toString(testBag1.intersection(testBag2).toArray()), "[A, B]");
      Assert.assertEquals(Arrays.toString(testBag2.intersection(testBag1).toArray()), "[A, B]");
      Assert.assertEquals(Arrays.toString(testBag1.intersection(testBag3).toArray()), "[]");
      Assert.assertEquals(Arrays.toString(testBag2.intersection(testBag3).toArray()), "[]");
      Assert.assertEquals(Arrays.toString(testBag3.intersection(testBag3).toArray()), "[]");
   }

   /** Test for the difference method */
   @Test
   public void testDifference() {
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
      Assert.assertEquals(Arrays.toString(testBag1.difference(testBag2).toArray()), "[D, C]");
      Assert.assertEquals(Arrays.toString(testBag2.difference(testBag1).toArray()), "[F]");
      Assert.assertEquals(Arrays.toString(testBag1.difference(testBag3).toArray()), "[A, B, C, D]");
      Assert.assertEquals(Arrays.toString(testBag2.difference(testBag3).toArray()), "[A, B, F]");
      Assert.assertEquals(Arrays.toString(testBag3.difference(testBag2).toArray()), "[]");
      Assert.assertEquals(Arrays.toString(testBag3.difference(testBag3).toArray()), "[]");
   }
}