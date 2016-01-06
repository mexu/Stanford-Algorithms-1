package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Hw3 {
	
	private HashMap<Integer,ArrayList<Integer>> readIntCollection() {
		File file = new File("kargerMinCut.txt");
		HashMap<Integer,ArrayList<Integer>> graph = new HashMap<Integer,ArrayList<Integer>>();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			int n=6;
			while (true){
				String line = reader.readLine();
				if (line == null) break;
				line = line.trim();
				if (line.equals("")) continue; // ignore possible blank lines
				String[] str=line.split("\\s");
				int key=Integer.parseInt(str[0]);
				for(int i=0;i<str.length;i++){
					if(i==0) {
						graph.put(key, new ArrayList<Integer>());
					}else{
						graph.get(key).add(Integer.parseInt(str[i]));
					}
				}
				n--;
			}
		}
		catch (IOException e) {
			System.out.print(e.getMessage());
		}
		return graph;
		}
	
	private void helper() {
		HashMap<Integer,ArrayList<Integer>> graph=this.readIntCollection();
        int minimumCut = 0;
        
        for(int i = 0; i < 1000; i++)
        { //get original graph
            HashMap<Integer, ArrayList<Integer>> copyGraph = copyGraph(graph);
            int result = MinimumCut(copyGraph);
            if(minimumCut == 0) minimumCut = result;
            else{
                if(result < minimumCut){
                    minimumCut = result;
                }
            }
            System.out.println("Partial Result => " + result);
        }
        System.out.println("*** Minimum Cut => " + minimumCut + " ***");
	}
	 private int MinimumCut(HashMap<Integer, ArrayList<Integer>> graph)
	    {
	        //Iterate until there are only two nodes
	        while(graph.size() > 2){
	        	this.KargerAlgo(graph);
	        }
	            
	        //Return the Minimum Cut (the number of edges of both nodes is the same)
	        return graph.get((Integer)graph.keySet().toArray()[0]).size();
	    }

	private List<Integer> radomSelect(HashMap<Integer, ArrayList<Integer>> graph)
	    {
	    	//randomNum = minimum + (int)(Math.random()*maximum); 
	        ArrayList<Integer> randomItems = new ArrayList<Integer>();
	        
	        int nodeIndex = (int)(Math.random() * graph.keySet().size());
	        //System.out.println(graph.keySet().size()+"size here"+nodeIndex+"node");
	        Integer randomNode = (Integer)(graph.keySet().toArray()[nodeIndex]);
	        
	        
	        int edgeIndex = (int)(Math.random() * graph.get(randomNode).size());
	        //System.out.println(graph.get(randomNode).size()+"size here"+edgeIndex+"edge");
	        Integer randomEdge = graph.get(randomNode).get(edgeIndex);
	        
	        randomItems.add(randomNode);
	        randomItems.add(randomEdge);
	        return randomItems;
	    }

	private void KargerAlgo(HashMap<Integer, ArrayList<Integer>> graph){
        //Choose randome items
        List<Integer> randomItems = this.radomSelect(graph);
        Integer firstItem = randomItems.get(0);
        Integer secondItem = randomItems.get(1);
        //System.out.println(firstItem+" "+secondItem+ "aaa");
        ArrayList<Integer> firstItemList = graph.get(firstItem);
        ArrayList<Integer> secondItemList = graph.get(secondItem);
        
        //Add second list items to first list, remove second list items
        System.out.println(secondItemList+"size here"+secondItem);
        /////////////////focus here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        firstItemList.addAll(secondItemList);
        
        graph.remove(secondItem);
        System.out.println(secondItem+"removed"+graph.keySet());
        
        Iterator it = graph.keySet().iterator();
        while(it.hasNext()) {
            Integer currentKey = (Integer)it.next();
            ArrayList<Integer> currentItemList = graph.get(currentKey);
            for(Integer i : currentItemList) {
                if(i == secondItem) {
                	//Replace second item appeareances by first item
                    currentItemList.set(currentItemList.indexOf(i), firstItem);
                }
            }
        }
        //Remove loops
        ArrayList<Integer> itemsToRemove = new ArrayList<Integer>();
        for(Integer i : firstItemList) {
            if(i== firstItem) {
                itemsToRemove.add(i);
            }
        }
        firstItemList.removeAll(itemsToRemove);
	}    

	private HashMap<Integer, ArrayList<Integer>> copyGraph(HashMap<Integer, ArrayList<Integer>> graph){
	    HashMap<Integer, ArrayList<Integer>> graphCopy = new HashMap<Integer, ArrayList<Integer>>();   
	    Iterator it = graph.keySet().iterator();
	        
	    while(it.hasNext()) {	//iterator object to integer
	        Integer currentKey = (Integer)it.next();
	        ArrayList<Integer> currentItemList = graph.get(currentKey);
	        graphCopy.put(currentKey, new ArrayList<Integer>(currentItemList));
	    }
	    return graphCopy;
	}
	
	public static void main(String[] args) {
		//main function
		Hw3 test= new Hw3();
		HashMap<Integer,ArrayList<Integer>> t = test.readIntCollection();
		/*for(int i :t.keySet()){
			System.out.print(i+" key: ");
			for(int j=0;j<t.get(i).size();j++){
				System.out.print(t.get(i).get(j)+" ");
			}
			System.out.println(" ");
		}*/
		test.helper();
		System.out.println(t.keySet().size());
		Iterator aaa=t.keySet().iterator();
		while(aaa.hasNext()){
			System.out.println(t.get((Integer)aaa.next()));
		}
		//test.helper();
		
	}
}
