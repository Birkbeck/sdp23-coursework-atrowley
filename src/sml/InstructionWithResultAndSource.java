package sml;

import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Represents an abstract instruction of the type that specifically utilises
 * both a result and source register as both of its operands. Implements all
 * functionality related to such instruction types other than the final operation
 * (which is to be defined by subclasses). <br><br>
 *
 * Defines register parameters that will be common to all derived subclasses
 * (result and source), executes the operation of any derived subclass, and defines the
 * following abstract method that must be implemented:
 * <br><br>
 * execute()<br>
 * @author Adam Rowley
 * @author GitHub username atrowley
 */
public abstract class InstructionWithResultAndSource extends Instruction {
	protected final RegisterName result;
	protected final RegisterName source;

	public InstructionWithResultAndSource(String label, RegisterName result, RegisterName source, String OP_CODE) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Method that can be called by a subclass in order to apply the subclass
	 * operation implemented within the execute() method to the result and source registers.
	 * <br><br>
	 * The first and second parameters of the BinaryOperator will relate to the result
	 * and source registers respectively. For example the below instruction will
	 * subtract value in the source register from the value in the result register:
	 * <br><br> (a,b) -> a - b
	 * @param func BinaryOperator<Integer> an operation that is defined by a respective
	 *             subclass. For example: (a,b) -> a * b for simple multiplication
	 * @param machine the instanced Machine object
	 */
	public void applyExecution(BinaryOperator<Integer> func, Machine machine){
		int value1 = machine.getRegisters().get(result);
		int value2 = machine.getRegisters().get(source);
		machine.getRegisters().set(result, func.apply(value1,value2));
	}

	/**
	 * Checks whether an object has equal properties to this Instruction. As this
	 * includes verifying the opcode it can be used to compare subclasses of different
	 * instruction/opcode type.
	 * @param obj an object
	 * @return true if other object is also an InstructionWithResultAndSource object,
	 * and has the same label, opcode, result register, and source register
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof InstructionWithResultAndSource other)) return false;
		return this.result.equals(other.result)
				&& this.source.equals(other.source)
				&& this.getOpcode().equals(other.getOpcode())
				&& Objects.equals(this.getLabel(), other.getLabel());
	}

	/**
	 * Abstract method to be implemented by all subclasses of
	 * InstructionWithResultAndSource
	 * @param machine the machine the instruction runs on
	 * @return int that is used to update program counter value
	 */
	public abstract int execute(Machine machine);

	/**
	 * Implements specific hash code methodology for InstructionWithResultAndSource
	 * objects and subclasses
	 * @return int representing the hashcode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getLabel(), getOpcode(), result, source);
	}

	/**
	 * Returns a string that represents the instruction. For example:
	 * <br><b>f1: add EAX EBX</b>
	 * @return string that represents the instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}
}
