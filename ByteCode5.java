/**
 * This class acts as an ASM bytecode compiler that will write a class file that will get input from the user
 * using the Scanner class and output that input to the console.
 * 
 * @author Christina Porter
 * @author Kaitlyn Reed
 * @author Rob Kelley
 * @version 09/21/2021
 * ASM Bytecode Library Project
 * 
 */


import java.io.FileOutputStream; // These are both used for the writeFile method.
import java.io.IOException;

import org.objectweb.asm.ClassWriter; // This will be used as my ClassWriter class.
import org.objectweb.asm.MethodVisitor; //This will be used as my method visitor.
import org.objectweb.asm.Opcodes; //This will be used to access the Opcodes.

public class ByteCode5 {
	
	/**
     * This method will create a file with the bytearray that is created from the main method into the specified name.
     * @param bytearray
     * @param fileName
     */
	public static void writeFile(byte[] bytearray, String fileName){

        try{
            FileOutputStream out = new FileOutputStream(fileName);
            out.write(bytearray);
            out.close();
        } // end try
        catch(IOException e){
        System.out.println(e.getMessage());
        } // end catch
        
    } //end writeFile
	
	
	/**
     * This is the main method.
     * @param args
     */
	public static void main(String[] args) {
		
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES); //This ClassWriter will generate my class file structure.
	    cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC,"DeclareStringVar", null, "java/lang/Object",null); //creating my main constructor for the file
	    
	    /**
         * This block of code is creating my constructor.
         */
	    {
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		} // end construction creation
	    
	    {
	    	MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null); //visits my main method in the class
	        mv.visitCode(); //visits the code inside the method
	        
	        mv.visitLdcInsn((String)"bruh"); // initializes string
	        mv.visitVarInsn(Opcodes.ASTORE,1); // stores string in the stack
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // sets up stream to print the next line
	        mv.visitVarInsn(Opcodes.ALOAD,1); // loads the string to be printed
	        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false); // prints the string
	        
	        /**
             * This block of code is my return statement
             */
	        mv.visitInsn(Opcodes.RETURN);
	        mv.visitMaxs(1, 1);
	        mv.visitEnd();
	    }
	    
	    /**
         * This block of code ends the constructor and creates the class file.
         */
	    cw.visitEnd(); //Ends class

        byte[] b = cw.toByteArray(); //Creates byte array from class

        writeFile(b,"DeclareStringVar.class"); //Writes the byte array to class file
        
        System.out.println("Done!");
	} // end main
	
} // end ByteCode5