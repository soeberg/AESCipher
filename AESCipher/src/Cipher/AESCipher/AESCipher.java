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
		
		for(int i = 1; i < Nr-1; i++){
			subBytes(state);
			shiftRows(state);
			mixColumns(state);
			AddRoundKey(state, Arrays.copyOfRange(w, i*Nb, (i+1)*Nb));
			
		}
		
		if(Nr >= 10){
			subBytes(state);
			shiftRows(state);
			AddRoundKey(state, Arrays.copyOfRange(w,Nr*Nb,(Nr+1)*Nb));
		}
		
		
		return state;
	}
	
	public byte[][] decipher(byte[] in) {
		byte[][] state = fillState(in);
		
		AddRoundKey(state, Arrays.copyOfRange(w, Nr*Nb, (Nr+1)*Nb-1));
		
		for(int i = Nr; i > 1; i--){
			invShiftRows(state);
			invSubBytes(state);
			AddRoundKey(state, Arrays.copyOfRange(w, i*Nb, (i+1)*Nb-1));
			invMixColumns(state);
		}
		
		invShiftRows(state);
		invSubBytes(state);
		AddRoundKey(state, Arrays.copyOfRange(w, 0, Nb-1));
		
		return state;
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
	
	private void invSubBytes(byte[][] state){
		for (int i = 0; i < state.length; i++){
			for(int j = 0; j < state[i].length; j++){
				state[i][j] = sbox.getByteInv(state[i][j]);
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
			state[i] = BinUtil.integerToByteArray(Integer.rotateLeft(BinUtil.byteArrayToInteger(state[i]),i*8));
		}
	}

	public void invShiftRows(byte[][] state){
		for (int i = 0; i < state.length; i++) {
			state[i] = BinUtil.integerToByteArray(Integer.rotateRight(BinUtil.byteArrayToInteger(state[i]),i*8));
		}
	}
	
	public void mixColumns(byte[][] state) {
		
		for (int i = 0; i < state.length; i++){
			byte[] result = mixColumn(new byte[]{state[0][i], state[1][i], state[2][i], state[3][i]});
			for (int j = 0; j < state.length; j++) {
				state[j][i] = result[j];
			}
		}
	}
	
	public byte[] mixColumn(byte[] stateColumn) {
		byte[] s = stateColumn;
		byte[] result = new byte[]{0x00,0x00,0x00,0x00};
		int fixed = 0x1b;
		
		// Bruges som template til udregningen:
		// BinUtil.modShift((byte)0x02, s[0], fixed);
		// result[0] = (byte) (s[0]^s[1]^s[2]^s[3]); 
		
		result[0] = (byte) ((BinUtil.modShift((byte)0x02, s[0], fixed))^(BinUtil.modShift((byte)0x03, s[1], fixed))^s[2]^s[3]);
		result[1] = (byte) (s[0]^(BinUtil.modShift((byte)0x02, s[1], fixed))^(BinUtil.modShift((byte)0x03, s[2], fixed))^s[3]);
		result[2] = (byte) (s[0]^s[1]^(BinUtil.modShift((byte)0x02, s[2], fixed))^(BinUtil.modShift((byte)0x03, s[3], fixed)));
		result[3] = (byte) ((BinUtil.modShift((byte)0x03, s[0], fixed))^s[1]^s[2]^(BinUtil.modShift((byte)0x02, s[3], fixed)));
		return result;
	}

	public void invMixColumns(byte[][] state) {
		
		for (int i = 0; i < state.length; i++){
			byte[] result = invMixColumn(new byte[]{state[0][i], state[1][i], state[2][i], state[3][i]});
			for (int j = 0; j < state.length; j++) {
				state[j][i] = result[j];
			}
		}
	}
	public byte[] invMixColumn(byte[] stateColumn) {
		byte[] s = stateColumn;
		byte[] result = new byte[]{0x00,0x00,0x00,0x00};
		int fixed = 0x1b;
		
		// Bruges som template til udregningen:
		// BinUtil.modShift((byte)0x02, s[0], fixed);
		// result[0] = (byte) (s[0]^s[1]^s[2]^s[3]); 
		
		result[0] = (byte) ((BinUtil.modShift((byte)0x0e, s[0], fixed))^
							(BinUtil.modShift((byte)0x0b, s[1], fixed))^
							(BinUtil.modShift((byte)0x0d, s[2], fixed))^
							(BinUtil.modShift((byte)0x09, s[3], fixed))); 
		result[1] = (byte) ((BinUtil.modShift((byte)0x09, s[0], fixed))^
							(BinUtil.modShift((byte)0x0e, s[1], fixed))^
							(BinUtil.modShift((byte)0x0b, s[2], fixed))^
							(BinUtil.modShift((byte)0x0d, s[3], fixed))); 
		result[2] = (byte) ((BinUtil.modShift((byte)0x0d, s[0], fixed))^
							(BinUtil.modShift((byte)0x09, s[1], fixed))^
							(BinUtil.modShift((byte)0x0e, s[2], fixed))^
							(BinUtil.modShift((byte)0x0b, s[3], fixed))); 
		result[3] = (byte) ((BinUtil.modShift((byte)0x0b, s[0], fixed))^
							(BinUtil.modShift((byte)0x0d, s[1], fixed))^
							(BinUtil.modShift((byte)0x09, s[2], fixed))^
							(BinUtil.modShift((byte)0x0e, s[3], fixed))); 
		return result;
	}

}
