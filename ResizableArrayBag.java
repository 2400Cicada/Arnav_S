import java.util.Arrays;

/**
   A class that implements a bag of objects by using an array.
	The bag is never full.
   @author Josiah Degeneffe
*/

public final class ResizableArrayBag<T> implements BagInterface<T>
{
	private T[] bag; // Cannot be final due to doubling
	private int numberOfEntries;
    private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25; // Initial capacity of bag
	private static final int MAX_CAPACITY = 10000;

	/** Creates an empty bag whose initial capacity is 25. */
	public ResizableArrayBag() 
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor

	/** Creates an empty bag having a given initial capacity.
	    @param initialCapacity  The integer capacity desired. */
	public ResizableArrayBag(int initialCapacity)
	{
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempBag = (T[])new Object[initialCapacity]; // Unchecked cast
      bag = tempBag;
      numberOfEntries = 0;
      integrityOK = true;
	} // end constructor

	/** Creates a bag containing given entries.
	    @param contents  An array of objects. */
   public ResizableArrayBag(T[] contents) 
   {
      checkCapacity(contents.length);
      bag = Arrays.copyOf(contents, contents.length);
      numberOfEntries = contents.length;
      integrityOK = true;
   } // end constructor
       
	/** Adds a new entry to this bag.
       @param newEntry  The object to be added as a new entry.
       @return  True. */
	public boolean add(T newEntry)
	{
		checkintegrity();
      if (isArrayFull())
      {
         doubleCapacity();
      } // end if
      
      bag[numberOfEntries] = newEntry;
      numberOfEntries++;
      
      return true;
	} // end add

	/** Retrieves all entries that are in this bag.
       @return  A newly allocated array of all the entries in this bag. */
	public T[] toArray() 
	{
		checkintegrity();
      
      // The cast is safe because the new array contains null entries.
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
      for (int index = 0; index < numberOfEntries; index++)
      {
         result[index] = bag[index];
      } // end for
      
      return result;
	} // end toArray
   
	/** Sees whether this bag is empty.
       @return  True if this bag is empty, or false if not. */
	public boolean isEmpty()
	{
      return numberOfEntries == 0;
	} // end isEmpty
   
	/** Gets the current number of entries in this bag.
       @return  The integer number of entries currently in this bag. */
	public int getCurrentSize()
	{
      return numberOfEntries;
	} // end getCurrentSize
   
	/** Counts the number of times a given entry appears in this bag.
       @param anEntry  The entry to be counted.
       @return  The number of times anEntry appears in this ba. */
	public int getFrequencyOf(T anEntry)
	{
		checkintegrity();
      int counter = 0;
      
      for (int index = 0; index < numberOfEntries; index++)
      {
         if (anEntry.equals(bag[index]))
         {
            counter++;
         } // end if
      } // end for
      
      return counter;
	} // end getFrequencyOf
   
	/** Tests whether this bag contains a given entry.
       @param anEntry  The entry to locate.
       @return  True if this bag contains anEntry, or false otherwise. */
   public boolean contains(T anEntry)
	{
		checkintegrity();
      return getIndexOf(anEntry) >= 0;
	} // end contains
   
	/** Removes all entries from this bag. */
	public void clear()
	{
      while (!isEmpty())
         remove();
	} // end clear
	
	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal
       was successful, or null. */
	public T remove()
	{
		checkintegrity();
      T result = removeEntry(numberOfEntries - 1);
      return result;
	} // end remove
	
	/** Removes one occurrence of a given entry from this bag.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false if not. */
	public boolean remove(T anEntry)
	{
		checkintegrity();
      int index = getIndexOf(anEntry);
      T result = removeEntry(index);
      return anEntry.equals(result);
	} // end remove

    /** Combine this bag with another bag
     * @param bagToUnify The bag to be unified with this bag
     * @return The resulting bag after combining both bags
    */
    public BagInterface<T> union(BagInterface<T> bagToUnify) {
        // Turn bag contents into arrays
        T[] array1 = this.toArray();
        T[] array2 = bagToUnify.toArray();
        // Create a new bag that will be long enough to contain both bag's contents
        int resultLength = array1.length + array2.length;
        BagInterface<T> resultBag = new ResizableArrayBag<>(resultLength);
        // Add items from this bag to the result bag
        for (int i = 0; i < array1.length; i++) {
            resultBag.add(array1[i]);
        }
        // Add items from the other bag to the result bag
        for (int i = 0; i < array2.length; i++) {
            resultBag.add(array2[i]);
        }
        return resultBag;
    } // end union

    /** Create a new bag out of the shared entries from two bags
     * @param bagToIntersect The bag that will be compared with this bag to
     * check for common entries
     * @return The resulting bag after performing the intersection operation
    */
    public BagInterface<T> intersection(BagInterface<T> bagToIntersect) {
        // Turn bag contents into arrays
        T[] array1 = this.toArray();
        T[] array2 = bagToIntersect.toArray();
        // Make a blacklist array so entries don't get counted multiple times
        boolean[] blacklist = new boolean[array2.length];
        // Create a new bag that is large enough to contain the worst-case senario
        // (They have everything in common)
        BagInterface<T> resultBag = new ResizableArrayBag<>(array1.length);
        // Add shared entries to result bag and blacklist the added entry's index
        // so it doesn't get added more than one time
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if ((!blacklist[j]) && (array1[i] == array2[j])) {
                    resultBag.add(array1[i]);
                    blacklist[j] = true;
                    break;
                }
            }
        }
        return resultBag;
    } // end intersection

    /** Compare this bag with another bag and remove all shared entries from this bag
     * @param subtractionBag Bag whose contents are used to remove the contents of the current bag
     * @return The resulting bag after performing the difference operation
    */
    public BagInterface<T> difference(BagInterface<T> subtractionBag) {
        // Turn contents into arrays
        T[] array1 = this.toArray();
        T[] array2 = subtractionBag.toArray();
        // Make a blacklist array so entries don't get counted multiple times
        boolean[] blacklist = new boolean[array2.length];
        // Create a new bag that is large enough to contain the worst-case senario
        // (They have nothing in common)
        BagInterface<T> resultBag = new ResizableArrayBag<>(array1.length);
        // Populate result bag with this bag's contents
        for (int i = 0; i < array1.length; i++) {
            resultBag.add(array1[i]);
        }
        // Remove shared entries from result bag and blacklist the removed entry's
        // index so it doesn't get removed more than one time
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if ((!blacklist[j]) && (array1[i] == array2[j])) {
                    resultBag.remove(array1[i]);
                    blacklist[j] = true;
                    break;
                }
            }
        }
        return resultBag;
    } // end difference
   
 	// Locates a given entry within the array bag.
	// Returns the index of the entry, if located,
	// or -1 otherwise.
    // Precondition: checkintegrity has been called.
	private int getIndexOf(T anEntry)
	{
		int where = -1;
		boolean found = false;
		int index = 0;
      
      while (!found && (index < numberOfEntries))
		{
			if (anEntry.equals(bag[index]))
			{
				found = true;
				where = index;
			} // end if
         index++;
		} // end while
      
      // Assertion: If where > -1, anEntry is in the array bag, and it
      // equals bag[where]; otherwise, anEntry is not in the array.
      
		return where;
	} // end getIndexOf
   
   // Removes and returns the entry at a given index within the array.
   // If no such entry exists, returns null.
   // Precondition: 0 <= givenIndex < numberOfEntries.
   // Precondition: checkintegrity has been called.
	private T removeEntry(int givenIndex)
	{
		T result = null;
      
		if (!isEmpty() && (givenIndex >= 0))
		{
         result = bag[givenIndex];          // Entry to remove
         int lastIndex = numberOfEntries - 1;
         bag[givenIndex] = bag[lastIndex];  // Replace entry to remove with last entry
         bag[lastIndex] = null;             // Remove reference to last entry
         numberOfEntries--;
		} // end if
      
      return result;
	} // end removeEntry
   
   // Returns true if the array bag is full, or false if not.
	private boolean isArrayFull()
	{
		return numberOfEntries >= bag.length;
	} // end isArrayFull
   
   // Doubles the size of the array bag.
   // Precondition: checkInitialization has been called.
	private void doubleCapacity()
	{
      int newLength = 2 * bag.length;
      checkCapacity(newLength);
      bag = Arrays.copyOf(bag, newLength);
	} // end doubleCapacity
   
   // Throws an exception if the client requests a capacity that is too large.
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   } // end checkCapacity
   
   // Throws an exception if receiving object is not initialized.
   private void checkintegrity()
   {
      if (!integrityOK)
         throw new SecurityException ("ArrayBag object is corrupt.");
   } // end checkintegrity
} // end ResizableArrayBag
