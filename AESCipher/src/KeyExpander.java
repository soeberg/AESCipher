import java.util.BitSet;

public class KeyExpander {
	
	public static byte[] KeyExpansion(byte[] key, BitSet[] w, int Nk){
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
				temp = SubWord(RotWord(temp));
			}
		}
		
		return null;
	}
	
	private static BitSet SubWord(BitSet word){
		byte[] bytes = word.toByteArray();
		byte[] subbytes = new byte[word.length()];
		for (int i = 0; i < bytes.length; i++){
			subbytes[i] = SBox.getbyte(bytes[i]);
		}
		return BitSet.valueOf(subbytes);
	}
	
	private static BitSet RotWord(BitSet word){
		return null;
	}
	
	public BitSet XORRCon(BitSet b1,int i){
		return null;
	}
	

}
