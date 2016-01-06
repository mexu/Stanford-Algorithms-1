package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Hw1 {
	private ArrayList<Integer> readIntCollection() {
		File file = new File("IntegerArray.txt");
		ArrayList<Integer> collection = new ArrayList<Integer>();
		try {
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		//int n=8000;
		while (true){
		String line = reader.readLine();
		if (line == null) break;
		line = line.trim();
		if (line.equals("")) continue; // ignore possible blank lines
		Integer num = Integer.parseInt(line);
		collection.add(num);
		//n--;
		}
		}
		catch (IOException e) {
		System.out.print(e.getMessage());
		}
		return collection;
		}
	
	private Long helper(){
		ArrayList<Integer> testlist=new ArrayList<Integer>();
		/*testlist.add(3);
		testlist.add(4);
		testlist.add(1);
		testlist.add(2);
		testlist.add(20);
		testlist.add(27);
		testlist.add(10);
		testlist.add(16);
		testlist.add(8);
		testlist.add(5);
		testlist.add(7);*/
		ArrayList<Integer> intlist=this.readIntCollection();
		Helpclass ans = fastsort(intlist,0,intlist.size()-1);
		return ans.invNum;
	}
	class Helpclass{
		public ArrayList<Integer> res;
		public long invNum;
		Helpclass(ArrayList<Integer> res, long invNum){
			this.res=res;
			this.invNum=invNum;
		}
	}
	private Helpclass fastsort(ArrayList<Integer> intlist, int start, int end){
		
		if(end-start==1){
			//System.out.println(start+"start end"+end+"==1");
			ArrayList<Integer> res= new ArrayList<Integer>();
			if(intlist.get(end)>intlist.get(start)){
				//System.out.println("should be 0");
				res.add(intlist.get(start));
				res.add(intlist.get(end));
				return new Helpclass(res,0);
			} 
			if(intlist.get(end)<intlist.get(start)) {
				//System.out.println("should be 1");
				res.add(intlist.get(end));
				res.add(intlist.get(start));
				return new Helpclass(res,1);
			}
		}else if(end-start<=0) {
			//System.out.println(start+"start end"+end+""+"<=0");
			ArrayList<Integer> res= new ArrayList<Integer>();
			res.add(intlist.get(start));
			return new Helpclass(res,0);
		}
		
		int mid= (start+end)/2;
		//System.out.println(start+""+end+""+mid);
		Helpclass leftinv=fastsort(intlist,start,mid);
		Helpclass rightinv=fastsort(intlist,mid+1,end);
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		long inverNum= 0;
		int i=0,j=0;
		
		for(int n=0;n<leftinv.res.size()+rightinv.res.size();n++){
			if(i<leftinv.res.size() && j<rightinv.res.size() && leftinv.res.get(i)<=rightinv.res.get(j)){
				res.add(leftinv.res.get(i));
				i++;
			}else if(i<leftinv.res.size() && j<rightinv.res.size() && leftinv.res.get(i)>=rightinv.res.get(j)){
				res.add(rightinv.res.get(j));
				j++;
				inverNum+=leftinv.res.size()-i;
			}else if(i>=leftinv.res.size()){
				res.add(rightinv.res.get(j));
				j++;
			}else {
				res.add(leftinv.res.get(i));
				i++;
			}
		}
		//for(int x=0;x<res.size();x++){
		//	System.out.println(res.get(x)+"here");
		//}
		inverNum = inverNum+leftinv.invNum+rightinv.invNum;
		return new Helpclass(res,inverNum);
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> t= new ArrayList<Integer>();
		Hw1 mytest= new Hw1();
		//=mytest.readIntCollection();
		//for(int i=0;i<t.size();i++){
		//	System.out.println(t.get(i));
		//}
		long ans=mytest.helper();
		System.out.println(ans);
	}

}
