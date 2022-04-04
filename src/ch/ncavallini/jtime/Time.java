package ch.ncavallini.jtime;
/**
 *
 *
 */

/**
 * @author Niccolò Cavallini
 *
 */
public final class Time {
	private int defaultPrecision;
	private boolean hasBeenUsed;
	private boolean isRunning;
	private long startTime;
	private double time;

	/**
	 * Default constructor. It initialises a {@code Time} object and sets the
	 * default precision to 5 decimal digits.
	 */

	public Time() {
		startTime = 0L;
		time = 0.0d;
		isRunning = false;
		hasBeenUsed = false;
		defaultPrecision = 5;
	}

	/**
	 * It initialises a {@code Time} object and sets the default precision to
	 * {@code defaultPrecision} decimal digits.
	 * 
	 * @param defaultPrecision the number of decimal digits wanted in {@code String} representation.
	 * @throws IllegalArgumentException if {@code defaultPrecision < 0}.
	 */
	public Time(int defaultPrecision) {
		this();
		if (defaultPrecision < 0)
			throw new IllegalArgumentException("Precision must be >= 0!");
		this.defaultPrecision = defaultPrecision;
	}

	/**
	 *
	 * Returns the time elapsed (as {@code double}) since {@code start()} was
	 * called. If {@code start()} hasn't been called yet, {@code 0.0d} is returned.
	 * 
	 * @return a {@code double} representing the elapsed time (in milliseconds)
	 *         since {@code start()} has been called.
	 */

	public double getTime() {
		return this.time;
	}

	/**
	 *
	 * @return {@code true} if the instance has already been started and stopped.
	 */
	public boolean hasBeenUsed() {
		return hasBeenUsed;
	}

	/**
	 *
	 * @return {@code true} if the instance is running, i.e. the {@code start()}
	 *         method has been called, but {@code stop()} hasn't yet.
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * Resets the instance at the initial state, making it possible to reuse an
	 * existing {@code Time} object. It keeps the {@code defaultPrecision} field
	 * unchanged.
	 */
	public void reset() {
		stop();
		startTime = 0L;
		time = 0.0d;
		isRunning = false;
		hasBeenUsed = false;

	}

	/***
	 * Starts counting time. To be used right before the job you want to compute the
	 * time it takes.
	 * 
	 * @throws IllegalStateException if this {@code Time} instance is already
	 *                               runnning (i.e. {@code start()} has already been
	 *                               called) or if the instance has already been
	 *                               used (i.e. both {@code start()} and
	 *                               {@code stop()} have been called).
	 */
	public void start() {
		if (isRunning)
			throw new IllegalStateException("This Time instance is already running!");
		if (hasBeenUsed)
			throw new IllegalStateException("Cannot use a Time instance more than once without resetting it first!");
		this.startTime = System.nanoTime();
		this.isRunning = true;
		this.hasBeenUsed = true;
	}

	/**
	 * Stops counting time. To be called right after the job you want to compute the
	 * time it takes finishes.
	 */
	public void stop() {
		long endTime = System.nanoTime();
		long elapsedNs = endTime - startTime;
		this.time = elapsedNs / 1.0e6;
		this.isRunning = false;
	}

	/**
	 * Stops counting time (analogously to {@code stop()} and returns the time
	 * elapsed (in milliseconds) as {@code double}.
	 * 
	 * @return a {@code double} representing the elapsed time (in milliseconds)
	 *         since {@code start()} has been called.
	 */
	public double stopAndGet() {
		stop();
		return getTime();
	}

	/**
	 *
	 * @return a String representation of the time elapsed since {@code start()} was
	 *         called with the number of decimal digits defined in the
	 *         {@code Time(int 			precision)} constructor. If the
	 *         zero-argument constructor was used, a default precision of 5 decimal
	 *         digits is hard-coded.
	 */
	@Override
	public String toString() {
		Double d = Double.valueOf(this.time);
		String f = "%." + defaultPrecision + "f";
		return String.format(f, d);
	}

	/**
	 *
	 * @param precision The number of desired decimal digits
	 * @return a String representation of the time elapsed since {@code start()} was
	 *         called with the given number of decimal digits.
	 * @throws IllegalArgumentException if {@code precision < 0}.
	 */

	public String toString(int precision) {
		if (precision < 0)
			throw new IllegalArgumentException("Precision must be >= 0!");
		Double d = Double.valueOf(this.time);
		String f = "%." + precision + "f";
		return String.format(f, d);
	}
}
