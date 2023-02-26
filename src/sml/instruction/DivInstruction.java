package sml.instruction;
import sml.InstructionWithResultAndSource;
import sml.Machine;
import sml.RegisterName;


// TODO: write a JavaDoc for the class
/**
 * @author
 */

public class DivInstruction extends InstructionWithResultAndSource {

	public static final String OP_CODE = "div";

	public DivInstruction(String label, RegisterName result, RegisterName source) {
		super(label, result, source, OP_CODE);
	}

	public DivInstruction(String label, String result, String source) {
		super(label, result, source, OP_CODE);
	}

	@Override
	public int execute(Machine m) {
		applyExecution((a, b) -> a / b, m);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

}
