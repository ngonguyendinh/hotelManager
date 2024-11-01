import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    private String username;
    private String password;
    private JButton khachHang_btn;
    private JButton phong_btn;
    private JButton hoaDon_btn;
    public Home(String username, String password){
        this.username = username;
        this.password = password;
        setTitle("Home");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        placeComponents(panel);

        setVisible(true);
    }
    private void placeComponents(JPanel panel){
        khachHang_btn = new JButton("Khách Hàng");
        khachHang_btn.addActionListener(this);
        panel.add(khachHang_btn);
        phong_btn = new JButton("Phòng");
        phong_btn.addActionListener(this);
        panel.add(phong_btn);
        hoaDon_btn= new JButton("Hóa Đơn");
        panel.add(hoaDon_btn);
        hoaDon_btn.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == khachHang_btn){
            new CreateKhachHang(username,password);
            dispose();
        }
        if (e.getSource() == phong_btn){
            new Phong(username,password);
            dispose();
        }
        if(e.getSource() == hoaDon_btn){
            new HoaDon(username,password);
            dispose();
        }
    }
}
