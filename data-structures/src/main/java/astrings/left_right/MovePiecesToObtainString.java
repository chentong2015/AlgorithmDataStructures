package astrings.left_right;

// Move Pieces to Obtain a String
// You are given two strings start and target, both of length n.
// Each string consists only of the characters 'L', 'R', and '_'
//
// The characters 'L' and 'R' represent pieces,
// a piece 'L' can move to the left only if there is a blank space directly to its left,
// a piece 'R' can move to the right only if there is a blank space directly to its right.
// The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
//
// Return true if it is possible to obtain the string target
// by moving the pieces of the string start any number of times. Otherwise, return false.
//
// n == start.length == target.length
// 1 <= n <= 10^5
// start and target consist of the characters 'L', 'R', and '_'.
public class MovePiecesToObtainString {

    // TODO.
    // start = "_L__R__R_", target = "L______RR" -> true
    // start = "R_L_", target = "__LR" -> false
    // start = "_R", target = "R_" -> false
    //
    // L_ cannot convert to _L
    // _R cannot convert to R_
    //
    public boolean canChange(String start, String target) {

        return true;
    }
}
