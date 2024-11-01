import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class HoaDon extends JFrame implements ActionListener {
    private static final String API_URL ="http://localhost:8080/api/v1/eaut/hoadon";
    private static final String ERROR_MESSAGE = "Create Hoa Don failed. Please try again.";
    private static final String SUCCESS_MESSAGE = "Create Hoa Don successful!";
    private static  int COUNT=0;
    private String username;
    private String password;
    private JTextField maNhanVienField;
    private JTextField maDatPhongField;
    private JTextField ngayTraPhongField;
    private JTextField loaiThanhToanField;
    private JTextField maDichVuField;
    private JButton cancelButton;
    private JButton submitButton;

    private DefaultListModel<String> hoaDonListModel;
    private JList<String> hoaDonList;

    public HoaDon(String username, String password) {
        this.username = username;
        this.password= password;
        setTitle("Nhập thông tin hóa đơn");
        setSize(600, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        getContentPane().add(panel);

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        placeInputComponents(inputPanel);
        panel.add(inputPanel);

        JPanel listPanel = new JPanel(new BorderLayout());
        hoaDonListModel = new DefaultListModel<>();
        hoaDonList = new JList<>(hoaDonListModel);
        JScrollPane scrollPane = new JScrollPane(hoaDonList);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        panel.add(listPanel);

        setVisible(true);
    }

    private void placeInputComponents(JPanel panel) {
        JLabel maNhanVienLabel = new JLabel("Mã nhân viên:");
        maNhanVienField = new JTextField();
        panel.add(maNhanVienLabel);
        panel.add(maNhanVienField);

        JLabel maDatPhongLabel = new JLabel("Mã đặt phòng:");
        maDatPhongField = new JTextField();
        panel.add(maDatPhongLabel);
        panel.add(maDatPhongField);

        JLabel ngayTraPhongLabel = new JLabel("Ngày trả phòng:");
        ngayTraPhongField = new JTextField();
        panel.add(ngayTraPhongLabel);
        panel.add(ngayTraPhongField);

        JLabel loaiThanhToanLabel = new JLabel("Loại thanh toán:");
        loaiThanhToanField = new JTextField();
        panel.add(loaiThanhToanLabel);
        panel.add(loaiThanhToanField);

        JLabel maDichVuLabel = new JLabel("Mã dịch vụ:");
        maDichVuField = new JTextField();
        panel.add(maDichVuLabel);
        panel.add(maDichVuField);

        submitButton = new JButton("Tạo hóa đơn");
        submitButton.addActionListener(this);
        panel.add(new JLabel()); // Thêm một ô trống để căn chỉnh nút
        panel.add(submitButton);
        cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(this);
        panel.add(new JLabel());
        panel.add(cancelButton);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
            int maNhanVien = Integer.parseInt(maNhanVienField.getText());
            int maDatPhong = Integer.parseInt(maDatPhongField.getText());
            String ngayTraPhong = ngayTraPhongField.getText();
            String loaiThanhToan = loaiThanhToanField.getText();
            List<Integer> maDichVu = parseMaDichVu(maDichVuField.getText());

            boolean createHD = inputValue(maNhanVien, maDatPhong, ngayTraPhong, loaiThanhToan, maDichVu);
            if (createHD) {
                JOptionPane.showMessageDialog(this, SUCCESS_MESSAGE, "Success", JOptionPane.INFORMATION_MESSAGE);
                try {
                    ++COUNT;
                    requestAPI();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, ERROR_MESSAGE, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == cancelButton){
            new Home(username,password);
            dispose();
        }

    }
    public boolean inputValue(int maNhanVien, int maDatPhong, String ngayTraPhong, String loaiThanhToan,List<Integer> maDichVu ){
        HttpURLConnection con = null;
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("maNhanVien",maNhanVien);
            jsonBody.put("maDatPhong",maDatPhong);
            jsonBody.put("ngayTraPhong",ngayTraPhong);
            jsonBody.put("loaiThanhToan",loaiThanhToan);
            jsonBody.put("maDichVu",maDichVu);
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

    private List<Integer> parseMaDichVu(String maDichVuStr) {
        List<Integer> maDichVuList = new ArrayList<>();
        String[] maDichVuArray = maDichVuStr.split(",");
        for (String ma : maDichVuArray) {
            maDichVuList.add(Integer.parseInt(ma.trim()));
        }
        return maDichVuList;
    }
    private void requestAPI() throws  Exception{
        URL url = new URL(API_URL+"/"+COUNT);

        // Mở kết nối HTTP
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Thiết lập phương thức yêu cầu và tiêu đề
        connection.setRequestMethod("GET");

        // Xác thực Basic Authentication
        String authString = username + ":" + password;
        String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);

        // Đọc phản hồi từ API
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


    private void extractContent(String jsonResponse) {
        // Phân tích cú pháp JSON
        JSONObject jsonObject = new JSONObject(jsonResponse);

            // Lấy các giá trị từ đối tượng contentObject
            int maNhanVien = jsonObject.getInt("maNhanVien");
            JSONArray tenDichVu = jsonObject.getJSONArray("tenDichVu");
            String tenKhachHang = jsonObject.getString("tenKhachHang");
            String loaiPhong = jsonObject.getString("loaiPhong");
            String thoiGianSuDung = jsonObject.getString("thoiGianSuDung");
            int maDatPhong = jsonObject.getInt("maDatPhong");
            String thoiGianNhan = jsonObject.getString("thoiGianNhan");
            String thoiGianTra = jsonObject.getString("thoiGianTra");
            float tongTien = jsonObject.getFloat("tongTien");

            // Tạo các JLabel để hiển thị thông tin của hóa đơn
            JLabel maNhanVienLabel = new JLabel("Mã Nhân Viên:");
            JLabel maNhanVienValue = new JLabel(String.valueOf(maNhanVien));
            JLabel tenDichVuLabel = new JLabel("Tên Dịch Vụ:");
            JLabel tendichVuValue = new JLabel(String.valueOf(tenDichVu));
            JLabel tenKhachHangLabel = new JLabel("Tên Khách Hàng:");
            JLabel tenKhachHanglValue = new JLabel(tenKhachHang);
            JLabel loaiPhongLabel = new JLabel("Loại Phòng:");
            JLabel loaiPhongValue = new JLabel(loaiPhong);
            JLabel thoiGianSuDungLabel = new JLabel("Thời Gian Sử Dụng:");
            JLabel thoiGianSuDungValue = new JLabel(String.valueOf(thoiGianSuDung));
            JLabel maDatPhongLabel = new JLabel("Mã Đặt Phòng:");
            JLabel maDatPhongValue = new JLabel(String.valueOf(maDatPhong));
            JLabel thoiGianNhanLabel = new JLabel("Thời Gian Nhận Phòng:");
            JLabel thoiGianNhanValue = new JLabel(thoiGianNhan);
            JLabel thoiGianTraLabel = new JLabel("Thời Gian Trả Phòng:");
            JLabel thoiGianTraValue = new JLabel(thoiGianTra);
            JLabel tongTienLabel = new JLabel("Tổng tiền:");
            JLabel tongTienValue = new JLabel(String.valueOf(tongTien));

            // Thêm các JLabel vào danh sách
            hoaDonListModel.addElement("=================="); // Thêm một dòng trống để tạo khoảng cách giữa các khách hàng
            hoaDonListModel.addElement(maNhanVienLabel.getText()+" "+maNhanVienValue.getText());
            hoaDonListModel.addElement(tenDichVuLabel.getText()+" "+tendichVuValue.getText());
            hoaDonListModel.addElement(tenKhachHangLabel.getText() + " " + tenKhachHanglValue.getText());
            hoaDonListModel.addElement(loaiPhongLabel.getText()+ " " + loaiPhongValue.getText());
            hoaDonListModel.addElement(thoiGianSuDungLabel.getText()+ " " + thoiGianSuDungValue.getText());
            hoaDonListModel.addElement(maDatPhongLabel.getText()+ " " + maDatPhongValue.getText());
            hoaDonListModel.addElement(thoiGianNhanLabel.getText()+ " " + thoiGianNhanValue.getText());
            hoaDonListModel.addElement(thoiGianTraLabel.getText()+ " " + thoiGianTraValue.getText());
            hoaDonListModel.addElement(tongTienLabel.getText()+ " " + tongTienValue.getText());
        }

}
