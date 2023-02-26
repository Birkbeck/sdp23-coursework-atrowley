package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import java.util.Objects;

/**
 * Instruction subclass that handles printing values from program registers
 * to the console. For example the following instruction would print the value
 * held by register EAX:
 *
 * <br><br><b>out EAX</b><br><br>
 *
 * The superclass handles all common functionality of instructions. This class
 * handles specific functionality relating to the "out" operation.
 *
 * @author Adam Rowley
 * @author GitHub username atrowley
 */
public class OutInstruction extends Instruction {

	public static final String OP_CODE = "out";
	private final RegisterName source;

	public OutInstruction(String label, RegisterName source) {
		super(label, OP_CODE);
		this.source = source;
	}

	/**
	 * Prints value held in the indicated register to the console
	 * @param machine an instanced Machine object
	 * @return always returns NORMAL_PROGRAM_COUNTER_UPDATE value
	 */
	@Override
	public int execute(Machine machine) {
		System.out.println(machine.getRegisters().get(source));
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * Checks whether an object has equal properties to this OutInstruction
	 * @param obj an object
	 * @return true if other object is also an OutInstruction, and has the
	 * same label, opcode, and source register
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof OutInstruction other)) return false;
		return this.source.equals(other.source)
				&& this.getOpcode().equals(other.getOpcode())
				&& Objects.equals(this.getLabel(), other.getLabel());
	}

	/**
	 * Implements specific hash code methodology for movInstruction objects
	 * @return int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getLabel(), getOpcode(), source);
	}

	/**
	 * Returns a string that represents the instruction. For example:
	 * <br><b>f5 out EBX</b>
	 * @return string that represents the instruction
	 */
	@Override
	public String toString() {
		return getLabelString() +  getOpcode() + " " + source;
	}
}
