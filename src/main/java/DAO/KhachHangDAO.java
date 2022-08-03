package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Database.SessionFactoryUtil;
import Model.KhachHang;
import Model.NhanVien;
import Service.KhachHangService;

public class KhachHangDAO implements KhachHangService{
	private SessionFactory sessionFactory=null;
	
	public KhachHangDAO() {
		sessionFactory = SessionFactoryUtil.getSessionFactory();
	}

	@Override
	public List<KhachHang> layDanhSachKhachHang() {
		List<KhachHang> dskh = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dskh = session.createQuery("from KhachHang",KhachHang.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dskh;
	}

	@Override
	public List<KhachHang> layDanhSachKhachHangTheoTen(String ten) {
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String sql = "select * from [dbo].[KhachHang] as nv where nv.tenKH like N'%"+ten+"%'";
		try {
			transaction.begin();
			dskh = session.createNativeQuery(sql, KhachHang.class).getResultList();
			transaction.commit();
			return dskh;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public List<KhachHang> layDanhSachKhachHangTheoSDT(String sdt) {
		List<KhachHang> dskh =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dskh = session.createNativeQuery("select * from KhachHang as nv where nv.sdtKH = "+sdt+"", KhachHang.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dskh;
	}

	@Override
	public KhachHang layDanhSachKhachHangTheoMa(String ma) {
		KhachHang kh =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			kh = session.get(KhachHang.class, ma);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return kh;
	}




	@Override
	public List<KhachHang> timKhachHangTheoTieuChi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KhachHang timKhachHangTheoCMND(String cmnd) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<KhachHang> timKhachHangTheoMail(String mail) {
		List<KhachHang> dskh =null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			dskh = session.createNativeQuery("select * from KhachHang as kh where kh.email = '"+mail+"' ", KhachHang.class).getResultList();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return dskh;
	}

	@Override
	public String setCodeEmployees() {
		String sql = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		String maKH = null;
		sql = "select top 1 kh.maKH from [dbo].[KhachHang] as kh order by kh.maKH DESC";
		try {
			transaction.begin();
			maKH = (String) session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		Integer count = Integer.parseInt(maKH.substring(2));
		String countStr = String.valueOf(count);
	
			if(countStr.length()==1 && count<9) {
				return "KH000000000"+(count+1);
			}
			else if((countStr.length()==2 && count<99) || (countStr.length()==1 && count==9)) {
				return "KH00000000"+(count+1);
			}
			else if((countStr.length()==3 && count<999) || (countStr.length()==2 && count==99)) {
				return "KH0000000"+(count+1);
			}
			else if((countStr.length()==4 && count<9999) || (countStr.length()==3 && count==999)) {
				return "KH000000"+(count+1);
			}
			
			else if((countStr.length()==5 && count<99999) || (countStr.length()==4 && count==99999)) {
				return "KH00000"+(count+1);
			}
			else if((countStr.length()==6 && count<999999) || (countStr.length()==5 && count==99999)) {
				return "KH0000"+(count+1);
			}
			else if((countStr.length()==7 && count<9999999) || (countStr.length()==6 && count==999999)) {
				return "KH000"+(count+1);
			}
			else if((countStr.length()==8 && count<99999999) || (countStr.length()==7 && count==9999999)) {
				return "KH00"+(count+1);
			}
			else if((countStr.length()==9 && count<999999999) || (countStr.length()==8 && count==99999999)) {
				return "KH0"+(count+1);
			}
			else if((countStr.length()==10 && count<9999999999L) || (countStr.length()==7 && count==9999999999L)) {
				return "KH"+(count+1);
			}
			else return "VQ";
	}

	@Override
	public Boolean xoaKhachHang(String makh) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			KhachHang kh = session.get(KhachHang.class, makh);
			session.delete(kh);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}

	@Override
	public Boolean themKhachHang(KhachHang kh) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(kh);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}

	@Override
	public Boolean suaKhachHang(KhachHang kh) {
		Boolean check=false;
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.saveOrUpdate(kh);
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			transaction.rollback();
		}
		return true;
	}


}
