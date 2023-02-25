package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class MoveInstruction extends Instruction {
	private final RegisterName result;
	private final int value;

	public static final String OP_CODE = "mov";

	public MoveInstruction(String label, RegisterName result, int value) {
		super(label, OP_CODE);
		this.result = result;
		this.value = value;
	}

	@Override
	public int execute(Machine m) {
//		int value1 = m.getRegisters().get(result);
		m.getRegisters().set(result, this.value);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}


	/**
	 * AR TODO: Javadoc
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MoveInstruction)) return false;
		MoveInstruction other = (MoveInstruction) obj;
		return this.result.equals(other.result)
				&& this.value == other.value
				&& this.opcode.equals(other.opcode)
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
		return getLabelString() + getOpcode() + " " + result + " " + Integer.toString(this.value);
	}
}
