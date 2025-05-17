CREATE DATABASE SieuThiMini;
GO

USE SieuThiMini;
GO
CREATE TABLE PhanQuyen(
    MaQuyen int NOT NULL PRIMARY KEY IDENTITY(1,1),
    TenQuyen nvarchar(50) NOT NULL,
    MoTa nvarchar(100)
);
GO

CREATE TABLE ChucVu(
    MaChucVu int NOT NULL PRIMARY KEY IDENTITY(1,1), 
    TenChucVu nvarchar(50), 
    MoTa nvarchar(100), 
    Is_Deleted bit
);
GO

CREATE TABLE NhanVien(
    MaNV int NOT NULL PRIMARY KEY IDENTITY(1,1), 
    TenNV nvarchar(50), 
    GioiTinh bit, 
    NgaySinh date, 
    SoDienThoai nvarchar(11), 
    Email nvarchar(50), 
    DiaChi nvarchar(100),  
    MaChucVu int, 
    Is_Deleted bit,
    CONSTRAINT FK_NhanVien_ChucVu FOREIGN KEY (MaChucVu) REFERENCES ChucVu(MaChucVu)
);
GO

CREATE TABLE KhachHang(
    MaKH int NOT NULL PRIMARY KEY IDENTITY(1,1), 
    TenKH nvarchar(50), 
    SoDienThoai nvarchar(11), 
    DiemTichLuy int DEFAULT 0, 
    Is_Deleted bit
);
GO

CREATE TABLE NhaCungCap(
    MaNCC int NOT NULL PRIMARY KEY IDENTITY(1,1), 
    TenNCC nvarchar(50), 
    DiaChi nvarchar(100), 
    Sdt nvarchar(11), 
    NguoiLienHe nvarchar(50), 
    Is_Deleted bit
);
GO

CREATE TABLE LoaiSanPham(
    MaLoai int NOT NULL PRIMARY KEY, 
    TenLoai nvarchar(50), 
    MoTa nvarchar(100), 
    Is_Deleted bit
);
GO

CREATE TABLE SanPham(
    MaSP int NOT NULL PRIMARY KEY IDENTITY(1,1), 
    TenSP nvarchar(50), 
    MoTa nvarchar(500), 
    SoLuong int, 
    HinhAnh nvarchar(100), 
    GiaBan int, 
    MaLoai int, 
    Is_Deleted bit,
    CONSTRAINT FK_SanPham_LoaiSanPham FOREIGN KEY (MaLoai) REFERENCES LoaiSanPham(MaLoai)
);
GO




CREATE TABLE PhieuNhap(
    MaPN int NOT NULL PRIMARY KEY IDENTITY(1,1), 
    NgayLap datetime, 
    MaNV int, 
    MaNCC int, 
	TrangThai int,
    Is_Deleted bit,
    CONSTRAINT FK_PhieuNhap_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    CONSTRAINT FK_PhieuNhap_NhaCungCap FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(MaNCC)
);
GO

CREATE TABLE CTPhieuNhap(
    MaPN int NOT NULL, 
    MaSP int NOT NULL, 
    SoLuong int, 
    GiaNhap int,
    PRIMARY KEY (MaPN, MaSP),
    CONSTRAINT FK_CTPhieuNhap_PhieuNhap FOREIGN KEY (MaPN) REFERENCES PhieuNhap(MaPN),
    CONSTRAINT FK_CTPhieuNhap_SanPham FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);
GO

CREATE TABLE HoaDon(
    MaHD int NOT NULL PRIMARY KEY IDENTITY(1,1), 
    NgayLap datetime, 
    HinhThuc nvarchar(20), 
    TongTien int, 
    TienGiam int, 
	ThanhTien int,
	TienKhachDua int,
	TienTraLai int,
    MaNV int, 
    MaKH int NULL, 
    Is_Deleted bit,
    CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    CONSTRAINT FK_HoaDon_KhachHang FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH)
);
GO

CREATE TABLE CTHoaDon(
    MaHD int NOT NULL, 
    MaSP int NOT NULL, 
    SoLuong int,
    GiaBan int,
    PRIMARY KEY (MaHD, MaSP),
    CONSTRAINT FK_CTHoaDon_HoaDon FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD),
    CONSTRAINT FK_CTHoaDon_SanPham FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);
GO

CREATE TABLE TaiKhoan(
    MaTK int NOT NULL PRIMARY KEY IDENTITY(1,1), 
    TenTK nvarchar(50), 
    MatKhau nvarchar(50), 
    MaNV int, 
    MaQuyen int,
    Is_Deleted bit,
    CONSTRAINT FK_TaiKhoan_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    CONSTRAINT FK_TaiKhoan_PhanQuyen FOREIGN KEY (MaQuyen) REFERENCES PhanQuyen(MaQuyen)
);
GO

CREATE TABLE ChucNang(
    MaChucNang int NOT NULL PRIMARY KEY IDENTITY(1,1),
    TenChucNang nvarchar(50),
	MoTa nvarchar(50)
);
GO

CREATE TABLE CTPhanQuyen(
	MaQuyen int,
    MaChucNang int,
	MaHanhDong int,
	HanhDong nvarchar(50),
    PRIMARY KEY (MaChucNang, MaQuyen, MaHanhDong),
    Is_Deleted bit,
    CONSTRAINT FK_CTPhanQuyen_ChucNang FOREIGN KEY (MaChucNang) REFERENCES ChucNang(MaChucNang),
    CONSTRAINT FK_CTPhanQuyen_PhanQuyen FOREIGN KEY (MaQuyen) REFERENCES PhanQuyen(MaQuyen)
);
GO

CREATE TABLE LichSuDiem (
    MaGiaoDich int NOT NULL PRIMARY KEY IDENTITY(1,1),
    MaKH int NOT NULL,
    MaHD int,
    Diem int,
    NgayTichLuy datetime,
	LoaiGD nvarchar(50),
    CONSTRAINT FK_LichSuDiem_KhachHang FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH),
    CONSTRAINT FK_LichSuDiem_HoaDon FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD)
);
GO



-- Thêm dòng dữ liệu mặc định với ID 1 nếu bảng trống

-- Thêm dữ liệu mẫu cho bảng PhanQuyen
INSERT INTO PhanQuyen (TenQuyen, MoTa)
VALUES (N'Quản lý', N'ALL'),
       (N'Nhân viên bán hàng', N'Quản lý bán hàng, Quản lý khách hàng, Quản lý sản phẩm'),
	   (N'Nhân viên kiểm kho', N'Quản lý sản phẩm, phiếu nhập xuất'),
	   (N'Kế toán', N'Quản lý bán hàng, Quản lý thống kê, Quản lý tài khoản'),
	   (N'Nhân viên quản lý hệ thống', N'Quản lý tài khoản, Quản lý phân quyền, Quản lý thống kê')

-- Thêm dữ liệu mẫu cho bảng ChucVu
INSERT INTO ChucVu (TenChucVu, MoTa, Is_Deleted)
VALUES (N'Quản lý', N'ALL', 0),
       (N'Nhân viên bán hàng', N'Quản lý bán hàng, Quản lý khách hàng, Quản lý sản phẩm', 0),
	   (N'Nhân viên kiểm kho', N'Quản lý sản phẩm, phiếu nhập xuất', 0),
	   (N'Kế toán', N'Quản lý bán hàng, Quản lý thống kê, Quản lý tài khoản', 0),
	   (N'Nhân viên quản lý hệ thống', N'Quản lý tài khoản, Quản lý phân quyền, Quản lý thống kê', 0)
GO

-- Thêm dữ liệu mẫu cho bảng NhanVien
INSERT INTO NhanVien (TenNV, GioiTinh, NgaySinh, SoDienThoai, Email, DiaChi, MaChucVu, Is_Deleted)
VALUES (N'Nguyễn Văn A', 1, '1990-01-01', '0912345678', 'vana@gmail.com', N'123 Đường ABC', 1, 0),
       (N'Trần Thị B', 0, '1995-05-15', '0987654321', 'thib@gmail.com', N'456 Đường XYZ', 2, 0),
	   (N'Nguyễn Văn C', 1, '1990-01-01', '0912345678', 'vanc@gmail.com', N'123 Đường ABC', 3, 0),
	   (N'Nguyễn Văn D', 1, '1990-01-01', '0912345678', 'vanb@gmail.com', N'123 Đường ABC', 4, 0);

GO

-- Thêm dữ liệu mẫu cho bảng KhachHang
INSERT INTO KhachHang (TenKH, SoDienThoai, DiemTichLuy, Is_Deleted)
VALUES (N'Lê Văn C', '0911222333', 100, 0),
       (N'Phạm Thị D', '0999888777', 50, 0),
       (N'Lê Văn F', '0911222333', 100, 0),
       (N'Phạm Thị RR', '0999888777', 50, 0);
GO

-- Thêm dữ liệu mẫu cho bảng NhaCungCap
INSERT INTO NhaCungCap (TenNCC, DiaChi, Sdt, NguoiLienHe, Is_Deleted)
VALUES (N'Công ty ABC', N'789 Đường DEF', '0933444555', N'Nguyễn Văn A', 0),
       (N'Công ty XYZ', N'321 Đường GHI', '0977665544', N'Nguyễn Văn B', 0);
GO

-- Thêm dữ liệu mẫu cho bảng LoaiSanPham
INSERT INTO LoaiSanPham (MaLoai, TenLoai, MoTa, Is_Deleted)
VALUES (1, N'Đồ uống', N'Các loại nước giải khát', 0),
       (2, N'Thực phẩm', N'Thực phẩm tươi sống', 0),
       (3, N'Rau củ quả', N'Rau củ tươi xanh', 0),
       (4, N'Trái cây', N'Các loại trái cây tươi ngon', 0);
GO

-- Thêm dữ liệu mẫu cho bảng SanPham
INSERT INTO SanPham (TenSP, MoTa, SoLuong, HinhAnh, GiaBan, MaLoai, Is_Deleted)
VALUES 
(N'Cá Hồi Nhật', N'Cá hồi tươi Nhật', 100, 'CaHoiNhat.png', 200000, 2, 0),
(N'Cá Ngừ Đại Dương', N'Cá ngừ tươi ngon', 50, 'CaNguDaiDuong.png', 250000, 2, 0),
(N'Thịt Bò Úc', N'Thịt bò nhập Úc', 100, 'ThitBoUt.png', 300000, 2, 0),
(N'Tôm Sú Tươi', N'Tôm tươi ngon', 50, 'TomSuTuoi.png', 150000, 2, 0),
(N'Thịt Heo Tươi', N'Thịt heo tươi sạch', 80, 'ThitHeoTuoi.png', 120000, 2, 0),
(N'Hàu Sữa Tươi', N'Hàu sữa tươi ngon', 60, 'HaoSuaTuoi.png', 180000, 2, 0),
(N'Ớt Chuông', N'Ớt chuông Đà Lạt', 100, 'OtChuong.png', 40000, 3, 0),
(N'Cà Tím', N'Cà tím tươi', 70, 'CaTim.png', 35000, 3, 0),
(N'Cà Chua', N'Cà chua sạch', 120, 'CaChua.png', 30000, 3, 0),
(N'Dưa Leo', N'Dưa leo tươi mát', 90, 'DuaLeo.png', 25000, 3, 0),
(N'Cà Rốt', N'Cà rốt Đà Lạt', 110, 'CaRot.png', 32000, 3, 0),
(N'Súp Lơ Xanh', N'Súp lơ xanh hữu cơ', 50, 'SupLoXanh.png', 45000, 3, 0),
(N'Táo Mỹ', N'Táo nhập khẩu từ Mỹ', 50, 'TaoMy.png', 25000, 4, 0),
(N'Cam Vàng', N'Cam vàng ngọt tươi ngon', 60, 'CamVang.png', 30000, 4, 0),
(N'Dâu Tây', N'Dâu tây Đà Lạt tươi', 40, 'DauTay.png', 40000, 4, 0),
(N'Nho Xanh', N'Nho xanh tươi mát', 30, 'NhoXanh.png', 35000, 4, 0),
(N'Chuối Tây', N'Chuối tây chín ngọt', 70, 'ChuoiTay.png', 20000, 4, 0),
(N'Ổi Hồng', N'Ổi hồng thơm ngọt', 80, 'OiHong.png', 18000, 4, 0),
(N'Pepsi', N'Nước giải khát Pepsi', 100, 'Pepsi.png', 12000, 1, 0),
(N'Coca Cola', N'Nước giải khát Coca Cola', 100, 'CocaCola.png', 12000, 1, 0),
(N'Mirinda Cam', N'Nước giải khát vị cam', 100, 'MirindaCam.png', 12000, 1, 0),
(N'Seven Up', N'Nước giải khát chanh tươi mát', 100, 'SevenUp.png', 12000, 1, 0),
(N'Sting', N'Nước tăng lực Sting', 100, 'Sting.png', 15000, 1, 0),
(N'Bò Húc', N'Nước tăng lực Bò Húc', 100, 'BoHuc.png', 15000, 1, 0);
GO

-- Thêm dữ liệu mẫu cho bảng GiamGiaSP


-- Thêm dữ liệu mẫu cho bảng CTGiamGiaSP


-- Thêm dữ liệu mẫu cho bảng PhieuHuy


-- Thêm dữ liệu mẫu cho bảng CTPhieuHuy


-- Thêm dữ liệu mẫu cho bảng PhieuNhap
INSERT INTO PhieuNhap (NgayLap, MaNV, MaNCC, TrangThai, Is_Deleted)
VALUES ('2024-09-01', 1, 1, 0, 0),
       ('2024-09-10', 2, 2, 0, 0);
GO

-- Thêm dữ liệu mẫu cho bảng CTPhieuNhap
INSERT INTO CTPhieuNhap (MaPN, MaSP, SoLuong, GiaNhap)
VALUES (1, 1, 100, 18000),
       (2, 2, 50, 9000);
GO

-- Thêm lại các bản ghi trong bảng HoaDon
INSERT INTO HoaDon (NgayLap, HinhThuc, TongTien, TienGiam, ThanhTien, TienKhachDua, TienTraLai, MaNV, MaKH, Is_Deleted)
VALUES 
('2024-10-20', N'Tiền mặt', 200000, 10000, 190000, 200000, 10000, 1, 1, 0),
('2024-10-21', N'Chuyển khoản', 300000, 15000, 285000, 300000, 15000, 2, 2, 0),
('2024-11-10', N'Tiền mặt', 150000, 5000, 145000, 150000, 5000, 1, 1, 0),
('2024-11-15', N'Chuyển khoản', 500000, 25000, 475000, 500000, 25000, 2, 2, 0);
GO

-- Thêm lại các bản ghi trong bảng CTHoaDon
INSERT INTO CTHoaDon (MaHD, MaSP, SoLuong, GiaBan)
VALUES 
(1, 1, 2, 200000), 
(2, 2, 1, 250000),
(3, 1, 3, 200000),
(3, 2, 1, 250000),
(4, 1, 5, 200000),
(4, 2, 2, 250000);
GO

-- Thêm dữ liệu mẫu cho bảng TaiKhoan
INSERT INTO TaiKhoan (TenTK, MatKhau, MaNV, MaQuyen, Is_Deleted)
VALUES (N'admin', '123456', 1, 1, 0),
       (N'nvbanhang', '123456', 2, 2, 0),
	   (N'nvkho', '123456', 3, 3, 0),
	   (N'ketoan', '123456', 4, 4, 0);
GO

-- Thêm dữ liệu mẫu cho bảng ChucNang
INSERT INTO ChucNang (TenChucNang, MoTa)
VALUES 
(N'Quản lý sản phẩm', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý nhà cung cấp', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý phiếu nhập', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý phiếu hủy', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý khách hàng', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý nhân viên', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý bán hàng', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý giảm giá sản phẩm', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý tài khoản', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý thống kê', N'Thêm, sửa, xóa, tìm kiếm khách hàng'),
(N'Quản lý phân quyền', N'Thêm, sửa, xóa chương trình giảm giá');
GO

INSERT INTO CTPhanQuyen (MaQuyen, MaChucNang, MaHanhDong, HanhDong, Is_Deleted)
VALUES
-- MaChucNang từ 1 đến 11, MaHanhDong 1 và 2
(1, 1, 1, N'Xem', 0), (1, 1, 2, N'Thao tác', 0),
(1, 2, 1, N'Xem', 0), (1, 2, 2, N'Thao tác', 0),
(1, 3, 1, N'Xem', 0), (1, 3, 2, N'Thao tác', 0),
(1, 4, 1, N'Xem', 0), (1, 4, 2, N'Thao tác', 0),
(1, 5, 1, N'Xem', 0), (1, 5, 2, N'Thao tác', 0),
(1, 6, 1, N'Xem', 0), (1, 6, 2, N'Thao tác', 0),
(1, 7, 1, N'Xem', 0), (1, 7, 2, N'Thao tác', 0),
(1, 8, 1, N'Xem', 0), (1, 8, 2, N'Thao tác', 0),
(1, 9, 1, N'Xem', 0), (1, 9, 2, N'Thao tác', 0),
(1, 10, 1, N'Xem', 0), (1, 10, 2, N'Thao tác', 0),
(1, 11, 1, N'Xem', 0), (1, 11, 2, N'Thao tác', 0);
