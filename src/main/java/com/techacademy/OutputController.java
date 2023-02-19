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
public class OutputController {
    @PostMapping("/output")
    public String postOutput(@RequestParam("inputValue") String inputValue, Model model) {
        // プロパティファイルからテキストを取得する
        Properties props = new Properties();
        try (InputStream is = getClass().getResourceAsStream("/messages.properties")) {
            props.load(is);
        } catch (IOException e) {
            // プロパティファイルの読み込みに失敗した場合の処理
            e.printStackTrace();
        }
        String returnMessage = props.getProperty("return_message");

        // モデルに必要な値を設定する
        model.addAttribute("inputValue", inputValue);
        model.addAttribute("returnMessage", returnMessage);
     // input.htmlを表示する
        return "output";
    }

    }
