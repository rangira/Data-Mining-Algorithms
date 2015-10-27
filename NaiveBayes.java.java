import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NaiveBayes {
	
	static List<List<Integer>>l=new ArrayList<List<Integer>>();
	static Map<Integer,Map<Integer,Double>> conditionalProbAttr1=new HashMap<Integer,Map<Integer,Double>>();
	static Map<Integer,Map<Integer,Double>> conditionalProbAttr2=new HashMap<Integer,Map<Integer,Double>>();
	static Map<Integer,Map<Integer,Double>> conditionalProbAttr3=new HashMap<Integer,Map<Integer,Double>>();
	static Map<Integer,Map<Integer,Double>> conditionalProbAttr4=new HashMap<Integer,Map<Integer,Double>>();
	static Map<Integer,Map<Integer,Double>> conditionalProbAttr5=new HashMap<Integer,Map<Integer,Double>>();
	static Map<Integer,Map<Integer,Double>> conditionalProbAttr7=new HashMap<Integer,Map<Integer,Double>>();
	static Map<Integer,Map<Integer,Double>> conditionalProbAttr8=new HashMap<Integer,Map<Integer,Double>>();
	static Map<Integer,Map<Integer,Double>> conditionalProbAttr9=new HashMap<Integer,Map<Integer,Double>>();
	static Map<Integer,Double> priorLabels=new HashMap<Integer,Double>();

   // @SuppressWarnings("rawtypes")
	public static List<Integer> readList(int i) throws Exception
	{
		 	List<Integer> row = new ArrayList<Integer>();
		    String[] b=null;int j=i;
	        BufferedReader br = new BufferedReader(new FileReader("F://data.csv"));
	        String splitBy = ",";
	        String line =null;
	        while((line=br.readLine())!=null){
	        	
	              b = line.split(splitBy);
	              
	              row.add(Integer.parseInt(b[j]));
	             
	            }
	          br.close();
	          
		return row;
	}
	
	
    public static void main(String[] args) throws Exception {
                
                //List<List<Integer>>l=new ArrayList<List<Integer>>();
                List<Integer> row = new ArrayList<Integer>();
                
            
                	for( int i=0;i<11;i++)
                	{
                		row=readList(i);
                		l.add(row);
                		//System.out.println(row);
                	}
                	
                	
                prior();
        
        

  }
    
    public static List<Integer> prior()
    {
    	List<Integer> row = new ArrayList<Integer>();
    	
    	/* pick distinct elements from list*/
    	
    	//ArrayList<String> values = null; //Your values
    	HashSet<Integer> uniqueValues = new HashSet<Integer>(l.get(1));
    	Iterator<Integer> keyIterator =uniqueValues.iterator();
		System.out.println(uniqueValues);
////////////////////Storing values of attr1 grouped according to attribute 6////////////////////////////		
		Map<Integer,List<Integer>> m=new HashMap<Integer,List<Integer>>();
		//List<Integer> mapValues = new ArrayList<Integer>();

		   while(keyIterator.hasNext()){ 
			Integer keyName = keyIterator.next(); 
			List<Integer> mapValues = new ArrayList<Integer>();
			for(int i=0;i<l.get(1).size();i++)
			{
				
				if(l.get(6).get(i).equals(keyName))
				{
					mapValues.add(l.get(1).get(i));
				}
					
			}
			 
		    m.put(keyName,mapValues);
		    
		   
		    
		    
		    }
		    
		    System.out.println( m.get(1));
////////////////////////////*Calculate the conditional probabilities for attribute 2  P(Attr2/Attr7) for all the categories*/
		    //Map<Integer,Map<Integer,Double>> conditionalProbAttr1=new HashMap<Integer,Map<Integer,Double>>();
		    Double cond=null;
		    double temp;
		     keyIterator =m.keySet().iterator();
		    // System.out.println(m.keySet());
		    while(keyIterator.hasNext()){
		    Integer keyName = keyIterator.next();
		    Map<Integer,Double> mapValues = new HashMap<Integer,Double>();
		    List<Integer> tempList=new ArrayList<Integer>();
		    tempList=m.get(keyName);
		   // System.out.println(tempList);
		    uniqueValues=new HashSet<Integer>(tempList);
		   // System.out.println("unique values in list"+uniqueValues);
		    
		    Iterator<Integer> keyIterator1 =uniqueValues.iterator();
		    Integer keyName1=null;
		    while(keyIterator1.hasNext()){
		     keyName1=keyIterator1.next();
		   //  System.out.println("keyname is"+keyName1);
		    int freq=Collections.frequency(tempList, keyName1);
		    
		   // System.out.println("frequency is"+freq);
		    temp=(double)freq/(double)tempList.size();
		    cond=(Double)(temp);
		    //System.out.println("cond is"+temp);
		    
		    mapValues.put(keyName1, cond);
		    }
		    
		    conditionalProbAttr1.put(keyName, mapValues);
		    
		    }
		    System.out.println(conditionalProbAttr1.get(1));
/////////////////////// /* Conditional Probability Attr1 ends*////////////////////////
		    
//////////////Storing values of attr3 grouped according to attribute 7/////////////////////////////////		    
		    uniqueValues = new HashSet<Integer>(l.get(2));
	    	 keyIterator =uniqueValues.iterator();

			   while(keyIterator.hasNext()){ 
				Integer keyName = keyIterator.next(); 
				List<Integer> mapValues = new ArrayList<Integer>();
				
				for(int i=0;i<l.get(2).size();i++)
				{
					
					if(l.get(6).get(i).equals(keyName))
					{
						mapValues.add(l.get(2).get(i));
					}
						
				}
				 
			    m.put(keyName,mapValues);
			    
			   
			    
			    
			    }
			    
			   System.out.println( m.get(4));
//////////Ending Storing values of Attribute 3 grouped according to Attribute 7///////////////////////
			   
			   
////////////////////////////*Calculate the conditional probabilities for attribute 3  P(Attr2/Attr7) for all the categories*/
//Map<Integer,Map<Integer,Double>> conditionalProbAttr2=new HashMap<Integer,Map<Integer,Double>>();
 cond=null;
 temp=0;
keyIterator =m.keySet().iterator();
// System.out.println(m.keySet());
while(keyIterator.hasNext()){
Integer keyName = keyIterator.next();
Map<Integer,Double> mapValues = new HashMap<Integer,Double>();
List<Integer> tempList=new ArrayList<Integer>();
tempList=m.get(keyName);
// System.out.println(tempList);
uniqueValues=new HashSet<Integer>(tempList);
// System.out.println("unique values in list"+uniqueValues);

Iterator<Integer> keyIterator1 =uniqueValues.iterator();
Integer keyName1=null;
while(keyIterator1.hasNext()){
keyName1=keyIterator1.next();
//  System.out.println("keyname is"+keyName1);
int freq=Collections.frequency(tempList, keyName1);

// System.out.println("frequency is"+freq);
temp=(double)freq/(double)tempList.size();
cond=(Double)(temp);
//System.out.println("cond is"+temp);

mapValues.put(keyName1, cond);
}

conditionalProbAttr2.put(keyName, mapValues);

}
System.out.println("check if 4 is there "+conditionalProbAttr2.get(6));
/////////////////////// /* Conditional Probability Attr3 ends*////////////////////////


//////////////Storing values of attr4 grouped according to attribute 7/////////////////////////////////		    
uniqueValues = new HashSet<Integer>(l.get(3));
keyIterator =uniqueValues.iterator();

 while(keyIterator.hasNext()){ 
	Integer keyName = keyIterator.next(); 
	List<Integer> mapValues = new ArrayList<Integer>();
	
	for(int i=0;i<l.get(3).size();i++)
	{
		
		if(l.get(6).get(i).equals(keyName))
		{
			mapValues.add(l.get(3).get(i));
		}
			
	}
	 
  m.put(keyName,mapValues);
  
 
  
  
  }
  
 System.out.println( m.get(4));
//////////Ending Storing values of Attribute 4 grouped according to Attribute 7///////////////////////
 
////////////////////////////*Calculate the conditional probabilities for attribute 4  P(Attr4/Attr7) for all the categories*/
//Map<Integer,Map<Integer,Double>> conditionalProbAttr3=new HashMap<Integer,Map<Integer,Double>>();
cond=null;
temp=0;
keyIterator =m.keySet().iterator();
//System.out.println(m.keySet());
while(keyIterator.hasNext()){
Integer keyName = keyIterator.next();
Map<Integer,Double> mapValues = new HashMap<Integer,Double>();
List<Integer> tempList=new ArrayList<Integer>();
tempList=m.get(keyName);
//System.out.println(tempList);
uniqueValues=new HashSet<Integer>(tempList);
//System.out.println("unique values in list"+uniqueValues);

Iterator<Integer> keyIterator1 =uniqueValues.iterator();
Integer keyName1=null;
while(keyIterator1.hasNext()){
keyName1=keyIterator1.next();
//System.out.println("keyname is"+keyName1);
int freq=Collections.frequency(tempList, keyName1);

//System.out.println("frequency is"+freq);
temp=(double)freq/(double)tempList.size();
cond=(Double)(temp);
//System.out.println("cond is"+temp);

mapValues.put(keyName1, cond);
}

conditionalProbAttr3.put(keyName, mapValues);

}
System.out.println(conditionalProbAttr3.get(4));
/////////////////////// /* Conditional Probability Attr4 ends*////////////////////////


//////////////Storing values of attr5 grouped according to attribute 7/////////////////////////////////		    
uniqueValues = new HashSet<Integer>(l.get(4));
keyIterator =uniqueValues.iterator();

while(keyIterator.hasNext()){ 
Integer keyName = keyIterator.next(); 
List<Integer> mapValues = new ArrayList<Integer>();

for(int i=0;i<l.get(4).size();i++)
{

if(l.get(6).get(i).equals(keyName))
{
mapValues.add(l.get(4).get(i));
}

}

m.put(keyName,mapValues);




}

System.out.println( m.get(4));
//////////Ending Storing values of Attribute 5 grouped according to Attribute 7///////////////////////

////////////////////////////*Calculate the conditional probabilities for attribute 5 P(Attr5/Attr7) for all the categories*/
//Map<Integer,Map<Integer,Double>> conditionalProbAttr4=new HashMap<Integer,Map<Integer,Double>>();
cond=null;
temp=0;
keyIterator =m.keySet().iterator();
//System.out.println(m.keySet());
while(keyIterator.hasNext()){
Integer keyName = keyIterator.next();
Map<Integer,Double> mapValues = new HashMap<Integer,Double>();
List<Integer> tempList=new ArrayList<Integer>();
tempList=m.get(keyName);
//System.out.println(tempList);
uniqueValues=new HashSet<Integer>(tempList);
//System.out.println("unique values in list"+uniqueValues);

Iterator<Integer> keyIterator1 =uniqueValues.iterator();
Integer keyName1=null;
while(keyIterator1.hasNext()){
keyName1=keyIterator1.next();
//System.out.println("keyname is"+keyName1);
int freq=Collections.frequency(tempList, keyName1);

//System.out.println("frequency is"+freq);
temp=(double)freq/(double)tempList.size();
cond=(Double)(temp);
//System.out.println("cond is"+temp);

mapValues.put(keyName1, cond);
}

conditionalProbAttr4.put(keyName, mapValues);

}
System.out.println(conditionalProbAttr4.get(4));
/////////////////////// /* Conditional Probability Attr5 ends*////////////////////////

//////////////Storing values of attr6 grouped according to attribute 7/////////////////////////////////		    
uniqueValues = new HashSet<Integer>(l.get(5));
keyIterator =uniqueValues.iterator();

while(keyIterator.hasNext()){ 
Integer keyName = keyIterator.next(); 
List<Integer> mapValues = new ArrayList<Integer>();

for(int i=0;i<l.get(5).size();i++)
{

if(l.get(6).get(i).equals(keyName))
{
mapValues.add(l.get(5).get(i));
}

}

m.put(keyName,mapValues);




}

System.out.println( m.get(4));
//////////Ending Storing values of Attribute 6 grouped according to Attribute 7///////////////////////

////////////////////////////*Calculate the conditional probabilities for attribute 6 P(Attr6/Attr7) for all the categories*/
//Map<Integer,Map<Integer,Double>> conditionalProbAttr5=new HashMap<Integer,Map<Integer,Double>>();
cond=null;
temp=0;
keyIterator =m.keySet().iterator();
//System.out.println(m.keySet());
while(keyIterator.hasNext()){
Integer keyName = keyIterator.next();
Map<Integer,Double> mapValues = new HashMap<Integer,Double>();
List<Integer> tempList=new ArrayList<Integer>();
tempList=m.get(keyName);
//System.out.println(tempList);
uniqueValues=new HashSet<Integer>(tempList);
//System.out.println("unique values in list"+uniqueValues);

Iterator<Integer> keyIterator1 =uniqueValues.iterator();
Integer keyName1=null;
while(keyIterator1.hasNext()){
keyName1=keyIterator1.next();
//System.out.println("keyname is"+keyName1);
int freq=Collections.frequency(tempList, keyName1);

//System.out.println("frequency is"+freq);
temp=(double)freq/(double)tempList.size();
cond=(Double)(temp);
//System.out.println("cond is"+temp);

mapValues.put(keyName1, cond);
}

conditionalProbAttr5.put(keyName, mapValues);

}
System.out.println(conditionalProbAttr5.get(4));
/////////////////////// /* Conditional Probability Attr6 ends*////////////////////////

//////////////Storing values of attr8 grouped according to attribute 7/////////////////////////////////		    
uniqueValues = new HashSet<Integer>(l.get(7));
keyIterator =uniqueValues.iterator();

while(keyIterator.hasNext()){ 
Integer keyName = keyIterator.next(); 
List<Integer> mapValues = new ArrayList<Integer>();

for(int i=0;i<l.get(7).size();i++)
{

if(l.get(6).get(i).equals(keyName))
{
mapValues.add(l.get(7).get(i));
}

}

m.put(keyName,mapValues);




}

System.out.println( m.get(4));
//////////Ending Storing values of Attribute 8 grouped according to Attribute 7///////////////////////


////////////////////////////*Calculate the conditional probabilities for attribute 8 P(Attr8/Attr7) for all the categories*/
//Map<Integer,Map<Integer,Double>> conditionalProbAttr7=new HashMap<Integer,Map<Integer,Double>>();
cond=null;
temp=0;
keyIterator =m.keySet().iterator();
//System.out.println(m.keySet());
while(keyIterator.hasNext()){
Integer keyName = keyIterator.next();
Map<Integer,Double> mapValues = new HashMap<Integer,Double>();
List<Integer> tempList=new ArrayList<Integer>();
tempList=m.get(keyName);
//System.out.println(tempList);
uniqueValues=new HashSet<Integer>(tempList);
//System.out.println("unique values in list"+uniqueValues);

Iterator<Integer> keyIterator1 =uniqueValues.iterator();
Integer keyName1=null;
while(keyIterator1.hasNext()){
keyName1=keyIterator1.next();
//System.out.println("keyname is"+keyName1);
int freq=Collections.frequency(tempList, keyName1);

//System.out.println("frequency is"+freq);
temp=(double)freq/(double)tempList.size();
cond=(Double)(temp);
//System.out.println("cond is"+temp);

mapValues.put(keyName1, cond);
}

conditionalProbAttr7.put(keyName, mapValues);

}
System.out.println(conditionalProbAttr7.get(4));
/////////////////////// /* Conditional Probability Attr8 ends*////////////////////////

//////////////Storing values of attr9 grouped according to attribute 7/////////////////////////////////		    
uniqueValues = new HashSet<Integer>(l.get(8));
keyIterator =uniqueValues.iterator();

while(keyIterator.hasNext()){ 
Integer keyName = keyIterator.next(); 
List<Integer> mapValues = new ArrayList<Integer>();

for(int i=0;i<l.get(8).size();i++)
{

if(l.get(6).get(i).equals(keyName))
{
mapValues.add(l.get(8).get(i));
}

}

m.put(keyName,mapValues);




}

System.out.println( m.get(4));
//////////Ending Storing values of Attribute 9 grouped according to Attribute 7///////////////////////


////////////////////////////*Calculate the conditional probabilities for attribute 9 P(Attr9/Attr7) for all the categories*/
//Map<Integer,Map<Integer,Double>> conditionalProbAttr8=new HashMap<Integer,Map<Integer,Double>>();
cond=null;
temp=0;
keyIterator =m.keySet().iterator();
//System.out.println(m.keySet());
while(keyIterator.hasNext()){
Integer keyName = keyIterator.next();
Map<Integer,Double> mapValues = new HashMap<Integer,Double>();
List<Integer> tempList=new ArrayList<Integer>();
tempList=m.get(keyName);
//System.out.println(tempList);
uniqueValues=new HashSet<Integer>(tempList);
//System.out.println("unique values in list"+uniqueValues);

Iterator<Integer> keyIterator1 =uniqueValues.iterator();
Integer keyName1=null;
while(keyIterator1.hasNext()){
keyName1=keyIterator1.next();
//System.out.println("keyname is"+keyName1);
int freq=Collections.frequency(tempList, keyName1);

//System.out.println("frequency is"+freq);
temp=(double)freq/(double)tempList.size();
cond=(Double)(temp);
//System.out.println("cond is"+temp);

mapValues.put(keyName1, cond);
}

conditionalProbAttr8.put(keyName, mapValues);

}
System.out.println(conditionalProbAttr8.get(4));
/////////////////////// /* Conditional Probability Attr9 ends*////////////////////////

//////////////Storing values of attr10 grouped according to attribute 7/////////////////////////////////		    
uniqueValues = new HashSet<Integer>(l.get(9));
keyIterator =uniqueValues.iterator();

while(keyIterator.hasNext()){ 
Integer keyName = keyIterator.next(); 
List<Integer> mapValues = new ArrayList<Integer>();

for(int i=0;i<l.get(9).size();i++)
{

if(l.get(6).get(i).equals(keyName))
{
mapValues.add(l.get(9).get(i));
}

}

m.put(keyName,mapValues);




}

System.out.println( m.get(4));
//////////Ending Storing values of Attribute 10 grouped according to Attribute 7///////////////////////

////////////////////////////*Calculate the conditional probabilities for attribute 10 P(Attr10/Attr7) for all the categories*/
//Map<Integer,Map<Integer,Double>> conditionalProbAttr9=new HashMap<Integer,Map<Integer,Double>>();
cond=null;
temp=0;
keyIterator =m.keySet().iterator();
//System.out.println(m.keySet());
while(keyIterator.hasNext()){
Integer keyName = keyIterator.next();
Map<Integer,Double> mapValues = new HashMap<Integer,Double>();
List<Integer> tempList=new ArrayList<Integer>();
tempList=m.get(keyName);
//System.out.println(tempList);
uniqueValues=new HashSet<Integer>(tempList);
//System.out.println("unique values in list"+uniqueValues);

Iterator<Integer> keyIterator1 =uniqueValues.iterator();
Integer keyName1=null;
while(keyIterator1.hasNext()){
keyName1=keyIterator1.next();
//System.out.println("keyname is"+keyName1);
int freq=Collections.frequency(tempList, keyName1);

//System.out.println("frequency is"+freq);
temp=(double)freq/(double)tempList.size();
cond=(Double)(temp);
//System.out.println("cond is"+temp);

mapValues.put(keyName1, cond);
}

conditionalProbAttr9.put(keyName, mapValues);

}
System.out.println(conditionalProbAttr9.get(4));
/////////////////////// /* Conditional Probability Attr10 ends*////////////////////////

////////////////////Store the prior probablities of the labels as well //////////////////////////////////////////////////////

Iterator<Integer> keyIterator1 =uniqueValues.iterator();
Integer keyName1=null;
while(keyIterator1.hasNext()){
	keyName1=keyIterator1.next();
	int freq=Collections.frequency(l.get(6), keyName1);
	temp=(double)freq/(double)l.get(6).size();
	cond=(Double)(temp);
	
	priorLabels.put(keyName1,cond);
}

System.out.println(priorLabels.get(4));

//////////////////////End prior probablities of the labels /////////////////////////////////////////////////////////////////

System.out.println(m.keySet())

		    ;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

////////////////////////////////////Predict the values now /////////////////////////////////////////////////////////////////////////////
Integer kmax=null;
Double max=0*1.0;
keyIterator  =uniqueValues.iterator();
System.out.println(uniqueValues);
while(keyIterator.hasNext()){
	keyName1=keyIterator.next();
	
	System.out.println("in the loop");
	
	if(!conditionalProbAttr1.get(keyName1).keySet().contains(1))
		System.out.println("1");
	if(!conditionalProbAttr2.get(keyName1).keySet().contains(1))
		System.out.println("2");
	if(!conditionalProbAttr3.get(keyName1).keySet().contains(1))
		System.out.println("3");
	if(!conditionalProbAttr4.get(keyName1).keySet().contains(1))
		System.out.println("4");
	if(!conditionalProbAttr5.get(keyName1).keySet().contains(1))
		System.out.println("5");
	if(!conditionalProbAttr7.get(keyName1).keySet().contains(2))
		System.out.println("7");
	if(!conditionalProbAttr8.get(keyName1).keySet().contains(1))
		System.out.println("8");
	if(!conditionalProbAttr9.get(keyName1).keySet().contains(1))
		System.out.println("9");


	if(!conditionalProbAttr1.get(keyName1).keySet().contains(1) ||
		!conditionalProbAttr2.get(keyName1).keySet().contains(1)||
		!conditionalProbAttr3.get(keyName1).keySet().contains(1)||
		!conditionalProbAttr4.get(keyName1).keySet().contains(1)||
		!conditionalProbAttr5.get(keyName1).keySet().contains(1)||
		!conditionalProbAttr7.get(keyName1).keySet().contains(2)||
		!conditionalProbAttr8.get(keyName1).keySet().contains(1)||
		!conditionalProbAttr9.get(keyName1).keySet().contains(1))
		continue;
	/*System.out.println("prior bla bla bla" + priorLabels.get(keyName1)*
	conditionalProbAttr1.get(keyName1).get(6)*
	conditionalProbAttr2.get(keyName1).get(6)*
	conditionalProbAttr3.get(keyName1).get(6)*
	conditionalProbAttr4.get(keyName1).get(9)*
	conditionalProbAttr5.get(keyName1).get(6)*
	conditionalProbAttr7.get(keyName1).get(7)*
	conditionalProbAttr8.get(keyName1).get(8)*
	conditionalProbAttr9.get(keyName1).get(1));*/
	
	if(priorLabels.get(keyName1)*
	conditionalProbAttr1.get(keyName1).get(1)*
	conditionalProbAttr2.get(keyName1).get(1)*
	conditionalProbAttr3.get(keyName1).get(1)*
	conditionalProbAttr4.get(keyName1).get(1)*
	conditionalProbAttr5.get(keyName1).get(1)*
	conditionalProbAttr7.get(keyName1).get(2)*
	conditionalProbAttr8.get(keyName1).get(1)*
	conditionalProbAttr9.get(keyName1).get(1) > max)
	{
		max=priorLabels.get(keyName1)*
				conditionalProbAttr1.get(keyName1).get(1)*
				conditionalProbAttr2.get(keyName1).get(1)*
				conditionalProbAttr3.get(keyName1).get(1)*
				conditionalProbAttr4.get(keyName1).get(1)*
				conditionalProbAttr5.get(keyName1).get(1)*
				conditionalProbAttr7.get(keyName1).get(2)*
				conditionalProbAttr8.get(keyName1).get(1)*
				conditionalProbAttr9.get(keyName1).get(1);
		kmax=keyName1;
		System.out.println("max"+max);
	}
}

System.out.println("kmax value is "+kmax);



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	return row;
    }
}