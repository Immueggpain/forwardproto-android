package com.github.immueggpain.smartproxy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Launcher {

    private static final String VERSTR = "1.1.7";

    public static class ClientSettings {
        public String local_listen_ip;
        public int local_listen_port;
        public String server_ip;
        public int server_port;
        public String password;
        public String logfile;
    }

    public static class ServerSettings {
        public String password;
        public int server_port;
        public String cert;
        public String private_key;
    }

}
