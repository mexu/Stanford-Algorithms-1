package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Hw2 {
	public long compNum=0;
	public ArrayList<Integer> intlist= new ArrayList<Integer>();	
	private ArrayList<Integer> readIntCollection() {
		File file = new File("QuickSort.txt");
		ArrayList<Integer> collection = new ArrayList<Integer>();
		try {
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		int n=6;
		while (true){
		String line = reader.readLine();
		if (line == null) break;
		line = line.trim();
		if (line.equals("")) continue; // ignore possible blank lines
		Integer num = Integer.parseInt(line);
		collection.add(num);
		n--;
		}
		}
		catch (IOException e) {
		System.out.print(e.getMessage());
		}
		return collection;
		}
	
	private void Helper(){	
		intlist=this.readIntCollection(); 
		//for(int l=0;l<intlist.size();l++){
		//System.out.print(intlist.size()+",");
		//}
		QuickSort3(0,intlist.size()-1);
	}
	
	private void swaplist(int i, int j){	
		int x=intlist.get(i);
		intlist.set(i, intlist.get(j));
		intlist.set(j, x);
	}
	
	private void QuickSort(int start, int end){
		if(end<=start) return;
		this.compNum+=(end-start);
		int pivot=start;
		int i=start+1,j=start+1;
		while(j<intlist.size()){
			if(intlist.get(j)<intlist.get(pivot)){
				this.swaplist(j,i);
				i++;
			}
			j++;
		}
		i--;
		this.swaplist(i, pivot);
		this.QuickSort(start, i-1);
		this.QuickSort(i+1, end);
	}
	private void QuickSort2(int start, int end){
		if(end<=start) return;
		this.compNum+=(end-start);
		this.swaplist(end, start);
		int pivot=start;
		int i=start+1,j=start+1;
		while(j<intlist.size()){
			if(intlist.get(j)<intlist.get(pivot)){
				this.swaplist(j,i);
				i++;
			}
			j++;
		}
		i--;
		this.swaplist(i, pivot);
		this.QuickSort2(start, i-1);
		this.QuickSort2(i+1, end);
	}
	private void QuickSort3(int start, int end){
		//median have certain restrictions
		if(end<=start) return;
		this.compNum+=(end-start);
		int mid=(end+start)/2;
		int[] tmp={intlist.get(mid), intlist.get(end),intlist.get(start)};
		Arrays.sort(tmp);
		System.out.println(tmp[0]+" "+tmp[1]+" "+tmp[2]);
		if(intlist.get(mid)==tmp[1]){
			this.swaplist(mid, start);
		}else if(intlist.get(end)==tmp[1]){
			this.swaplist(end, start);
		}
		int pivot=start;
		int i=start+1,j=start+1;
		while(j<intlist.size()){
			if(intlist.get(j)<intlist.get(pivot)){
				this.swaplist(j,i);
				i++;
			}
			j++;
		}
		i--;
		this.swaplist(i, pivot);
		this.QuickSort3(start, i-1);
		this.QuickSort3(i+1, end);
	}
	
	public static void main(String[] args) {
		Hw2 tmp= new Hw2();
		ArrayList<Integer> t= new ArrayList<Integer>();
		tmp.Helper();
		System.out.println(tmp.compNum);
	}

}
