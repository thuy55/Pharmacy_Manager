package Service;

import java.util.List;

public interface ThongKeService {

	public double thongKeDoanhThuTrongNgay(int ngay, int thang ,int nam);
	public double thongKeDoanhThuTrongThang(int thang, int nam);
	public double thongKeDoanhThuTrongNam(int nam);
	public int thongKeKhachHang();
	public double thongKeThuocDoanhThuLonNhatTrongNam();
	public List<String> thongKeTopThuocBanChayNhat();
}
