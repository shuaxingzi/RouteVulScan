package func;

import burp.*;
import yaml.YamlUtil;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class init_Yaml_thread extends Thread {
    private BurpExtender burp;
    private View view_class;
    private List<View.LogEntry> log;
    private JTextField txtfield1;
    private JPanel one;

    public init_Yaml_thread(BurpExtender burp, View view_class, List<View.LogEntry> log, JTextField txtfield1, JPanel one) {
        this.burp = burp;
        this.view_class = view_class;
        this.log = log;
        this.txtfield1 = txtfield1;
        this.one = one;


    }

    public void run() {

        URL Url = null;
        try {
            Url = new URL(BurpExtender.Download_Yaml_protocol, BurpExtender.Download_Yaml_host, BurpExtender.Download_Yaml_port, BurpExtender.Download_Yaml_file);

            byte[] request = this.burp.help.buildHttpRequest(Url);
            byte[] YamlResponse = this.burp.call.makeHttpRequest(BurpExtender.Download_Yaml_host, BurpExtender.Download_Yaml_port, true, request);

            if (YamlResponse != null) {
                IResponseInfo AnalyzeYamlResponse = this.burp.help.analyzeResponse(YamlResponse);
                String ResponseBody = this.burp.help.bytesToString(YamlResponse).substring(AnalyzeYamlResponse.getBodyOffset());

                FileOutputStream file = new FileOutputStream(BurpExtender.Yaml_Path);
                file.write(this.burp.help.stringToBytes(ResponseBody));
                file.close();

                Bfunc.show_yaml(this.view_class, this.log, this.txtfield1.getText());
                JOptionPane.showMessageDialog(one, "Update successful", "Tips ", 1);
            } else {
                JOptionPane.showMessageDialog(one, "Request failed, please try to use proxy", "Error ", 0);
            }
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(one, "URL creation failed", "Error ", 0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(one, "File write failed", "Error ", 0);

        }


    }
}
