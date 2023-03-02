package sml.instruction;
import sml.InstructionWithResultAndSource;
import sml.Machine;
import sml.RegisterName;

/**
 * Extends the InstructionWitResultAndSource class as constructor takes both
 * a source register and result register.
 * <br><br>
 * Overrides the execute method to pass a BinaryOperator that represents an integer
 * division operation to the superclass method applyExecution().
 * <br><br>
 * The superclass handles all common functionality of instructions that take both
 * a result and source register as an operand. This class handles specific functionality
 * relating to the addition operation.
 *
 * @author Adam Rowley
 * @author GitHub username atrowley
 */
public class DivInstruction extends InstructionWithResultAndSource {

	public static final String OP_CODE = "div";

	/**
	 * Constructor that is accessed by an implementation of InstructionSetFactory via reflection
	 * @param label the instruction label
	 * @param result the name of the result register
	 * @param source the name of the source register
	 */
	public DivInstruction(String label, String result, String source) {
		super(label, result, source, OP_CODE);
	}

	/**
	 * Passes a BinaryOperator that represents an integer division operation
	 * to the .applyExecution() method of the superclass
	 *
	 * @param machine an instanced Machine object
	 * @return will always return NORMAL_PROGRAM_COUNTER_UPDATE
	 * as no jump will result from the operation
	 */
	@Override
	public int execute(Machine machine) {
		applyExecution((a, b) -> a / b, machine);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

}
