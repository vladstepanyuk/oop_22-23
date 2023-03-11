package calculator.context;

import calculator.exception.context.PeekException;
import calculator.exception.context.PopException;

import java.util.HashMap;
import java.util.Stack;


public class ProgramContext {
    private final Stack<Double> stack;
    private final HashMap<String, Double> variables;

    public ProgramContext() {
        this.stack = new Stack<>();
        this.variables = new HashMap<>();
    }

    public void push(double arg) {
        stack.push(arg);
    }

    public double pop() throws PopException {
        if (stack.isEmpty()) throw new PopException("stack is empty");
        return stack.pop();
    }

    public double peek() throws PeekException {
        if (stack.isEmpty()) throw new PeekException("stack is empty");
        return stack.peek();
    }

    public boolean containsVariable(String var) {
        return variables.containsKey(var);
    }

    public double getVariableValue(String var) {
        return variables.get(var);
    }

    public void putVariableValue(String variable, double value) {
        variables.put(variable, value);
    }
}
