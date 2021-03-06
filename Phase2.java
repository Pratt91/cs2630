// package Minima // this is our package declaration so it would run in our IDE
package edu.uiowa.cs;

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

	
	static HashMap<Integer, Instruction> memory = new HashMap<Integer, Instruction>();  // Used to map Memory Address/Instruction pairs

	
	public static List<Instruction> resolve_addresses(List<Instruction> unresolved, int first_pc) {
		System.out.println("Original unresolved");
		instrMap(unresolved, first_pc);
		for(int i = 0; i < unresolved.size(); i ++){
			if(unresolved.get(i).instruction_id == 5 || unresolved.get(i).instruction_id == 6 
					|| unresolved.get(i).instruction_id == 100 || unresolved.get(i).instruction_id == 101){
				
				int branchAddress2 = getAddress2(unresolved.get(i), unresolved.get(i).branch_label, first_pc);
				unresolved.get(i).immediate = branchAddress2;
			}
		}
		
		System.out.println("\n\n New unresolved");
		instrMap(unresolved, first_pc);
		
        return null;
    }

	/**
	 * Given the Instruction branch label and first memory address, getAddress2 calculates the address of 
	 *     desired branch label
	 * 
	 * 
	 * @param instruction
	 * @param branch_label
	 * @param first_pc
	 * @return Address of branch label
	 */
	
	private static int getAddress2(Instruction instruction, int branch_label, int first_pc){
		for(Integer i : memory.keySet()){
			if(memory.get(i).equals(instruction)){
				int origAddress = i;
			}
		}
		int branchAddress = (instruction.branch_label *4) + first_pc - 4;
			
		
		return branchAddress;
		
	}
	


	/**
	 * instrMap function takes in a list of Tal instructions, pairs them with their location in memory,
	 *     and returns a HashMap representation
	 * 
	 * @param instructions
	 * @return HashMap of Memory Addresses/Instructions (Key/Value) pairs
	 */
	static HashMap<Integer, Instruction> instrMap(List<Instruction> instructions, int first_pc){
		int firstAddress = first_pc;
		for(int i = 0; i < instructions.size(); i++){
			int newAddress = firstAddress + (i*4);
			memory.put(newAddress, instructions.get(i));
			System.out.println(newAddress + "  " + instructions.get(i).toString());
		}
		
		return memory;
	}
	
}