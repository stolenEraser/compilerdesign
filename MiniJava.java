import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import Parser.sym;
import Scanner.scanner;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;

public class MiniJava {
	/* 
	 * takes the file using -s and the filename,
	 * files are found in the sample programs
	 * 
	 * BinarySearch.java	
	 * Factorial.java		
	 * QuickSort.java
	 * BinaryTree.java		
	 * LinearSearch.java	
	 * TreeVisitor.java 
	 * BubbleSort.java		
	 * LinkedList.java
	 * 
	 */
	public static void executeScanner(InputStream filename) throws IOException {
		
		ComplexSymbolFactory sf = new ComplexSymbolFactory();
        Reader in = new BufferedReader(new InputStreamReader(filename));
        scanner s = new scanner(in, sf);
        Symbol t = s.next_token();
        while (t.sym != sym.EOF){ 
            // print each token that we scan
            System.out.println(s.symbolToString(t) + " ");
            t = s.next_token();
        }
        //System.out.print("\nLexical analysis completed"); 
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//check that there's at least 2 arguments
		//assert(args.length ==2);
		
		try {
			// check if arguments are in the correct place
			if (args[0].equals("-S")) {
				File f= new File(args[1]);
				InputStream in= new FileInputStream(f);
				executeScanner(in);
			}else {
				// exit system if not in the right place.
				System.exit(0);
			}
        } catch (Exception e) {
            System.err.println("Unexpected internal compiler error: " + 
                        e.toString());
            e.printStackTrace();
            System.exit(1);
   
        }
		/*
		String fname= "/Users/carahvillacampa/Downloads/CSC348_compiler/minijava_starter/SamplePrograms/SampleMiniJavaPrograms/BinarySearch.java";
		//String fname= " ";
		File f= new File(fname);
		try {
			StringBuilder b= new StringBuilder();
			InputStream in= new FileInputStream(f);			
			executeScanner(in);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		*/
		
		
		
		
		
		
	}

}
