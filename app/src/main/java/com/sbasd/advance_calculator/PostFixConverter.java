package com.sbasd.advance_calculator;

import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostFixConverter
{
    private String infix; // The infix expression to be converted
    private Deque<Character> stack = new ArrayDeque<Character>();
    private List<String> postfix = new ArrayList<String>(); // To hold the postfix expression

    public PostFixConverter(String expression)
    {
        infix = expression;
        convertExpression();
    }

    /* The approach is basically, if it's a number, push it to postfix list
     * else if it's an operator, push it to stack
     */
    private void convertExpression()
    {
        // Temporary string to hold the number
        StringBuilder temp = new StringBuilder();

        for(int i = 0; i != infix.length(); ++i)
        {
            if(Character.isDigit(infix.charAt(i)))
            {
                /* If we encounter a digit, read all digit next to it and append to temp
                 * until we encounter an operator.
                 */
                temp.append(infix.charAt(i));

                while((i+1) != infix.length() && (Character.isDigit(infix.charAt(i+1))
                        || infix.charAt(i+1) == '.'))
                {
                    temp.append(infix.charAt(++i));
                }


                /* If the loop ends it means the next token is an operator or end of expression
                 * so we put temp into the postfix list and clear temp for next use
                 */
                postfix.add(temp.toString());
                temp.delete(0, temp.length());
            }
            // Getting here means the token is an operator
            else
                inputToStack(infix.charAt(i));
        }
        clearStack();
    }


    private void inputToStack(char input)
    {
        if(stack.isEmpty() || input == '(')
            stack.addLast(input);
        else
        {
            if(input == ')')
            {
                while(!stack.getLast().equals('('))
                {
                    postfix.add(stack.removeLast().toString());
                }
                stack.removeLast();
            }
            else
            {
                if(stack.getLast().equals('('))
                    stack.addLast(input);
                else
                {
                    while(!stack.isEmpty() && !stack.getLast().equals('(') &&
                            getPrecedence(input) <= getPrecedence(stack.getLast()))
                    {
                        postfix.add(stack.removeLast().toString());
                    }
                    stack.addLast(input);
                }
            }
        }
    }


    private int getPrecedence(char op)
    {
        if (op == '+' || op == '-')
            return 1;
        else if (op == '*' || op == '/')
            return 2;
        else if (op == '^')
            return 3;
        else return 0;
    }


    private void clearStack()
    {
        while(!stack.isEmpty())
        {
            postfix.add(stack.removeLast().toString());
        }
    }



    public String printExpression()
    {
        String temp =" ";
        for(String str : postfix)
        {
            temp += str + ' ';
        }
        return temp;
    }


    public List<String> getPostfixAsList()
    {
        return postfix;
    }
}
