package Cipher.AESCipher;

import java.util.Arrays;

import util.BinUtil;

public class AESCipher {
	
	private int Nb = 4;
	private int Nk = 4;
	private int Nr;
	private SBox sbox;
	private KeyExpander ke;
	private int[] w; 
	private byte[] cipherKey;
	
	public AESCipher(byte[] cipherKey, int Nr){
		this.Nr = Nr;
		this.sbox = new SBox();
		this.ke = new KeyExpander(sbox);
		this.w = new int[Nb*(Nr+1)];
		this.cipherKey = cipherKey;
		ke.KeyExpansion(this.cipherKey, w, Nk);
	}
	
	public byte[][] cipher(byte[] in){
		byte[][] state = fillState(in);
		
		AddRoundKey(state, Arrays.copyOfRange(w, 0,4));
		
		for(int i = 1; i < Nr; i++){
			subBytes(state);
			shiftRows(state);
			//mixColumns(state);
			AddRoundKey(state, Arrays.copyOfRange(w, i*Nb, (i+1)*Nb-1));
			
		}
		
		
		return state;
	}
	
	public String decipher(String cipherText, String key ){
		return "";
	}
	
	private byte[][] fillState(byte[] in){
		byte[][] state = new byte[4][4];
		for (int i = 0; i < state.length; i++){
			state[i] = new byte[]{in[i], in[i+4], in[i+8], in[i+12]};
		}
		
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

	public void shiftRows(byte[][] state){
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < i; j++) {
				state[i] = shiftRow(state[i]);
			}
		}
	}
	
	public byte[] shiftRow(byte[] stateRow) {
		byte temp = stateRow[stateRow.length-1];
		for (int i = (stateRow.length - 1); i >= 0; i--) {                
		   stateRow[i+1] = stateRow[i];
		}
		stateRow[0] = temp;
		return stateRow;
	}

}
