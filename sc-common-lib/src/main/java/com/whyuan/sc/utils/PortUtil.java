package com.whyuan.sc.utils;

import javax.swing.*;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

//检测本地端口是否被占用
public class PortUtil {

    public static boolean isUsed(int port) {
        try {
            ServerSocket ss = new ServerSocket(port);
            ss.close();
            return false;
        } catch (BindException e) {
            return true;
        } catch (IOException e) {
            return true;
        }
    }


    public static void checkPort(int port, String server, boolean shutdown) {
        if (!isUsed(port)) {
            if (shutdown) {
                String message = String.format("在端口 %d 未检查得到 %s 启动%n", port, server);
                JOptionPane.showMessageDialog(null, message);
                System.exit(1);
            } else {
                String message = String.format("在端口 %d 未检查得到 %s 启动%n                                  是否继续?", port, server);
                if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(null, message))
                    System.exit(1);
            }
        }
    }

}