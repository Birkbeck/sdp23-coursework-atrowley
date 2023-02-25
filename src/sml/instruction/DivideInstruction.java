package sml.instruction;
import sml.InstructionWithResultAndSource;
import sml.Machine;
import sml.RegisterName;


// TODO: write a JavaDoc for the class
/**
 * @author
 */

public class DivideInstruction extends InstructionWithResultAndSource {

	public static final String OP_CODE = "div";

	public DivideInstruction(String label, RegisterName result, RegisterName source) {
		super(label, result, source, OP_CODE);
	}

	@Override
	public int execute(Machine m) {
		applyExecution((a, b) -> a / b, m);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

}