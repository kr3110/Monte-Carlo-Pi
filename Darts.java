package montecarl;

import java.util.Scanner;

public class Darts
{
//"throwing" a dart
public static boolean [] dartThrow(int r, int d){
boolean [] booleanArray = new boolean[d];
for(int i = 0; i < d; i++){
double xCoord = Math.random() * r;
double yCoord = Math.random() * r;
double zCoord = Math.sqrt((Math.pow(xCoord,2) + Math.pow(yCoord,2)));
double diagonal=Math.sqrt((Math.pow(r/2,2) + Math.pow(r/2,2))) ;
if(zCoord >= diagonal || r>=1 ) // the mirror does not work with r>1 so i just restricted to 1;
{
	if(zCoord <= r ){
		booleanArray[i] = true;
	}
	else{
		booleanArray [i] = false;
	}
}
else //mirror if the z is greater that diagonal point.
{
	double c=10;
	double a=-1;
	double vPoint=(xCoord+(yCoord-c)*a)/(1+(a*a));
	double xNewCoord=(2*vPoint) - xCoord;
	//System.out.println("x: "+ xCoord +">>"+xNewCoord);
	double yNewCoord=(2*vPoint*a)-yCoord+(2*c);
	double zNewCoord=Math.sqrt((Math.pow(xNewCoord,2) + Math.pow(yNewCoord,2)));
	if(zNewCoord <= r ){
		booleanArray[i] = true;
	}
	else{
		booleanArray [i] = false;
	}
}
}
return booleanArray;
}

//calculating pi from throwing results
public static double piEstimater(boolean [] h, int d){
int trueCounter = 0;
for(int i = 0; i < h.length; i++){
if(h[i] == true){
trueCounter++;
}
}
return 4 * ((double)trueCounter / d);
}

//printing results
public static void printer(double [] a){
System.out.println(" Pi Estimation Tool ");
System.out.println("---------------------------");
for(int i = 0; i < a.length; i++){
System.out.print("Trial [" + i + "]: pi = ");
System.out.printf("%6f\n", a[i]);
}
}

public static void main(String[] args){
//variables
Scanner in = new Scanner(System.in);

System.out.println("Enter the radius of the Cirlcle:");
int radius = in.nextInt();
int darts;
int trials;

System.out.println("Enter the number of darts to calculate for: ");
darts = in.nextInt();
System.out.println("Enter the number of trials to calculate for: ");
trials = in.nextInt();

double [] arrayOfEstimates = new double [trials];
int i = 0;
for(double a : arrayOfEstimates){
boolean [] hitCounter = dartThrow(radius, darts);
double piEstimate = piEstimater(hitCounter, darts);
arrayOfEstimates[i] = piEstimate;
i++;
}

printer(arrayOfEstimates);
}
}
