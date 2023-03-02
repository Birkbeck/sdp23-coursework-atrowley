package sml.instruction;
import sml.InstructionWithResultAndSource;
import sml.Machine;
import sml.RegisterName;

/**
 * Extends the InstructionWitResultAndSource class as constructor takes both
 * a source register and result register.
 * <p>
 * Overrides the execute method to pass a BinaryOperator that represents a multiplication
 * operation to the superclass method applyExecution().
 * <p>
 * The superclass handles all common functionality of instructions that take both
 * a result and source register as an operand. This class handles specific functionality
 * relating to the addition operation.
 *
 * @author Adam Rowley
 * @author GitHub username atrowley
 */
public class MulInstruction extends InstructionWithResultAndSource {

	public static final String OP_CODE = "mul";

	public MulInstruction(String label, RegisterName result, RegisterName source) {
		super(label, result, source, OP_CODE);
	}

	/**
	 * Constructor that is accessed by the InstructionSetFactory via reflection
	 * @param label the instruction label
	 * @param result the name of the result register
	 * @param source the name of the source register
	 */
	public MulInstruction(String label, String result, String source) {
		super(label, result, source, OP_CODE);
	}

	/**
	 * Passes a BinaryOperator that represents a multiplication operation
	 * to the .applyExecution() method of the superclass
	 *
	 * @param machine an instanced Machine object
	 * @return will always return NORMAL_PROGRAM_COUNTER_UPDATE
	 * as no jump will result from the operation
	 */
	@Override
	public int execute(Machine machine) {
		applyExecution((a, b) -> a * b, machine);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}
}
