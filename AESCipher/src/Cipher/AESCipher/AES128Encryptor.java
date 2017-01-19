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
		if(args.length == 4){
			if(args[0] == "-e"){
				
				String file = args[1];
				String newFile = args[2];
				encryptFile(file, newFile);
				System.out.print("Success! encrypted file saved as: "+newFile);
				System.exit(1);
			}
			
			if(args[0] == "-d"){
				String file = args[1];
				String newFile = args[2];
				decryptFile(file, newFile);
				System.exit(1);
			}
		}
		
		// Terminate due to wrong input.
		System.out.println("the function takes 4 inputs:");
		System.out.println("task file newfile");
		System.out.println("task               -e for encryption and -d for decryption");
		System.out.println("file               path to file");
		System.out.println("newfile            path to new file");
		System.out.println("key                the 128 bit cipher key");
		System.exit(0);
		
		
		
		
	}
	
	private static void encryptFile(String file, String newFile){
		Path path = Paths.get(file);
		byte[] data;
		try {
			//Read file
			data = Files.readAllBytes(path);
			if (data == null){
				System.out.print("Unable read any data in "+file);
			}
			byte[] paddedData = dataPadding(data, data.length % 16);
			
			//Encrypt file
			byte[] encryptedData = new byte[paddedData.length];
			ac = new AESCipher(cipherkey, 10);
			for(int i = 0; i < paddedData.length/16; i++){
				byte[] temp = flattenMatrix(ac.cipher(Arrays.copyOfRange(paddedData, i, i+16)));
				
				for(int j = 0; j < temp.length; j++){
					encryptedData[i*16+j] = temp[j];
				}
			}
			
			//Write file
			Path newPath = Paths.get(newFile);
			if(!Files.exists(newPath)){
				Files.createFile(newPath);
			}
			Files.write(newPath, encryptedData);
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("Error reading " + file);
			System.exit(0);
		}
		
		
		
	}
	
	private static void decryptFile(String file, String newFile){
		
	}
	
	private static byte[] dataPadding(byte[] data, int padding){
		byte[] paddedData = new byte[data.length + padding];
		for(int i = 0; i <data.length; i++){
			paddedData[i] = data[i];
		}
		for(int i = data.length; i < data.length + padding; i++){
			paddedData[i] = (byte) 0;
		}
		return paddedData;	
	}
	
	private static byte[] flattenMatrix(byte[][] m){
		byte[] data = new byte[m.length*m.length];
		for (int i = 0; i < data.length; i++){
			int y = i % 4;
			int x = (i-y)/4;
			data[i] = m[x][y];
		}
		
		return data;
	}
	
	
	

}
