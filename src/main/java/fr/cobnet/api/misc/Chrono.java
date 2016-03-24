package fr.cobnet.api.misc;

/**
 * @TODO faire nano seconde
 */
public class Chrono {
	private long start;
	private long end;
	
	public void end() {
		end = System.currentTimeMillis();
	}
	
	public void start() {
		start = System.currentTimeMillis();
	}
	
	public long getTime() {
		return (end < start) ? 0 : end - start;
	}
}