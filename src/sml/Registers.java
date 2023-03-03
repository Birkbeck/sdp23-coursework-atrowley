// Name: Adam Rowley
// Username (GitHub): atrowley
// Birkbeck ID: 13192359

package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class [COMPLETED]
/**
 * This class contains a registers Hashmap that stores each register name (key) and the
 * respective integer value it contains. Register names will be set in accordance with enum Register,
 * and all values set to zero on instancing the class.
 * <br><br>
 * Class converted to a singleton as only one instance to exist in the program
 * @author BBK staff member
 */
public final class Registers {

  private static Registers registersInstance = null;
  private final Map<Register, Integer> registers = new HashMap<>();

  public enum Register implements RegisterName {
    EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI;
  }

  /**
   * Constructor is private in order to make class singleton by preventing
   * new instances being created outside the class
   */
  private  Registers() {
    clear(); // the class is final
  }

  /**
   * [AR] Adds each respective Register and start val zero to registers Map
   */
  public void clear() {
    for (Register register : Register.values())
      registers.put(register, 0);
  }

  /**
   * Returns the value stored in the register.
   *
   * @param register register name
   * @return value
   */
  public int get(RegisterName register) {
    return registers.get((Register)register);
  }

  // TODO: use pattern matching for instanceof [COMPLETED]
  // https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
  @Override
  public boolean equals(Object o) {
    if (o instanceof Registers other) {
      return registers.equals(other.registers);
    }
    return false;
  }

  /**
   * Returns the singleton instance of the class if it exists or instantiates it if not
   * @return the singleton instance of Registers
   */
  public static synchronized Registers getRegisters(){
    if (registersInstance == null){
      registersInstance = new Registers();
    }
    return registersInstance;
  }

  @Override
  public int hashCode() {
    return registers.hashCode();
  }

  /**
   * Sets the given register to the value.
   *
   * @param register register name
   * @param value new value
   */
  public void set(RegisterName register, int value) {
    registers.put((Register)register, value);
  }


  @Override
  public String toString() {
    return registers.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(e -> e.getKey() + " = " + e.getValue())
            .collect(Collectors.joining(", ", "[", "]")) ;
  }
}
