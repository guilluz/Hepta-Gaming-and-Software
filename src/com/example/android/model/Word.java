package Model;

public class Word {
	
	private int cursorLetter;
	private String word;
	
	public Word(String word)
	{
		this.cursorLetter = 0;
		this.word = word;
	}
	
	public boolean checkLetter(char a)
	{
		if ( !( this.ckeckWordEnd() ) )
		{
			if ( word.charAt(cursorLetter) == a )
			{
				cursorLetter++;
				return true;
			}
			else
			{
				cursorLetter=0;
				return false;
			}
		}
		else
		{
			System.err.println("no more letter in this word " + this.getWord() );
			return false;
		}
	}
	
	public boolean ckeckWordEnd()
	{
		if ( word.length() == cursorLetter )
		{
			return true;
		}
		return false;
	}
	
	public int getCursorLetter() {
		return cursorLetter;
	}

	public void setCursorLetter(int cursorLetter) {
		if (this.word.length() >= cursorLetter)
		{
			this.cursorLetter = cursorLetter;
		}
		else
		{
			System.err.println("imposible to insert a cursor higher than the length of the word ");
		}
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
