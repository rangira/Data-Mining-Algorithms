/* Some code pieces have been borrowed from existing Git code  chen (Author)
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.StringReader;

public class KMeans1
{
  
  private double [][] dataset; 
  private int [] classes;  
  private int [] withclasses; 
                               
                              
  private double [][] centroids; 
  private int rows, cols; 
  private int numClusters;

 
  public KMeans1(String fileName, String classesname) 
  {
    BufferedReader reader;
    ExcelHelper excel = new ExcelHelper();
    ArrayList<String> values; 
   
    try 
    {
      reader = new BufferedReader(new FileReader(fileName));
      
   
      rows =1;
      values = excel.parseLine(reader);
      System.out.println(values);
      cols = values.size();
      while(reader.readLine()!=null)
        rows++;
      reader.close();
      System.out.println(rows + " "+cols);

     
      dataset = new double[rows][];
      for (int i=0; i<rows; i++)
        dataset[i] = new double[cols];

    
      reader = new BufferedReader(new FileReader(fileName));
      int row=0;
      while ((values = excel.parseLine(reader))!=null){
        double [] dv = new double[values.size()];
        for (int i=0; i< values.size(); i++){
            dv[i] = Double.parseDouble(values.get(i));
        }
        dataset[row] = dv;
        row ++;
      }      
      reader.close();
      System.out.println("loaded dataset");

      if (classesname!=null){
        // load classes file to withclasses;
        reader = new BufferedReader(new FileReader(classesname));
        withclasses = new int[rows];
        int c=0;
        while ((values = excel.parseLine(reader))!=null){
          withclasses[c] = Integer.parseInt(values.get(0)); 
        }
        reader.close();
        System.out.println("loaded classes");
      } 
    }
    catch(Exception e) 
    {
      System.out.println( e );
      System.exit( 0 ); 
    }
    
    
    for(int i=0;i<rows;i++)
    {
    	for(int j=0;j<cols;j++)
    	{
    		 System.out.print(dataset[i][j]+"");
    	}
    	System.out.println();
    }

  }
  
 
  public void clustering(int numClusters, int niter, double [][] centroids) 
  {
      numClusters = numClusters;
      if (centroids !=null)
          centroids = centroids;
      else{
        // randomly selected centroids
        centroids = new double[numClusters][];

        ArrayList idx= new ArrayList();
        for (int i=0; i<numClusters; i++){
          int c;
          do{
            c = (int) (Math.random()*rows);
          }while(idx.contains(c)); // avoid duplicates
          idx.add(c);

          // copy the value from dataset[c]
          centroids[i] = new double[cols];
          for (int j=0; j<cols; j++)
            centroids[i][j] = dataset[c][j];
        }
        System.out.println("selected random centroids");
       
        for (int i=0; i<numClusters; i++)
        {
        for (int j=0; j<cols; j++)
            System.out.print(centroids[i][j]+" ") ;
        System.out.println();
        }
        
      }

      double [][] c1 = centroids;
      double threshold = 0.001;
      int round=0;

      while (true){
       
        centroids = c1;

        
        classes = new int[rows];
        for (int i=0; i<rows; i++){
          classes[i] = functionclose(dataset[i]);
        }
        
          
        c1 = updateCentroids();
        round ++;
        if ((niter >0 && round >=niter) || converge(centroids, c1, threshold))
          break;
      }

      System.out.println("Clustering converges at round " + round);
  }

   
  private int functionclose(double [] v){
    double mindist = dist(v, centroids[0]);
    int classes =0;
    for (int i=1; i<numClusters; i++){
      double t = dist(v, centroids[i]);
      if (mindist>t){
        mindist = t;
        classes = i;
      }
    }
    return classes;
  }

  
  private double dist(double [] v1, double [] v2){
    double sum=0;
    for (int i=0; i<cols; i++){
      double d = v1[i]-v2[i];
      sum += d*d;
    }
    return Math.sqrt(sum);
  }



  private double [][] updateCentroids(){
   
    double [][] newc = new double [numClusters][];  
    int [] counts = new int[numClusters]; 

    
    for (int i=0; i<numClusters; i++){
      counts[i] =0;
      newc[i] = new double [cols];
      for (int j=0; j<cols; j++)
        newc[i][j] =0;
    }


    for (int i=0; i<rows; i++){
      int cn = classes[i]; 
      for (int j=0; j<cols; j++){
        newc[cn][j] += dataset[i][j]; 
      }
      counts[cn]++;
    }

    
    for (int i=0; i< numClusters; i++){
      for (int j=0; j<cols; j++){
        newc[i][j]/= counts[i];
      }
    } 

    return newc;
  }

  
  private boolean converge(double [][] c1, double [][] c2, double threshold){
     
    double maxv = 0;
    for (int i=0; i< numClusters; i++){
        double d= dist(c1[i], c2[i]);
        if (maxv<d)
            maxv = d;
    } 

    if (maxv <threshold)
      return true;
    else
      return false;
    
  }
  public double[][] getCentroids()
  {
    return centroids;
  }

  public int [] getclasses()
  {
    return classes;
  }

  public int rows(){
    return rows;
  }

  public void printResults(){
      System.out.println("classes:");
     for (int i=0; i<rows; i++)
        System.out.println(classes[i]);
      System.out.println("Centroids:");
     for (int i=0; i<numClusters; i++){
        for(int j=0; j<cols; j++)
           System.out.print(centroids[i][j] + " ");
         System.out.println();
     }

  }


  public static void main( String[] astrArgs ) 
  {
    
     KMeans1 KM = new KMeans1( "F://data.csv", null );
     KM.clustering(2, 20, null); // 2 clusters, maximum 10 iterations
     KM.printResults();

   

  }
}

