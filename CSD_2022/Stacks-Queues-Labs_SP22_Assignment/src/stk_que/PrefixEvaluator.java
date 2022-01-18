package stk_que;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author nhattpam
 */
public class PrefixEvaluator {

    public static boolean isOperator(String S) {
        return (S.equals("+") || S.equals("-"))
                || S.equals("*") || S.equals("/");
    }

    //Evaluate a simple expression + - * /
    public static double evaluate(String op, double n1, double n2) {
        if (op.equals("+")) {
            return n1 + n2;
        }
        if (op.equals("-")) {
            return n1 - n2;
        }
        if (op.equals("*")) {
            return n1 * n2;
        }
        if (op.equals("/")) {
            if (n2 == 0) {
                throw new RuntimeException("Divide  by 0!");
            }
            return n1 / n2;
        }
        throw new RuntimeException("Operator is not supported!");
    }

    // pre : input contains a legal prefix expression
    // post: expression is consumed and the result is returned
    public static double evaluate(Scanner input) {
        if (input.hasNextDouble()) {
            return input.nextDouble();
        } else {
            String operator = input.next();
            double operand1 = evaluate(input);
            double operand2 = evaluate(input);
            return evaluate(operator, operand1, operand2);
        }
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("This program evaluates prefix expressions");
        System.out.println("for operators +, -, *, / and %");
        System.out.print("expression? ");
        System.out.println("value = " + evaluate(console));
    }

}
