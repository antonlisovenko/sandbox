package com.alisovenko.arithmetics;

import java.util.Stack;

/**
 * Task: parse the formula and get the calculation result.
 *
 * Implementation: first transform to postfix notation (reverse polish notation) and then calculate using simple stack.
 *
 * Postfix notation: parse the formula from left to right, append digits to result, compare operators with the stack:
 * - if stack is empty - push to stack
 * - if the digit in stack is of less priority - push the new operator to stack (e.g. stack: '+', new: '*' -> push '*' to stack)
 * - if the digit in stack is of the same or bigger priority - pop it from stack and append to result. Compare with next element in stack. Push the current element to stack
 *
 * @author alisovenko 29.12.14
 */
public class ParseNumberUsingPostfixNotation {
    public static int calculate(String str) {
        String postfix = convertToPostfix(str);

        Stack<Integer> stack = new Stack<>();

        for (Character c : postfix.toCharArray()) {
            if (Character.isDigit(c)) {
                // this is operand, let's put it to stack
                stack.push(Character.digit(c, 10));
            }
            else {
                // this is operator, let's apply it to two operands from stack
                if (stack.size() <= 1) {
                    throw new IllegalArgumentException("Wrong expression");
                }
                int two = stack.pop();
                int one = stack.pop();

                if (c == '*') {
                    stack.push(one * two);
                }
                else if (c == '/') {
                    stack.push(one / two);
                }
                else if (c == '+') {
                    stack.push(one + two);
                }
                else if (c == '-') {
                    stack.push(one - two);
                }
            }
        }

        if (stack.size() != 1) {
            throw new RuntimeException();
        }
        return stack.pop();
    }

    private static String convertToPostfix(String str) {
        char[] ch = str.toCharArray();
        Stack<Character> stack = new Stack<>();

        StringBuilder b = new StringBuilder();

        for (Character c : ch) {
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                b.append(c);
            }
            else if (stack.isEmpty()) {
                stack.push(c);
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                char n;
                while (true) {
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Wrong expression");
                    }
                    n = stack.pop();
                    if (n == '(')
                        break;
                    b.append(n);
                }
            }
            else if (isHigherPrecedence(c, stack.peek())) {
                stack.push(c);
            }
            else {
                while (true) {
                    if (stack.isEmpty() || isHigherPrecedence(c, stack.peek()) || stack.peek() == '(' ) {
                        stack.push(c);
                        break;
                    }
                    else {
                        b.append(stack.pop());
                    }
                }
            }
        }
        // Processing the rest of stack as no characters left
        while (!stack.isEmpty()) {
            b.append(stack.pop());
        }
        return b.toString();
    }
    private static boolean isHigherPrecedence(char one, char two) {
        if ((one == '*' || one == '/') && !(two == '*' || two == '/')) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(calculate("4 * ((6 + 2) - 3)"));
    }


}
