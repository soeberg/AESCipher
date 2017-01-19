package junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import Cipher.AESCipher.AES128Encryptor;
import Cipher.AESCipher.AESCipher;
import util.BinUtil;

public class AES128EncryptorTests {
	
	AES128Encryptor aes;
	
	@Test
	public void EnDecryptiontest() {
		aes = new AES128Encryptor();
		String file = "/Users/soeberg/Desktop/Untitled.txt";
		String newFile = "/Users/soeberg/Desktop/Untitled-enc.txt";
		String decryptedfile = "/Users/soeberg/Desktop/Untitled2.txt";
		String[] encryptArgs = new String[]{ "-e", file, newFile};
		String[] decryptArgs = new String[]{ "-d", newFile, decryptedfile};
		aes.main(encryptArgs);
		aes.main(decryptArgs);
		Path pathOrig = Paths.get(file);
		Path pathNew = Paths.get(decryptedfile);
		Path crypt = Paths.get(newFile);
		
		try {
			byte[] filebytes = Files.readAllBytes(pathOrig);
			byte[] decryptedfilebytes = Files.readAllBytes(pathNew);
			for (int i = 0; i < filebytes.length-1; i++){
				assertEquals("current index is: "+i,BinUtil.integerValue(filebytes[i]), BinUtil.integerValue(decryptedfilebytes[i]));
			}
			Files.delete(pathNew);
			Files.delete(crypt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		// cleanup
		
		
	}
	
	@Test
	public void flattenMatrixTest(){
		AESCipher ac = new AESCipher(new byte[16], 10);
		aes = new AES128Encryptor();
		byte[] result = new byte[]{
				(byte) 0x32,(byte) 0x43,(byte) 0xf6,(byte) 0xa8,
				(byte) 0x88,(byte) 0x5a,(byte) 0x30,(byte) 0x8d,
				(byte) 0x31,(byte) 0x31,(byte) 0x98,(byte) 0xa2,
				(byte) 0xe0,(byte) 0x37,(byte) 0x07,(byte) 0x34
		};		

		byte[][] matrix = ac.fillState(result);
		byte[] flatmatrix = aes.flattenMatrix(matrix);
		
		
		for(int i = 0; i < result.length; i++){
			assertEquals(result[i], flatmatrix[i]);
		}
		
	}
	
	@Test
	public void testPadding(){
		aes = new AES128Encryptor();
		byte[] result = new byte[]{
				(byte) 0x32,(byte) 0x43,(byte) 0xf6,(byte) 0xa8,
				(byte) 0x88,(byte) 0x5a,(byte) 0x30,(byte) 0x8d,
				(byte) 0x31,(byte) 0x31,(byte) 0x98,(byte) 0xa2,
				(byte) 0xe0,(byte) 0x37};
		byte[] paddedResult = aes.addPadding(result);
		byte[] unpaddedResult = aes.removePadding(paddedResult);
		for (int i = 0; i < unpaddedResult.length; i++){
			assertEquals(result[i],unpaddedResult[i]);
		}
	}

}
