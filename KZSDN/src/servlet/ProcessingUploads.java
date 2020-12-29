package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


import bean.User;
import dao.GetUser;
import dao.ResetDat;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.ImageUpload;

@WebServlet("/proupload")
public class ProcessingUploads extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "images";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    /**
     * 上传数据及保存文件
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String imagePath = ImageUpload.upLoadImg(req);
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        user.setTouxiang(imagePath);
        PrintWriter writer = resp.getWriter();
        writer.println(imagePath);
        writer.flush();
        writer.close();
        req.getSession().setAttribute("user",user);
        /*User user=new User();
        user.setUsername(req.getParameter("username"));
        System.out.println(user.getUsername());
        user=new GetUser().getUser(user);
        user.setTouxiang(imagePath1);
        System.out.println(user.getTouxiang());
        try {
            boolean f=new ResetDat().resetDat(user);
            if(f){
                req.getSession().setAttribute("user",user);
                req.getRequestDispatcher("/ResetData.jsp").forward(req,resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("imagePath",imagePath1);*/

        /*// 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        *//*String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;*//*

        String uploadPath = "/temp";

        System.out.println("文件上传路径："+uploadPath);
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            System.out.println(formItems);

            if (formItems != null && formItems.size() > 0) {
                System.out.println("SIZE==="+formItems.size());
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        System.out.println("filePath===="+filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        req.setAttribute("message", "文件上传成功!");
                        System.out.println("文件上传成功");
                    }
                }
            }
        } catch (Exception ex) {
            req.setAttribute("message", "错误信息: " + ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/ResetData.jsp").forward(req, resp);*/
    }
}
