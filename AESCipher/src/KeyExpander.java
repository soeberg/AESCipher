
public class KeyExpander {
	
	public static byte[] KeyExpansion(byte[] key, Word[] w, int Nk){
		Word temp;
		
		
		int i = 0;
		
		//first Nk keys
		while (i < Nk){
			w[i] = new Word(new byte[]{key[i*4], key[i*4+1], key[i*4+1], key[i*4+2]});
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
	
	private static Word SubWord(Word word){
		return null;
	}
	
	private static Word RotWord(Word word){
		return null;
	}
	
	

}
