package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class JnzInstruction extends Instruction {
	private final RegisterName source;
	private final String labelToJumpTo;

	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, RegisterName source, String labelToJumpTo) {
		super(label, OP_CODE);
		this.source = source;
		this.labelToJumpTo = labelToJumpTo;
	}

	public JnzInstruction(String label, String source, String labelToJumpTo) {
		super(label, OP_CODE);
		this.source = Registers.Register.valueOf(source);
		this.labelToJumpTo = labelToJumpTo;
	}

	@Override
	public int execute(Machine m) {
		if(m.getRegisters().get(source) != 0){
			return m.getLabels().getAddress(labelToJumpTo);
		};
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}


	/**
	 * AR TODO: Javadoc
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof JnzInstruction)) return false;
		JnzInstruction other = (JnzInstruction) obj;
		return this.source.equals(other.source)
				&& this.opcode.equals(other.opcode)
				&& this.labelToJumpTo.equals(other.labelToJumpTo)
				&& Objects.equals(this.getLabel(), other.getLabel());
	}

	/**
	 * AR TODO: placeholder to complete
	 */
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source + " " + labelToJumpTo;
	}
}
