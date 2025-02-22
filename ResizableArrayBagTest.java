import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class ResizableArrayBagTest {
   
   @Test
   void testAddAndToArray() {
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      assertEquals(Arrays.toString(testBag.toArray()), "[A, B, C, D]");
   }

   @Test
   void testRemoveAndToArray() {
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      testBag.remove("A");
      testBag.remove("B");
      testBag.remove("Z");
      assertEquals(Arrays.toString(testBag.toArray()), "[D, C]");
   }

   @Test
   void testGetCurrentSize() {
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      assertEquals(testBag.getCurrentSize(), 0);
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      assertEquals(testBag.getCurrentSize(), 4);
   }

   @Test
   void testIsEmpty() {
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      assertEquals(testBag.isEmpty(), true);
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      assertEquals(testBag.isEmpty(), false);
   }

   @Test
   void testClear() {
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      testBag.add("A");
      testBag.add("B");
      testBag.add("C");
      testBag.add("D");
      testBag.clear();
      assertEquals(testBag.isEmpty(), true);
   }

   @Test
   void testGetFrequencyOf() {
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      testBag.add("A");
      testBag.add("A");
      testBag.add("A");
      testBag.add("B");
      assertEquals(testBag.getFrequencyOf("A"), 3);
      assertEquals(testBag.getFrequencyOf("B"), 1);
      assertEquals(testBag.getFrequencyOf("C"), 0);
   }

   @Test
   void testContains() {
      BagInterface<String> testBag = new ResizableArrayBag<String>();
      testBag.add("A");
      assertEquals(testBag.contains("A"), true);
      assertEquals(testBag.contains("B"), false);
   }

   @Test
   void testUnion() {
      BagInterface<String> testBag1 = new ResizableArrayBag<String>();
      BagInterface<String> testBag2 = new ResizableArrayBag<String>();
      BagInterface<String> testBag3 = new ResizableArrayBag<String>();
      testBag1.add("A");
      testBag1.add("B");
      testBag1.add("C");
      testBag1.add("D");
      testBag2.add("E");
      testBag2.add("F");
      assertEquals(Arrays.toString(testBag1.union(testBag2).toArray()), "[A, B, C, D, E, F]");
      assertEquals(Arrays.toString(testBag1.union(testBag3).toArray()), "[A, B, C, D]");
      assertEquals(Arrays.toString(testBag2.union(testBag3).toArray()), "[E, F]");
   }

   @Test
   void testIntersection() {
      BagInterface<String> testBag1 = new ResizableArrayBag<String>();
      BagInterface<String> testBag2 = new ResizableArrayBag<String>();
      BagInterface<String> testBag3 = new ResizableArrayBag<String>();
      testBag1.add("A");
      testBag1.add("B");
      testBag1.add("C");
      testBag1.add("D");
      testBag2.add("A");
      testBag2.add("B");
      testBag2.add("F");
      assertEquals(Arrays.toString(testBag1.intersection(testBag2).toArray()), "[A, B]");
      assertEquals(Arrays.toString(testBag2.intersection(testBag1).toArray()), "[A, B]");
      assertEquals(Arrays.toString(testBag1.intersection(testBag3).toArray()), "[]");
      assertEquals(Arrays.toString(testBag2.intersection(testBag3).toArray()), "[]");
      assertEquals(Arrays.toString(testBag3.intersection(testBag3).toArray()), "[]");
   }

   @Test
   void testDifference() {
      BagInterface<String> testBag1 = new ResizableArrayBag<String>();
      BagInterface<String> testBag2 = new ResizableArrayBag<String>();
      BagInterface<String> testBag3 = new ResizableArrayBag<String>();
      testBag1.add("A");
      testBag1.add("B");
      testBag1.add("C");
      testBag1.add("D");
      testBag2.add("A");
      testBag2.add("B");
      testBag2.add("F");
      assertEquals(Arrays.toString(testBag1.difference(testBag2).toArray()), "[D, C]");
      assertEquals(Arrays.toString(testBag2.difference(testBag1).toArray()), "[F]");
      assertEquals(Arrays.toString(testBag1.difference(testBag3).toArray()), "[A, B, C, D]");
      assertEquals(Arrays.toString(testBag2.difference(testBag3).toArray()), "[A, B, F]");
      assertEquals(Arrays.toString(testBag3.difference(testBag2).toArray()), "[]");
      assertEquals(Arrays.toString(testBag3.difference(testBag3).toArray()), "[]");
   }
}