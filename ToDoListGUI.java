package CoreProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoListGUI {
	private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private ArrayList<Boolean> taskStatus; // To track completed tasks

    public ToDoListGUI() {
        frame = new JFrame("To-Do List");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskStatus = new ArrayList<>();
        JScrollPane scrollPane = new JScrollPane(taskList);

        taskField = new JTextField();
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark Completed");
        JButton deleteButton = new JButton("Delete Task");
        JButton clearButton = new JButton("Clear All");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(addButton);
        panel.add(completeButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        frame.add(taskField, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        // Action Listeners
        addButton.addActionListener(e -> addTask());
        completeButton.addActionListener(e -> markTaskCompleted());
        deleteButton.addActionListener(e -> deleteTask());
        clearButton.addActionListener(e -> clearTasks());

        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            listModel.addElement("[ ] " + task);
            taskStatus.add(false);
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Task cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void markTaskCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1 && !taskStatus.get(selectedIndex)) {
            String updatedTask = "[✔] " + listModel.getElementAt(selectedIndex).substring(3);
            listModel.set(selectedIndex, updatedTask);
            taskStatus.set(selectedIndex, true);
        } else {
            JOptionPane.showMessageDialog(frame, "Select an incomplete task!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
            taskStatus.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Select a task to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearTasks() {
        listModel.clear();
        taskStatus.clear();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListGUI::new);
    }
}
