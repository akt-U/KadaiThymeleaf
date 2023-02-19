package com.techacademy;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InputController {
    @GetMapping("/input")
    public String getInput(@RequestParam(value="previous", required=false) String previous, Model model) {
        // プロパティファイルからテキストを取得する
        Properties props = new Properties();
        try (InputStream is = getClass().getResourceAsStream("/messages.properties")) {
            props.load(is);
        } catch (IOException e) {
            // プロパティファイルの読み込みに失敗した場合の処理
            e.printStackTrace();
        }
        String welcomeMessage = props.getProperty("welcome_message");
        String previousMessage = props.getProperty("previous_message");

        // previousの値に応じて、previousMessageを生成する
        String message = "";
        if (previous != null && !previous.isEmpty()) {
            message = MessageFormat.format(previousMessage, previous);
        }

        // モデルに必要な値を設定する
        model.addAttribute("welcomeMessage", welcomeMessage);
        model.addAttribute("previousMessage", message);

        // input.htmlを表示する
        return "input";
    }
}