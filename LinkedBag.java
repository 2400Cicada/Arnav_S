
public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize(){
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty(){
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry){
        
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;

        firstNode = newNode;
        numberOfEntries++;

        return true;
    }

    @Override
    public T remove(){
        T result = null;

        if(firstNode != null){
            result = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
        }

        return result;
    }

    @Override
    public boolean remove(T anEntry){
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);

        if(nodeN != null){
            nodeN.data = firstNode.data;
            firstNode = firstNode.next;

            numberOfEntries--;
            result = true;
        }

        return result;
    }

    @Override
    public void clear(){
        firstNode = null;
    }

    @Override
    public int getFrequencyOf(T anEntry){
        int frequency = 0;

        int counter = 0;
        Node currentNode = firstNode;
        while((counter < numberOfEntries) && (currentNode != null)){
            if(anEntry.equals(currentNode.data)){
                frequency++;
            }

            counter++;
            currentNode = currentNode.next;
        }

        return frequency;
    }

    @Override
    public boolean contains(T anEntry){
        boolean found = false;
        Node currentNode = firstNode;

        while(!found && (currentNode != null)){
            if(anEntry.equals(currentNode.data)){
                found = true;
            }
            else{
                currentNode = currentNode.next;
            }
        }

        return found;
    }

    @Override
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[]result = (T[])new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while((index < numberOfEntries) && (currentNode != null)){
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }

        return result;
    }

    private Node getReferenceTo(T anEntry){
        boolean found = false;
        Node currentNode = firstNode;

        while(!found && (currentNode != null)){
            if(anEntry.equals(currentNode.data)){
                found = true;
            }
            else{
                currentNode = currentNode.next;
            }
        }

        return currentNode;
    }

    @Override
    public BagInterface<T> union(BagInterface<T> bagToUnify){
        T[] array1 = this.toArray();
        T[] array2 = bagToUnify.toArray();

        int resultLength = array1.length + array2.length;
        BagInterface<T> resultBag = new ResizableArrayBag<>(resultLength);

        for(int i = 0; i < array1.length; i++){
            resultBag.add(array1[i]);
        }

        for(int i = 0; i < array2.length; i++){
            resultBag.add(array2[i]);
        }

        return resultBag;
    }

    @Override
    public BagInterface<T> intersection(BagInterface<T> bagToIntersect){
        
        T[] array1 = this.toArray();
        T[] array2 = bagToIntersect.toArray();
        int resultLength;
        int difference = 0;

        if(array1.length > array2.length){
            resultLength = array1.length;
        }
        else{
            resultLength = array2.length;
        }

        BagInterface<T> resultBag = new ResizableArrayBag<>(resultLength);
        BagInterface<T> blacklistBag = new ResizableArrayBag<>(resultLength);

        
        for(int i = 0; i < resultLength; i++){
            if(this.contains(array1[i]) && bagToIntersect.contains(array1[i]) && !blacklistBag.contains(array1[i])){
                difference = Math.abs(this.getFrequencyOf(array1[i]) - bagToIntersect.getFrequencyOf(array1[i]));
                while(difference != 0){
                    resultBag.add(array1[i]);
                    blacklistBag.add(array1[i]);
                    difference--;
                }
            }
        }

        return resultBag;
    }

    @Override
    public BagInterface<T> difference(BagInterface<T> subtractionBag){

        T[] array1 = this.toArray();

        BagInterface<T> resultBag = new ResizableArrayBag<>(this.getCurrentSize());
        BagInterface<T> blacklistBag = new ResizableArrayBag<>(subtractionBag.getCurrentSize());

        for(int i = 0; i < array1.length; i++){
            if(!subtractionBag.contains(array1[i]) && !blacklistBag.contains(array1[i])){
                resultBag.add(array1[i]);
            }

            if(resultBag.getFrequencyOf(array1[i]) == (this.getFrequencyOf(array1[i]) - subtractionBag.getFrequencyOf(array1[i]))){
                blacklistBag.add(array1[i]);
            }
        }

        return resultBag;
    }

    private class Node{
        private T data;
        private Node next;

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode){
            data = dataPortion;
            next = nextNode;
        }
    }
}
