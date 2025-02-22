/**
 * 
 * Driver to create and check all the bags for Union, Intersection and Difference 
 * 
 * 
 * @author Arnav Singh
 */

import java.util.Arrays;

public class BagDriver{

    public static void main (String[] args){

        //create 2 bags
        BagInterface <String> bag_1 = new LinkedBag<>();
        BagInterface <String> bag_2 = new ResizableArrayBag<>();

        //Add elements to bag 1
        bag_1.add("a");
        bag_1.add("b");
        bag_1.add("c");
        bag_1.add("d");
        bag_1.add("d");

        //Add elements to bag 2

        bag_2.add("a");
        bag_2.add("a");
        bag_2.add("c");
        bag_2.add("c");
        bag_2.add("e");
        bag_2.add("f");

        //Union

        BagInterface <String> Result_union = bag_1.union(bag_2);
        System.out.println("Union: " + Arrays.toString(Result_union.toArray()));

        //Intersection

        BagInterface <String> Result_Intersection = bag_1.intersection(bag_2);
        System.out.println("Intersection: " + Arrays.toString(Result_Intersection.toArray()));


        //Difference

        BagInterface <String> Result_Difference = bag_1.difference(bag_2);
        System.out.println("Difference bag 1 - bag 2:  " + Arrays.toString(Result_Difference.toArray()));

        BagInterface <String> Result_Difference_2 = bag_2.difference(bag_1);
        System.out.println("Difference bag 2 - bag 1:  " + Arrays.toString(Result_Difference_2.toArray()));



    }
}

