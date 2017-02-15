package mma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Phase2 {

	/*
	 * Returns a list of copies of the Instructions with the immediate field of
	 * the instruction filled in with the address calculated from the
	 * branch_label.
	 *
	 * The instruction should not be changed if it is not a branch instruction.
	 *
	 * unresolved: list of instructions without resolved addresses first_pc:
	 * address where the first instruction will eventually be placed in memory
	 */

	List<Instruction> talInstructions; // List of tal instructions generated from Phase1.java
	HashMap<String, Instruction> memAddr = new HashMap<String, Instruction>(); // Used to represent locations of instructions in memory
	Phase1 phase1 = new Phase1(); // Instance of Phase1; idk if I'm going to use this

    /**      
     *
     * @param unresolved
     * @param first_pc
     * @return
     */
	
	
	public List<Instruction> resolve_addresses(List<Instruction> unresolved, int first_pc) {
		System.out.println("Initial Instruction list");
		AddrInstr(unresolved);   // Generate HashMap of memory/instruction pairs.
		for(int i = 0; i < unresolved.size(); i ++){
			if(unresolved.get(i).branch_label != 0){                             // If instruction has a branch ID, set immediate = to branch ID
				unresolved.get(i).immediate = unresolved.get(i).branch_label;
			}
		}
		System.out.println("\n\n");
		System.out.println("Unresolved Instruction List");
		AddrInstr(unresolved);
		
		return null;
	}

	
	/**
	 *  AddrInstr generates the memory address for the tal instructions provided from phase1
	 *  
	 *  @param List<Instructions> -> pass from phase1 
	 *  @return HashMap of address, instruction pairs
	 * 
	 * **/
	public HashMap<String, Instruction> AddrInstr(List<Instruction> instructions) {
		
		for (int i = 0; i < instructions.size(); i++) {
			
			String address = dec2hexAddr(i * 4);          // Order tal instructions to addresses
			Instruction instr = instructions.get(i);      // Get instruction indexes
			memAddr.put(address, instr);                  // create key/value mapping
			System.out.println(address + " -> " + instructions.get(i).toString());  //Display hasmap
		}

		return memAddr;
	}

	/**
	 *  dec2hex converts decimal integers into their hexadecimal representations
	 *  @param decimal to be converted to hex
	 *  @return String representation of memory address
	 * 
	 * **/
	public String dec2hexAddr(int decimal) {
		String hexAddr = Integer.toHexString(decimal);
		if (decimal < 16) {
			hexAddr = "0x0040000" + hexAddr;
		}
		if (decimal >= 16 && decimal < 256) {
			hexAddr = "0x004000" + hexAddr;
		}
		if (decimal >= 256 && decimal < 4096) {
			hexAddr = "0x00400" + hexAddr;
		}

		return hexAddr;
	}
	
	/** hex2decAddr converts hexadecimal strings into thier decimal representations
	 * 
	 * @param hexadecimal to be converted to hex
	 * @return int representation of memory address
	 */
	public int hex2decAddr(String hexadecimal){
		String hexNumber = "hexadecimal";
		int decimal = Integer.parseInt(hexNumber, 16);
		return decimal;
	}

	public static void main(String[] args) {
		Phase1 phase1 = new Phase1();

		List<Instruction> test = new ArrayList<Instruction>();
		Instruction t1 = new Instruction(0, 0, 0, 0, 0, 0, 0, 0, 0); // base
																		// step
		Instruction t2 = new Instruction(1, 0, 9, 8, 65535, 0, 0, 0, 0); // addiu
																			// step
		Instruction t3 = new Instruction(10, 0, 11, 10, 65536, 0, 0, 0, 0); // ori
																			// step
		Instruction t4 = new Instruction(100, 0, 16, 8, 0, 0, 0, 0, 3); // blt
																		// step
		Instruction t5 = new Instruction(101, 0, 17, 9, 0, 0, 0, 0, 4); // bge
																		// step

		test.add(t1);
		test.add(t2);
		test.add(t3);
		test.add(t4);
		test.add(t5);

		List<Instruction> result = new ArrayList<Instruction>();
		result = phase1.mal_to_tal(test);
		// System.out.println(result.toString());

		
		Phase2 phase2 = new Phase2();
		phase2.resolve_addresses(result, 0);

	}

}

