import java.util.BitSet;

public class KeyExpander {
	
	public static BitSet[] KeyExpansion(byte[] key, BitSet[] w, int Nk){
		BitSet temp;
		byte[] t2 = w[0].toByteArray();
		
		int i = 0;
		
		//first Nk keys
		while (i < Nk){
			w[i] = BitSet.valueOf(new byte[]{key[i*4], key[i*4+1], key[i*4+1], key[i*4+2]});
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
			w[i] = (BitSet) w[i-Nk].clone();
			w[i].xor(temp);
			i++;
		}
		
		return w;
	}
	
	private static BitSet SubWord(BitSet word){
		byte[] bytes = word.toByteArray();
		byte[] subbytes = new byte[word.length()];
		for (int i = 0; i < bytes.length; i++){
			subbytes[i] = SBox.getbyte(bytes[i]);
		}
		return BitSet.valueOf(subbytes);
	}

	private static BitSet InvSubWord(BitSet word){
		byte[] bytes = word.toByteArray();
		byte[] subbytes = new byte[word.length()];
		for (int i = 0; i < bytes.length; i++){
			subbytes[i] = SBox.getByteInv(bytes[i]);
		}
		return BitSet.valueOf(subbytes);
	}
	
	private static BitSet RotWord(BitSet word){
		byte[] bytes = word.toByteArray();
		byte[] result = new byte[bytes.length];
		System.arraycopy(bytes, 1, result, 0, bytes.length - 1);
		result[bytes.length-1] = bytes[0];
		return BitSet.valueOf(result);
	}

	private static BitSet InvRotWord(BitSet word) {
		byte[] bytes = word.toByteArray();
		byte[] result = new byte[bytes.length];
		System.arraycopy(bytes, 0, result, 1, bytes.length - 1);
		result[0] = bytes[bytes.length-1];
		return BitSet.valueOf(result);
	}


	
	public static BitSet XORRcon(BitSet b1,int i){
		//i++; skal måske tilføjes
		BitSet temp = (BitSet) b1.clone();
		BitSet Rcon = SBox.getRcon(i);
		temp.xor(Rcon);
		return temp;
	}
	

}
