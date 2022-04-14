package Shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import edu.umbc.web.Similarity;

public class SemanticDistinction {
	public List<String> inList = new ArrayList<String>() ;
	public List<String> outList = new ArrayList<String>() ;
	private double d=0;
	private int difference =0;
	public final static double Threshold = 0.75;
	private Similarity sim;
	private boolean check= true;
	public SemanticDistinction(List<String> l)
	{
		if (check){
		sim = new Similarity();
		check=false;
		}
		
		this.inList = l;
	}
	
	public List<String> getOutList() throws IOException
	{
		removeValue();
		String check="";
		int l = inList.size();
		double a,max;
	
		for(int i=0; i<l; i++)
		{
			check="";
			a=0;max=0;
			for(int j=0; j+i<l; j++)
			{
				if(i!=j+i)
				{
					a=SemSimilarity(inList.get(i), inList.get(j+i));
					if(a>max){ max=a; check=inList.get(j+i);}
					System.out.println(inList.get(i)+" =||= "+inList.get(j+i)+" = "+ a);
				
				}

			}
			if(max<Threshold)
			{
				outList.add(inList.get(i));
			}else{
				
				System.out.println(inList.get(i)+"<=>"+check);
			}
			
		}
		 
		 return outList;
		
	}
	
	public double SemSimilarity(String a, String b) 
	{
	
		return sim.getSim(a, b);
	}
	
	public void removeValue()
	{
		for(int i=0; i<this.inList.size(); i++)
		{
			if(this.inList.get(i).toLowerCase().equals("global"))
			{
				this.inList.remove(i);
			}
		}
		
	}

}
