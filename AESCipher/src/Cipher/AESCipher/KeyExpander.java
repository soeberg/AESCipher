package Cipher.AESCipher;
import net.cscott.jutil.*;
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
			w[i] = BinUtil.byteArrayToInteger(new byte[]{key[i*4], key[i*4+1], key[i*4+1], key[i*4+2]});
			i++;
		}
		
		i = Nk;
		
		while (i < w.length){
			temp = w[i-1];
			if (i % Nk == 0){
				temp = XORRcon(SubWord(RotWord(temp)),i/Nk);
			} else{
				if(Nk > 6 && i % Nk == 4){
					temp = SubWord(temp);
				}
			}
			w[i] = w[i-Nk];
			// w[i]. xor with temp 
			i++;
		}
	}
	
	private int SubWord(int word){
		byte[] bytes = BinUtil.integerToByteArray(word);
		byte[] subbytes = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++){
			subbytes[i] = this.sbox.getbyte(bytes[i]);
		}
		return BinUtil.byteArrayToInteger(subbytes);
	}

	private BitString InvSubWord(int word){
		BitString subbytes = new BitString(word.length());
		for (int i = 0; i < word.length; i++){
			subbytes[i] = this.sbox.getByteInv(bytes[i]);
		}
		return BitString;
	}
	
	private static byte[] RotWord(byte[] bytes){
		byte[] result = new byte[bytes.length];
		System.arraycopy(bytes, 1, result, 0, bytes.length - 1);
		result[bytes.length-1] = bytes[0];
		return result;
	}

	private static byte[] InvRotWord(byte[] bytes) {
		byte[] result = new byte[bytes.length];
		System.arraycopy(bytes, 0, result, 1, bytes.length - 1);
		result[0] = bytes[bytes.length-1];
		return result;
	}

}
