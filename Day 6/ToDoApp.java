import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToDoApp extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField, timeField;
    private JLabel totalTimeLabel;
    private ArrayList<String> tasks;
    private ArrayList<Integer> timeIntervals;

    public ToDoApp() {
        setTitle("To-Do List with Time Tracker");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Colors & Fonts
        Color bgColor = new Color(240, 248, 255);
        Color btnColor = new Color(100, 149, 237);
        Font font = new Font("Segoe UI", Font.BOLD, 14);

        getContentPane().setBackground(bgColor);

        // Top Panel for Inputs
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBackground(bgColor);

        taskField = new JTextField();
        taskField.setFont(font);
        taskField.setBorder(BorderFactory.createTitledBorder("Task Name"));

        timeField = new JTextField();
        timeField.setFont(font);
        timeField.setBorder(BorderFactory.createTitledBorder("Time (minutes)"));

        JButton addButton = new JButton("Add Task");
        addButton.setBackground(btnColor);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(font);

        JButton removeButton = new JButton("Remove Task");
        removeButton.setBackground(new Color(220, 20, 60));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(font);

        inputPanel.add(taskField);
        inputPanel.add(timeField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        // Task List
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setBorder(BorderFactory.createTitledBorder("Your Tasks"));

        JScrollPane scrollPane = new JScrollPane(taskList);

        // Bottom Panel for Total Time
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(bgColor);
        totalTimeLabel = new JLabel("Total Time: 0 minutes");
        totalTimeLabel.setFont(font);
        bottomPanel.add(totalTimeLabel);

        // Store data
        tasks = new ArrayList<>();
        timeIntervals = new ArrayList<>();

        // Add Task Logic
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            String timeText = timeField.getText().trim();

            if (!task.isEmpty() && !timeText.isEmpty()) {
                try {
                    int minutes = Integer.parseInt(timeText);
                    tasks.add(task);
                    timeIntervals.add(minutes);
                    rebuildTaskList();
                    taskField.setText("");
                    timeField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Enter valid time in minutes!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter both task and time!");
            }
        });

        // Remove Task Logic
        removeButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1 && !taskListModel.get(selectedIndex).startsWith("-")) {
                int taskIndex = selectedIndex / 2; // every other line is a separator
                tasks.remove(taskIndex);
                timeIntervals.remove(taskIndex);
                rebuildTaskList();
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to remove!");
            }
        });

        // Add to Frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Build task list with numbering & separators
    private void rebuildTaskList() {
        taskListModel.clear();
        for (int i = 0; i < tasks.size(); i++) {
            taskListModel.addElement("Task " + (i + 1) + ": " + tasks.get(i) + " | " + timeIntervals.get(i) + " min");
            taskListModel.addElement("---------------------------------------------------");
        }
        updateTotalTime();
    }

    // Update total time label
    private void updateTotalTime() {
        int total = timeIntervals.stream().mapToInt(Integer::intValue).sum();
        totalTimeLabel.setText("Total Time: " + total + " minutes");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}
