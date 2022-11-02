public class ThreadCheckArray implements Runnable 
{
	private boolean flag;
	private boolean [] winArray;
	SharedData sd;
	int[] array;
	int b;
	
	public ThreadCheckArray(SharedData sd) 
	{
		this.sd = sd;	
		synchronized (sd) 
		{
			array = sd.getArray();
			b = sd.getB();
		}		
		winArray = new boolean[array.length];
	}
	
	void rec(int n, int b)
	{
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		if (n == 1)
		{
			if(b == 0 || b == array[n-1])
			{
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
					/**
                     * @author Ron
                     * Added the following lines for the winner thread
                     */
                    if (b!=0)
                        System.out.println(Thread.currentThread().getName() + " win");
				}			
			}
			if (b == array[n-1])
				winArray[n-1] = true;
			return;
		}
		
		rec(n-1, b - array[n-1]);
		if (flag)
			winArray[n-1] = true;
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		rec(n-1, b);
	}

	public void run() {
        /**
         * @author NitMa
         * Added the following line for save start running time for thread
         */
        long startTimeMillis = System.nanoTime();
		if (array.length != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array.length-1, b - array[array.length - 1]);
			else 
				rec(array.length-1, b);
		if (array.length == 1)
			if (b == array[0] && !flag)
			{
				winArray[0] = true;
				flag = true;
				synchronized (sd) 
				{
					/**
                     * @author Ron
                     * Added the following lines for the winner thread
                     */
                    if (sd.getFlag() && b!=0)
                        System.out.println(Thread.currentThread().getName() + " win");
					sd.setFlag(true);
					
				}
			}
		if (flag)
		{
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array.length - 1] = true;
			synchronized (sd) 
			{
				sd.setWinArray(winArray);
			}	
		}
        /**
         * @author NitMa
         * Added the following line for printing running time of thread
         */
        System.out.println(Thread.currentThread().getName() + " runs for " +
                (System.nanoTime() - startTimeMillis) + " nano sec");
	}
}
