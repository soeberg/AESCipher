package Cipher.AESCipher;
import util.BinUtil;;

/*
 * key = 128 bit
 * word = 32 bit
 */

public class KeyExpander {
	
	private SBox sbox;
	
	public KeyExpander(SBox sbox){
		this.sbox = sbox;
	}
	
	public void KeyExpansion(byte[] key, int[] w, int Nk){
		int temp = 0;
		
		int i = 0;
		
		//first Nk keys
		while (i < Nk){
			w[i] = BinUtil.byteArrayToInteger(new byte[]{key[4*i], key[4*i+1], key[4*i+2], key[4*i+3]});
			i++;
		}
		i = Nk;
		
		while (i < w.length){
			temp = w[i-1];
			if (i % Nk == 0){
				temp = SubWord(RotWord(temp))^sbox.getRcon(i/Nk);
			} else{
				if(Nk > 6 && i % Nk == 4){
					temp = SubWord(temp);
				
				}
			}
			
			w[i] = w[i-Nk]^temp; 
			i++;
		}
	}
	
	private int SubWord(int word){
		byte[] bytes = BinUtil.integerToByteArray(word);
		for (int i = 0; i < bytes.length; i++){
			bytes[i] = this.sbox.getbyte(bytes[i]);
		}
		return BinUtil.byteArrayToInteger(bytes);
	}
	
	private static int RotWord(int word){
		return Integer.rotateLeft(word,8);
	}

}
