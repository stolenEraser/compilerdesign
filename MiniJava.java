import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import AST.Program;
import AST.Statement;
import AST.Visitor.PrettyPrintVisitor;
import Parser.parser;
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
	
	public static int executeParser(String filename) {
		int errors=0;
		
		try {
			
            // create a scanner on the input file
            ComplexSymbolFactory sf = new ComplexSymbolFactory();
            Reader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            scanner s = new scanner(in, sf);
            parser p = new parser(s, sf);
            Symbol root;
	    // replace p.parse() with p.debug_parse() in next line to see trace of
	    // parser shift/reduce actions during parse
			//root = p.parse();
			root =p.parse();
            Program program= (Program) root.value;
            program.accept(new PrettyPrintVisitor());
            //List<Statement> program = (List<Statement>)root.value;
            //for (Statement statement: program) {
                //program.accept(new PrettyPrintVisitor());
                //System.out.print("\n");
            //}
            System.out.print("\nParsing completed"); 
        } catch (Exception e) {
            // yuck: some kind of error in the compiler implementation
            // that we're not expecting (a bug!)
            System.err.println("Unexpected internal compiler error: " + 
                               e.toString());
            // print out a stack dump
            e.printStackTrace();
        }
		
		
		return errors== 0 ? 0 : 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//check that there's at least 2 arguments
		//assert(args.length ==2);
		
		try {
			// check if arguments are in the correct place
			if (args[0].equals("-S") && args.length== 2) {
				File f1= new File(args[1]);
				InputStream in1= new FileInputStream(f1);
				executeScanner(in1);
			}else if(args[0].equals("-P") && args.length== 2) {
				//File f2= new File(args[1]);
				//InputStream in2= new FileInputStream(f2);
				executeParser(args[1]);
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
			//StringBuilder b= new StringBuilder();
			//InputStream in= new FileInputStream(f);			
			executeParser(fname);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		*/
		
		
		
		
		
		
		
	}

}
