import java.util.*;
import java.io.*;
class invader {
	public static void main(String args[])throws IOException{
		Scanner sc=new Scanner(System.in);
		String inputfile=args[0];
		String outputfile=args[1];
		File file = new File(inputfile);
		Scanner s1 = new Scanner(file);
        int c=0;
        while (s1.hasNextLine()) {
            s1.nextLine();
            c++;
        }
        
        double arp[]=new double[c+1];
        int arw[]= new int[c+1];
        int i=0;
        
        String str="";
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
        	str=s.nextLine();
        	arp[i]=Double.parseDouble(str.substring(0, str.indexOf(" ")));

        	arw[i]=Integer.parseInt(str.substring(str.indexOf(" ")+1));

        	i++;
        }
       
        /*Code for the problem statement*/
        // the probability taken is for the sensor to be off
        double time1[]=new double[c];
        	
    	for(int j=0;j<c;j++) {
    		double average=0;
            for(int k=0;k<=29;k++) {
            	int w=arw[j];
            	double p=arp[j];
            	int time=0;
            	int x=-1;      //initial x position
            	int y=-1;      //initial y position
            	double ar[][]= new double[w][1000];        //array for all the sensors
            	double arfirstline[]=new double[1000];     //This array is for checking if any sensor is off while entering
	    		f:for(;;) {
	            	for(int jj=0;jj<1000;jj++) {               //generating random values for the first line to check if we can enter or not
	            		double rand = Math.random();
	            		arfirstline[jj]=rand;
	            	}	
	            	for(int jj=0;jj<=999;jj++) {
	    		
	            		if(arfirstline[jj]<=arp[j]) {
	            			time=time+1;
	            			y=0;
	            			x=jj;
	            			break f;
	            		}
	            		
	            	}
	            	time=time+10;
	    		}
            	time=time+9;
    	
            	while(y<arw[j]-1) {
            		l:for(;;) {
            			double pos1=Math.random();   //this position is for the the cell just below and left of the discussed point
        				double pos2=Math.random();   //this position is for the the cell just below the discussed point
        				double pos3=Math.random();   //this position is for the the cell just below and right of the discussed point
    			
        				
        				if(x==0) {
        					if(pos2<=arp[j]) {
        						time=time+1;
        						y=y+1;
        						x=x;
        						break l;
    					
        					}
        					else if(pos3<=arp[j]) {
        						time=time+1;
        						y=y+1;
        						x=x+1;      				
        						break l;
        					}
        					else {
        						time=time+10;
        						continue l;
        					}
        				}
    		
        				else if(x>0 && x<1000) {
        					if(pos2<=arp[j]) {
        						time=time+1;
        						y=y+1;
        						x=x;
        						break l;
        					}
        					else if(pos1<=arp[j]) {
        						time=time+1;
        						y=y+1;       				
        						x=x-1;
        						break l;
        					}
        					else if(pos3<=arp[j]) {
        						time=time+1;
        						y=y+1;
        						x=x+1;      				
        						break l;
        					}
        					else {
        						time=time+10;
        						continue l;
        					}
        				}
        				else if(x==999) {
        					if(pos2<=arp[j]) {
        						time=time+1;
        						y=y+1;
        						x=x;
        						break l;
        					}
        					else if(pos1<=arp[j]) {
            					time=time+1;
            					y=y+1;       				
            					x=x-1;
            					break l;
            				}
            				else {
            					time=time+10;
            					continue l;
            				}
        				}      			
            		}
            		time=time+9;
	     		}
	            average = average+time;
	    	}
	        average = average/30;
	    	time1[j]=average;
    	}
        FileWriter myWriter = new FileWriter(outputfile);
        for(int ii=0;ii<c;ii++) {
        	myWriter.write(""+arp[ii]+", "+arw[ii]+", "+time1[ii]+"\n");
        }
        myWriter.close();
	}
}
        
