package sml.instruction;

import sml.*;

import java.util.Objects;

import static sml.Registers.Register.EBX;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class OutInstruction extends Instruction {
	private final RegisterName source;

	public static final String OP_CODE = "out";

	public OutInstruction(String label, RegisterName source) {
		super(label, OP_CODE);
		this.source = source;
	}

	public OutInstruction(String label, String source, String empty) {
		super(label, OP_CODE);
		this.source = Registers.Register.valueOf(source);
	}

	@Override
	public int execute(Machine m) {
		System.out.println(m.getRegisters().get(source));
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}


	/**
	 * AR TODO: Javadoc
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof OutInstruction)) return false;
		OutInstruction other = (OutInstruction) obj;
		return this.source.equals(other.source)
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
		return getLabelString() +  getOpcode() + " " + source;
	}
}
