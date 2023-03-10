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
 * Instruction subclass that handles printing values from program registers
 * to the console. For example the following instruction would print the value
 * held by register EAX:
 *
 * <br><br><b>out EAX</b><br><br>
 *
 * The superclass handles all common functionality of instructions. This class
 * handles specific functionality relating to the "out" operation.
 *
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
public class OutInstruction extends Instruction {

	public static final String OP_CODE = "out";
	private final RegisterName source;

	/**
	 * Constructor that is accessed by an implementation of InstructionSetFactory via reflection
	 * @param label the instruction label
	 * @param source the name of the source register
	 * @param empty will be null as OutInstructions have no second operand
	 */
	public OutInstruction(String label, String source, String empty) {
		super(label, OP_CODE);

		if(source==null) throw new RuntimeException("A register value for opcode '" + OP_CODE + "' is missing (null)");

		try{
			this.source = Registers.Register.valueOf(source);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Register does not exist: "+ source, e);
		}
	}

	/**
	 * Checks whether an object has equal properties to this OutInstruction
	 * @param obj an object
	 * @return true if other object is also an OutInstruction, and has the
	 * same label, opcode, and source register
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(!(obj instanceof OutInstruction other)) return false;
		return this.source.equals(other.source)
						&& this.getOpcode().equals(other.getOpcode())
						&& Objects.equals(this.getLabel(), other.getLabel());
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
	 * Implements specific hash code methodology for movInstruction objects
	 * @return int
	 */
	@Override
	public int hashCode() {
		final int PRIME = 41;
		return PRIME + Objects.hash(getLabel(), getOpcode(), source);
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
