package junit;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Cipher.AESCipher.AESCipher;
import Cipher.AESCipher.KeyExpander;
import Cipher.AESCipher.SBox;
import util.BinUtil;

//@RunWith(Suite.class)
//@SuiteClasses({})
public class AllTests {
	SBox sbox = new SBox();
	KeyExpander ke = new KeyExpander(sbox);
	byte[] input = new byte[]{	(byte) 0x32,(byte) 0x43,(byte) 0xf6,(byte) 0xa8,
								(byte) 0x88,(byte) 0x5a,(byte) 0x30,(byte) 0x8d,
								(byte) 0x31,(byte) 0x31,(byte) 0x98,(byte) 0xa2,
								(byte) 0xe0,(byte) 0x37,(byte) 0x07,(byte) 0x34
							};
	byte[] cipherkey = new byte[]{
								(byte) 0x2b,(byte) 0x7e,(byte) 0x15,(byte) 0x16,
								(byte) 0x28,(byte) 0xae,(byte) 0xd2,(byte) 0xa6,
								(byte) 0xab,(byte) 0xf7,(byte) 0x15,(byte) 0x88,
								(byte) 0x09,(byte) 0xcf,(byte) 0x4f,(byte) 0x3c
								};
	int[] w = new int[4];
	
	@Test
	public void testKeyExpander(){
		
		int Nk = 4;
		ke.KeyExpansion(cipherkey, w, Nk);
		
		for(int i = 0; i < w.length; i++){
			byte[] bytes = BinUtil.integerToByteArray(w[i]);
			System.out.print("{ ");
			for(int j = 0; j < bytes.length; j++){
				System.out.print(Integer.toHexString(BinUtil.integerValue(bytes[j]))+", ");
			}
			System.out.println("}");
		}
	}
	
	@Test
	public void testRoundZero(){
		int Nr = 0;
		AESCipher ac = new AESCipher(cipherkey,Nr);
		byte[][] output = ac.cipher(input);
		
		for(int i = 0; i < output.length; i++){
			for(int j = 0; j < output[i].length; j++){
				System.out.print(Integer.toHexString(BinUtil.integerValue(output[i][j]))+" ");
			}
			System.out.println("");
		}
	}
}
