package Cipher.AESCipher;

import java.util.Arrays;

public class AESCipher {
	
	private int Nb = 4;
	private int Nk = 4;
	private int Nr = 10;
	private SBox sbox;
	private KeyExpander ke;
	private int[] w; 
	private byte[] cipherKey;
	
	public AESCipher(byte[] cipherKey){
		this.sbox = new SBox();
		this.ke = new KeyExpander(sbox);
		int[] w = new int[Nb*(Nr+1)];
		this.cipherKey = cipherKey;
		ke.KeyExpansion(this.cipherKey, w, Nk);
	}
	
	public String cipher(byte[] in, int[] word){
		byte[][] state = fillState(in);
		
		
		
		
		
		return "";
	}
	
	public String decipher(String cipherText, String key ){
		return "";
	}
	
	public byte[][] fillState(byte[] in){
		byte[][] state = new byte[][]{
										Arrays.copyOfRange(in, 0, 3),
										Arrays.copyOfRange(in, 4, 7),
										Arrays.copyOfRange(in, 8, 11),
										Arrays.copyOfRange(in, 12, 15)
			};
		return state;
	}

}
