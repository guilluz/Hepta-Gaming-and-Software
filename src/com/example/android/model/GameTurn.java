package Model;

import java.util.ArrayList;

public class GameTurn 
{
	private int cursorWord;
	private ArrayList<Word> words;
	//private Chrono c;
	//private Long time;
	
	public GameTurn(ArrayList<String> words)
	{
		//this.time = 0L;
		this.cursorWord = 0;
		int i;
		this.words = new ArrayList<Word>();
		for (i=0; i<words.size() ;i++) // initialisation de l'arraylist
		{
			this.words.add( new Word( words.get(i) ) );
		}/*
		c = new Chrono();
		c.StartChrono();*/
	}
	
	public Boolean CheckGameEnd()
	{
		if ( ( words.size()-1 == cursorWord ) && ( words.get(cursorWord).ckeckWordEnd() ) ) // verification que l'on se trouve bien a la fin du dernier mot
		{
			//c.StopChrono();
			return true;
		}
		return false;
	}
	
	public void NewWord()
	{
		if ( ( cursorWord < words.size() ) )
		{
			if ( (words.get(cursorWord).ckeckWordEnd() ) ) // verification que le joueur a bien trouver le mot prececent
			{
				cursorWord++;
			}
			else
			{
				System.out.println("word not finish");
			}
			//time = c.getUpdatedTime();
		}
		else
		{
			System.err.println("not more word in this game");
		}
	}

	public int getCursorWord() {
		return cursorWord;
	}

	public void setCursorWord(int cursorWord) 
	{
		if (this.words.size() >= cursorWord)
		{
			this.cursorWord = cursorWord;
		}
		else
		{
			System.err.println("imposible to insert a cursor higher than the length of the Arraylist of words ");
		}
	}

	public ArrayList<Word> getWords() {
		return words;
	}

	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}
/*
	public Chrono getC() {
		return c;
	}

	public void setC(Chrono c) {
		this.c = c;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}	*/
}
