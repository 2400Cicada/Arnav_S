import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 
 * Application used to test different methods of LinkedBag and Node.
 * 
 * 
 * @author Warren Maxwell
 */

public class LinkedBagTest {
    
    /**Test the add and toArray functions in LinkedBag */
    @Test
   void testAddAndToArray() {
      BagInterface<String> testBag = new LinkedBag<String>();
      String[] testStrings1 = {"A", "B","C","A","D","D","A"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag.add(testStrings1[i]);
      }

      assertEquals(Arrays.toString(testBag.toArray()), "[A, D, D, A, C, B, A]");
      assertNotEquals(Arrays.toString(testBag.toArray()), "[A, Z]");
   } // end testAddAndToArray

    /**Test the remove, add, and toArray functions in LinkedBag */
   @Test
   void testRemoveAndToArray() {
      BagInterface<String> testBag = new LinkedBag<String>();

      String[] testStrings1 = {"A", "B", "C", "A", "D", "D", "A"};
      String[] removeStrings1 = {"A", "A", "A"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag.add(testStrings1[i]);
      }

      for(int i = 0; i < removeStrings1.length; i++){
        testBag.remove(removeStrings1[i]);
      }

      assertNotEquals(Arrays.toString(testBag.toArray()), "[A, A, A]");
      assertEquals(Arrays.toString(testBag.toArray()), "[D, C, B, D]");
   } // end 'testRemoveAndToArray'

   /**Test the add, getCurrentSize and toArray functions in LinkedBag */
   @Test
   void testGetCurrentSize() {
      BagInterface<String> testBag = new LinkedBag<String>();

      assertEquals(testBag.getCurrentSize(), 0);
      
      String[] testStrings1 = {"A", "B", "C", "A", "D", "D", "A"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag.add(testStrings1[i]);
      }

      assertEquals(testBag.getCurrentSize(), 7);
   } // end testGetCurrentSize

   /**Test the add, isEmpty and toArray functions in LinkedBag */
   @Test
   void testIsEmpty() {
      BagInterface<String> testBag = new LinkedBag<String>();

      assertTrue(testBag.isEmpty());

      String[] testStrings1 = {"A", "B", "C", "A", "D", "D", "A"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag.add(testStrings1[1]);
      }

      assertFalse(testBag.isEmpty());    
   } // end testIsEmpty

   /**Test the add, clear, isEmpty and toArray functions in LinkedBag */
   @Test
   void testClear() {
      BagInterface<String> testBag = new LinkedBag<String>();
      
      String[] testStrings1 = {"A", "B", "C", "A", "D", "D", "A"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag.add(testStrings1[i]);
      }

      testBag.clear();

      assertTrue(testBag.isEmpty());
   } // end testClear

   /**Test the add, getFrequencyOf and toArray functions in LinkedBag */
   @Test
   void testGetFrequencyOf() {
      BagInterface<String> testBag = new LinkedBag<String>();

      assertTrue(testBag.getFrequencyOf("Z") == 0);

      String[] testStrings1 = {"A", "B", "C", "A", "D", "D", "A"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag.add(testStrings1[i]);
      }

      assertTrue(testBag.getFrequencyOf("A") == 3);
   } // end testGetFrequencyOf

   /**Test the add, contains and toArray functions in LinkedBag */
   @Test
   void testContains() {
      BagInterface<String> testBag = new LinkedBag<String>();

      String[] testStrings1 = {"A", "B", "C", "A", "D", "D", "A"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag.add(testStrings1[i]);
      }

      assertFalse(testBag.contains("Z"));
      assertTrue(testBag.contains("A"));  
   } // end testContains

   /**Test the add, union and toArray functions in LinkedBag */
   @Test
   void testUnion() {
      BagInterface<String> testBag1 = new LinkedBag<String>();
      BagInterface<String> testBag2 = new LinkedBag<String>();
      BagInterface<String> testBag3 = new LinkedBag<String>();

      String[] testStrings1 = {"A", "B", "C", "A", "D", "D", "A"};
      String[] testStrings3 = {"A", "B", "B", "A"};
      String[] testStrings2 = {"Z"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag1.add(testStrings1[i]);
      }

      for(int i = 0; i < testStrings2.length; i++){
        testBag2.add(testStrings2[i]);
      }

      for(int i = 0; i < testStrings3.length; i++){
        testBag3.add(testStrings3[i]);
      }

      assertEquals(Arrays.toString(testBag1.union(testBag2).toArray()), "[Z, A, B, C, A, D, D, A]");
      assertEquals(Arrays.toString(testBag1.union(testBag3).toArray()), "[A, B, B, A, A, B, C, A, D, D, A]" );
      assertEquals(Arrays.toString(testBag2.union(testBag3).toArray()), "[A, B, B, A, Z]");
   } // end testUnion

   /**Test the add, intersection and toArray functions in LinkedBag */
   @Test
   void testIntersection() {
      BagInterface<String> testBag1 = new LinkedBag<String>();
      BagInterface<String> testBag2 = new LinkedBag<String>();
      BagInterface<String> testBag3 = new LinkedBag<String>();

      String[] testStrings1 = {"A", "B", "C", "A", "D", "D", "A"};
      String[] testStrings3 = {};
      String[] testStrings2 = {"A", "B", "C", "D"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag1.add(testStrings1[i]);
      }

      for(int i = 0; i < testStrings2.length; i++){
        testBag2.add(testStrings2[i]);
      }

      for(int i = 0; i < testStrings3.length; i++){
        testBag3.add(testStrings3[i]);
      }
      
      assertEquals(Arrays.toString(testBag1.intersection(testBag2).toArray()), "[B, C, D, A]");
      assertEquals(Arrays.toString(testBag2.intersection(testBag3).toArray()), "[]" );
      assertEquals(Arrays.toString(testBag3.intersection(testBag1).toArray()), "[]");
      assertEquals(Arrays.toString(testBag1.intersection(testBag1).toArray()), "[B, C, D, D, A, A, A]");
      assertEquals(Arrays.toString(testBag2.intersection(testBag2).toArray()), "[A, B, C, D]" );
      assertEquals(Arrays.toString(testBag3.intersection(testBag3).toArray()), "[]");
   } // end testIntersection

   /**Test the add, difference and toArray functions in LinkedBag */
   @Test
   void testDifference() {
      BagInterface<String> testBag1 = new LinkedBag<String>();
      BagInterface<String> testBag2 = new LinkedBag<String>();
      BagInterface<String> testBag3 = new LinkedBag<String>();

      String[] testStrings1 = {"A", "B", "C", "A", "D", "D", "A"};
      String[] testStrings3 = {};
      String[] testStrings2 = {"A, B, C, D"};

      for(int i = 0; i < testStrings1.length; i++){
        testBag1.add(testStrings1[i]);
      }

      for(int i = 0; i < testStrings2.length; i++){
        testBag2.add(testStrings2[i]);
      }

      for(int i = 0; i < testStrings3.length; i++){
        testBag3.add(testStrings3[i]);
      }

      assertEquals(Arrays.toString(testBag1.difference(testBag2).toArray()), "[A, B, C, A, D, D, A]");
      assertEquals(Arrays.toString(testBag1.difference(testBag3).toArray()), "[A, B, C, A, D, D, A]" );
      assertEquals(Arrays.toString(testBag2.difference(testBag1).toArray()), "[A, B, C, D]");
      assertEquals(Arrays.toString(testBag2.difference(testBag3).toArray()), "[A, B, C, D]");
      assertEquals(Arrays.toString(testBag3.difference(testBag1).toArray()), "[]" );
      assertEquals(Arrays.toString(testBag3.difference(testBag2).toArray()), "[]");
   } // end testDifference
} // end LinkedBag

