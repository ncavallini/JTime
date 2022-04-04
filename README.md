# JTime
A simple Java Class to ease keeping time of running jobs.
## How to Use JTime
Simply import the `Time.java` class in your project and instanciate it when needed. See a Minimal Working Example below.

```java
package ch.ncavallini.jtime;

public class MWE {

	public static void main(String[] args) {

		Time time = new Time(); // Instantiate object.

		time.start(); // Start keeping time.

		doSomeLongComputation(13); // dumb computation: O(n!) function

		double elapsed = time.stopAndGet(); // Stop keeping time and returns the time value in milliseconds as double.

		System.out.println("Time elapsed: " + elapsed + " ms");
	
  }

	private static void doSomeLongComputation(int n) {

		for(int i=0; i<n; i++) {
			doSomeLongComputation(n-1);

		}
	}

}
```
(On my PC that computation took about 20 seconds).
## Documentation
The code is self-documenting (Javadoc annotations), however, you may want to read the documentation in a more user-friendly way. If this is the case, just open the `index.html` file in the `/doc` folder or simply [click here](https://ncavallini.github.io/JTime/).
