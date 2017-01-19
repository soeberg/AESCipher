package Cipher.AESCipher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import util.BinUtil;

public class AES128Encryptor {
	private static byte[] cipherkey = new byte[]{
			(byte) 0x2b,(byte) 0x7e,(byte) 0x15,(byte) 0x16,
			(byte) 0x28,(byte) 0xae,(byte) 0xd2,(byte) 0xa6,
			(byte) 0xab,(byte) 0xf7,(byte) 0x15,(byte) 0x88,
			(byte) 0x09,(byte) 0xcf,(byte) 0x4f,(byte) 0x3c
			};
	private static AESCipher ac;
	
	
	public static void main(String[] args){
		if(args.length == 3){
			
			if(args[0].equals("-e")){
				
				String file = args[1];
				String newFile = args[2];
				encryptFile(file, newFile);
				return;
			}
			
			if(args[0].equals("-d")){
				String file = args[1];
				String newFile = args[2];
				decryptFile(file, newFile);
				return;
			}
		}
		
		// Terminate due to wrong input.
		System.out.println("the function takes 3 inputs:");
		System.out.println("task file newfile");
		System.out.println("task               -e for encryption and -d for decryption");
		System.out.println("file               path to file");
		System.out.println("newfile            path to new file");
		//System.exit(0);
		
		
		
		
	}
	
	private static void encryptFile(String file, String newFile){
		byte[] data = readData(file);
		
		int padding = data.length % 16;
		byte[] paddedData = addPadding(data);
		
		//Encrypt file
		byte[] encryptedData = new byte[paddedData.length];
		ac = new AESCipher(cipherkey, 10);
		for(int i = 0; i < paddedData.length/16; i++){
			byte[] temp = flattenMatrix(ac.cipher(Arrays.copyOfRange(paddedData, (i*16), (i*16)+16)));
			
			for(int j = 0; j < temp.length; j++){
				encryptedData[i*16+j] = temp[j];
			}
		}
		
		writeData(Arrays.copyOfRange(encryptedData, 0, encryptedData.length-(padding+1)),newFile);	
		
		
		
	}
	
	private static void decryptFile(String file, String newFile){
		byte[] data = readData(file);
		
		//Decrypt file
		byte[] decryptedData = new byte[data.length];
		ac = new AESCipher(cipherkey, 10);
		for(int i = 0; i < data.length/16; i++){
			byte[] temp = flattenMatrix(ac.decipher(Arrays.copyOfRange(data, 16*i, (16*i)+16)));
			
			for(int j = 0; j < temp.length; j++){
				decryptedData[i*16+j] = temp[j];
			}
		}
		decryptedData = removePadding(decryptedData);
		writeData(decryptedData,newFile);	
	}
	
	public static byte[] readData(String file){
		Path path = Paths.get(file);
		byte[] data;
		try {
			//Read file
			data = Files.readAllBytes(path);
			if (data == null){
				System.out.print("Unable read any data in location: "+file);
				//System.exit(0);
			}
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("Error reading " + file);
			//System.exit(0);
		}
		return new byte[0];
	}
	
	public static void writeData(byte[] data, String newFile){
		try {
			Path newPath = Paths.get(newFile);
			if(!Files.exists(newPath)){
				Files.createFile(newPath);
			}
			Files.write(newPath, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static byte[] addPadding(byte[] data){
		int extrapadding = data.length % 16;
		byte[] paddedData = new byte[data.length + 16 +extrapadding];
		for(int i = 0; i <data.length; i++){
			paddedData[i] = data[i];
		}
		for(int i = data.length; i < data.length + 16+extrapadding-1; i++){
			paddedData[i] = (byte) 0;
		}
		paddedData[paddedData.length-1] = (byte) extrapadding;
		return paddedData;	
	}
	
	public static byte[] removePadding(byte[] data){
		int extrapadding = data[data.length-1];
		return Arrays.copyOfRange(data, 0, data.length-16-extrapadding);
	}
	
	public static byte[] flattenMatrix(byte[][] m){
		byte[] data = new byte[m.length*m.length];
		for (int i = 0; i < data.length; i++){
			int y = i % 4;
			int x = (i-y)/4;
			data[i] = m[y][x];
		}
		
		return data;
	}
	
	
	

}
