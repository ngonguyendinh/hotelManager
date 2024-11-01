import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;

public class DichVu extends JFrame implements ActionListener {
    private String username;
    private String password;
    private JTextField tenDichVuField;
    private JTextField giaDichVuField;
    private JComboBox<String> loaiDichVuComboBox;
    private DefaultListModel<String> danhSachModel;
    private JList<String> danhSachDichVuList;
    private JButton createDichVu_btn;

    public DichVu(String username,String password) {
        this.username = username;
        this.password = password;
        setTitle("Form Nhập Dịch Vụ");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel tenDichVuLabel = new JLabel("Tên Dịch Vụ:");
        tenDichVuField = new JTextField();
        JLabel giaDichVuLabel = new JLabel("Giá Dịch Vụ:");
        giaDichVuField = new JTextField();
        JLabel loaiDichVuLabel = new JLabel("Loại Dịch Vụ:");
        String[] loaiDichVuOptions = {"DOAN", "DOUONG"};
        loaiDichVuComboBox = new JComboBox<>(loaiDichVuOptions);

        inputPanel.add(tenDichVuLabel);
        inputPanel.add(tenDichVuField);
        inputPanel.add(giaDichVuLabel);
        inputPanel.add(giaDichVuField);
        inputPanel.add(loaiDichVuLabel);
        inputPanel.add(loaiDichVuComboBox);

        // Danh sách dịch vụ
        danhSachModel = new DefaultListModel<>();
        danhSachDichVuList = new JList<>(danhSachModel);
        JScrollPane scrollPane = new JScrollPane(danhSachDichVuList);

        danhSachDichVuList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Lấy dữ liệu từ phần tử được chọn trong danh sách dịch vụ
                    String selectedDichVu = danhSachDichVuList.getSelectedValue();
                    if (selectedDichVu != null) {
                        // Phân tách dữ liệu thành các trường riêng biệt
                        String[] parts = selectedDichVu.split(" - ");
                        if (parts.length == 3) {
                            // Đặt dữ liệu vào các JTextField tương ứng
                            tenDichVuField.setText(parts[0]);
                            giaDichVuField.setText(parts[1]);
                            loaiDichVuComboBox.setSelectedItem(parts[2]);
                        }
                    }
                }
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, scrollPane);
        splitPane.setDividerLocation(300); // Thiết lập vị trí của divider

        // Nút "Submit"
        createDichVu_btn = new JButton("Tạo Dịch Vụ");
        createDichVu_btn.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createDichVu_btn);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(splitPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DichVu("ngo@gmail.com","admin");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==createDichVu_btn){
                String tenDichVu = tenDichVuField.getText();
                float giaDichVu = Float.parseFloat(giaDichVuField.getText());
                String loaiDichVu = (String) loaiDichVuComboBox.getSelectedItem();

                // Kiểm tra điều kiện
                if (tenDichVu.isEmpty()) {
                    JOptionPane.showMessageDialog(DichVu.this, "Tên Dịch Vụ không được để trống");
                    return;
                }
                if (giaDichVu <= 0) {
                    JOptionPane.showMessageDialog(DichVu.this, "Giá Dịch Vụ phải lớn hơn 0");
                    return;
                }
                if (!Pattern.matches("DOAN|DOUONG", loaiDichVu)) {
                    JOptionPane.showMessageDialog(DichVu.this, "Loại Dịch Vụ chỉ có thể là DOAN hoặc DOUONG");
                    return;
                }

                boolean createDV = create(tenDichVu,giaDichVu,loaiDichVu);
            if (createDV) {
                JOptionPane.showMessageDialog(this, "Create Dich Vu successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new Home(username,password);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Create Dich Vu failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
                // Xóa dữ liệu từ các trường nhập sau khi thêm vào danh sách
                tenDichVuField.setText("");
                giaDichVuField.setText("");
            }
        }
        private boolean create(String tenDichVu, float giaDichVu,String loaiDichVu){
            try {
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("tenDichVu", tenDichVu);
                jsonBody.put("giaDichVu", giaDichVu);
                jsonBody.put("loaiDichVu", loaiDichVu);
                // xác thực phản hồi API
                URL url = new URL("http://localhost:8080/api/v1/eaut/dichvu");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                String authString = username + ":" + password;
                String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
                con.setRequestProperty("Authorization", "Basic " + encodedAuthString);
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
                con.disconnect();
                int responseCode = con.getResponseCode();
                return responseCode == HttpURLConnection.HTTP_OK;

            }
            catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }

    }

