import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class CustomEmptyStackException extends Exception {
    public CustomEmptyStackException(String message) {
        super(message);
    }
}

class CustomStack {
    private int[] stackArray;
    private int top;
    private int capacity;

    public CustomStack(int capacity) {
        this.capacity = capacity;
        stackArray = new int[capacity];
        top = -1;
    }

    public void push(int item) {
        if (top == capacity - 1) {
            System.out.println("Стек переполнен");
            return;
        }
        stackArray[++top] = item;
        System.out.println(item + " добавлен в стек");
    }

    public int pop() throws CustomEmptyStackException {
        if (top == -1) {
            throw new CustomEmptyStackException("Стек пуст");
        }
        return stackArray[top--];
    }
}

public class Main {
    public static void main(String[] args) {
        CustomStack stack = new CustomStack(5);

        try {
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
            stack.push(5);
            stack.push(6);

            for (int i = 0; i < 6; i++) {
                System.out.println(stack.pop());
            }
        } catch (CustomEmptyStackException e) {
            logException(e);
        }
    }

    private static void logException(Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("exception_log.txt", true))) {
            writer.println("Exception occurred: " + e.getMessage());
            e.printStackTrace(writer);
            writer.println("-------------------------------------------");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
