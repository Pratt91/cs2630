package edu.uiowa.cs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Phase1 {

    /* Translates the MAL instruction to 1-3 TAL instructions
     * and returns the TAL instructions in a list
     *
     * mals: input program as a list of Instruction objects
     *
     * returns a list of TAL instructions (should be same size or longer than input list)
     */
    public static List<Instruction> mal_to_tal(List<Instruction> mals) {
        List<Instruction> output =  new ArrayList<Instruction>();
        for(int i = 0; i < mals.size(); i++){
            if(mals.get(i).instruction_id == 1){ // case for addiu
                if(mals.get(i).immediate >= 65535){ // if the immediate is larger than 16 bits, breaks into steps
                    Instruction step1 = new Instruction(9, 0, 0, mals.get(i).rs, mals.get(i).immediate, 0, 0, 0, 0); // lui step
                    Instruction step2 = new Instruction(10, 0, 1, 1, mals.get(i).immediate, 0, 0, 0, 0); // ori step, uses $at register
                    Instruction step3 = new Instruction(2, mals.get(i).rt, mals.get(i).rs, 1, 0, 0, 0, 0, 0); // addu step
                }
                else{
                    output.add(mals.get(i)); // if immediate is small enough, just adds the instruction to output
                }
                                
            }else if(mals.get(i).instruction_id == 10){ // case for ori
                if(mals.get(i).immediate >= 65535){ // if the immediate is larger than 16 bits, breaks into steps
                    Instruction step1 = new Instruction(9, 0, 0, mals.get(i).rs, mals.get(i).immediate, 0, 0, 0, 0); // lui step
                    Instruction step2 = new Instruction(10, 0, 1, 1, mals.get(i).immediate, 0, 0, 0, 0); // ori step, uses $at register
                    Instruction step3 = new Instruction(2, mals.get(i).rt, mals.get(i).rs, 1, 0, 0, 0, 0, 0); // addu step
                }
                else{
                    output.add(mals.get(i)); // if immediate is small enough, just adds the instruction to output
                }
                
            }else if(mals.get(i).instruction_id == 100){ // case for blt
                Instruction step1 = new Instruction(8, 1, mals.get(i).rs, mals.get(i).rt, 0, 0, 0, 0, 0); //breaks mal instr. into 2 steps, this step is the slt part
                Instruction step2 = new Instruction(6, 0, 1, 0, 0, 0, 0, 0, mals.get(i).branch_label); // this step is the bne part
                output.add(step1); // adds the 2 steps in order to the output list
                output.add(step2);
                
            }else if (mals.get(i).instruction_id == 101){ // case for bge
                Instruction step1 = new Instruction(8, 1, mals.get(i).rt, mals.get(i).rs, 0, 0, 0, 0, 0); // step for the slt part
                Instruction step2 = new Instruction(5, 0, 1, 0, 0, 0, 0, 0, mals.get(i).branch_label); // step for the beq part
                output.add(step1); //adds the 2 steps in order to the output list
                output.add(step2);
                
            }else{
                output.add(mals.get(i)); // if none of the above conditions are met, instruction is a tal and is added to the list
            }
        }
        return output;
    }
    
    public static void main(String[] args){ //test method
        List<Instruction> test = new ArrayList<Instruction>();
        Instruction t1 = new Instruction(0,0,0,0,0,0,0,0,0); // base step
        Instruction t2 = new Instruction(1,0,9,8,65535,0,0,0,0); //addiu step
        Instruction t3 = new Instruction(10,0,11,10,65536,0,0,0,0); //ori step
        Instruction t4 = new Instruction(100,0,16,8,0,0,0,0,3); // blt step
        Instruction t5 = new Instruction(101,0,17,9,0,0,0,0,4); // bge step
        
        test.add(t1);
        test.add(t2);
        test.add(t3); 
        test.add(t4);
        test.add(t5);
        
        List<Instruction> result = new ArrayList<Instruction>();
        result = mal_to_tal(test);
        System.out.println(result.toString());
        
    }
    
}
