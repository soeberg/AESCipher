package junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Cipher.AESCipher.AESCipher;
import Cipher.AESCipher.KeyExpander;
import Cipher.AESCipher.SBox;
import util.BinUtil;

public class AESCipherTests {
	
	static SBox sbox;
	static KeyExpander ke;
	static byte[] input;
	static byte[] cipherkey;
	static int[] w;
	
	@BeforeClass
	public static void setupVariables(){
		sbox = new SBox();
		ke = new KeyExpander(sbox);
		input = new byte[]{	(byte) 0x32,(byte) 0x43,(byte) 0xf6,(byte) 0xa8,
				(byte) 0x88,(byte) 0x5a,(byte) 0x30,(byte) 0x8d,
				(byte) 0x31,(byte) 0x31,(byte) 0x98,(byte) 0xa2,
				(byte) 0xe0,(byte) 0x37,(byte) 0x07,(byte) 0x34
			};
		cipherkey = new byte[]{
				(byte) 0x2b,(byte) 0x7e,(byte) 0x15,(byte) 0x16,
				(byte) 0x28,(byte) 0xae,(byte) 0xd2,(byte) 0xa6,
				(byte) 0xab,(byte) 0xf7,(byte) 0x15,(byte) 0x88,
				(byte) 0x09,(byte) 0xcf,(byte) 0x4f,(byte) 0x3c
				};
		w = new int[4];
	}

	@Test
	public void testRoundZero(){
		int Nr = 0;
		AESCipher ac = new AESCipher(cipherkey,Nr);
		byte[][] output = ac.cipher(input);
		
		byte[][] roundZeroResult = new byte[][]{
												{(byte) 0x19,(byte)  0xa0,(byte)  0x9a,(byte)  0xe9},
												{(byte) 0x3d,(byte)  0xf4,(byte)  0xc6,(byte)  0xf8},
												{(byte) 0xe3,(byte)  0xe2,(byte)  0x8d,(byte)  0x48},
												{(byte) 0xbe,(byte)  0x2b,(byte)  0x2a,(byte)  0x08}};
		
		for(int i = 0; i < output.length; i++){
			for(int j = 0; j < output[i].length; j++){
				assertEquals(roundZeroResult[i][j],output[i][j]);
			}
		}
	}

}
