import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class KhachHang extends JFrame implements ActionListener {
    private static final String URL_API = "http://localhost:8080/api/v1/eaut/khachhang";
    private String username;
    private String password;
    private JButton createKhachHang_btn;
    private JButton findKhachHang_btn;
    private JButton delete_btn;
    private JButton suaKhachHang_btn;
    private JTextField cccdKhachHang_tf;
    private JLabel cccdKhachHang_lb;

    private DefaultListModel<String> listKh;
    private JList<String> dsKh;

    public KhachHang(String username, String password) {
        this.username = username;
        this.password = password;

        setTitle("KhachHang");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        cccdKhachHang_lb = new JLabel("Nhập CCCD:");
        panel.add(cccdKhachHang_lb);

        cccdKhachHang_tf = new JTextField(10);
        panel.add(cccdKhachHang_tf);


        listKh = new DefaultListModel<>();
        dsKh = new JList<>(listKh);
        JScrollPane scrollPane = new JScrollPane(dsKh);
        panel.add(scrollPane);
        createKhachHang_btn = new JButton("Tạo khách hàng");
        createKhachHang_btn.addActionListener(this);
        panel.add(createKhachHang_btn);

        delete_btn = new JButton("Xóa khách hàng");
        delete_btn.addActionListener(this);
        panel.add(delete_btn);

        suaKhachHang_btn = new JButton("Sửa khách hàng");
        suaKhachHang_btn.addActionListener(this);
        panel.add(suaKhachHang_btn);

        findKhachHang_btn = new JButton("Tìm kiếm khách hàng");
        findKhachHang_btn.addActionListener(this);
        panel.add(findKhachHang_btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createKhachHang_btn) {
            new CreateKhachHang(username, password);
        }
        if (e.getSource() == findKhachHang_btn) {
            try {
                if(cccdKhachHang_tf.getText().isEmpty()){
                    API_find();
                }else {
                    API_find_cccd();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource()==delete_btn){
            try {
                API_delete();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == suaKhachHang_btn){
            new UpdateKhachHang(username,password);
            dispose();
        }
    }
private void API_delete() throws Exception{
        listKh.clear();
    String cccd = cccdKhachHang_tf.getText();
    String urlString = URL_API;
    URL url = new URL(urlString);
    JSONObject jsonBody = new JSONObject();
    jsonBody.put("cccd",cccd);
// Mở kết nối HTTP
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    try {

        // Thiết lập phương thức yêu cầu và tiêu đề
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/json");
        // Xác thực Basic Authentication
        String authString = username + ":" + password;
        String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);
        // thiết lập dữ liệu requestbody
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        // Kiểm tra mã phản hồi của yêu cầu
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            // Xử lý khi xóa thành công
            JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Xử lý khi xóa không thành công
            JOptionPane.showMessageDialog(this, "Xóa khách hàng không thành công", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } finally {
        // Đảm bảo luôn đóng kết nối sau khi hoàn thành
        connection.disconnect();
    }

}
    private void API_find() throws Exception {
        listKh.clear();
        URL url = new URL(URL_API);

        // Mở kết nối HTTP
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Thiết lập phương thức yêu cầu và tiêu đề
        connection.setRequestMethod("GET");

        // Xác thực Basic Authentication
        String authString = username + ":" + password;
        String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);
        // đọc phản hồi
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Hiển thị phản hồi từ API trên giao diện
        extractContent(response.toString());
        // Đóng kết nối
        connection.disconnect();
    }
    private void API_find_cccd() throws Exception {
        listKh.clear();
        URL url = new URL(URL_API+"/cccd");
        String cccd = cccdKhachHang_tf.getText();

        // Mở kết nối HTTP
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Thiết lập phương thức yêu cầu và tiêu đề
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("cccd",cccd);
        // Xác thực Basic Authentication
        String authString = username + ":" + password;
        String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);
        // thiết lập dữ liệu requestbody
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        // đọc phản hồi
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Hiển thị phản hồi từ API trên giao diện
        extractElement(response.toString());
        // Đóng kết nối
        connection.disconnect();
    }

    private void extractContent(String jsonResponse) {
        // Phân tích cú pháp JSON
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Lấy giá trị của khóa "content"
        JSONArray contentArray = jsonObject.getJSONArray("content");

        // Lặp qua mỗi phần tử trong mảng content
        for (int i = 0; i < contentArray.length(); i++) {
            JSONObject contentObject = contentArray.getJSONObject(i);

            // Lấy các giá trị từ đối tượng contentObject
            String tenKhachHang = contentObject.getString("tenKhachHang");
            String diaChi = contentObject.getString("diaChi");
            String email = contentObject.getString("email");
            String sdt = contentObject.getString("sdt");

            // Tạo các JLabel để hiển thị thông tin của khách hàng
            JLabel tenKhachHangLabel = new JLabel("Tên Khách Hàng:");
            JLabel tenKhachHangValueLabel = new JLabel(tenKhachHang);
            JLabel diaChiLabel = new JLabel("Địa Chỉ:");
            JLabel diaChiValueLabel = new JLabel(diaChi);
            JLabel emailLabel = new JLabel("Email:");
            JLabel emailValueLabel = new JLabel(email);
            JLabel sdtLabel = new JLabel("SDT:");
            JLabel sdtValueLable = new JLabel(sdt);

            // Thêm các JLabel vào danh sách
            listKh.addElement("=================="); // Thêm một dòng trống để tạo khoảng cách giữa các khách hàng
            listKh.addElement(tenKhachHangLabel.getText() + " " + tenKhachHangValueLabel.getText());
            listKh.addElement(diaChiLabel.getText()+ " " + diaChiValueLabel.getText());
            listKh.addElement(emailLabel.getText()+ " " + emailValueLabel.getText());
            listKh.addElement(sdtLabel.getText()+ " " + sdtValueLable.getText());
        }
    }
    private void extractElement(String jsonResponse) {
        // Phân tích cú pháp JSON
        JSONObject jsonObject = new JSONObject(jsonResponse);

            // Lấy các giá trị từ đối tượng jsonObject
            String tenKhachHang = jsonObject.getString("tenKhachHang");
            String diaChi = jsonObject.getString("diaChi");
            String email = jsonObject.getString("email");
            String sdt = jsonObject.getString("sdt");

            // Tạo các JLabel để hiển thị thông tin của khách hàng
            JLabel tenKhachHangLabel = new JLabel("Tên Khách Hàng:");
            JLabel tenKhachHangValueLabel = new JLabel(tenKhachHang);
            JLabel diaChiLabel = new JLabel("Địa Chỉ:");
            JLabel diaChiValueLabel = new JLabel(diaChi);
            JLabel emailLabel = new JLabel("Email:");
            JLabel emailValueLabel = new JLabel(email);
            JLabel sdtLabel = new JLabel("SDT:");
            JLabel sdtValueLable = new JLabel(sdt);

            // Thêm các JLabel vào danh sách
            listKh.addElement("=================="); // Thêm một dòng trống để tạo khoảng cách giữa các khách hàng
            listKh.addElement(tenKhachHangLabel.getText() + " " + tenKhachHangValueLabel.getText());
            listKh.addElement(diaChiLabel.getText()+ " " + diaChiValueLabel.getText());
            listKh.addElement(emailLabel.getText()+ " " + emailValueLabel.getText());
            listKh.addElement(sdtLabel.getText()+ " " + sdtValueLable.getText());

    }

    public static void main(String[] args) {
        new KhachHang("ngo@gmail.com","admin");
    }
}
