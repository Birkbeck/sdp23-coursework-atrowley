package sml;

// TODO: write a JavaDoc for the class

import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * @author
 */

public abstract class InstructionWithResultAndSource extends Instruction {
	protected final RegisterName result;
	protected final RegisterName source;

//	public static final String OP_CODE = "div";

	public InstructionWithResultAndSource(String label, RegisterName result, RegisterName source, String OP_CODE) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	public void applyExecution(BinaryOperator<Integer> func, Machine m){
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, func.apply(value1,value2));
	}

	/**
	 * AR TODO: placeholder to complete
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof InstructionWithResultAndSource)) return false;
		InstructionWithResultAndSource other = (InstructionWithResultAndSource) obj;
		return this.result.equals(other.result)
				&& this.source.equals(other.source)
				&& this.opcode.equals(other.opcode)
				&& Objects.equals(this.getLabel(), other.getLabel());
	}

	public abstract int execute(Machine machine);

	/**
	 * AR TODO: placeholder to complete
	 */
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}
}
