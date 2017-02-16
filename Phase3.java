// package Minima // this is our package declaration so it would run in our IDE
package edu.uiowa.cs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Phase3 {

    /* Translate each Instruction object into
     * a 32-bit number.
     *
     * tals: list of Instructions to translate
     *
     * returns a list of instructions in their 32-bit binary representation
     *
     */
    public static List<Integer> translate_instructions(List<Instruction> tals) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        ArrayList<String> result = new ArrayList<String>();
        
        
        for(int i = 0; i < tals.size(); i++){
                        
            if(tals.get(i).instruction_id == 1){ // addiu, I-type, opcode = 9
                
                String opcode = Integer.toBinaryString(9);
                opcode = String.format("%06d", Integer.parseInt(opcode)); // gets us correct number of bits
                                
                String $rs = Integer.toBinaryString(tals.get(i).rs);
                $rs = String.format("%05d", Integer.parseInt($rs));
                
                String $rt = Integer.toBinaryString(tals.get(i).rt);
                $rt = String.format("%05d", Integer.parseInt($rt));
                
                String imm = Integer.toBinaryString(tals.get(i).immediate);
                imm = String.format("%016d", Integer.parseInt(imm));
                
                result.add(opcode + $rs + $rt + imm);
                
            }else if(tals.get(i).instruction_id == 2){ // addu, R-type, funct = 33
                
                String opcode = "000000"; // opcode for R-type
                
                String $rs = Integer.toBinaryString(tals.get(i).rs);
                $rs = String.format("%05d", Integer.parseInt($rs));
                
                String $rt = Integer.toBinaryString(tals.get(i).rt);
                $rt = String.format("%05d", Integer.parseInt($rt));
                
                String $rd = Integer.toBinaryString(tals.get(i).rd);
                $rd = String.format("%05d", Integer.parseInt($rd));
                
                String shamt = "00000"; // zero in binary
                
                String funct = "100001"; // 33 in binary
                
                result.add(opcode + $rs + $rt + $rd + shamt + funct);
                
            }else if (tals.get(i).instruction_id == 3){ // or, R-type, funct = 37
                
                String opcode = "000000"; // opcode for R-type
                
                String $rs = Integer.toBinaryString(tals.get(i).rs);
                $rs = String.format("%05d", Integer.parseInt($rs));
                
                String $rt = Integer.toBinaryString(tals.get(i).rt);
                $rt = String.format("%05d", Integer.parseInt($rt));
                
                String $rd = Integer.toBinaryString(tals.get(i).rd);
                $rd = String.format("%05d", Integer.parseInt($rd));
                
                String shamt = Integer.toBinaryString(tals.get(i).shift_amount);
                shamt = String.format("%05d", Integer.parseInt(shamt));
                
                String funct = "100101"; // 37 in binary
                
                result.add(opcode + $rs + $rt + $rd + shamt + funct);
                
            }else if (tals.get(i).instruction_id == 5){ // beq, I-type, opcode = 4
                
                String opcode = Integer.toBinaryString(4);
                opcode = String.format("%06d", Integer.parseInt(opcode)); // gets us correct number of bits
                
                String $rs = Integer.toBinaryString(tals.get(i).rs);
                $rs = String.format("%05d", Integer.parseInt($rs));
                
                String $rt = Integer.toBinaryString(tals.get(i).rt);
                $rt = String.format("%05d", Integer.parseInt($rt));
                
                String imm = Integer.toBinaryString(tals.get(i).immediate);
                imm = String.format("%016d", Integer.parseInt(imm));
                
                result.add(opcode + $rs + $rt + imm);
                
            }else if (tals.get(i).instruction_id == 6){ // bne, I-type, opcode = 5
                
                String opcode = Integer.toBinaryString(5);
                opcode = String.format("%06d", Integer.parseInt(opcode)); // gets us correct number of bits
                
                String $rs = Integer.toBinaryString(tals.get(i).rs);
                $rs = String.format("%05d", Integer.parseInt($rs));
                
                String $rt = Integer.toBinaryString(tals.get(i).rt);
                $rt = String.format("%05d", Integer.parseInt($rt));
                
                String imm = Integer.toBinaryString(tals.get(i).immediate);
                imm = String.format("%016d", Integer.parseInt(imm));
                
                result.add(opcode + $rs + $rt + imm);
                
            }else if (tals.get(i).instruction_id == 8){ // slt, R-type, funct = 42
                
                String opcode = "000000"; // opcode for R-type
                
                String $rs = Integer.toBinaryString(tals.get(i).rs);
                $rs = String.format("%05d", Integer.parseInt($rs));
                
                String $rt = Integer.toBinaryString(tals.get(i).rt);
                $rt = String.format("%05d", Integer.parseInt($rt));
                
                String $rd = Integer.toBinaryString(tals.get(i).rd);
                $rd = String.format("%05d", Integer.parseInt($rd));
                
                String shamt = Integer.toBinaryString(tals.get(i).shift_amount);
                shamt = String.format("%05d", Integer.parseInt(shamt));
                
                String funct = "101010"; // 42 in binary
                
                result.add(opcode + $rs + $rt + $rd + shamt + funct);
                
            }else if (tals.get(i).instruction_id == 9){ // lui, I-type, opcode = 15
                
                String opcode = Integer.toBinaryString(15);
                opcode = String.format("%06d", Integer.parseInt(opcode)); // gets us correct number of bits
                
                String $rs = Integer.toBinaryString(tals.get(i).rs);
                $rs = String.format("%05d", Integer.parseInt($rs));
                
                String $rt = Integer.toBinaryString(tals.get(i).rt);
                $rt = String.format("%05d", Integer.parseInt($rt));
                
                String imm = Integer.toBinaryString(tals.get(i).immediate);
                imm = imm.substring(0, 15);               
                
                result.add(opcode + $rs + $rt + imm);
                
                
            }else if (tals.get(i).instruction_id == 10){ // ori, I-type, opcode = 13
                
                String opcode = Integer.toBinaryString(13);
                opcode = String.format("%06d", Integer.parseInt(opcode)); // gets us correct number of bits
                
                String $rs = Integer.toBinaryString(tals.get(i).rs);
                $rs = String.format("%05d", Integer.parseInt($rs));
                
                String $rt = Integer.toBinaryString(tals.get(i).rt);
                $rt = String.format("%05d", Integer.parseInt($rt));
                
                String imm = Integer.toBinaryString(tals.get(i).immediate);
                imm = imm.substring(0, 15);                
                
                result.add(opcode + $rs + $rt + imm);
                
            }else{
                System.out.println("Error on instruction # " + i);
                result.add("00000000000000000000000000000000");
            }
            
            for(int j = 0; j < result.size(); j++){
                // convert each string back into an int and add to output list
                Integer temp = Integer.parseInt(result.get(i), 2);
                output.add(temp);
                
            }
        }
        return output;
    }
}
