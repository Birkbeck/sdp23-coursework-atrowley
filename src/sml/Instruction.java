package sml;

// TODO: write a JavaDoc for the class
//  [COMPLETED]
/**
 * Represents an abstract instruction.
 * Includes parameters common to all subclasses (label, opcode, and default
 * program counter incrementation value). Defines following abstract methods that
 * must be implemented by instruction subclasses:
 * <br><br>
 * execute()<br>
 * toString()<br>
 * equals()<br>
 * hashCode()
 * @author BBK staff member / updated by Adam Rowley (GitHub username atrowley)
 */
public abstract class
Instruction {

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() {
		return opcode;
	}

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */
	public abstract int execute(Machine machine);

	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// TODO: What does abstract in the declaration below mean?
	//       (Write a short explanation.)
	//Explanation:
	//Making the toString() method abstract will ensure that all
	//subclasses of Instruction will have to include in implementation of the method
	@Override
	public abstract String toString();

	// TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine). [COMPLETED]
	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract int hashCode();

}
