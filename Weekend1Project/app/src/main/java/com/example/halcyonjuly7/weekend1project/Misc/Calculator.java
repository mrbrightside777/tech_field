package com.example.halcyonjuly7.weekend1project.Misc;

import java.util.Stack;

import java.util.Stack;

public class Calculator {


    private static int precedence(char current_char) {


        switch (current_char)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    public Integer solve(String expression) {
        StringBuilder cleaned_string = new StringBuilder();
        for (int index = 0; index < expression.length(); index++) {
            if (expression.charAt(index) != ' ')
                cleaned_string.append(expression.charAt(index));
        }
        String converted_postfix = infix_to_postfix(cleaned_string.toString());
        return postfix_solve(converted_postfix);
    }

    private String infix_to_postfix(String expression) {
        int arr_len = expression.length();
        char current_char;

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int index = 0; index < arr_len; index++) {
            current_char = expression.charAt(index);
            if (current_char == '(') {
                stack.push(current_char);
            } else if (current_char == ')') {
                while (!stack.empty() && stack.peek() != '(')
                    result.append(String.format(" %s ", stack.pop()));
                stack.pop();

            } else if (Character.isDigit(current_char) ||
                    (current_char == '-' && Character.isDigit(expression.charAt(index + 1)) && precedence(expression.charAt(index - 1)) != -1)) {
                StringBuilder digits = new StringBuilder();
                digits.append(current_char);
                index++;
                if (index < arr_len) {

                    current_char = expression.charAt(index);
                    while (index < arr_len && Character.isDigit(current_char)) {
                        digits.append(current_char);
                        index++;
                        if (index >= arr_len)
                            break;
                        current_char = expression.charAt(index);

                    }
                }
                index--;
                digits.append(' ');
                result.append(digits.toString());

            } else if (current_char == ' ') {

            } else {
                while (!stack.isEmpty() && precedence(current_char) < precedence(stack.peek()))
                    result.append(String.format(" %s ", stack.pop()));
                stack.push(current_char);
            }
        }
        while (!stack.empty())
            result.append(String.format(" %s ", stack.pop()));
        return result.toString();
    }


    private Integer postfix_solve(String infix_expression) {
        Stack<Integer> val_stack = new Stack<>();
        char current_char;
        int infix_len = infix_expression.length();
        for (int index = 0; index < infix_expression.length(); index++) {
            current_char = infix_expression.charAt(index);
            if (Character.isDigit(current_char) || current_char == '-' && Character.isDigit(infix_expression.charAt(index + 1))) {
                StringBuilder digits = new StringBuilder();
                digits.append(current_char);
                index++;
                current_char = infix_expression.charAt(index);
                while (index < infix_len && Character.isDigit(current_char)) {
                    digits.append(current_char);
                    index++;
                    if (index >= infix_len)
                        break;
                    current_char = infix_expression.charAt(index);

                }
                index--;
                val_stack.push(Integer.parseInt(digits.toString()));
            } else if (current_char == ' ') {

            } else {
                Integer val1 = val_stack.pop();
                Integer val2 = val_stack.pop();

                switch (current_char) {
                    case '+':
                        val_stack.push(val2 + val1);
                        break;
                    case '-':
                        val_stack.push(val2 - val1);
                        break;
                    case '/':
                        val_stack.push(val2 / val1);
                        break;
                    case '*':
                        val_stack.push(val2 * val1);
                        break;
                }
            }
        }
        return val_stack.pop();
    }

}
