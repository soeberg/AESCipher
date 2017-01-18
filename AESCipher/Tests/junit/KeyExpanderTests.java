package junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Cipher.AESCipher.KeyExpander;
import Cipher.AESCipher.SBox;
import util.BinUtil;

public class KeyExpanderTests {
	
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
		w = new int[44];
	}

	@Test
	public void testRoundZeroKeyMatrix(){	
		int round = 0;
		int Nk = 4;
		byte[][] keymatrix = new byte[][]{
											{(byte) 0x2b,(byte) 0x28,(byte) 0xab,(byte) 0x09},
											{(byte) 0x7e,(byte) 0xae,(byte) 0xf7,(byte) 0xcf},
											{(byte) 0x15,(byte) 0xd2,(byte) 0x15,(byte) 0x4f},
											{(byte) 0x16,(byte) 0xa6,(byte) 0x88,(byte) 0x3c}
											};
		ke.KeyExpansion(cipherkey, w, Nk);
		byte[][] arr = new byte[][]{
									BinUtil.integerToByteArray(w[4*round]),
									BinUtil.integerToByteArray(w[4*round+1]),
									BinUtil.integerToByteArray(w[4*round+2]),
									BinUtil.integerToByteArray(w[4*round+3])
										};
		arr = BinUtil.transformMatrix(arr);
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				assertEquals(keymatrix[i][j], arr[i][j]);
				//System.out.print(Integer.toHexString(BinUtil.integerValue(arr[i][j]))+" ");
			}
		}
		
	}
	
	@Test
	public void testRoundOneKeyMatrix(){
		int round = 1;
		int Nk = 4;
		byte[][] keymatrix = new byte[][]{
											{(byte) 0xa0,(byte)  0x88,(byte)  0x23,(byte)  0x2a},
											{(byte) 0xfa,(byte)  0x54,(byte)  0xa3,(byte)  0x6c},
											{(byte) 0xfe,(byte)  0x2c,(byte)  0x39,(byte)  0x76},
											{(byte) 0x17,(byte)  0xb1,(byte)  0x39,(byte)  0x05}						};
		ke.KeyExpansion(cipherkey, w, Nk);			
		
		byte[][] arr = new byte[][]{
			BinUtil.integerToByteArray(w[4*round]),
			BinUtil.integerToByteArray(w[4*round+1]),
			BinUtil.integerToByteArray(w[4*round+2]),
			BinUtil.integerToByteArray(w[4*round+3])
				};
		arr = BinUtil.transformMatrix(arr);
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				assertEquals(keymatrix[i][j], arr[i][j]);
			}
		}	
	}
	
	@Test
	public void testRoundTwoKeyMatrix(){
		int round = 2;
		int Nk = 4;
		byte[][] keymatrix = new byte[][]{
											{(byte) 0xf2,(byte)  0x7a,(byte)  0x59,(byte)  0x73},
											{(byte) 0xc2,(byte)  0x96,(byte)  0x35,(byte)  0x59},
											{(byte) 0x95,(byte)  0xb9,(byte)  0x80,(byte)  0xf6},
											{(byte) 0xf2,(byte)  0x43,(byte)  0x7a,(byte)  0x7f}};
		ke.KeyExpansion(cipherkey, w, Nk);								
		byte[][] arr = new byte[][]{
			BinUtil.integerToByteArray(w[4*round]),
			BinUtil.integerToByteArray(w[4*round+1]),
			BinUtil.integerToByteArray(w[4*round+2]),
			BinUtil.integerToByteArray(w[4*round+3])
				};
		arr = BinUtil.transformMatrix(arr);
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				assertEquals(keymatrix[i][j], arr[i][j]);
			}
		}
	}
	
	@Test
	public void testRoundTenKeyMatrix(){
		int round = 10; 
		int Nk = 4;
		byte[][] keymatrix = new byte[][]{
											{(byte) 0xd0,(byte)  0xc9,(byte)  0xe1,(byte)  0xb6},
											{(byte) 0x14,(byte)  0xee,(byte)  0x3f,(byte)  0x63},
											{(byte) 0xf9,(byte)  0x25,(byte)  0x0c,(byte)  0x0c},
											{(byte) 0xa8,(byte)  0x89,(byte)  0xc8,(byte)  0xa6}};
		ke.KeyExpansion(cipherkey, w, Nk);								
		byte[][] arr = new byte[][]{
			BinUtil.integerToByteArray(w[4*round]),
			BinUtil.integerToByteArray(w[4*round+1]),
			BinUtil.integerToByteArray(w[4*round+2]),
			BinUtil.integerToByteArray(w[4*round+3])
				};
		arr = BinUtil.transformMatrix(arr);
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				assertEquals(keymatrix[i][j], arr[i][j]);
			}
		}
	}
}
