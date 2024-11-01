import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class Phong extends JFrame implements ActionListener {
    private String username;
    private String password;
    private static final String API_URL= "http://localhost:8080/api/v1/eaut/phong";
    private static final String ERROR_MESSAGE = "Create Phong failed. Please try again.";
    private static final String SUCCESS_MESSAGE = "Create Phong successful!";

    private JTextField soPhong_tf;
    private JComboBox<String> loaiPhong_cb;
    private JTextField giaPhong_tf;
    private JButton createPhong_btn;
    private JButton deletePhong_btn;
    private JButton showPhong_btn;
    private JButton home_btn;
    private DefaultListModel<String> listModel;
    private JList<String> dsPhong;
    private JPanel panel;
    private JButton updatePhong_btn;

    public Phong(String username,String password) {
        this.username = username;
        this.password = password;
        setTitle("Phong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(480, 300);

        panel = new JPanel();
        placeComponents(panel);

        add(panel);
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        soPhong_tf = new JTextField(20);
        String[] loaiPhongOptions = {"THUONG", "VIP"};
        loaiPhong_cb = new JComboBox<>(loaiPhongOptions);
        giaPhong_tf = new JTextField(20);
        createPhong_btn = new JButton("Tạo Phòng");
        deletePhong_btn = new JButton("Xóa Phòng");
        updatePhong_btn = new JButton("Cập nhật Phòng");
        showPhong_btn = new JButton("Hiển Thị Phòng");
        home_btn = new JButton("Home");
        inputPanel.add(new JLabel("Số Phòng:"));
        inputPanel.add(soPhong_tf);
        inputPanel.add(new JLabel("Loại Phòng:"));
        inputPanel.add(loaiPhong_cb);
        inputPanel.add(new JLabel("Giá Phòng:"));
        inputPanel.add(giaPhong_tf);
        inputPanel.add(createPhong_btn);
        inputPanel.add(deletePhong_btn);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(deletePhong_btn);
        buttonPanel.add(updatePhong_btn);
        buttonPanel.add(showPhong_btn);
        buttonPanel.add(home_btn);

        JPanel listPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        dsPhong = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(dsPhong);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);


        createPhong_btn.addActionListener(this);
        showPhong_btn.addActionListener(this);
        home_btn.addActionListener(this);

        // Thêm một MouseListener để xử lý sự kiện click vào danh sách
        dsPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lấy index của phần tử đã chọn
                int selectedIndex = dsPhong.getSelectedIndex();
                // Lấy phần tử tương ứng trong listModel
                String selectedElement = listModel.getElementAt(selectedIndex);
                // Phân tích thông tin phần tử đã chọn và đặt vào các JTextField tương ứng
                parseSelectedElement(selectedElement);
            }
        });
    }

    // Phương thức này sẽ phân tích thông tin phần tử đã chọn từ danh sách và đặt vào các JTextField
    private void parseSelectedElement(String selectedElement) {
        // Phân tích thông tin phần tử đã chọn
        String[] parts = selectedElement.split(", ");
        String soPhong = parts[0].split(": ")[1];
        String loaiPhong = parts[1].split(": ")[1];
        String giaPhong = parts[2].split(": ")[1];
        // Đặt thông tin vào các JTextField tương ứng
        soPhong_tf.setText(soPhong);
        loaiPhong_cb.setSelectedItem(loaiPhong);
        giaPhong_tf.setText(giaPhong);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createPhong_btn){
            int soPhong = Integer.parseInt(soPhong_tf.getText());
            String loaiPhong = (String) loaiPhong_cb.getSelectedItem();
            float giaPhong = Float.parseFloat(giaPhong_tf.getText());
            boolean createPhong =API_create(soPhong,loaiPhong,giaPhong);
            if(createPhong){
                JOptionPane.showMessageDialog(this, SUCCESS_MESSAGE, "Success", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, ERROR_MESSAGE, "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
        if(e.getSource()==showPhong_btn){
            try {
                API_find();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == home_btn){
            new Home(username,password);
            dispose();
        }

    }
    public boolean API_create(int soPhong, String loaiPhong, float giaPhong ){
        HttpURLConnection con = null;
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("soPhong",soPhong);
            jsonBody.put("loaiPhong",loaiPhong);
            jsonBody.put("giaPhong",giaPhong);

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

    private void API_find()throws  Exception{
        URL url = new URL(API_URL);

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

    private void extractContent(String jsonrespon){
        JSONObject jsonObject = new JSONObject(jsonrespon);
        JSONArray contentArray = jsonObject.getJSONArray("content");

        // Xóa các phần tử cũ trong listModel trước khi thêm các phần tử mới
        listModel.clear();

        for (int i = 0; i < contentArray.length(); i++) {
            JSONObject contentObject = contentArray.getJSONObject(i);

            int soPhong = contentObject.getInt("soPhong");
            String loaiPhong = contentObject.getString("loaiPhong");
            float giaPhong = contentObject.getFloat("giaPhong");
            String trangThaiPhong = contentObject.getString("trangThaiPhong");

            // Tạo một chuỗi để biểu diễn thông tin của mỗi phòng
            String roomInfo = "Số Phòng: " + soPhong + ", Loại Phòng: " + loaiPhong + ", Giá Phòng: " + giaPhong + ", Trạng Thái Phòng: " + trangThaiPhong;

            // Thêm chuỗi này vào listModel
            listModel.addElement(roomInfo);
        }
    }

}
