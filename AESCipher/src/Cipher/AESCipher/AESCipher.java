package Cipher.AESCipher;

import java.util.Arrays;

import util.BinUtil;

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
		this.w = new int[Nb*(Nr+1)];
		this.cipherKey = cipherKey;
		ke.KeyExpansion(this.cipherKey, w, Nk);
	}
	
	public String cipher(byte[] in, int[] word){
		byte[][] state = fillState(in);
		
		AddRoundKey(state, Arrays.copyOfRange(w, 0,3));
		
		for(int i = 1; i < Nr; i++){
			subBytes(state);
			//shiftRows(state);
			//mixColumns(state);
			AddRoundKey(state, Arrays.copyOfRange(w, i*Nb, (i+1)*Nb-1));
			
		}
		
		
		return "";
	}
	
	public String decipher(String cipherText, String key ){
		return "";
	}
	
	private byte[][] fillState(byte[] in){
		byte[][] state = new byte[][]{
										Arrays.copyOfRange(in, 0, 3),
										Arrays.copyOfRange(in, 4, 7),
										Arrays.copyOfRange(in, 8, 11),
										Arrays.copyOfRange(in, 12, 15)
			};
		return state;
	}
	
	private void subBytes(byte[][] state){
		for (int i = 0; i < state.length; i++){
			for(int j = 0; j < state[i].length; j++){
				state[i][j] = sbox.getbyte(state[i][j]);
			}
		}
	}
	
	private void AddRoundKey(byte[][] state, int[] words){
		for(int i = 0; i < state.length; i++){
			state[i] = BinUtil.integerToByteArray(BinUtil.byteArrayToInteger(state[i])^words[i]);
		}
	}

}
