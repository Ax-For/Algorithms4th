package org.study;

import edu.princeton.cs.algs4.StdIn;
import org.study.utils.MyStack;

// 利用栈实现中缀表达式求职，支持 + - * / [sqrt]
public class Evaluate {
    public static void main(String[] args) {
        MyStack<String> ops = new MyStack<>();
        MyStack<Double> vals = new MyStack<>();
        vals.push(0.0);
        while(!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if(s.equals("("));
            else if(s.equals("+")) ops.push("+");
            else if(s.equals("-")) ops.push("-");
            else if(s.equals("*")) ops.push("*");
            else if(s.equals("/")) ops.push("/");
            else if(s.equals(")"))
            {
                String op = ops.pop();
                double v = vals.pop();
                switch (op) {
                    case "+":
                        v = vals.pop() + v;
                        break;
                    case "-":
                        v = vals.pop() - v;
                        break;
                    case "*":
                        v = vals.pop() * v;
                        break;
                    case "/":
                        v = vals.pop() / v;
                        break;
                    case "sqrt":
                        v = Math.sqrt(v);
                        break;
                }
                vals.push(v);
            }
            else
                vals.push(Double.parseDouble(s));
        }
        System.out.println(vals.pop());
    }
}
