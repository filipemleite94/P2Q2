package exe2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SecureClass {
	public void vulnerableMethod(String FILENAME){
		final int MAX = 1;
		final int MAXCHAR = 100;
		int i = 0;
		if(FILENAME.contains("..")){
			System.out.println("Nome do arquivo ilegal");
			return;
		}
		File file;
		file = new File(FILENAME);
		if(!file.exists()){
			System.out.println("Arquivo n�o existe");
			return;
		}
		while (i++<MAX) {
		    Scanner console = new Scanner(System.in);
		    System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, "
		    		+ "W para escrever em um arquivo>? ");
		    
		    String opr;
		    
		    if(console.hasNext("[RW]")){
		    	opr = console.next("[RW]");
		    }else{
		    	System.out.println("Entrada inv�lida");
		    	return;
		    }
		    if (opr.equals("R")){
				FileReader fr = null;
				StringBuilder sb = null;
				char carac = 0;
				int intcarac;
				int j = 0;
				try {
					fr = new FileReader(file);
					sb = new StringBuilder();
					while ((j++<MAXCHAR)&&(intcarac = fr.read())!=-1) {
						sb.append((char)intcarac);
					}
					System.out.println(sb.toString());
					fr.close();
				} catch (IOException e) {
					System.out.println("Arquivo n�o � leg�vel");
				} 
			}
			
			else {
				  FileWriter fw;
				  BufferedWriter buffWrite;
				  try {
					fw = new FileWriter(file);
					buffWrite = new BufferedWriter(fw);
					String linha = "";
					console.nextLine();
					System.out.println("Escreva algo: ");
					if(console.hasNextLine()){
						linha = console.nextLine();
					}
					if(linha.length()>MAXCHAR){
						linha = linha.substring(0, MAXCHAR);
					}
				    buffWrite.append(linha+"\n");
				    buffWrite.flush();
				    buffWrite.close();
				     
				} catch (IOException e) {
					System.out.println("Arquivo n�o pode ser escrito");
				} 
			}
		    console.close();
		}
	}
}
