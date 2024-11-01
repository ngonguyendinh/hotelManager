import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public Login() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(3, 1));

        JLabel userLabel = new JLabel("Username:");
        panel.add(userLabel);

        usernameField = new JTextField(20);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        panel.add(loginButton);
        loginButton.addActionListener(this);

        registerButton = new JButton("Regist");
        panel.add(registerButton);
        registerButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                // Gửi yêu cầu đăng nhập và nhận token
                String token = AuthService.authenticate(username, password);
                if (token != null) {
                    // Chuyển tới màn hình chính sau khi đăng nhập thành công
                    new Home(username, password);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Login failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == registerButton){
            // chuyển tới màn hình đăng ký
            new DangKy();
            dispose();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

