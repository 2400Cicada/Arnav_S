
public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    }

    public int getCurrentSize(){
        return numberOfEntries;
    }

    public boolean isEmpty(){
        return numberOfEntries == 0;
    }

    public boolean add(T[] newEntry){
        
        for(int i = 0; i < newEntry.length; i++){
            Node newNode = new Node(newEntry[i]);
            newNode.next = firstNode;
            firstNode = newNode;
            numberOfEntries++;
        }


        return true;
    }

    public boolean remove(T[] anEntry){
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

    public void clear(){
        firstNode = null;
    }

    public int getFrequencyOf(T[] anEntry){
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

    public boolean contains(T[] anEntry){
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

    private Node getReferenceTo(T[] anEntry){
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
