package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;
import java.util.Objects;

/**
 * Instruction subclass that controls the flow of the program by implementing
 * a jump to a specific location in the program (denoted by a label) if the
 * indicated source register does not contain the value 0
 * <p>
 * The superclass handles all common functionality of instructions. This class
 * handles specific functionality relating to the "jump" operation.
 *
 * @author Adam Rowley
 * @author GitHub username atrowley
 */
public class JnzInstruction extends Instruction {

	public static final String OP_CODE = "jnz";
	private final RegisterName source;
	private final String labelToJumpTo;

	public JnzInstruction(String label, RegisterName source, String labelToJumpTo) {
		super(label, OP_CODE);
		this.source = source;
		this.labelToJumpTo = labelToJumpTo;
	}

	/**
	 * Constructor that is accessed by an implementation of InstructionSetFactory via reflection
	 * @param label the instruction label
	 * @param source the name of the source register
	 * @param labelToJumpTo the name of the label to jump to
	 */
	public JnzInstruction(String label, String source, String labelToJumpTo) {
		super(label, OP_CODE);
		this.source = Registers.Register.valueOf(source);
		this.labelToJumpTo = labelToJumpTo;
	}

	/**
	 * Returns number of the instruction to jump to in the program if the
	 * source register does not contain a value of 0.
	 *
	 * @param machine an instanced Machine object
	 * @return NORMAL_PROGRAM_COUNTER_UPDATE if source register holds a value of 0.
	 * Otherwise, will return address to which the label refers.
	 */
	@Override
	public int execute(Machine machine) {
		if(machine.getRegisters().get(source) != 0){
			return machine.getLabels().getAddress(labelToJumpTo);
		};
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * Checks whether an object has equal properties to this JnzInstruction
	 * @param obj an object
	 * @return true if other object is also a JnzInstruction, and has same
	 * label, opcode, source register, and instruction to jump to
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof JnzInstruction other)) return false;
		return this.source.equals(other.source)
				&& this.getOpcode().equals(other.getOpcode())
				&& this.labelToJumpTo.equals(other.labelToJumpTo)
				&& Objects.equals(this.getLabel(), other.getLabel());
	}

	/**
	 * Implements specific hash code methodology for jnz instructions
	 * @return int
	 */
	@Override
	public int hashCode() {
		final int PRIME = 41;
		return PRIME +Objects.hash(source, getOpcode(), labelToJumpTo, getLabel());
	}

	/**
	 * Returns a string that represents the instruction. For example:
	 * <br><b>jnz EAX f1</b>
	 * @return string that represents the instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source + " " + labelToJumpTo;
	}
}
