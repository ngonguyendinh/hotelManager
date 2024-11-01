import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class CreateKhachHang extends JFrame implements ActionListener{
    private static final String API_URL = "http://localhost:8080/api/v1/eaut/khachhang";
    private static final String ERROR_MESSAGE = "Create Khach Hang failed. Please try again.";
    private static final String SUCCESS_MESSAGE = "Create Khach Hang successful!";

    private String username;
    private String password;
    private JTextField tenKhachHangField;
    private JTextField diaChiField;
    private JTextField emailField;
    private JTextField sdtField;
    private JTextField cccdField;
    private JButton submitButton;

    public CreateKhachHang(String username, String password) {
        this.username = username;
        this.password = password;

        setTitle("Form Nhập Khách Hàng");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        component(panel);
        setVisible(true);

    }
    public void component(JPanel panel){
        panel.setLayout(new GridLayout(6, 2));

        JLabel tenKhachHangLabel = new JLabel("Tên Khách Hàng:");
        tenKhachHangField = new JTextField();
        JLabel diaChiLabel = new JLabel("Địa Chỉ:");
        diaChiField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel sdtLabel = new JLabel("Số Điện Thoại:");
        sdtField = new JTextField();
        JLabel cccdLabel = new JLabel("CCCD:");
        cccdField = new JTextField();

        submitButton = new JButton("Tạo Khách hàng");
        submitButton.addActionListener(this);

        panel.add(tenKhachHangLabel);
        panel.add(tenKhachHangField);
        panel.add(diaChiLabel);
        panel.add(diaChiField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(sdtLabel);
        panel.add(sdtField);
        panel.add(cccdLabel);
        panel.add(cccdField);
        panel.add(new JLabel(""));
        panel.add(submitButton);
    }
    public void actionPerformed(ActionEvent e) {
        // Lấy dữ liệu từ các trường nhập và xử lý
        if (e.getSource() == submitButton) {
            String tenKhachHang = tenKhachHangField.getText();
            String diaChi = diaChiField.getText();
            String email = emailField.getText();
            String sdt = sdtField.getText();
            String cccd = cccdField.getText();

            boolean createKH = create(tenKhachHang, diaChi, email, sdt, cccd);
            if (createKH) {
                JOptionPane.showMessageDialog(this, SUCCESS_MESSAGE, "Success", JOptionPane.INFORMATION_MESSAGE);
                new KhachHang(username,password);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, ERROR_MESSAGE, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean create(String tenKhachHang,String diaChi, String email, String sdt, String cccd){

        HttpURLConnection con = null;
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("tenKhachHang",tenKhachHang);
            jsonBody.put("diaChi",diaChi);
            jsonBody.put("email",email);
            jsonBody.put("sdt",sdt);
            jsonBody.put("cccd",cccd);
            // gửi yêu cầu POST đến api
            URL url = new URL(API_URL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");// sử dụng phương thức POST
            // xác thực auth
            String authString = username + ":" + password;
            String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
            con.setRequestProperty("Authorization", "Basic " + encodedAuthString);
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
        finally {
            con.disconnect();
        }
    }

}
