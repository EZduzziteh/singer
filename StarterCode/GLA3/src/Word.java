
public class Word {
	
	public Word(String w, int c, double f)
	{
		word=w;
		count=c;
		frequency=f;
	}
	public String word;
	public int count;
	public double frequency;
	
	@Override
	public String toString()
	{
		return "Word: "+word+" Count: "+count+" frequency: "+frequency;
	}
}
