/**
*
*   Initialization of the Linked Bag and Node, and there different data types and methods.
*
*
*   @author Warren Maxwell
*/

public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    /** Creates an empty bag */
    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end Constructor

    /** Gets the number of entries currently in this bag.
     * @return The integer number of entries currently in this bag.
     */
    @Override
    public int getCurrentSize(){
        return numberOfEntries;
    } // end getCurrentSize

    /** Sees whether this bag is empty.
     * @return True if this bag is empty, or false if not
     */
    @Override
    public boolean isEmpty(){
        return numberOfEntries == 0;
    } // end isEmpty

    /** Adds a new entry to this bag.
     * @param newEntry The object to be added as a new entry
     * @return True if the addition is successful, or false if not.
     */
    @Override
    public boolean add(T newEntry){ // OutOfMemoryError possible
        // Add to beginning of chain
        Node newNode = new Node(newEntry);
        newNode.next = firstNode; // Make new node reference rest of chain
                                    // (firstNode is null if chain is empty)

        firstNode = newNode;    // New node is at beginning of chain
        numberOfEntries++;

        return true;
    } // end add

    /** Removes one unspecified entry from this bag, if possible.
     * @return Either the removed entry, if the removal was successful or null.
     */
    @Override
    public T remove(){
        T result = null;

        if(firstNode != null){
            result = firstNode.getData();
            firstNode = firstNode.getNextNode(); // remove first node from chain
            numberOfEntries--;
        } // end if

        return result;
    } //end remove

    /** Removes on occurence of a given entry from this bag, if possible.
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, or false otherwise. 
     */
    @Override
    public boolean remove(T anEntry){
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);

        if(nodeN != null){
            // Replace located entry with entry in first node
            nodeN.setData(firstNode.getData());
            // Remove first node
            firstNode = firstNode.getNextNode();

            numberOfEntries--;
            result = true;
        } // end if

        return result;
    } // end remove

    /** Removes all entries from this bag. */
    @Override
    public void clear(){
        while(!isEmpty())
            remove();
    } // end clear

    /** Counts the number of times a given entry appears in this bag.
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in this bag.
     */
    @Override
    public int getFrequencyOf(T anEntry){
        int frequency = 0;

        int counter = 0;
        Node currentNode = firstNode;
        while((counter < numberOfEntries) && (currentNode != null)){
            if(anEntry.equals(currentNode.getData())){
                frequency++;
            } // end if

            counter++;
            currentNode = currentNode.getNextNode();
        } // end while

        return frequency;
    } // end getFrequencyOf

    /** Tests whether this bag contains a given entry.
     * @param anEntry the entry to locate.
     * @return True if the bag contains anEntry, or false otherwise.
     */
    @Override
    public boolean contains(T anEntry){
        boolean found = false;
        Node currentNode = firstNode;

        while(!found && (currentNode != null)){
            if(anEntry.equals(currentNode.getData())){
                found = true;
            }
            else{
                currentNode = currentNode.getNextNode();
            }
        } // end while

        return found;
    } // end contains

    /** Retrieves all entries that are in this bag.
     * @return A newly allocated array of all the entries in this bag.
     */
    @Override
    public T[] toArray(){
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[]result = (T[])new Object[numberOfEntries]; // Unchecked cast

        int index = 0;
        Node currentNode = firstNode;
        while((index < numberOfEntries) && (currentNode != null)){
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        } // end while

        return result;
    } // end toArray

    // Locates a given entry within this bag.
    // Returns a reference to the node containing the // entry, if located,
    // or null otherwise.
    private Node getReferenceTo(T anEntry){
        boolean found = false;
        Node currentNode = firstNode;

        while(!found && (currentNode != null)){
            if(anEntry.equals(currentNode.getData())){
                found = true;
            }
            else{
                currentNode = currentNode.getNextNode();
            }
        } // end while

        return currentNode;
    } // end getReferenceTo

    /** Combines all the contents of two bags together.
     * @param bagToUnify The bag that is being used in union with current bag.
     * @return A new bag containing the contents of the union of the two bags used.
     */
    @Override
    public BagInterface<T> union(BagInterface<T> bagToUnify){
        // Create the bags and arrays
        T[] array1 = this.toArray();
        T[] array2 = bagToUnify.toArray();

        BagInterface<T> resultBag = new LinkedBag<>();

        // Combine both arrays into new bag
        for(int i = 0; i < array1.length; i++){
            resultBag.add(array1[i]);
        } // end for

        for(int i = 0; i < array2.length; i++){
            resultBag.add(array2[i]);
        } // end for

        return resultBag;
    } // end union

    /** Combines the contents that two bags have in common
     * @param bagToIntersect The bag being used in intersection if the current bag.
     * @return A new bag containing the contents of the intersection of the two bags.
     */
    @Override
    public BagInterface<T> intersection(BagInterface<T> bagToIntersect){
        //Create bags and arrays
        T[] array1 = this.toArray();
        int resultLength;
        int difference = 0;

        resultLength = array1.length;

        BagInterface<T> resultBag = new LinkedBag<>();
        BagInterface<T> blacklistBag = new LinkedBag<>();

        // loop through new array and check for common values between the two bags
        for(int i = 0; i < resultLength; i++){
            if(this.contains(array1[i]) && bagToIntersect.contains(array1[i]) && !blacklistBag.contains(array1[i])){
                difference = Math.min(this.getFrequencyOf(array1[i]), bagToIntersect.getFrequencyOf(array1[i]));
                while(difference != 0){
                    resultBag.add(array1[i]);
                    blacklistBag.add(array1[i]);
                    difference--;
                }
            }
        } // end for

        return resultBag;
    } // end intersection

    /** Creates a new bag which doesn't include the common values from the two used bags.
     * @param subtractionBag Bag whose contents are used to remove the contents of the current bag
     * @return A new bag containing the contents that are left in the current bag after removing
     * contents using the subtractionBag.
      */
    @Override
    public BagInterface<T> difference(BagInterface<T> subtractionBag){

        // Create arrays and Bags
        T[] array1 = this.toArray();

        BagInterface<T> resultBag = new LinkedBag<>();
        BagInterface<T> blacklistBag = new LinkedBag<>();

        // Iterate through array1, checking for differences in blacklistbag and subtractionbag
        for(int i = 0; i < array1.length; i++){
            if(!subtractionBag.contains(array1[i]) && !blacklistBag.contains(array1[i])){
                resultBag.add(array1[i]);
            }

            if(resultBag.getFrequencyOf(array1[i]) == (this.getFrequencyOf(array1[i]) - subtractionBag.getFrequencyOf(array1[i]))){
                blacklistBag.add(array1[i]);
            }
        } // end for

        return resultBag;
    } // end difference

    private class Node{
        private T data; // entry in bag
        private Node next; // link to next node

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode){
            data = dataPortion;
            next = nextNode;
        } // end constructor

        private T getData(){
            return data;
        } // end getData

        private void setData(T newData){
            data = newData;
        } // end setData

        private Node getNextNode(){
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode){
            next = nextNode;
        } // end setNextNode
    } // end Node
}
