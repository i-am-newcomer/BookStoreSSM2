package com.zy.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckCodeController {
	
	@RequestMapping("/checkcode")
	public void checkcode(HttpServletResponse response, HttpSession session) throws IOException {
		//设置响应首部
		response.setContentType("image/jpeg");
		response.setCharacterEncoding("utf-8");
		//设置不缓存图片
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		//设置图片长度和宽度
		int width = 80;
		int height = 25;	
		//通过BufferedImage类获取image对象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//得到画笔g
		Graphics g = image.getGraphics();
		//用画笔g填充矩形图片
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, width, height);
		//生成随机数
		Random rd = new Random();
		int rdint = rd.nextInt(8999)+1000;
		String rdstr = String.valueOf(rdint);
		//将随机数保存在会话属性中
		session.setAttribute("checkcode", rdstr);
		//画随机数
		g.setColor(Color.BLUE);
		g.setFont(new Font("", Font.PLAIN, 22));
		g.drawString(rdstr, 15, 24);
		//画100个随机点
		for(int i=0; i<100; i++) {
			int x = rd.nextInt(width);
			int y = rd.nextInt(height);
			g.drawOval(x, y, 1, 1);		
		}
		//释放资源
		g.dispose();
		//输出图片
		ImageIO.write(image, "jpeg", response.getOutputStream());
	}


}
