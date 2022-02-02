import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainForm {
    private JPanel mainPanel;
    private JButton first;
    private JTextArea thirdName;
    private JTextArea secondName;
    private JTextArea firstName;
    private JTextArea fullName;
    private JLabel firstNameLabel;
    private JLabel secondNameLabel;
    private JLabel thirdNameLabel;
    private JLabel fullNameLabel;

    private Pattern fs = Pattern.compile("^[А-Я][а-я]*$");
    private Pattern patternThird = Pattern.compile("^([А-Я]*|[А-Я][а-я]*)$");
    private Pattern patternFIO = Pattern.compile("^([А-Я][а-я]*\\s*){1,3}$");
    private Matcher matcher;

    public MainForm(){
        setFullNameVisible(false);
        first.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = firstName.getText();
                String secondNameText = secondName.getText();
                String thirdNameText = thirdName.getText();
                if (first.getText().equals("Collapse")) {
                    if (fs.matcher(name).find() &&
                            fs.matcher(secondNameText).find() &&
                            patternThird.matcher(thirdNameText).find()) {
                        first.setText("Expand");
                        fullName.setText(createFullName());
                        setNamesFieldsVisible(false);
                        setFullNameVisible(true);
                    } else {
                        showErrorMessage();
                    }
                } else {
                    String[] dataFromArea = fullName.getText().split("\\s+");
                    if (dataFromArea.length < 2) {
                        showErrorMessage();
                    } else {
                        clearTextAreas();
                        firstName.setText(dataFromArea[0]);
                        secondName.setText(dataFromArea[1]);
                        if (dataFromArea.length > 2) {
                            thirdName.setText(dataFromArea[2]);
                        }
                        first.setText("Collapse");
                        setFullNameVisible(false);
                        setNamesFieldsVisible(true);
                    }
                }
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    private void clearTextAreas() {
        firstName.setText("");
        secondName.setText("");
        thirdName.setText("");
        fullName.setText("");
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(mainPanel, "Пожалуйста, введите имя и фамилию", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private String createFullName() {
        return firstName.getText() + " "
                + secondName.getText() + " "
                + thirdName.getText();
    }

    private void setNamesFieldsVisible(boolean arg) {
        firstName.setVisible(arg);
        secondName.setVisible(arg);
        thirdName.setVisible(arg);
        firstNameLabel.setVisible(arg);
        secondNameLabel.setVisible(arg);
        thirdNameLabel.setVisible(arg);
    }

    private void setFullNameVisible(boolean arg) {
        fullName.setVisible(arg);
        fullNameLabel.setVisible(arg);
    }

}
