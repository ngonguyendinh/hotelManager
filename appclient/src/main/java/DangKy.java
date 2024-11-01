import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class DangKy extends JFrame implements ActionListener {
    private JTextField tenNhanVienField;
    private JTextField emailField;
    private JTextField diaChiField;
    private JTextField sdtField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private  JButton cancelButton;

    public DangKy() {
        setTitle("Register");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(6, 2));

        JLabel tenNhanVienLabel = new JLabel("Tên nhân viên:");
        panel.add(tenNhanVienLabel);
        tenNhanVienField = new JTextField(20);
        panel.add(tenNhanVienField);

        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel);
        emailField = new JTextField(20);
        panel.add(emailField);

        JLabel diaChiLabel = new JLabel("Địa chỉ:");
        panel.add(diaChiLabel);
        diaChiField = new JTextField(20);
        panel.add(diaChiField);

        JLabel sdtLabel = new JLabel("Số điện thoại:");
        panel.add(sdtLabel);
        sdtField = new JTextField(20);
        panel.add(sdtField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField(20);
        panel.add(passwordField);

        registerButton = new JButton("Register");
        panel.add(registerButton);
        registerButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String tenNhanVien = tenNhanVienField.getText();
            String email = emailField.getText();
            String diaChi = diaChiField.getText();
            String sdt = sdtField.getText();
            String password = new String(passwordField.getPassword());

            boolean registered = register(tenNhanVien, email, diaChi, sdt, password);
            if (registered) {
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
               new Login();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == cancelButton){
            new Login();
            dispose();
        }
    }

    private boolean register(String tenNhanVien, String email, String diaChi, String sdt, String password) {
        try {
            // Tạo một đối tượng JSON chứa thông tin đăng ký
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("tenNhanVien", tenNhanVien);
            jsonBody.put("email", email);
            jsonBody.put("diaChi", diaChi);
            jsonBody.put("sdt", sdt);
            jsonBody.put("password", password);

            // Gửi yêu cầu POST đến API
            URL url = new URL("http://localhost:8080/api/v1/eaut/nhanvien");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
