// Name: Adam Rowley
// Username (GitHub): atrowley
// Birkbeck ID: 13192359

package sml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

/**
 * Represents the machine, the context in which programs run.
 * <p>
 * An instance contains 32 registers and methods to access and change them.
 *
 */
public final class Machine {

  private static Machine machineInstance = null;
  private final Labels labels = Labels.getLabels();
  private final List<Instruction> program = new ArrayList<>();
  private final Registers registers;

  // The program counter; it contains the index (in program)
  // of the next instruction to be executed.
  private int programCounter = 0;

  /**
   * Private constructor used for singleton instantiation
   * @param registers the Registers object
   */
  private Machine(Registers registers) {
    this.registers = registers;
  }

  /**
   * Returns the singleton instance of the class if it exists or instantiates it if not
   * @return the singleton instance of Machine
   */
  public static Machine getMachine(Registers register){
    if(machineInstance == null){
      machineInstance = new Machine(register);
    }
    return machineInstance;
  }

  /**
   * Execute the program in program, beginning at instruction 0.
   * Precondition: the program and its labels have been stored properly.
   */
  public void execute() {
    programCounter = 0;
    registers.clear();
    while (programCounter < program.size()) {
      Instruction ins = program.get(programCounter);
      int programCounterUpdate = ins.execute(this);
      programCounter = (programCounterUpdate == NORMAL_PROGRAM_COUNTER_UPDATE)
              ? programCounter + 1
              : programCounterUpdate;
    }
  }

  public Labels getLabels() {
    return this.labels;
  }

  public List<Instruction> getProgram() {
    return this.program;
  }

  public Registers getRegisters() {
    return this.registers;
  }


  /**
   * String representation of the program under execution.
   *
   * @return pretty formatted version of the code.
   */
  @Override
  public String toString() {
    return program.stream()
            .map(Instruction::toString)
            .collect(Collectors.joining("\n"));
  }

  // TODO: use pattern matching for instanceof [COMPLETED]
  // https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
  @Override
  public boolean equals(Object o) {
    if (o instanceof Machine other) {
      return Objects.equals(this.labels, other.labels)
              && Objects.equals(this.program, other.program)
              && Objects.equals(this.registers, other.registers)
              && this.programCounter == other.programCounter;
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int PRIME = 41;
    return PRIME + Objects.hash(labels, program, registers, programCounter);
  }
}
