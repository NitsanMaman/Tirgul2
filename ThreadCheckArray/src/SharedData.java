import java.util.ArrayList;

/** class for managing the data shared between the two threads
 * @author NitMa
 * @author Ron
 * version : 1.0 11/2/2022
 */
public class SharedData 
{
	/**  array- contain the user inputed numbers to search from
	 *   winArray- contain 1 in the index (same as the array) for number/s to combine and get b
	 *   flag- alert flag when b combination found in array
	 *   b- the desired number that can be combined from array's numbers
	 */
	private ArrayList<Integer> array;
	private boolean [] winArray;
	private boolean flag;
	private final int b;
	
	/** builder
	 * @param array- insert user inputed numbers into array
	 * @param b- insert user inputed desire number into b
	 */
	public SharedData(ArrayList<Integer> array, int b) {
		
		this.array = array;
		this.b = b;
	}

	/**
	 * @return winArray
	 */
	public boolean[] getWinArray() 
	{
		return winArray;
	}

	/**
	 * @param winArray- set 0/1 into winArray
	 */
	public void setWinArray(boolean [] winArray) 
	{
		this.winArray = winArray;
	}

	/**
	 * @return array
	 */
	public ArrayList<Integer> getArray() 
	{
		return array;
	}

	/**
	 * @return b
	 */
	public int getB() 
	{
		return b;
	}

	/**
	 * @return flag
	 */
	public boolean getFlag() 
	{
		return flag;
	}

	/**
	 * @param flag- set flag to alert if b found in array or not
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
