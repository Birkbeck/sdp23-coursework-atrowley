package sml.instruction;
import sml.InstructionWithResultAndSource;
import sml.Machine;
import sml.RegisterName;


// TODO: write a JavaDoc for the class
/**
 * @author
 */

public class AddInstruction extends InstructionWithResultAndSource {

	public static final String OP_CODE = "add";

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, result, source, OP_CODE);
	}

	@Override
	public int execute(Machine m) {
		applyExecution(Integer::sum, m);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

}
