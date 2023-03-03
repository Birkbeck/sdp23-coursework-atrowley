// Name: Adam Rowley
// Username (GitHub): atrowley
// Birkbeck ID: 13192359

package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

/**
 * Instruction subclass that handles assignment of new values to the program registers.
 * For example the following instruction would move value 5 to register EAX:
 *
 * <br><br><b>mov EAX 5</b><br><br>
 *
 * The superclass handles all common functionality of instructions. This class
 * handles specific functionality relating to the move operation.
 *
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
public class MovInstruction extends Instruction {

	public static final String OP_CODE = "mov";
	private final int value;
	private final RegisterName result;

	/**
	 * Constructor that is accessed by an implementation of InstructionSetFactory via reflection
	 * @param label the instruction label
	 * @param result the name of the result register
	 * @param value the value to move to the register
	 */
	public MovInstruction(String label, String result, String value) {
		super(label, OP_CODE);

		if(result==null) throw new RuntimeException("A register value for opcode '" + OP_CODE + "' is missing (null)");

		try{
			this.result = Registers.Register.valueOf(result);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Register does not exist: "+ result, e);
		}

		try{
			this.value = Integer.parseInt(value);
		}catch(NumberFormatException e){
			throw new RuntimeException("Invalid value for mov instruction: '" + value + "'", e);
		}
	}

	/**
	 * Assigns the value indicated in the second operand of the instruction
	 * to the register indicated in the first operand
	 * @param machine an instanced Machine object
	 * @return always returns NORMAL_PROGRAM_COUNTER_UPDATE value
	 */
	@Override
	public int execute(Machine machine) {
		machine.getRegisters().set(result, this.value);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * Checks whether an object has equal properties to this MovInstruction
	 * @param obj an object
	 * @return true if other object is also a MovInstruction, and has same
	 * label, opcode, result register, and int value
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof MovInstruction other)) return false;
		return this.result.equals(other.result)
				&& this.value == other.value
				&& this.getOpcode().equals(other.getOpcode())
				&& Objects.equals(this.getLabel(), other.getLabel());
	}

	/**
	 * Implements specific hash code methodology for movInstruction objects
	 * @return int
	 */
	@Override
	public int hashCode() {
		final int PRIME = 41;
		return PRIME +Objects.hash(getLabel(), getOpcode(), result, value);
	}

	/**
	 * Returns a string that represents the instruction. For example:
	 * <br><b>f3 mov EAX 5</b>
	 * @return string that represents the instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result
				+ " " + Integer.toString(this.value);
	}

}
