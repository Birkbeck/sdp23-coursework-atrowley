package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class that holds the label map that is used by the Machine class
 * and written to by the Translation class.
 * <br><br>
 * The key is the label name<br>
 * The value is the numerical address of the instruction that the label
 * refers to, i.e. the index position of the respective instruction in
 * the program ArrayList.
 * @author BBK staff member (code)
 * @author Adam Rowley (javadocs only)
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.<br>
	 * Throws runtime exception if program contains a duplicate label
	 * and generates error message accordingly
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.
		if (labels.containsKey(label)) {
			throw new RuntimeException("Duplicate label occurrence: "+label);
		} else {
			labels.put(label, address);
		}
	}


	/**
	 * Returns the address associated with the label.<br>
	 * Throws runtime exception if a null pointer occurs
	 * and generates error message accordingly
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
		// Explanation: a null pointer could occur if an attempt
		// is made to get a label that doesn't exist in the hashmap.
		// For example, this could happen if a jump instruction indicates
		// a jump to a label that does not exist.
		Optional<Integer> optAddress = Optional.ofNullable(labels.get(label));
		if (optAddress.isPresent()) {
			return optAddress.get();
		} else {
			throw new RuntimeException("Label not found: " + label);
		}
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		String labelMapString = labels.entrySet().stream()
				.map(e -> e.getKey()+ " -> " +e.getValue())
				.collect(Collectors.joining(", "));

		return "["+labelMapString+"]";
	}

	// TODO: Implement equals and hashCode (needed in class Machine).
	/**
	 * Checks whether an object has equal properties to this Label class
	 * @param obj an object
	 * @return true if other object is also a Label class, and contains a
	 * labels hashmap that is equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof Labels other)) return false;
		return Objects.equals(labels, other.labels);
	}

	/**
	 * Implements specific hash code methodology for Labels objects
	 * @return int
	 */
	@Override
	public int hashCode() {
		final int PRIME = 41;
		return PRIME + Objects.hash(labels);
	}
}
