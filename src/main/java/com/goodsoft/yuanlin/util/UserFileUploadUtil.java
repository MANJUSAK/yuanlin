package com.goodsoft.yuanlin.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class UserFileUploadUtil {
    /* 创建本类的单例模式（具体说明参见本包下UUIDUtil类） */
    private volatile static UserFileUploadUtil instance;

    private UserFileUploadUtil() {
    }

    public static UserFileUploadUtil getInstance() {
        if (instance == null) {
            synchronized (UserFileUploadUtil.class) {
                if (instance == null)
                    instance = new UserFileUploadUtil();
            }
        }
        return instance;
    }

    // 实例化UUID工具类
    private UUIDUtil uuid = UUIDUtil.getInstance();

    /**
     * 用户文件上传
     *
     * @return 文件路径
     * @parameter file 用户文件、request http请求
     */
    public List userFileUpload(MultipartFile[] files, HttpServletRequest request) throws Exception {
        List<String> list = new ArrayList<>();
        // 解析服务器上下文
        String path = request.getSession().getServletContext().getRealPath("");
        // 截取系统需要的根目录
        String rootPath = path.substring(0, path.lastIndexOf("y"));
        // 定义文件路径
        String uploadPath = "/yuanlinfile/user/IDcard/";
        // 定义文件存放路径
        StringBuilder sb = new StringBuilder(rootPath);
        sb.append(uploadPath);
        String var = sb.toString();
        // 创建文件夹
        File folder = new File(var);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        for (int i = 0, length = files.length; i < length; ++i) {
            // 获取文件名
            String fileName = files[i].getOriginalFilename().toLowerCase();
            // 判断文件格式是否正确
            if (fileName.endsWith("jpg") || fileName.endsWith("jpeg")
                    || fileName.endsWith("png") || fileName.endsWith("gif")) {
                // 重命名文件名以免文件名重复
                String name = this.uuid.getUUID().toString();
                // 获取文件名后缀
                String nameSuffix = fileName.substring(fileName
                        .lastIndexOf("."));
                //设置文件保存路径
                sb.delete(0, sb.length());
                sb.append(var);
                sb.append("/");
                sb.append(name);
                sb.append(nameSuffix);
                //保存文件到服务器
                files[i].transferTo(new File(sb.toString()));
                //返回文件保存路径
                sb.delete(0, sb.length());
                sb.append(uploadPath);
                sb.append(name);
                sb.append(nameSuffix);
                System.out.println(sb.toString());
                list.add(sb.toString());
            } else {
                return list;
            }
        }
        return list;
    }
}
