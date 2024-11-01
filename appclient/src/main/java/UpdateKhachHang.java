import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class UpdateKhachHang extends JFrame implements ActionListener {
    private final  static  String API_URL = "http://localhost:8080/api/v1/eaut/khachhang";
    private static final String ERROR_MESSAGE = "Update Khach Hang failed. Please try again.";
    private static final String SUCCESS_MESSAGE = "Update Khach Hang successful!";
    private  String username;
    private  String password;

    private JLabel tenKhachHangLabel, diaChiLabel, emailLabel, sdtLabel, cccdLabel;
    private JTextField tenKhachHangField, diaChiField, emailField, sdtField, cccdField;
    private JButton updateButton;
    private JButton cancelButton;

    public UpdateKhachHang(String username,String password){
        this.username = username;
        this.password = password;
        setTitle("Cập nhật thông tin khách hàng");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        placeComponents(panel);
        add(panel);
        panel.setLayout(new GridLayout(7, 2));
        setVisible(true);
    }
    public void placeComponents(JPanel jPanel){
        tenKhachHangLabel = new JLabel("Tên khách hàng:");
        tenKhachHangField = new JTextField();
        diaChiLabel = new JLabel("Địa chỉ:");
        diaChiField = new JTextField();
        emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        sdtLabel = new JLabel("Số điện thoại:");
        sdtField = new JTextField();
        cccdLabel = new JLabel("CCCD:");
        cccdField = new JTextField();

        updateButton = new JButton("Cập nhật");
        cancelButton= new JButton("Hủy");

        jPanel.add(tenKhachHangLabel);
        jPanel.add(tenKhachHangField);
        jPanel.add(diaChiLabel);
        jPanel.add(diaChiField);
        jPanel.add(emailLabel);
        jPanel.add(emailField);
        jPanel.add(sdtLabel);
        jPanel.add(sdtField);
        jPanel.add(cccdLabel);
        jPanel.add(cccdField);

        jPanel.add(updateButton);
        jPanel.add(cancelButton);
        updateButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public static void main(String[] args) {
        new UpdateKhachHang("ngo@gmail.com","admin");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== updateButton){
            String tenKH = tenKhachHangField.getText();
            String diaChi = diaChiField.getText();
            String email = emailField.getText();
            String sdt = sdtField.getText();
            String cccd = cccdField.getText();

            boolean updateKH = update(tenKH,diaChi,email,sdt,cccd);
            if(updateKH){
                JOptionPane.showMessageDialog(this, SUCCESS_MESSAGE, "Success", JOptionPane.INFORMATION_MESSAGE);
                new KhachHang(username,password);
                dispose();
            }else {

                JOptionPane.showMessageDialog(this, ERROR_MESSAGE, "Error", JOptionPane.ERROR_MESSAGE);

            }
        }if (e.getSource()== cancelButton){
            new KhachHang(username,password);
            dispose();
        }
    }
    private boolean update(String tenKhachHang, String diaChi, String email, String sdt , String cccd){
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
            con.setRequestMethod("PUT");// sử dụng phương thức POST
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
//    private void showKH(){
//        listKh.clear();
//        URL url = new URL(URL_API+"/cccd");
//        String cccd = cccdKhachHang_tf.getText();
//
//        // Mở kết nối HTTP
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        // Thiết lập phương thức yêu cầu và tiêu đề
//        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "application/json");
//        JSONObject jsonBody = new JSONObject();
//        jsonBody.put("cccd",cccd);
//        // Xác thực Basic Authentication
//        String authString = username + ":" + password;
//        String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
//        connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);
//        // thiết lập dữ liệu requestbody
//        connection.setDoOutput(true);
//        try (OutputStream os = connection.getOutputStream()) {
//            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
//            os.write(input, 0, input.length);
//        }
//        // đọc phản hồi
//        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        StringBuilder response = new StringBuilder();
//        String inputLine;
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        // Hiển thị phản hồi từ API trên giao diện
//        extractElement(response.toString());
//        // Đóng kết nối
//        connection.disconnect();
//    }
}
