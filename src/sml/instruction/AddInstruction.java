package sml.instruction;
import sml.InstructionWithResultAndSource;
import sml.Machine;
import sml.RegisterName;

/**
 * Extends the InstructionWitResultAndSource class as constructor takes both
 * a source register and result register.
 *
 * Overrides the execute method to pass a BinaryOperator that represents an addition
 * operation to the superclass method applyExecution().
 *
 * The superclass handles all common functionality of instructions that take both
 * a result and source register as an operand. This class handles specific functionality
 * relating to the addition operation.
 *
 * @author Adam Rowley
 * @author GitHub username atrowley
 */
public class AddInstruction extends InstructionWithResultAndSource {

	public static final String OP_CODE = "add";

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, result, source, OP_CODE);
	}

	/**
	 * Passes a BinaryOperator that represents an addition function
	 * to the .applyExecution() method of the superclass
	 *
	 * @param machine an instanced Machine object
	 * @return will always return NORMAL_PROGRAM_COUNTER_UPDATE
	 * as no jump will result from the operation
	 */
	@Override
	public int execute(Machine machine) {
		applyExecution(Integer::sum, machine);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}
}
