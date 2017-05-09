package portscanner;

import java.io.IOException;
import java.util.Scanner;

public class StartClass {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("--------------NetTools--------");
		System.out.println("1=httpheader | 2= portscan");
		Scanner scan = new Scanner(System.in);
		System.out.print("NetTools> ");
		int chooseTool = scan.nextInt();
		while(true){
			if(chooseTool==1){
				boolean weiter = false;
				do{
					
					HTTPHeaderClass.httpheader();
					System.out.println("Again? y/n: ");
					String weiterScanned = scan.next();
					if(
							weiterScanned.equals("y")){weiter = false;}
					else if(
							weiterScanned.equals("n")){weiter = true;}
					else{
						System.out.println("Bad input!"); weiter = false;}
					
				}while(weiter == false); 
				break;}
			
			else if(chooseTool==2){ //Fix here
				boolean weiter = false;
				do{
					
					PortScannerClass.portscan(); 
					System.out.println("Again? y/n: ");
					String weiterScanned = scan.next();
					if(weiterScanned.equals("y")){
						weiter = false;}
					else if(weiterScanned.equals("n")){
						weiter = true;}
					else{
						System.out.println("Bad input!");}
					
				} while(weiter == false); break;}
			else{System.out.println("Bad request!");}
			System.out.println("Goodbye!");
		}
	}
					
}
