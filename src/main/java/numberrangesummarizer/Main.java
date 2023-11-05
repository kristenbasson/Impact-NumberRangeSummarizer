package numberrangesummarizer;

import java.util.Collection;

public class Main {
    public static void main (String [] args){
        // example usage
        CollectNumbers myNumberCollector = new CollectNumbers();

        // collect the numbers provided in the input String
        Collection<Integer> myNumbers = myNumberCollector.collect("5,4,-4,-2,1,3,6,9");

        // summarize the collection
        String numSummarizedCollection = myNumberCollector.summarizeCollection(myNumbers);

        // print the output
        System.out.println(numSummarizedCollection);
    }
}
