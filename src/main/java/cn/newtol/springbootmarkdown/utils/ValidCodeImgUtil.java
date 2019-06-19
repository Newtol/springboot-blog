package cn.newtol.springbootmarkdown.utils;


import cn.newtol.springbootmarkdown.entity.ResultVO;
import cn.newtol.springbootmarkdown.myEnum.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * @Author: 公众号：Newtol
 * @Description:
 * @Date: Created in 14:47 2019/1/27
 * @params:
 */

@Service
public class ValidCodeImgUtil {

    private static final Logger logger = LoggerFactory.getLogger(ValidCodeImgUtil.class);
    /**
     * 验证码在Session中存储的Key
     */
    private static final String VALID_CODE_IMG_KEY = "VaildCodeImgKey";

    /**
     * 判断用户是否已经通过验证
     */
    private static final String IS_VALID = "isValid";

    /**
     * 用户验证通过
     */
    private static final String VALID_SUCCESS = "success";

    /**
     * 用户验证失败
     */
    private static final String VALID_FALSE = "false";

    /**
     * 图片宽
     */
    private int width = 95;
    /**
     * 图片高
     */
    private int height = 25;
    /**
     * 干扰线数量
     */
    private int lineSize = 40;

    /**
     * 运算符
     */
    private String[] operatorNum = {"+","-","x","=","?"};

    private Random random = new Random();


    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.BOLD, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }

    /**
     * 绘制字符串
     */
    private void drowString(Graphics g,String str) {
        // 设置字符串字体
        g.setFont(getFont());
        g.setColor(getRandColor());

        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(str, 13 , 16);


    }

    /**
     * 获取算式
     */
    private String[] getString(){
        String str;
        // 获取数字,防止出现负数，所以num2小于num1
        Integer num1 = random.nextInt(100);
        while(num1 == 0){
            num1 = random.nextInt(100);
        }
        Integer num2 = random.nextInt(num1);
        // 获取运算符号
        String ch = operatorNum[random.nextInt(2)];
        // 获取表达式
        str = num1+ ch + num2 + operatorNum[3] + operatorNum[4];
        // 获取算术结果
        Integer result = getResult(num1,num2,ch);
        if(result == null){
            logger.error("验证码计算错误");
        }
        return new String[]{str, String.valueOf(result)};
    }


    /**
     * 获取计算结果
     */
    private Integer getResult(Integer num, Integer num2, String ch){
        switch (ch){
            case "+":
                return num + num2;
            case "-":
                return num - num2;
            // 乘法难以计算，舍弃
//            case "x":
//                return  num * num2;
            default:
                return null;
        }
    }
    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 生成随机图片
     */
    public void getVaildCodeImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置返回内容为图片
        response.setContentType("image/jpeg");
        // 设置为不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();

        //图片大小
        g.fillRect(0, 0, width, height);
        //字体大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        //字体颜色
        g.setColor(getRandColor());
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }

        //绘制算术表达式
        String[] strings = getString();
        drowString(g,strings[0]);
        g.dispose();


        // 将结果存入session
        Map<String,Object> map = new HashMap<>(2);
        map.put(VALID_CODE_IMG_KEY ,strings[1]);
        HttpServletUtil.setSession(map,request);


        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            logger.error("将内存中的图片通过流动形式输出到客户端失败>>>>   ", e);
        }
    }

    /**
     * @param request
     * @param code 用户输入的验证码
     * @return
     */
    public ResultVO isVaildCode(HttpServletRequest request, String code){
        if(!isRightValidCode(request,code)){
            return ResultUtil.error(ResultEnum.ValidCode_ERROR);
        }
        return ResultUtil.success();
    }

    public Boolean isRightValidCode(HttpServletRequest request, String code){
        // 获取存在session中的算式结果
        String validCode = (String) request.getSession().getAttribute(VALID_CODE_IMG_KEY);
        // 删除存在session中的验证码，即：只可使用一次
        request.getSession().removeAttribute(VALID_CODE_IMG_KEY);
        // 判断用户输入是否为空
        if (code == null){
            return false;
        }
        // 判断是否已经过期
        if (validCode == null){
            return false;
        }
        // 判断验证码是否输入正确
        if (Objects.equals(validCode, code)){
            request.getSession().setAttribute(IS_VALID,VALID_SUCCESS);
            return true;
        }else{
            request.getSession().setAttribute(IS_VALID,VALID_FALSE);
            return false;
        }
    }
}

