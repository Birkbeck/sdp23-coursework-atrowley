// Name: Adam Rowley
// Username (GitHub): atrowley
// Birkbeck ID: 13192359

package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *  This class defines the InstructionSetFactory implementation that will be used for the specific
 *  Coursework instruction set.
 *
 * @author Adam Rowley (Birkbeck ID: 13192359)
 * @author GitHub username atrowley
 */
public class InstructionSetCoursework implements InstructionSetFactory {

  /**
   * The constructor uses reflection to create an instance of the respective instruction
   * in accordance with the opcode provided. For example:
   * <br><br>
   * "div" = DivInstruction <br>
   * "out" = OutInstruction
   * <br><br>
   * Catches exceptions where instructions contain invalid opcode or register names.
   * @param opcode the opcode for the instruction
   * @param label the label for the instruction
   * @param operand1 the first operand of the instruction
   * @param operand2 the second operand of the instruction (if applicable)
   * @return an instruction of the type that corresponds to the opcode
   */
  @Override
  public Instruction newInstruction(String opcode, String label,String operand1, String operand2) throws RuntimeException {

    String insClassName = "sml.instruction." + opcode.substring(0, 1).toUpperCase()
            + opcode.substring(1) + "Instruction";

    try {
      Class<?> insClass = Class.forName(insClassName);
      Constructor<?> insConstructor = insClass.getConstructor(String.class, String.class, String.class);
      return (Instruction) insConstructor.newInstance(label, operand1, operand2);

    } catch (ClassNotFoundException e) {
      // Will catch invalid opcode
      throw new RuntimeException("No instruction found for opcode: " + opcode);
    } catch (InvocationTargetException e) {
      // Will catch invalid register
      throw new RuntimeException(e.getTargetException().getMessage());
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException e) {
      throw new RuntimeException("Failed to create instruction: " + e.toString());
    }

  }
}
